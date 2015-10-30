package nl.tudelft.model.pickups.utility;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.AbstractBubble;

import org.newdawn.slick.SlickException;

public class FreezeUtility extends AbstractUtility {

    private Level level;
    private int freezeCounter = 0;
    private static final int FREEZETIME = 300;

    /**
     * Creates a new {@link FreezeUtility}.
     * 
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this instance may use.
     * @param locX
     *            int - The x-coordinate of this instance.
     * @param locY
     *            int - The y-coordinate of this instance.
     */
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

    /**
     * Counts the amount of clock cycles that the freeze utility is activated and stops the effect
     * after its maximum duration.
     */
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
            setToRemove();
        }
    }

    protected Level getLevel() {
        return level;
    }

    protected void setFreezeCounter(int counter) {
        freezeCounter = counter;
    }

    protected int getFreezeCounter() {
        return freezeCounter;
    }

}
