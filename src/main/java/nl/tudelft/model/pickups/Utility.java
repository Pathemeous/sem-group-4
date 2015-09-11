package nl.tudelft.model.pickups;


public class Utility extends PickupContent {

    public enum UtilityType {
        SPLIT, SLOW, FREEZE, LEVELWON, TIME
    }

    private UtilityType type;

    /**
     * Creates a random utility.
     */
    public Utility(int random) {
        if (random == 20) {
            type = UtilityType.LEVELWON;
        } else if (random > 16) {
            type = UtilityType.SPLIT;
        } else if (random > 11) {
            type = UtilityType.SLOW;
        } else if (random > 6) {
            type = UtilityType.FREEZE;
        } else if (random > 0) {
            type = UtilityType.TIME;
        }
    }

    public UtilityType getType() {
        return type;
    }
}
