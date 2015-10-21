package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 3.
 */
public class Bubble3 extends AbstractBubble {

    /**
     * The constructor for this class. Generates a bubble of size 3, with a maxverticalspeed of 7.
     * Adds two bubbles of size 2 to the list of next bubbles; those will be spawned when this
     * bubble is hit.
     * 
     * @param resources
     *            {@link ResourceWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     */
    public Bubble3(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage3(), locX, locY, resources);
    }
    
    @Override
    public List<AbstractBubble> createNextBubbles() {
        List<AbstractBubble> next = new ArrayList<>();
        
        // Create 2 new bubbles, that will come free when this bubble splits
        for (int i = 0; i < 2; i++) {
            next.add(new Bubble2(getResources(), 0, 0)); 
        }
        
        return next;
    }
    
    @Override
    public float initMaxVerticalSpeed() {
        return 7.0f;
    }

}
