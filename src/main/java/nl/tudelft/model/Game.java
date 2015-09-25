package nl.tudelft.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;
import nl.tudelft.semgroup4.logger.DefaultLogger;
import nl.tudelft.semgroup4.logger.Logger;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Audio;
import nl.tudelft.semgroup4.util.QuadTree;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Game implements Renderable, Modifiable {

    public static final Logger LOGGER;

    static {
        try {
            LOGGER = new DefaultLogger();
        } catch (IOException e) {
            throw new IllegalStateException("This shouldn't happen", e);
        }
    }

    private final int containerWidth;
    private final int containerHeight;
    private final Iterator<Level> levelIt;
    private final LinkedList<Player> players;
    private final LinkedList<Player> playerToDelete = new LinkedList<>();
    private Level curLevel;
    private final CollisionHandler<AbstractGameObject, AbstractGameObject> collisionHandler;
    private final LevelFactory levelFact;
    private final StateBasedGame mainApp;

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     * 
     * @param wrapper
     *            {@link ResourcesWrapper} - The resources that Game can injet into LevelFactory.
     * @param mainApp
     *            StateBasedGame - the mainApp that manages the states.
     * @param players
     *            LinkedList - List containing all players that take part in this game.
     * @param containerWidth
     *            int - width of the game field.
     * @param containerHeight
     *            int - height of the game field.
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public Game(StateBasedGame mainApp, LinkedList<Player> players, int containerWidth,
            int containerHeight, ResourcesWrapper wrapper) throws IllegalArgumentException {
        // LOGGER.log(VERBOSE, "Game", "constructor called");
        this.mainApp = mainApp;
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.levelFact = new LevelFactory(this, wrapper);
        LinkedList<Level> levels = levelFact.getAllLevels();

        this.players = players;

        this.levelIt = levels.iterator();

        if (!this.levelIt.hasNext() || this.players.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.curLevel = this.levelIt.next();

        collisionHandler = getNewCollisionHandler();
    }

    /**
     * Performs updates and logic on the Game object. This makes sure the game continues to
     * function.
     * 
     * <p>
     * Delegates all updating to the currentLevel. Checks all collisions.
     * </p>
     * 
     * @param delta
     *            int - the amount of milliseconds since this method was last called.
     * @throws SlickException
     *             - If the game engines crashes.
     */
    public void update(int delta) throws SlickException {
        final QuadTree quad =
                new QuadTree(0, new Rectangle(0, 0, containerWidth, containerHeight));
        // collision: QuadTree
        quadFill(quad);

        // collision : CollisionMap
        bubbleCollision(quad);
        projectileCollision(quad);
        playerCollision(quad);
        pickupCollision(quad);

        // updates
        playerUpdate(delta);
        getCurLevel().update(getCurLevel(), delta);

        levelCompleted();

        levelTimeExpired();
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        getCurLevel().render(container, graphics);

        for (AbstractGameObject gameObject : players) {
            gameObject.render(container, graphics);
        }
    }

    /**
     * Objects which can collide are added to the quadTree.
     */
    private void quadFill(QuadTree quad) {
        for (AbstractGameObject obj : players) {
            quad.insert(obj);
        }
        for (AbstractGameObject obj : curLevel.getProjectiles()) {
            quad.insert(obj);
        }
        for (AbstractGameObject obj : curLevel.getWalls()) {
            quad.insert(obj);
        }
    }

    /**
     * Logs if the game is completed and fires up the next level.
     */
    private void levelCompleted() {
        if (getCurLevel().isCompleted()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game",
                    "Level has been completed. Go to next level!");
            nextLevel();
        }
    }

    /**
     * Logs if the level timer has expired, makes the player lose a life, and resets the current
     * level.
     */
    private void levelTimeExpired() {
        if (getCurLevel().timerExpired()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Time has expired");

            Audio.playTimeUp();
            for (Player player : players) {
                player.removeLife();
            }
            levelReset();
        }
    }

    /**
     * Performs updates for the player, checks the collisions and manages the player list.
     * 
     * @param delta
     *            the time between updates.
     * @throws SlickException
     *             exception from Slick if something goes wrong.
     */
    private void playerUpdate(int delta) throws SlickException {
        for (AbstractGameObject gameObject : players) {
            gameObject.update(this, delta);
        }
        for (Player player : playerToDelete) {
            players.remove(player);
        }
        playerToDelete.clear();
    }

    /**
     * Checks for every pickup if it collides with anything a pickup can collide with.
     */
    private void pickupCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : curLevel.getPickups()) {
            // collision with walls and players
            for (AbstractGameObject collidesWithB : CollisionHelper.collideObjectWithList(
                    collidesWithA, null, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every player if it collides with anything a player can collide with.
     */
    private void playerCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : players) {
            for (AbstractGameObject collidesWithB : CollisionHelper.collideObjectWithList(
                    collidesWithA, null, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every player if it collides with anything a projectile can collide with.
     */
    private void projectileCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : curLevel.getProjectiles()) {
            for (AbstractGameObject collidesWithB : CollisionHelper.collideObjectWithList(
                    collidesWithA, null, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every player if it collides with anything a bubble can collide with.
     */
    private void bubbleCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : curLevel.getBubbles()) {
            // bubbles check against walls, players and projectiles
            for (AbstractGameObject collidesWithB : CollisionHelper.collideObjectWithList(
                    collidesWithA, null, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Sets the current level.
     * 
     * @param level
     *            Level object to set as the current level.
     */
    private void setCurLevel(Level level) {
        this.curLevel = level;
    }

    /**
     * Returns the current level.
     * 
     * @return Level - the current level.
     */
    public Level getCurLevel() {
        return this.curLevel;
    }

    /**
     * Returns the amount of lives that the players have left.
     * 
     * <p>
     * When the players run out of lives, the game is over.
     * </p>
     * 
     * @return int - the total amount of lives left until the game is over.
     */
    public int getPlayerLives() {
        int result = 0;
        for (Player player : players) {
            result += player.getLives();
        }
        return result;
    }

    /**
     * Calls {@link Player#reset()} on all players in the game.
     */
    public void resetPlayers() {
        for (Player player : players) {
            player.reset();
        }
    }

    /**
     * Resets the current level if the players have lives left, ends the game if they do not.
     */
    public void levelReset() {
        Audio.stopFireSound();
        if (getPlayerLives() > 0) {
            resetPlayers();
            setCurLevel(levelFact.getLevel(getCurLevel().getId()));
        } else {
            gameOver();
        }
    }

    /**
     * Tries to set the next level as the current level. If there is no next level, the game is
     * completed and {@link gameCompleted()} is called.
     */
    public void nextLevel() {
        if (levelIt.hasNext()) {
            resetPlayers();
            int score = (getCurLevel().getTime() / getCurLevel().getMaxTime()) * 500;
            for (Player player : players) {
                player.setScore(player.getScore() + score);
            }
            setCurLevel(levelIt.next());
        } else {
            gameCompleted();
        }
    }

    /**
     * The game has been completed.
     */
    private void gameCompleted() {
        Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Player has won the game!");
        mainApp.enterState(0);
    }

    /**
     * The game has been lost. Returns to the main screen.
     * 
     * <p>
     * This happens when the players run out of lives.
     * </p>
     */
    public void gameOver() {
        Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Game over for the player");
        mainApp.enterState(0);
    }

    /**
     * game will use CollisionHandler returned in this method.
     * 
     * @return the CollisionHandler that will be used.
     */
    protected final CollisionHandler<AbstractGameObject, AbstractGameObject>
            getNewCollisionHandler() {
        return new DefaultCollisionHandler();
    }

    public int getContainerWidth() {
        return this.containerWidth;
    }

    public int getContainerHeight() {
        return this.containerHeight;
    }

    /**
     * Addition of anything to the Game object is prohibited. Calling this method will delegate any
     * non-player objects to the current level.
     */
    @Override
    public void toAdd(AbstractGameObject obj) {
        if (!(obj instanceof Player)) {
            curLevel.toAdd(obj);
        }
    }

    /**
     * Can be used to remove Players from the Game object. This is the only type of GameObject
     * stored in Game.
     */
    @Override
    public void toRemove(AbstractGameObject obj) {
        if (obj instanceof Player) {
            playerToDelete.add((Player) obj);
        } else {
            curLevel.toRemove(obj);
        }

    }

    /**
     * Gets the list of players.
     *
     * @return List - players of the game.
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    public LinkedList<Player> getPlayerToDelete() {
        return playerToDelete;
    }
}
