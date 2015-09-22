package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.Resources;

/**
 * One of the Bubble types. This is a regular bubble of size 4.
 */
public class Bubble4 extends Bubble {
    
    /**
     * A simplified constructor, which makes the bubble go to the right by default.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     */
    public Bubble4(float locX, float locY) {
        this(locX, locY, true);
    }
    
    /**
     * The constructor for this class. Generates a bubble of size 4, with a
     * maxverticalspeed of 8. Adds two bubbles of size 3 to the list of 
     * next bubbles; those will be spawned when this bubble is hit.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     * @param goRight : boolean to indicate if the initial direction of the bubble
     *      is to the right.
     */
    public Bubble4(float locX, float locY, boolean goRight) {
        super(Resources.bubbleImage4, locX, locY, goRight);
        
        setMaxVerticalSpeed(8.0f);
        
        getNext().add(new Bubble3(locX, locY, true));
        getNext().add(new Bubble3(locX, locY, false));
    }

}
