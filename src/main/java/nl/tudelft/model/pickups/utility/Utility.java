package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.model.pickups.Pickup;

import org.newdawn.slick.Image;


public abstract class Utility extends Pickup {
    
    private static final int EXTRA_TIME = 20000;
    
    /**
     * Creates a random utility.
     * @param random : random variable which determines what type the utility will get.
     */
    public Utility(Image img, float locX, float locY) {
        super(img, locX, locY);
    }
    
    /**
     * Method to activate this utility. Depending on the instance of the Utility,
     * the utility is either immediately executed, or the activation method of
     * the specific class is called.
     * @param level : the level this utility belongs to.
     */
    public void activate(Level level) {
        if (!isActive()) {
            setActive(true);
            
            if (this instanceof TimeUtility) {
                int time = (level.getTime() + EXTRA_TIME < level.getMaxTime()) 
                        ? level.getTime() + EXTRA_TIME : level.getMaxTime();
                level.setTime(time);
                toRemove();
            } else if (this instanceof SplitUtility) {
                level.splitAllBubbles(level.getBubbles(), false);
                toRemove();
            } else if (this instanceof LevelWonUtility) {
                level.splitAllBubbles(level.getBubbles(), true);
            } else if (this instanceof SlowUtility) {
                ((SlowUtility)this).activate(level);
            } else if (this instanceof FreezeUtility) {
                ((FreezeUtility)this).activate(level);
            }
        }
    }
}
