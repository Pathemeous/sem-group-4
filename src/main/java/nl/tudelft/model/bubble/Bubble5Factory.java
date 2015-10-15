package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble5Factory extends BaseBubbleFactory {

    public Bubble5Factory(ResourceWrapper resources) {
        super(resources);
    }

    @Override
    public Bubble createBubble() {
        return new Bubble5(getResources(), 0, 0);
    }

}
