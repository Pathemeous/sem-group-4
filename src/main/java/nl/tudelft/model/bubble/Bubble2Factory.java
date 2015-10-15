package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble2Factory extends BaseBubbleFactory {

    public Bubble2Factory(ResourceWrapper resources) {
        super(resources);
    }

    @Override
    public Bubble createBubble() {
        return new Bubble2(getResources(), 0, 0);
    }

}
