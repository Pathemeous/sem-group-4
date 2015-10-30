package nl.tudelft.model.pickups.utility;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Level;

public class SplitUtility extends AbstractUtility {
    
    public SplitUtility(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilitySplit(), locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            setActive(true);
            
            level.splitAllBubbles(level.getBubbles(), false);
            setToRemove();
        }
    }
}
