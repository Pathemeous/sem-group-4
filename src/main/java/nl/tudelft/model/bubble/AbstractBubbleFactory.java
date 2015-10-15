package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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
}
