package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.Updateable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Helpers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * This class represents a single level within the {@link Game}.
 * 
 * <p>
 * A level contains the {@link AbstractEnvironmentObject}s specific to this level and the level
 * duration.
 * </p>
 */
public class Level implements Updateable, Renderable, Modifiable {

    private final LinkedList<AbstractWall> walls;
    private final LinkedList<Projectile> projectiles;
    private final LinkedList<Pickup> pickups;
    private final LinkedList<Bubble> bubbles;
    private final LinkedList<AbstractGameObject> pendingRemoval = new LinkedList<>();
    private final LinkedList<AbstractGameObject> pendingAddition = new LinkedList<>();
    private int time;
    private final int maxTime;
    private final int id;
    private boolean shopSlow = false;

    /**
     * Creates a level object with an object list, a timer and a speed.
     *
     * @param walls
     *            LinkedList - list containing all walls in this level.
     * @param projectiles
     *            LinkedList - list containing all projectiles in this level.
     * @param pickups
     *            LinkedList - list containing all pickups in this level.
     * @param bubbles
     *            LinkedList - list containing all bubbles in this level.
     * @param time
     *            double - the time the player has to complete the level in seconds.
     * @param id
     *            int - the number of this level.
     */
    public Level(LinkedList<AbstractWall> walls, LinkedList<Projectile> projectiles,
            LinkedList<Pickup> pickups, LinkedList<Bubble> bubbles, int time, int id) {
        this.walls = walls;
        this.projectiles = projectiles;
        this.bubbles = bubbles;
        this.pickups = pickups;
        this.time = time;
        this.maxTime = time;
        this.id = id;
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        for (AbstractGameObject gameObject : bubbles) {
            gameObject.update(this, delta);
        }
        for (AbstractGameObject gameObject : projectiles) {
            gameObject.update(this, delta);
        }
        for (AbstractGameObject gameObject : pickups) {
            gameObject.update(this, delta);
        }
        for (AbstractGameObject gameObject : walls) {
            gameObject.update(this, delta);
        }
        if (shopSlow) {
            for (Bubble gameObject : bubbles) {
                gameObject.setSlow(true);
            }
        }

        // Update the object lists.
        for (AbstractGameObject obj : pendingAddition) {
            if (obj instanceof Projectile) {
                projectiles.add((Projectile) obj);
            }
            if (obj instanceof Bubble) {
                bubbles.add((Bubble) obj);
            }
            if (obj instanceof Pickup) {
                pickups.add((Pickup) obj);
            }
            if (obj instanceof AbstractWall) {
                walls.add((AbstractWall) obj);
            }
        }

        for (AbstractGameObject obj : pendingRemoval) {
            if (obj instanceof Projectile) {
                projectiles.remove(obj);
            }
            if (obj instanceof Bubble) {
                bubbles.remove(obj);
            }
            if (obj instanceof Pickup) {
                pickups.remove(obj);
            }
            if (obj instanceof AbstractWall) {
                walls.remove(obj);
            }
        }

        pendingAddition.clear();
        pendingRemoval.clear();

        time -= delta;
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        final ResourcesWrapper resources = new ResourcesWrapper();
        graphics.drawImage(resources.getBackgroundImage(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getBackgroundImage().getWidth(),
                resources.getBackgroundImage().getHeight());

        for (AbstractGameObject gameObject : projectiles) {
            gameObject.render(container, graphics);
        }
        for (AbstractGameObject gameObject : walls) {
            gameObject.render(container, graphics);
        }
        for (AbstractGameObject gameObject : bubbles) {
            gameObject.render(container, graphics);
        }
        for (AbstractGameObject gameObject : pickups) {
            gameObject.render(container, graphics);
        }
    }

    @Override
    public void toAdd(AbstractGameObject obj) {
        pendingAddition.add(obj);
    }

    @Override
    public void toRemove(AbstractGameObject obj) {
        pendingRemoval.add(obj);
    }

    /**
     * Recursive method to split all bubbles.
     * 
     * @param bubbles
     *            : all the bubbles that need to be split.
     * @param endLevel
     *            : boolean that indicates if the all the bubbles need to be split, or that bubbles
     *            need to be split until they are of size 1.
     */
    public void splitAllBubbles(LinkedList<Bubble> bubbles, boolean endLevel) {
        for (Bubble bubble : bubbles) {
            if (bubble.getBubbleFactory() != null || endLevel) {
                LinkedList<Bubble> newBubbles = bubble.split(this, Helpers.randInt(1, 10));
                splitAllBubbles(newBubbles, endLevel);
            }
        }
    }

    /**
     * Gets the walls in this level.
     * 
     * @return {@link LinkedList} of {@link Wall}s - The walls in this level.
     */
    public LinkedList<AbstractWall> getWalls() {
        return this.walls;
    }

    /**
     * Gets the projectiles in this level.
     * 
     * @return {@link LinkedList} of {@link Projectile}s - The walls in this level.
     */
    public LinkedList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    /**
     * Gets the bubbles in this level.
     * 
     * @return {@link LinkedList} of {@link Bubble}s - The walls in this level.
     */
    public LinkedList<Bubble> getBubbles() {
        return this.bubbles;
    }

    /**
     * Gets the pickups in this level.
     * 
     * @return {@link LinkedList} of {@link Pickup}s - The walls in this level.
     */
    public LinkedList<Pickup> getPickups() {
        return this.pickups;
    }

    /**
     * Gets the id of this level.
     * 
     * @return int - the id of this level.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the remaining amount of time for this level to a different value.
     * 
     * @param time
     *            int - the amount of time left in milliseconds.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets the remaining amount of time left for this level.
     * 
     * @return int - the amount of time left in milliseconds.
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Gets the total amount of time before the level fails.
     * 
     * @return int - the total amount of time in milliseconds.
     */
    public int getMaxTime() {
        return this.maxTime;
    }

    /**
     * Sets the total amount of time before the level fails to a different value.
     * 
     * @param time
     *            int - the total amount of time in milliseconds.
     */
    public void setMaxTime(int time) {
        this.time = time;
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
     * Checks whether the level timer has expired.
     *
     * @return boolean - true if the timer hits zero or below.
     */
    public boolean timerExpired() {
        return this.time <= 0;
    }

    /**
     * Returns the buffer between the objects that need removal and their lists.
     * 
     * @return {@link LinkedList} of {@link AbstractGameObject}s - the buffer.
     */
    public LinkedList<AbstractGameObject> getToRemove() {
        return pendingRemoval;
    }

    /**
     * Returns the buffer between the objects that need addition and their lists.
     * 
     * @return {@link LinkedList} of {@link AbstractGameObject}s - the buffer.
     */
    public LinkedList<AbstractGameObject> getToAdd() {
        return pendingAddition;
    }

    /**
     * Sets the state of the level to be slowed
     * 
     * @param bool
     *            boolean - Sets the level to be permanently slowed if set to true.
     */
    public void setShopSlow(boolean bool) {
        this.shopSlow = bool;
    }
}
