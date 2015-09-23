package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.Resources.Resources;

public class SplitUtility extends Utility {
    
    public SplitUtility(float locX, float locY) {
        super(Resources.pickupUtilitySplit, locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            setActive(true);
            
            level.splitAllBubbles(level.getBubbles(), false);
            toRemove();
        }
    }
}
