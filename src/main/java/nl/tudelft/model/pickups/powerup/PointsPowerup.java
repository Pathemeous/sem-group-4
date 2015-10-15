package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class PointsPowerup extends Powerup {
    
    public PointsPowerup(ResourceWrapper resources, float locX, float locY) {
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
