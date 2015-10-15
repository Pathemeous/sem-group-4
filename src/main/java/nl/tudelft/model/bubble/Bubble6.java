package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 6.
 */
public class Bubble6 extends Bubble {

    /**
     * The constructor for this class. Generates a bubble of size 6, with a maxverticalspeed of 10.
     * Adds two bubbles of size 5 to the list of next bubbles; those will be spawned when this
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
    protected Bubble6(ResourceWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage6(), locX, locY, resources, 
                new Bubble5Factory(resources));

        setMaxVerticalSpeed(10.0f);
    }

}
