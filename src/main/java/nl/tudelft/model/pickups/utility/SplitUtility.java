package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class SplitUtility extends Utility {
    
    public SplitUtility(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilitySplit(), locX, locY);
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
