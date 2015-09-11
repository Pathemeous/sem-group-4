package nl.tudelft.model;

import java.util.Iterator;
import java.util.LinkedList;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;
import nl.tudelft.semgroup4.util.QuadTree;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Game implements Renderable, Modifiable {

    private final int containerWidth, containerHeight;
    private final LinkedList<Level> levels;
    private final Iterator<Level> levelIt;
    private LinkedList<Player> players;
    private LinkedList<Player> playerToDelete = new LinkedList<>();
    private Level curLevel;
    private final CollisionHandler<Game, GameObject, GameObject> collisionHandler;
    private final LevelFactory levelFact;
    private QuadTree quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));
    private final StateBasedGame mainApp;

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     * 
     * @param players
     *            LinkedList - List containing all players that take part in this game.
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public Game(StateBasedGame mainApp, LinkedList<Player> players, int containerWidth, int containerHeight)
            throws IllegalArgumentException {
        this.mainApp = mainApp;
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.levelFact = new LevelFactory(this);
        levels = levelFact.getAllLevels();
        
        this.players = players;

        this.levelIt = this.levels.iterator();

        if (!this.levelIt.hasNext() || this.players.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.curLevel = this.levelIt.next();

        collisionHandler = getNewCollisionHandler();
    }

    public void update(int delta) throws SlickException {
        LinkedList<? extends GameObject> walls, projectiles, bubbles, pickups;
        walls = getCurLevel().getWalls();
        projectiles = getCurLevel().getProjectiles();
        bubbles = getCurLevel().getBubbles();
        pickups = getCurLevel().getPickups();
        

        // collision: QuadTree
        quad.clear();
        for (GameObject obj : walls) {
          quad.insert(obj);
        }
        for (GameObject obj : projectiles) {
          quad.insert(obj);
        }
        for (GameObject obj : players) {
          quad.insert(obj);
        }
        
        // collision : CollisionMap
        for (GameObject collidesWithA : bubbles) {
        	// bubbles check against walls, players and projectiles
        	for( GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, null, quad) ) {
        		collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
        	}
        }
        
        for (GameObject collidesWithA : projectiles) {
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, null)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
            }
        }
        
        for (GameObject collidesWithA : players) {
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
            }
        }
        for (GameObject collidesWithA : pickups) {
        	// collision with walls and players
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, null, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
            }
        }
        
        // Updates
        for (GameObject gameObject : players) {
            gameObject.update(this, delta);
        }

        getCurLevel().update(getCurLevel(), delta);
        
        for (Player player : playerToDelete) {
            players.remove(player);
        }
        playerToDelete.clear();
        
        
        // Logic        
        if(getCurLevel().isCompleted()) {
            nextLevel();
        }
        if (getCurLevel().timerExpired()) {
        	Resources.timeUp.play();
            for (Player player : players) {
                player.removeLife();
                levelReset();
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        getCurLevel().render(container, graphics);

        for (GameObject gameObject : players) {
            gameObject.render(container, graphics);
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
    private int getPlayerLives() {
        int result = 0;
        for (Player player : players) {
            result += player.getLives();
        }
        return result;
    }
    
    /**
     * Calls {@link Player#reset()} on all players in the game.
     */
    private void resetPlayers() {
        for (Player player : players) {
            player.reset();
        }
    }

    /**
     * Resets the current level if the players have lives left, ends the game if they do not.
     */
    public void levelReset() {
    	Resources.weaponFire.stop();
        if (getPlayerLives() > 0) {
            resetPlayers();
            setCurLevel(levelFact.getLevel(getCurLevel().getID()));
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
            int score = (getCurLevel().getTime() / getCurLevel().getMaxTime() ) * 500;
            for (Player player : players) {
                player.addScore(score);
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
        // TODO
    }

    /**
     * The game has been lost. Returns to the main screen.
     * 
     * <p>
     * This happens when the players run out of lives.
     * </p>
     */
    public void gameOver() {
        mainApp.enterState(0);
    }

    /**
     * game will use CollisionHandler returned in this method.
     * 
     * @return the CollisionHandler that will be used.
     */
    protected CollisionHandler getNewCollisionHandler() {
        return new DefaultCollisionHandler();
    }
    
    public int getContainerWidth() {
        return this.containerWidth;
    }
    
    public int getContainerHeight() {
        return this.containerHeight;
    }

    /**
     * Addition of anything to the Game object is prohibited.
     * Calling this method will delegate any non-player objects to the current level.
     */
    @Override
    public void toAdd(GameObject obj) {
        if (!(obj instanceof Player)) {
            curLevel.toAdd(obj);
        }
    }

    /**
     * Can be used to remove Players from the Game object.
     * This is the only type of GameObject stored in Game.
     */
    @Override
    public void toRemove(GameObject obj) {
        if (obj instanceof Player) {
            playerToDelete.add((Player)obj);
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
}
