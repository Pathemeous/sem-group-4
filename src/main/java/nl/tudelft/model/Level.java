package nl.tudelft.model;

import java.util.ArrayList;
import java.util.LinkedList;

import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Level implements Updateable, Renderable {

    LinkedList<Wall> walls;
    LinkedList<Projectile> projectiles;
    LinkedList<Player> players;
    LinkedList<Bubble> bubbles;
    LinkedList<GameObject> toDelete;
    LinkedList<GameObject> toAdd;
    private double time;
    private double speed;

    /**
     * Creates a level object with an object list, a timer and a speed.
     * @param walls LinkedList - list containing all walls in this level.
     * @param projectiles LinkedList - list containing all projectiles in this level.
     * @param players LinkedList - list containing all players in this level.
     * @param bubbles LinkedList - list containing all bubbles in this level.
     * @param toDelete LinkedList - list containing all deletable objects in this level.
     * @param toAdd LinkedList - list containing all addable objects in this level.
     * @param time double - the time the player has to complete the level in seconds.
     */
    public Level(LinkedList<Wall> walls, LinkedList<Projectile> projectiles,
            LinkedList<Player> players, LinkedList<Bubble> bubbles, LinkedList<GameObject> toDelete,
            LinkedList<GameObject> toAdd, double time) {
        this.walls = walls;
        this.projectiles = projectiles;
        this.players = players;
        this.bubbles = bubbles;
        this.toDelete = toDelete;
        this.toAdd = toAdd;
        this.time = time;
        
        // TODO Set default speed properly
        this.speed = 1;
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {

        graphics.drawImage(Resources.backgroundImage, 0, 0, container.getWidth(), container.getHeight(),
                0, 0, Resources.backgroundImage.getWidth(), Resources.backgroundImage.getHeight());

        for (GameObject gameObject : walls) {
            gameObject.render(container, graphics);
        }
        for (GameObject gameObject : bubbles) {
            gameObject.render(container, graphics);
        }
        for (GameObject gameObject : projectiles) {
            gameObject.render(container, graphics);
        }
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return this.time;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }

    /**
     * Checks whether the level is completed.
     * 
     * <p>
     * A level is completed when there are no bubbles left.
     * </p>
     * 
     * @return true iff there are no bubbles remaining in this level.
     */
    public boolean isCompleted() {
        return this.bubbles.isEmpty();
    }

    /**
     * Checks whether the level has failed.
     * 
     * <p>
     * A level has failed when a) the timer runs out; b) a player collides with a bubble.
     * </p>
     * 
     * @return boolean - true if the timer hits zero or below.
     */
    public boolean hasFailed() {
        return this.time <= 0;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // TODO Auto-generated method stub

    }

}
