package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.Resources;

public class LevelWonUtility extends Utility {
    
    public LevelWonUtility(float locX, float locY) {
        super(Resources.pickupUtilityLevelwon, locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            super.activate(level);
            
            level.splitAllBubbles(level.getBubbles(), true);
            toRemove();
        }
    }
}
