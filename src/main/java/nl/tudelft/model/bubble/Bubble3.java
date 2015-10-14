package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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
     *            {@link ResourcesWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     */
    protected Bubble3(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage3(), locX, locY, resources);
        
        List<AbstractBubble> next = new ArrayList<>();
        
        // Create 2 new bubbles, that will come free when this bubble splits
        BubbleFactory bubbleFactory = new Bubble2Factory(new ResourcesWrapper());
        for (int i = 0; i < 2; i++) {
            next.add(bubbleFactory.createBubble()); 
        }
        
        setNext(next);

        setMaxVerticalSpeed(7.0f);
    }

}
