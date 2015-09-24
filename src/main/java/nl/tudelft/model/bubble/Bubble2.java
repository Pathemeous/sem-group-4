package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.Resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 2.
 */
public class Bubble2 extends Bubble {
    
    /**
     * A simplified constructor, which makes the bubble go to the right by default.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     */
    public Bubble2(ResourcesWrapper resources, float locX, float locY) {
        this(resources, locX, locY, true);
    }
    
    /**
     * The constructor for this class. Generates a bubble of size 2, with a
     * maxverticalspeed of 6. Adds two bubbles of size 1 to the list of 
     * next bubbles; those will be spawned when this bubble is hit.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     * @param goRight : boolean to indicate if the initial direction of the bubble
     *      is to the right.
     */
    public Bubble2(ResourcesWrapper resources, float locX, float locY, boolean goRight) {
        super(resources.getBubbleImage2(), locX, locY, goRight);
        
        setMaxVerticalSpeed(6.0f);
        
        getNext().add(new Bubble1(new ResourcesWrapper(), locX, locY, true));
        getNext().add(new Bubble1(new ResourcesWrapper(), locX, locY, false));
    }

}