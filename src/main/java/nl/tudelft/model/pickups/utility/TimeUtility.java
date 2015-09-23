package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.Resources.Resources;

public class TimeUtility extends Utility {

    private static final int EXTRA_TIME = 20000;
    
    public TimeUtility(float locX, float locY) {
        super(Resources.pickupUtilityTime, locX, locY);
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
