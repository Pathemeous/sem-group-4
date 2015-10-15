package nl.tudelft.model.pickups;

import nl.tudelft.model.pickups.powerup.RandomPowerupFactory;
import nl.tudelft.model.pickups.utility.RandomUtilityFactory;
import nl.tudelft.model.pickups.weapon.RandomWeaponFactory;
import nl.tudelft.semgroup4.util.Helpers;

/**
 * Class in which a random pickup can be generated.
 * @author Casper
 *
 */
public class RandomPickupFactory implements RandomFactory {
    
    private int pickupTypeNumber;
    private int pickupNumber;
    
    /**
     * Creates a random pickup factory, with two random numbers. 
     * One number is used to determine if the factory will in fact
     * create a pickup. The other number is used to determine
     * what type of pickup is created (Weapon, Utility or Powerup).
     */
    public RandomPickupFactory() {
        pickupTypeNumber = Helpers.randInt(1, 10);
        pickupNumber = Helpers.randInt(1, 10);
    }
    
    /**
     * Possibly creates a random pickup (of type Weapon, Utility or 
     * Powerup), based on a random number. 
     * There is also a chance that the method will not create a pickup,
     * in this case it will return 0. 
     * 
     * @param locX : the starting x coordinate of the pickup.
     * @param locY : the starting y coordinate of the pickup.
     * @return : Either a Pickup or null.
     */
    @Override
    public Pickup createPickup(float locX, float locY) {
        // Doesn't create pickup
        if (pickupNumber <= 7) {
            return null;
        }
        
        // Create weapon
        if (pickupTypeNumber < 4) {
            return new RandomWeaponFactory().createPickup(locX, locY);
            
        // Create powerup
        } else if (pickupTypeNumber < 7) {
            return new RandomPowerupFactory().createPickup(locX, locY);
            
        // Create utility
        } else {
            return new RandomUtilityFactory().createPickup(locX, locY);
        }
    }
    
    protected void setPickupTypeNumber(int number) {
        pickupTypeNumber = number;
    }
    
    protected int getPickupTypeNumber() {
        return pickupTypeNumber;
    }
    
    protected void setCreateNumber(int number) {
        pickupNumber = number;
    }
    
    protected int getCreateNumber() {
        return pickupNumber;
    }
    
}
