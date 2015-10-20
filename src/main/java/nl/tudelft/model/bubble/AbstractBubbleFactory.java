package nl.tudelft.model.bubble;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public abstract class AbstractBubbleFactory implements BubbleFactory {

    private final ResourcesWrapper resources;
    
    /**
     * Creates an Bubble Factory with a resourceswrapper. This factory can
     * be used to create bubbles.
     * @param resources : resourceswrapper. 
     */
    public AbstractBubbleFactory(ResourcesWrapper resources) {
        this.resources = resources;
    }

    protected final ResourcesWrapper getResources() {
        return this.resources;
    }

    /**
     * When not passing any arguments, we create a bubble at 0,0.
     *
     * <p>
     *     This is the same as calling createBubble(0,0).
     * </p>
     *
     * @return a ubble with x = 0, and y = 0x.
     */
    @Override
    public AbstractBubble createBubble() {
        return createBubble(0, 0);
    }
}
