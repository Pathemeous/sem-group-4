package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.pickups.RandomPickupFactory;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Helpers;

/**
 * Factory that can be used to create a random utility.
 * @author Casper
 *
 */
public class RandomUtilityFactory implements RandomPickupFactory {
    
    private int randomNumber;
    
    /**
     * Creates a randompowerupfactory, with a random number
     * between 1 and 20. Based on this random number, a 
     * utility is generated.
     */
    public RandomUtilityFactory() {
        randomNumber = Helpers.randInt(1, 20);
    }
    
    /**
     * Creates a utility, randomly chosen based on a randomnumber. 
     * @param locX : the starting x coordinate of the utility.
     * @param locY : the starting y coordinate of the utility.
     * @return : a utility.
     */
    @Override
    public Utility createPickup(float locX, float locY) {
        ResourcesWrapper res = new ResourcesWrapper();
        
        if (randomNumber == 20) {
            return new LevelWonUtility(res, locX, locY);
        } else if (randomNumber > 16) {
            return new SplitUtility(res, locX, locY);
        } else if (randomNumber > 11) {
            return new SlowUtility(res, locX, locY);
        } else if (randomNumber > 6) {
            return new FreezeUtility(res, locX, locY);
        } else {
            return new TimeUtility(res, locX, locY);
        }
    }
    
    protected void setRandomNumber(int random) {
        randomNumber = random;
    }
    
    protected int getRandomNumber() {
        return randomNumber;
    }
}
