package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * One of the Bubble types. This is a regular bubble of size 5.
 */
public class Bubble5 extends Bubble {

    /**
     * The constructor for this class. Generates a bubble of size 5, with a maxverticalspeed of 9.
     * Adds two bubbles of size 4 to the list of next bubbles; those will be spawned when this
     * bubble is hit.
     *
     * @param resources
     *            {@link ResourcesWrapper} - A new resourceWrapper that this class can use.
     * @param locX
     *            : the starting x-location of the bubble.
     * @param locY
     *            : the starting y-location of the bubble.
     * @param goRight
     *            : boolean to indicate if the initial direction of the bubble is to the right.
     * @param bubbleFactoryFactory
     *            {@link BubbleFactoryFactory} - The BubbleFactoryFactory that this class may use.
     */
    public Bubble5(ResourcesWrapper resources, BubbleFactoryFactory bubbleFactoryFactory,
                   float locX, float locY, boolean goRight) {
        super(resources.getBubbleImage5(), locX, locY, goRight, resources, bubbleFactoryFactory);

        this.bubbleFactory = getBubbleFactoryFactory().getSize4();

        setMaxVerticalSpeed(9.0f);
    }
}
