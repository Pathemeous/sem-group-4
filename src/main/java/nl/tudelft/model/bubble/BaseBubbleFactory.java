package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public abstract class BaseBubbleFactory implements BubbleFactory {

    private final ResourcesWrapper resources;
    private final BubbleFactoryFactory bubbleFactoryFactory;

    public BaseBubbleFactory(ResourcesWrapper resources,
                             BubbleFactoryFactory bubbleFactoryFactory) {
        this.resources = resources;
        this.bubbleFactoryFactory = bubbleFactoryFactory;
    }

    protected final ResourcesWrapper getResources() {
        return this.resources;
    }

    protected final BubbleFactoryFactory getBubbleFactoryFactory() {
        return this.bubbleFactoryFactory;
    }

}
