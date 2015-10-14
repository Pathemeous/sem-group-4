package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Helpers;

/**
 * Class that can be used to create a random weapon.
 * @author Casper
 *
 */
public class RandomWeaponFactory {
    
    private int randomNumber;
    
    /**
     * Creates a randompowerupfactory, with a random number
     * between 1 and 4. Based on this random number, a 
     * weapon is generated.
     */
    public RandomWeaponFactory() {
        randomNumber = Helpers.randInt(1, 4);
    }
    
    /**
     * Creates a weapon, randomly chosen based on a randomnumber. 
     * @param locX : the starting x coordinate of the weapon.
     * @param locY : the starting y coordinate of the weapon.
     * @return : a weapon.
     */
    public Weapon createWeapon(float locX, float locY) {
        ResourcesWrapper res = new ResourcesWrapper();
        
        switch (randomNumber) {
            // regular weapon
            case 1:
                return new RegularWeapon(res, locX, locY);
            // Double shoot
            case 2:
                return new DoubleWeapon(res, locX, locY);
            // Sticky weapon
            case 3:
                return new StickyWeapon(res, locX, locY);
            // Flower weapon
            case 4:
                return new FlowerWeapon(res, locX, locY);
            default:
                return new RegularWeapon(res, locX, locY);
        }
    }
    
    protected void setRandomNumber(int random) {
        randomNumber = random;
    }
    
    protected int getRandomNumber() {
        return randomNumber;
    }
}
