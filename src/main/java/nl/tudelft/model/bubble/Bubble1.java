package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.Resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 1.
 */
public class Bubble1 extends Bubble {
    
    /**
     * A simplified constructor, which makes the bubble go to the right by default.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     */
    public Bubble1(ResourcesWrapper resources, float locX, float locY) {
        this(resources, locX, locY, true);
    }
    
    /**
     * The constructor for this class. Generates a bubble of size 1, with a
     * maxverticalspeed of 5. No new bubbles are spawned out of this bubble.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     * @param goRight : boolean to indicate if the initial direction of the bubble
     *      is to the right.
     */
    public Bubble1(ResourcesWrapper resources, float locX, float locY, boolean goRight) {
        super(resources.getBubbleImage1(), locX, locY, goRight);
        
        setMaxVerticalSpeed(5.0f);
    }

}