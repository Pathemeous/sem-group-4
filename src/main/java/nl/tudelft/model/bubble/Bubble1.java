package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 1.
 */
public class Bubble1 extends Bubble {

    /**
     * The constructor for this class. Generates a bubble of size 1, with a maxverticalspeed of 5.
     * No new bubbles are spawned out of this bubble.
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
    protected Bubble1(ResourceWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage1(), locX, locY, resources, null);

        setMaxVerticalSpeed(5.0f);
    }

}
