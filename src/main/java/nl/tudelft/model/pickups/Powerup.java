package nl.tudelft.model.pickups;

import nl.tudelft.semgroup4.util.Helpers;

public class Powerup extends PickupContent {

    public enum PowerType {
        SHIELD, INVINCIBLE, SPEEDUP, POINTS, LIFE
    }

    private PowerType type;

    public Powerup(int random) {
        // some logic to determine which power up it is

        if (random == 10) {
            type = PowerType.LIFE;
        } else if (random > 8) {
            type = PowerType.INVINCIBLE;
        } else if (random > 6) {
            type = PowerType.SHIELD;
        } else if (random > 4) {
            type = PowerType.SPEEDUP;
        } else {
            type = PowerType.POINTS;
        }
    }

    public PowerType getPowerType() {
        return type;
    }
}
