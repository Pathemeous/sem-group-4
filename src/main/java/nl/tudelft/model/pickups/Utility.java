package nl.tudelft.model.pickups;

import nl.tudelft.semgroup4.util.Helpers;

public class Utility extends PickupContent {

    public enum UtilityType {
        SPLIT, SLOW, FREEZE, LEVELWON, TIME
    }

    private UtilityType type;

    public Utility() {
        int random = Helpers.randInt(1, 20);
        
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
