package nl.tudelft.model.pickups.utility;

import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.SlickException;

public class SlowUtility extends Utility {
    
    private Level level;
    private int slowCounter = 0;
    private static final int SLOWTIME = 300;
    
    public SlowUtility(float locX, float locY) {
        super(Resources.pickupUtilitySlow, locX, locY);
    }
    
    @Override
    public void activate(Level level) {
        if (!isActive()) {
            setActive(true);
            this.level = level;
        }
    }
    
    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);
        
        if (isActive() && slowCounter != SLOWTIME) {
            slowCounter++;
            
            for (Bubble bubble : level.getBubbles()) {
                bubble.setSlow(true);
            }
        }
        
        if (slowCounter == SLOWTIME) {
            for (Bubble bubble : level.getBubbles()) {
                bubble.setSlow(false);
            }
            toRemove();
        }
    }
}
