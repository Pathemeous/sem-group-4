package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble2Factory extends BaseBubbleFactory {

    public Bubble2Factory(ResourcesWrapper resources, BubbleFactoryFactory bubbleFactoryFactory) {
        super(resources, bubbleFactoryFactory);
    }

    @Override
    public Bubble createBubble() {
        return new Bubble2(getResources(), getBubbleFactoryFactory(), 0, 0, true);
    }

}
