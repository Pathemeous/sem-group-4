package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble3Factory extends BaseBubbleFactory {

    public Bubble3Factory(ResourcesWrapper resources, BubbleFactoryFactory bubbleFactoryFactory) {
        super(resources, bubbleFactoryFactory);
    }

    @Override
    public Bubble createBubble() {
        return new Bubble3(getResources(), getBubbleFactoryFactory(), 0, 0, true);
    }

}