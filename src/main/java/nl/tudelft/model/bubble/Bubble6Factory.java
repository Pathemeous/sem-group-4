package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble6Factory extends BaseBubbleFactory {

    public Bubble6Factory(ResourceWrapper resources) {
        super(resources);
    }

    @Override
    public Bubble createBubble() {
        return new Bubble6(getResources(), 0, 0);
    }

}
