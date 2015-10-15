package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 3.
 */
public class Bubble3 extends Bubble {

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
     * @param goRight
     *            : boolean to indicate if the initial direction of the bubble is to the right.
     */
    protected Bubble3(ResourceWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage3(), locX, locY, resources,
                new Bubble2Factory(resources));

        setMaxVerticalSpeed(7.0f);
    }

}
