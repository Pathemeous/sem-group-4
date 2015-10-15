package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * Created by justin on 08/10/15.
 */
public abstract class BaseBubbleFactory implements BubbleFactory {

    private final ResourceWrapper resources;

    public BaseBubbleFactory(ResourceWrapper resources) {
        this.resources = resources;
    }

    protected final ResourceWrapper getResources() {
        return this.resources;
    }
}
