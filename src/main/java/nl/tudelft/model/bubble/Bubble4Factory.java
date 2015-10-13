package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble4Factory extends BaseBubbleFactory {

    public Bubble4Factory(ResourcesWrapper resources) {
        super(resources);
    }

    @Override
    public Bubble createBubble() {
        return new Bubble4(getResources(), 0, 0);
    }

}
