package nl.tudelft.model.pickups.powerup;

import nl.tudelft.semgroup4.Resources;

public class PointsPowerup extends Powerup {
    
    public PointsPowerup(float locX, float locY) {
        super(Resources.pickupPowerPoints, locX, locY);
    }
}
