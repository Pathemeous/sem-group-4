package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.Pickup;

import org.newdawn.slick.Image;


public class Powerup extends Pickup {
    
    public static final String SPEED = "speedup";
    public static final String INVINCIBLE  = "invincible";
    public static final String SHIELD = "shield";
    
    /**
     * Creates a powerup.
     * @param img : the image of the powerup while falling.
     * @param locX : the xloc of the powerup.
     * @param locY : the yloc of the powerup.
     */
    public Powerup(Image img, float locX, float locY) {
        super(img, locX, locY);
    }
    
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            
            if (this instanceof LifePowerup) {
                player.setLives(player.getLives() + 1);
                System.out.println("Remove lives");
                toRemove();
            } else if (this instanceof InvinciblePowerup) {
                ((InvinciblePowerup)this).activate(player);
            } else if (this instanceof ShieldPowerup) {
                ((ShieldPowerup)this).activate(player);
            } else if (this instanceof PointsPowerup) {
                player.setScore(player.getScore() + 100);
                System.out.println("Remove points");
                toRemove();
            } else if (this instanceof SpeedPowerup) {
                ((SpeedPowerup)this).activate(player);
            }
        }
    }
}
