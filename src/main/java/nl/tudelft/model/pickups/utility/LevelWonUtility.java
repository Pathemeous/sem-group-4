package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class LevelWonUtility extends Utility {
    
    public LevelWonUtility(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilityLevelwon(), locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            setActive(true);
            
            level.splitAllBubbles(level.getBubbles(), true);
            toRemove();
        }
    }
}
