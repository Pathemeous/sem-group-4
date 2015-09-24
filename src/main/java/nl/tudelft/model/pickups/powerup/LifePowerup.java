package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class LifePowerup extends Powerup {

    public LifePowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilityLife(), locX, locY);
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
