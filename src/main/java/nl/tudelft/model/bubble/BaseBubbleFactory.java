package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public abstract class BaseBubbleFactory implements BubbleFactory {

    private final ResourcesWrapper resources;

    public BaseBubbleFactory(ResourcesWrapper resources) {
        this.resources = resources;
    }

    protected final ResourcesWrapper getResources() {
        return this.resources;
    }
}
