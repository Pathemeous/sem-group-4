package nl.tudelft.model.pickups;

import nl.tudelft.semgroup4.util.Helpers;

public class Utility extends PickupContent {

    public enum UtilityType {
        SPLIT, SLOW, FREEZE, LEVELWON, TIME
    }

    private UtilityType type;

    public Utility(int random) {

        switch (random) {
            case 1:
                type = UtilityType.SPLIT;
                break;
            case 2:
                type = UtilityType.SLOW;
                break;
            case 3:
                type = UtilityType.FREEZE;
                break;
            case 4:
                type = UtilityType.LEVELWON;
                break;
            case 5:
                type = UtilityType.TIME;
                break;
        }
    }

    public UtilityType getType() {
        return type;
    }
}
