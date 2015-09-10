package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Level implements Updateable, Renderable, Modifiable {

    LinkedList<Wall> walls;
    LinkedList<Projectile> projectiles;
    LinkedList<Pickup> pickups;
    LinkedList<Bubble> bubbles;
    private double time;
    private double speed;

    private int id;

    /**
     * Creates a level object with an object list, a timer and a speed.
     * 
     * @param walls
     *            LinkedList - list containing all walls in this level.
     * @param projectiles
     *            LinkedList - list containing all projectiles in this level.
     * @param players
     *            LinkedList - list containing all players in this level.
     * @param pickups
     *            LinkedList - list containing all pickups in this level.
     * @param bubbles
     *            LinkedList - list containing all bubbles in this level.
     * @param toDelete
     *            LinkedList - list containing all deletable objects in this level.
     * @param toAdd
     *            LinkedList - list containing all addable objects in this level.
     * @param time
     *            double - the time the player has to complete the level in seconds.
     */
    public Level(LinkedList<Wall> walls, LinkedList<Projectile> projectiles,
            LinkedList<Pickup> pickups, LinkedList<Bubble> bubbles, double time, int id) {
        this.walls = walls;
        this.projectiles = projectiles;
        this.bubbles = bubbles;
        this.time = time;

        this.id = id;

        // TODO Set default speed properly
        this.speed = 1;
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        for (GameObject gameObject : bubbles) {
            gameObject.update(this, delta);
        }
        for (GameObject gameObject : projectiles) {
            gameObject.update(this, delta);
        }
        for (GameObject gameObject : pickups) {
            gameObject.update(this, delta);
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {

        graphics.drawImage(Resources.backgroundImage, 0, 0, container.getWidth(), container.getHeight(),
                0, 0, Resources.backgroundImage.getWidth(), Resources.backgroundImage.getHeight());

        for (GameObject gameObject : projectiles) {
            gameObject.render(container, graphics);
        }
        for (GameObject gameObject : walls) {
            gameObject.render(container, graphics);
        }
        for (GameObject gameObject : bubbles) {
            gameObject.render(container, graphics);
        }
        for (GameObject gameObject : pickups) {
            gameObject.render(container, graphics);
        }
    }

    @Override
    public boolean toAdd(GameObject obj) {
        if (obj instanceof Projectile) {
            projectiles.add((Projectile)obj);
            return projectiles.contains(obj);
        }
        if (obj instanceof Bubble) {
            bubbles.add((Bubble)obj);
            return bubbles.contains(obj);
        }
        if (obj instanceof Wall) {
            walls.add((Wall)obj);
            return walls.contains(obj);
        }
        return false;
    }

    @Override
    public boolean toRemove(GameObject obj) {
        if (obj instanceof Projectile) {
            projectiles.remove(obj);
            return !projectiles.contains(obj);
        }
        if (obj instanceof Bubble) {
            bubbles.remove(obj);
            return !bubbles.contains(obj);
        }
        if (obj instanceof Wall) {
            walls.remove(obj);
            return !walls.contains(obj);
        }
        return false;
    }
    
    public int getID() {
        return this.id;
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

}
