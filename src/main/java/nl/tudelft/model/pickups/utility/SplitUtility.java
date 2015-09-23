package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.Resources;

public class SplitUtility extends Utility {
    
    public SplitUtility(float locX, float locY) {
        super(Resources.pickupUtilitySplit, locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            super.activate(level);
            
            level.splitAllBubbles(level.getBubbles(), false);
            toRemove();
        }
    }
}
