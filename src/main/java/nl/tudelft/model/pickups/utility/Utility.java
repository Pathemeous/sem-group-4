package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.model.pickups.Pickup;

import org.newdawn.slick.Image;


public abstract class Utility extends Pickup {
    
    
    
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
    public abstract void activate(Level level);
}
