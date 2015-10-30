package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.pickups.AbstractPickup;
import nl.tudelft.model.player.Player;

import org.newdawn.slick.Image;


public abstract class Powerup extends AbstractPickup {
    
    public static final String SPEED = "speedup";
    public static final String INVINCIBLE  = "invincible";
    public static final String SHIELD = "shield";
    public static final String SHOPSHIELD = "shopshield";
    
    /**
     * Creates a powerup.
     * @param img : the image of the powerup while falling.
     * @param locX : the xloc of the powerup.
     * @param locY : the yloc of the powerup.
     */
    public Powerup(Image img, float locX, float locY) {
        super(img, locX, locY);
    }
    
    /**
     * Method to activate the powerup. Depending on the instance of 
     * the powerup, either the powerup is immediately executed or the 
     * activation method of the specific class is called.
     * @param player : the player to which this powerup belongs.
     */
    public abstract void activate(Player player);
}
