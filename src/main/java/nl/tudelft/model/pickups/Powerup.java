package nl.tudelft.model.pickups;


public class Powerup extends PickupContent {

    public enum PowerType {
        SHIELD, INVINCIBLE, SPEEDUP, POINTS, LIFE
    }

    private PowerType type;

    /**
     * Creates a random power up.
     * @param a random variable which determines what type the powerup will get.
     */
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
