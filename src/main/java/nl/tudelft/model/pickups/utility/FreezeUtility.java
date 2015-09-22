package nl.tudelft.model.pickups.utility;

import org.newdawn.slick.SlickException;

import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;

public class FreezeUtility extends Utility {
    
    private Level level;
    private int freezeCounter = 0;
    private final int FREEZE_TIME = 300;
    
    public FreezeUtility(float locX, float locY) {
        super(Resources.pickupUtilityFreeze, locX, locY);
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
        
        if (isActive() && freezeCounter != FREEZE_TIME) {
            freezeCounter++;
            
            for (Bubble bubble : level.getBubbles()) {
                bubble.setFrozen(true);
            }
        }
        
        if (freezeCounter == FREEZE_TIME) {
            for (Bubble bubble : level.getBubbles()) {
                bubble.setFrozen(false);
            }
            toRemove();
        }
    }
    
}
