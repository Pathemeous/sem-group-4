package nl.tudelft.model.pickups.powerup;

import nl.tudelft.semgroup4.Resources;

public class LifePowerup extends Powerup {

    public LifePowerup(float locX, float locY) {
        super(Resources.pickupUtilityLife, locX, locY);
    }
}
