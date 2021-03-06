package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

public class LifePowerup extends AbstractPowerup {

    public LifePowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilityLife(), locX, locY);
    }
    
    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            player.setLives(player.getLives() + 1);
            setToRemove();
        }
    }
}
