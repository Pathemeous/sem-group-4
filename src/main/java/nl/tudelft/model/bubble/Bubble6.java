package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.Resources.Resources;

/**
 * One of the Bubble types. This is a regular bubble of size 6.
 */
public class Bubble6 extends Bubble {
    
    /**
     * A simplified constructor, which makes the bubble go to the right by default.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     */
    public Bubble6(float locX, float locY) {
        this(locX, locY, true);
    }
    
    /**
     * The constructor for this class. Generates a bubble of size 6, with a
     * maxverticalspeed of 10. Adds two bubbles of size 5 to the list of 
     * next bubbles; those will be spawned when this bubble is hit.
     * @param locX : the starting x-location of the bubble.
     * @param locY : the starting y-location of the bubble.
     * @param goRight : boolean to indicate if the initial direction of the bubble
     *      is to the right.
     */
    public Bubble6(float locX, float locY, boolean goRight) {
        super(Resources.bubbleImage6, locX, locY, goRight);
        
        setMaxVerticalSpeed(10.0f);
        
        getNext().add(new Bubble5(locX, locY, true));
        getNext().add(new Bubble5(locX, locY, false));
    }

}
