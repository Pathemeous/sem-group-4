package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

public class PointsPowerup extends Powerup {
    
    public PointsPowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerPoints(), locX, locY);
    }
    
    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            player.setScore(player.getScore() + 100);
            toRemove();
        }
    }
}
