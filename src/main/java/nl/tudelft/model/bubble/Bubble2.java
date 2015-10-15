package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 2.
 */
public class Bubble2 extends AbstractBubble {

    /**
     * The constructor for this class. Generates a bubble of size 2, with a maxverticalspeed of 6.
     * Adds two bubbles of size 1 to the list of next bubbles; those will be spawned when this
     * bubble is hit.
     * 
     * @param resources
     *            {@link ResourcesWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     */
    protected Bubble2(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage2(), locX, locY, resources);
    }
    
    @Override
    protected List<AbstractBubble> createNextBubbles() {
        List<AbstractBubble> next = new ArrayList<>();
        
        // Create 2 new bubbles, that will come free when this bubble splits
        BubbleFactory bubbleFactory = new Bubble1Factory(new ResourcesWrapper());
        for (int i = 0; i < 2; i++) {
            next.add(bubbleFactory.createBubble()); 
        }
        
        return next;
    }
    
    @Override
    protected float initMaxVerticalSpeed() {
        return 6.0f;
    }

}
