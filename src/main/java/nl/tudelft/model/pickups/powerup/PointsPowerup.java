package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Resources;

public class PointsPowerup extends Powerup {
    
    public PointsPowerup(float locX, float locY) {
        super(Resources.pickupPowerPoints, locX, locY);
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
