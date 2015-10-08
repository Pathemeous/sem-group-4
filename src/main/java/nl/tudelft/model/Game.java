package nl.tudelft.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import nl.tudelft.semgroup4.GameEndedState;
import nl.tudelft.semgroup4.GameState;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.ShopState;
import nl.tudelft.semgroup4.States;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;
import nl.tudelft.semgroup4.logger.DefaultLogger;
import nl.tudelft.semgroup4.logger.Logger;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.QuadTree;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The Game class represents a game session. A game can be single player or multiplayer, and
 * contains a list of levels and players.
 */
public abstract class Game implements Renderable, Modifiable {

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
    private Level curLevel;
    private final CollisionHandler<AbstractGameObject, AbstractGameObject> collisionHandler;
    private final LevelFactory levelFact;
    private final StateBasedGame mainApp;
    private final ResourcesWrapper resources;
    private boolean paused = false;
    private Countdown countdown;

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     * 
     * @param mainApp
     *            {@link StateBasedGame} - the mainApp that manages the states.
     * @param containerWidth
     *            int - width of the game field.
     * @param containerHeight
     *            int - height of the game field.
     * @param wrapper
     *            {@link ResourcesWrapper} - The resources that Game can inject into LevelFactory.
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public Game(StateBasedGame mainApp, int containerWidth,
                int containerHeight, ResourcesWrapper wrapper) {
        // LOGGER.log(VERBOSE, "Game", "constructor called");
        this.mainApp = mainApp;
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.levelFact = new LevelFactory(this, wrapper);
        LinkedList<Level> levels = levelFact.getAllLevels();

        this.resources = wrapper;
        this.levelIt = levels.iterator();

        this.curLevel = this.levelIt.next();
        countdown = new Countdown(this, wrapper);

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

        for (Player player : getPlayers()) {
            if (player.isAlive()) {
                player.render(container, graphics);
            }
        }

        countdown.render(container, graphics);
    }

    /**
     * Adds collidable objects to the quad tree.
     */
    private void quadFill(QuadTree quad) {
        for (AbstractGameObject obj : getPlayers()) {
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
     * Tests whether the current level is completed and if so, logs this event and proceeds to the
     * next level.
     */
    private void levelCompleted() {
        if (getCurLevel().isCompleted() && levelIt.hasNext()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game",
                    "Level has been completed. Go to next level!");
            nextLevel();
            ((ShopState) mainApp.getState(States.ShopState)).setup(this);
            mainApp.enterState(States.ShopState);
        }
    }

    /**
     * Tests whether the current level's timer has expired. If so, logs this event, makes the
     * player lose a life, and resets the current level.
     */
    private void levelTimeExpired() {
        if (getCurLevel().timerExpired()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Time has expired");

            resources.playTimeUp();
            for (Player player : getPlayers()) {
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
        for (Player player : getPlayers()) {
            if (player.isAlive()) {
                player.update(this, delta);
            }
        }
//        for (Player player : playerToDelete) {
//            players.remove(player);
//        }
//        playerToDelete.clear();
    }

    /**
     * Checks for every pickup if it collides with anything a pickup can collide with.
     */
    private void pickupCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : curLevel.getPickups()) {
            // collision with walls and players
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every player if it collides with anything a player can collide with.
     */
    private void playerCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : getPlayers()) {
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every player if it collides with anything a projectile can collide with.
     */
    private void projectileCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : curLevel.getProjectiles()) {
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
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
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
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
        countdown.reset();
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
        for (Player player : getPlayers()) {
            result += player.getLives();
        }
        return result;
    }

    /**
     * Calls {@link Player#reset()} on all players in the game.
     */
    public void resetPlayers() {
        for (Player player : getPlayers()) {
            player.reset();
        }
    }

    /**
     * Resets the current level if the players have lives left, ends the game if they do not.
     */
    public void levelReset() {
        resources.stopFireSound();
        if (getPlayerLives() > 0) {
            resetPlayers();
            setCurLevel(levelFact.getLevel(getCurLevel().getId()));
        } else {
            gameOver();
        }
    }

    /**
     * Tries to set the next level as the current level. If there is no next level, the game is
     * completed and {@link #gameCompleted()} is called.
     */
    public void nextLevel() {
        if (levelIt.hasNext()) {
            resetPlayers();
            int score = (getCurLevel().getTime() / getCurLevel().getMaxTime()) * 500;
            for (Player player : getPlayers()) {
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
        ((GameEndedState) mainApp.getState(States.GameEndedState)).setup(getPlayers(), true);
        mainApp.enterState(States.GameEndedState);
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
        ((GameEndedState) mainApp.getState(States.GameEndedState)).setup(getPlayers(), false);
        mainApp.enterState(States.GameEndedState);
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
     *
     * @deprecated use getCurLevel().toAdd(obj)
     */
    @Deprecated
    @Override
    public void toAdd(AbstractGameObject obj) {
        getCurLevel().toAdd(obj);
    }

    /**
     * Can be used to remove Players from the Game object. This is the only type of GameObject
     * stored in Game.
     *
     * @deprecated use getCurLevel().toAdd(obj)
     */
    @Deprecated
    @Override
    public void toRemove(AbstractGameObject obj) {
        getCurLevel().toRemove(obj);

    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    public Countdown getCountdown() {
        return countdown;
    }

    /**
     * Gets the players.
     *
     * @return Player[] - player(s) of the game.
     */
    public abstract Player[] getPlayers();

//    /**
//     * Gets the 'PlayerToDelete' list. This list servers as a buffer to the Players list to avoid
//     * aysnchronized access
//     *
//     * @return {@link LinkedList} of {@link Player}s - the buffer list.
//     */
//    public LinkedList<Player> getPlayerToDelete() {
//        return playerToDelete;
//    }
}
