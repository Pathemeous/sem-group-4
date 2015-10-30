package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.model.pickups.AbstractPickup;

import org.newdawn.slick.Image;

public abstract class AbstractUtility extends AbstractPickup {

    /**
     * Creates a random utility.
     * 
     * @param img
     *            {@link Image} - The image of this utility item.
     * @param locX
     *            int - the x-coordinate of this utility item.
     * @param locY
     *            int - the y-coordinate of this utility item.
     */
    public AbstractUtility(Image img, float locX, float locY) {
        super(img, locX, locY);
    }

    /**
     * Method to activate this utility.
     * 
     * <p>
     * Depending on the instance of the Utility, the utility is either immediately executed, or the
     * activation method of the specific class is called.
     * </p>
     * 
     * @param level
     *            {@link Level} - The level this utility belongs to.
     */
    public abstract void activate(Level level);
}
