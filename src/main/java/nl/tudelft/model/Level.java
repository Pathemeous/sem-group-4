package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Utility;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.Updateable;
import nl.tudelft.semgroup4.util.Helpers;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Level implements Updateable, Renderable, Modifiable {

    private final LinkedList<Wall> walls;
    private final LinkedList<Projectile> projectiles;
    private final LinkedList<Pickup> pickups;
    private final LinkedList<Bubble> bubbles;
    private final LinkedList<GameObject> pendingRemoval = new LinkedList<>();
    private final LinkedList<GameObject> pendingAddition = new LinkedList<>();
    private final static int EXTRA_TIME = 20000;
    private int time;
    private final int maxTime;
    private double speed;
    private int utilSlowCounter = 0;
    private boolean slowBalls = false;
    private final static int UTIL_SLOWDOWN_TIME = 300;
    private int utilFreezeCounter = 0;
    private boolean frozenBalls = false;
    private final static int UTIL_FREEZE_TIME = 300;

    private final int id;

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
    public Level(LinkedList<Wall> walls, LinkedList<Projectile> projectiles,
            LinkedList<Pickup> pickups, LinkedList<Bubble> bubbles, int time, int id) {
        this.walls = walls;
        this.projectiles = projectiles;
        this.bubbles = bubbles;
        this.pickups = pickups;
        this.time = time;
        this.maxTime = time;

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
        for (GameObject gameObject : walls) {
            gameObject.update(this, delta);
        }

        // Update the object lists.
        for (GameObject obj : pendingAddition) {
            if (obj instanceof Projectile) {
                projectiles.add((Projectile) obj);
            }
            if (obj instanceof Bubble) {
                bubbles.add((Bubble) obj);
            }
            if (obj instanceof Pickup) {
                pickups.add((Pickup) obj);
            }
            if (obj instanceof Wall) {
                walls.add((Wall) obj);
            }
        }

        for (GameObject obj : pendingRemoval) {
            if (obj instanceof Projectile) {
                projectiles.remove(obj);
            }
            if (obj instanceof Bubble) {
                bubbles.remove(obj);
            }
            if (obj instanceof Pickup) {
                pickups.remove(obj);
            }
            if (obj instanceof Wall) {
                walls.remove(obj);
            }
        }

        pendingAddition.clear();
        pendingRemoval.clear();

        time -= delta;

        setSlowBalls();
        freezeBalls();
    }

    private void setSlowBalls() {
        utilSlowCounter =
                (utilSlowCounter <= UTIL_SLOWDOWN_TIME && utilSlowCounter != 0) ? utilSlowCounter + 1
                        : 0;
        if (slowBalls) {
            for (Bubble bubble : bubbles) {
                bubble.slowBubbleDown(true);
            }
        }
        if (utilSlowCounter == UTIL_SLOWDOWN_TIME) {
            slowBalls = false;
            for (Bubble bubble : bubbles) {
                bubble.slowBubbleDown(false);
            }
        }
    }

    private void freezeBalls() {
        utilFreezeCounter =
                (utilFreezeCounter <= UTIL_FREEZE_TIME && utilFreezeCounter != 0)
                        ? utilFreezeCounter + 1 : 0;
        if (frozenBalls) {
            for (Bubble bubble : bubbles) {
                bubble.freeze(true);
            }
        }
        if (utilFreezeCounter == UTIL_FREEZE_TIME) {
            frozenBalls = false;
            for (Bubble bubble : bubbles) {
                bubble.freeze(false);
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {

        graphics.drawImage(Resources.backgroundImage, 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, Resources.backgroundImage.getWidth(),
                Resources.backgroundImage.getHeight());

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
    public void toAdd(GameObject obj) {
        pendingAddition.add(obj);
        // if (obj instanceof Projectile) {
        // projectiles.add((Projectile)obj);
        // return projectiles.contains(obj);
        // }
        // if (obj instanceof Bubble) {
        // bubbles.add((Bubble)obj);
        // return bubbles.contains(obj);
        // }
        // if (obj instanceof Wall) {
        // walls.add((Wall)obj);
        // return walls.contains(obj);
        // }
        // return false;
    }

    @Override
    public void toRemove(GameObject obj) {
        pendingRemoval.add(obj);
        // if (obj instanceof Projectile) {
        // projectiles.remove(obj);
        // return !projectiles.contains(obj);
        // }
        // if (obj instanceof Bubble) {
        // bubbles.remove(obj);
        // return !bubbles.contains(obj);
        // }
        // if (obj instanceof Wall) {
        // walls.remove(obj);
        // return !walls.contains(obj);
        // }
        // return false;
    }

    /**
     * Applies the effects of the utility to the state of the level.
     *
     * @param util
     *            Utility - The utility to apply.
     * @throws IllegalArgumentException
     *             - If the utility type is not correct.
     */
    public void applyUtility(Utility util) throws IllegalArgumentException {
        switch (util.getType()) {
            case FREEZE:
                utilFreezeCounter++;
                frozenBalls = true;
                break;
            case LEVELWON:
                splitAllBubbles(bubbles, true);
                // execute method here where the level is won (maybe split all the balls till
                // they're gona first)
                break;
            case SLOW:
                utilSlowCounter++;
                slowBalls = true;
                break;
            case SPLIT:
                splitAllBubbles(bubbles, false);
                break;
            case TIME:
                time = (time + EXTRA_TIME < maxTime) ? time + EXTRA_TIME : maxTime;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void splitAllBubbles(LinkedList<Bubble> bubbles, boolean endLevel) {
        for (Bubble bubble : bubbles) {
            if (bubble.getSize() > 1 || endLevel) {
                LinkedList<Bubble> newBubbles = bubble.split(this, Helpers.randInt(1, 10));
                splitAllBubbles(newBubbles, endLevel);
            }
        }
    }

    public LinkedList<Wall> getWalls() {
        return this.walls;
    }

    public LinkedList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public LinkedList<Bubble> getBubbles() {
        return this.bubbles;
    }

    public LinkedList<Pickup> getPickups() {
        return this.pickups;
    }

    public int getId() {
        return this.id;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    public int getMaxTime() {
        return this.maxTime;
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
     * Checks whether the level timer has expired.
     *
     * @return boolean - true if the timer hits zero or below.
     */
    public boolean timerExpired() {
        return this.time <= 0;
    }

    public LinkedList<GameObject> getToRemove() {
        return pendingRemoval;
    }

    public LinkedList<GameObject> getToAdd() {
        return pendingAddition;
    }
}
