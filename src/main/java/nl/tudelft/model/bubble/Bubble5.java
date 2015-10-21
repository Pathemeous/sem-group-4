package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 5.
 */
public class Bubble5 extends AbstractBubble {

    /**
     * The constructor for this class. Generates a bubble of size 5, with a maxverticalspeed of 9.
     * Adds two bubbles of size 4 to the list of next bubbles; those will be spawned when this
     * bubble is hit.
     *
     * @param resources
     *            {@link ResourceWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     */
    public Bubble5(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage5(), locX, locY, resources);
    }
    
    @Override
    public List<AbstractBubble> createNextBubbles() {
        List<AbstractBubble> next = new ArrayList<>();
        
        // Create 2 new bubbles, that will come free when this bubble splits
        for (int i = 0; i < 2; i++) {
            next.add(new Bubble4(getResources(), 0, 0)); 
        }
        
        return next;
    }
    
    @Override
    public float initMaxVerticalSpeed() {
        return 9.0f;
    }
    
    
}
