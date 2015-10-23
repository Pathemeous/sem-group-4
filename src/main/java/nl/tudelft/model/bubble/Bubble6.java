package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 6.
 */
public class Bubble6 extends AbstractBubble {

    /**
     * The constructor for this class. Generates a bubble of size 6, with a maxverticalspeed of 10.
     * Adds two bubbles of size 5 to the list of next bubbles; those will be spawned when this
     * bubble is hit.
     *
     * @param resources
     *            {@link ResourceWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     */
    public Bubble6(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage6(), locX, locY, resources);
    }
    
    @Override
    protected List<AbstractBubble> createNextBubbles() {
        List<AbstractBubble> next = new ArrayList<>();
        
        // Create 2 new bubbles, that will come free when this bubble splits
        for (int i = 0; i < 2; i++) {
            next.add(new Bubble5(getResources(), 0, 0)); 
        }
        
        return next;
    }
    
    @Override
    protected float initMaxVerticalSpeed() {
        return 10.0f;
    }

}
