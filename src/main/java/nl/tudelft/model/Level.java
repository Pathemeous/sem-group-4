package nl.tudelft.model;

import java.util.ArrayList;
import java.util.LinkedList;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Utility;
import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Level implements Updateable, Renderable, Modifiable {

    LinkedList<Wall> walls;
    LinkedList<Projectile> projectiles;
    LinkedList<Pickup> pickups;
    LinkedList<Bubble> bubbles;
    LinkedList<GameObject> toRemove = new LinkedList<>(), toAdd = new LinkedList<>();
    private double time;
    private double speed;
    private int utilSlowCounter = 0;
    private boolean slowBalls = false;
    private final int UTIL_SLOWDOWN_TIME = 300;
    private int utilFreezeCounter = 0;
    private boolean frozenBalls = false;
    private final int UTIL_FREEZE_TIME = 300;

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
        this.pickups = pickups;
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
        for (GameObject gameObject: walls) {
            gameObject.update(this, delta);
        }
        
        // Update the object lists.
        for (GameObject obj : toAdd) {
          if (obj instanceof Projectile) {
              projectiles.add((Projectile)obj);
          }
          if (obj instanceof Bubble) {
              bubbles.add((Bubble)obj);
          }
          if (obj instanceof Pickup) {
              pickups.add((Pickup)obj);
          }
          if (obj instanceof Wall) {
              walls.add((Wall)obj);
          }
        }
        
        for (GameObject obj : toRemove) {
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
        
        toAdd.clear();
        toRemove.clear();
        
        slowBalls();
        freezeBalls();
    }
    
    private void slowBalls() {
    	utilSlowCounter = (utilSlowCounter <= UTIL_SLOWDOWN_TIME && utilSlowCounter != 0) ? utilSlowCounter+1 : 0;
        if(slowBalls) {
        	for(Bubble bubble : bubbles) {
    			bubble.slowBubbleDown(true);
    		}
        }
        if(utilSlowCounter == UTIL_SLOWDOWN_TIME) {
        	slowBalls = false;
        	for (Bubble bubble : bubbles) {
        		bubble.slowBubbleDown(false);
        	}
        }
    }
    
    private void freezeBalls() {
    	utilFreezeCounter = (utilFreezeCounter <= UTIL_FREEZE_TIME && utilFreezeCounter != 0) ? utilFreezeCounter+1 : 0;
        if(frozenBalls) {
        	for(Bubble bubble : bubbles) {
    			bubble.freeze(true);
    		}
        }
        if(utilFreezeCounter == UTIL_FREEZE_TIME) {
        	frozenBalls = false;
        	for (Bubble bubble : bubbles) {
        		bubble.freeze(false);
        	}
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
    public void toAdd(GameObject obj) {
        toAdd.add(obj);
//        if (obj instanceof Projectile) {
//            projectiles.add((Projectile)obj);
//            return projectiles.contains(obj);
//        }
//        if (obj instanceof Bubble) {
//            bubbles.add((Bubble)obj);
//            return bubbles.contains(obj);
//        }
//        if (obj instanceof Wall) {
//            walls.add((Wall)obj);
//            return walls.contains(obj);
//        }
//        return false;
    }

    @Override
    public void toRemove(GameObject obj) {
        toRemove.add(obj);
//        if (obj instanceof Projectile) {
//            projectiles.remove(obj);
//            return !projectiles.contains(obj);
//        }
//        if (obj instanceof Bubble) {
//            bubbles.remove(obj);
//            return !bubbles.contains(obj);
//        }
//        if (obj instanceof Wall) {
//            walls.remove(obj);
//            return !walls.contains(obj);
//        }
//        return false;
    }
    
    public void applyUtility(Utility util) {
    	switch(util.getType()) {
    	case FREEZE: 
    		utilFreezeCounter++;
    		frozenBalls = true;
    		break;
    	case LEVELWON:
    		splitAllBubbles(bubbles, true);
    		// execute method here where the level is won (maybe split all the balls till they're gona first)
    		break;
    	case SLOW:
    		utilSlowCounter++;
    		slowBalls = true;
    		break;
    	case SPLIT:
    		splitAllBubbles(bubbles, false);
    		break;
    	case TIME: 
    		time += 20;
    		break;
    	}
    }
    
    private void splitAllBubbles(LinkedList<Bubble> bubbles, boolean endLevel) {
    	for (Bubble bubble : bubbles) {
    		if(bubble.getSize() > 1 || endLevel) {
    			LinkedList<Bubble> newBubbles = bubble.split(this);
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
