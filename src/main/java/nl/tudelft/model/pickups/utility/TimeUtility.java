package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class TimeUtility extends Utility {

    private static final int EXTRA_TIME = 20000;
    
    public TimeUtility(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilityTime(), locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            setActive(true);
            
            int time = (level.getTime() + EXTRA_TIME < level.getMaxTime()) 
                    ? level.getTime() + EXTRA_TIME : level.getMaxTime();
            level.setTime(time);
            toRemove();
        }
    }

}
