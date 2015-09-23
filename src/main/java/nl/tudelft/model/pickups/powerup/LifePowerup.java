package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Resources;

public class LifePowerup extends Powerup {

    public LifePowerup(float locX, float locY) {
        super(Resources.pickupUtilityLife, locX, locY);
    }
    
    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            player.setLives(player.getLives() + 1);
            toRemove();
        }
    }
}
