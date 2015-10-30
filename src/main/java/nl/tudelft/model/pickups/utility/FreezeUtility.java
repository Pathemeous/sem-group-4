package nl.tudelft.model.pickups.utility;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.AbstractBubble;

import org.newdawn.slick.SlickException;

public class FreezeUtility extends Utility {
    
    private Level level;
    private int freezeCounter = 0;
    private static final int FREEZETIME = 300;
    
    public FreezeUtility(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupUtilityFreeze(), locX, locY);
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
        
        if (isActive() && freezeCounter != FREEZETIME) {
            freezeCounter++;
            
            for (AbstractBubble bubble : level.getBubbles()) {
                bubble.setFrozen(true);
            }
        }
        
        if (freezeCounter == FREEZETIME) {
            for (AbstractBubble bubble : level.getBubbles()) {
                bubble.setFrozen(false);
            }
            isToRemove();
        }
    }
    
    protected Level getLevel() {
        return level;
    }
    
    protected int getFreezeCounter() {
        return freezeCounter;
    }
    
    protected void setFreezeCounter(int counter) {
        freezeCounter = counter;
    }
    
}
