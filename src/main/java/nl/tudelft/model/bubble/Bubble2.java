package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 2.
 */
public class Bubble2 extends Bubble {

    /**
     * The constructor for this class. Generates a bubble of size 2, with a maxverticalspeed of 6.
     * Adds two bubbles of size 1 to the list of next bubbles; those will be spawned when this
     * bubble is hit.
     * 
     * @param resources
     *            {@link ResourceWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     * @param goRight
     *            : boolean to indicate if the initial direction of the bubble is to the right.
     */
    protected Bubble2(ResourceWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage2(), locX, locY, resources, 
                new Bubble1Factory(resources));

        setMaxVerticalSpeed(6.0f);
    }

}
