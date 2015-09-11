package nl.tudelft.model.pickups;

import nl.tudelft.semgroup4.util.Helpers;

public class Powerup extends PickupContent {

    public enum PowerType {
        SHIELD, INVINCIBLE, SPEEDUP, POINTS, LIFE
    }

    private PowerType type;

    public Powerup(int random) {
        // some logic to determine which power up it is

        switch (random) {
            case 1:
                type = PowerType.SHIELD;
                break;
            case 2:
                type = PowerType.INVINCIBLE;
                break;
            case 3:
                type = PowerType.SPEEDUP;
                break;
            case 4:
                type = PowerType.POINTS;
                break;
            case 5:
                type = PowerType.LIFE;
                break;
        }
    }

    public PowerType getPowerType() {
        return type;
    }
}
