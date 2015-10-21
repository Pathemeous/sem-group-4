package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 1.
 */
public class Bubble1 extends AbstractBubble {

    /**
     * The constructor for this class. Generates a bubble of size 1, with a maxverticalspeed of 5.
     * No new bubbles are spawned out of this bubble.
     *
     * @param resources
     *            {@link ResourceWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     */
    public Bubble1(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage1(), locX, locY, resources);
    }
    
    @Override
    protected List<AbstractBubble> createNextBubbles() {
        return new ArrayList<>();
    }
    
    @Override
    protected float initMaxVerticalSpeed() {
        return 5.0f;
    }

}
