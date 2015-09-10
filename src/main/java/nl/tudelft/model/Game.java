package nl.tudelft.model;

import java.util.Iterator;
import java.util.LinkedList;

import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;
import nl.tudelft.semgroup4.util.QuadTree;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Game implements Renderable {

    private final LinkedList<Level> levels;
    private final Iterator<Level> levelIt;
    private LinkedList<Player> players;
    private Level curLevel;
    private int prevLives = 0;
    private final CollisionHandler<Game, GameObject, GameObject> collisionHandler;
    private QuadTree quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     * 
     * @param levels
     *            LinkedList - List containing all levels that the game consists of.
     * @param players
     *            LinkedList - List containing all players that take part in this game.
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public Game(LinkedList<Level> levels, LinkedList<Player> players)
            throws IllegalArgumentException {
        this.levels = levels;
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
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
            }
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, players, quad)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
            }
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, projectiles, quad)) {
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
        for (GameObject collidesWithA : players) {
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, pickups, null)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB); 
            }
        }
        
        for (GameObject collidesWithA : pickups) {
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, null)) {
                collisionHandler.onCollision(this, collidesWithA, collidesWithB);
            }
        }
        
        // Updates
        for (GameObject gameObject : players) {
            gameObject.update(getCurLevel(), delta);
        }

        getCurLevel().update(getCurLevel(), delta);
        
        
        // Logic
        if (playerDied()) {
            levelReset();
        }
        
        if(getCurLevel().isCompleted()) {
            nextLevel();
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
    private Level getCurLevel() {
        return this.curLevel;
    }

    /**
     * Sets the previous lives.
     * 
     * @param amount
     *            - the amount of lives
     */
    private void setPrevLives(int amount) {
        this.prevLives = amount;
    }

    /**
     * Checks whether a player has died since the last check. This method is used by the
     * {@link Game#update(GameContainer, int)} method.
     * 
     * @return boolean true iff a player has died since this method was last called.
     */
    private boolean playerDied() {
        int curLives = getPlayerLives();
        if (curLives < prevLives) {
            setPrevLives(curLives);
            return true;
        }
        setPrevLives(curLives);
        return false;
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
     * Resets the current level if the players have lives left, ends the game if they do not.
     */
    private void levelReset() {
        if (getPlayerLives() > 0) {
            // TODO Implement level.reset();
            // getCurLevel().reset();
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
     * The game has been lost.
     * 
     * <p>
     * This happens when the players run out of lives.
     * </p>
     */
    public void gameOver() {
        // TODO
    }

    /**
     * game will use CollisionHandler returned in this method.
     * 
     * @return the CollisionHandler that will be used.
     */
    protected CollisionHandler getNewCollisionHandler() {
        return new DefaultCollisionHandler();
    }

}
