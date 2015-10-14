package nl.tudelft.model.pickups.powerup;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Helpers;

/**
 * Factory that can be used to create a random powerup.
 * @author Casper
 *
 */
public class RandomPowerupFactory {
    
    private int randomNumber;
    
    /**
     * Creates a randompowerupfactory, with a random number
     * between 1 and 12. Based on this random number, a 
     * powerup is generated.
     */
    public RandomPowerupFactory() {
        randomNumber = Helpers.randInt(1, 12);
    }
    
    /**
     * Creates a powerup, randomly chosen based on a randomnumber. 
     * @param locX : the starting x coordinate of the powerup.
     * @param locY : the starting y coordinate of the powerup.
     * @return : a powerup.
     */
    public Powerup createPowerup(float locX, float locY) {
        ResourcesWrapper res = new ResourcesWrapper();
        
        if (randomNumber == 12) {
            return new LifePowerup(res, locX, locY);
        } else if (randomNumber > 10) {
            return new MoneyPowerup(res, locX, locY);
        } else if (randomNumber > 8) {
            return new InvinciblePowerup(res, locX, locY);
        } else if (randomNumber > 6) {
            return new ShieldPowerup(res, locX, locY);
        } else if (randomNumber > 4) {
            return new SpeedPowerup(res, locX, locY);
        } else {
            return new PointsPowerup(res, locX, locY);
        }
    }
    
    protected void setRandomNumber(int random) {
        randomNumber = random;
    }
    
    protected int getRandomNumber() {
        return randomNumber;
    }
}
