package nl.tudelft.model.bubble;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble4Factory extends AbstractBubbleFactory {

    public Bubble4Factory(ResourcesWrapper resources) {
        super(resources);
    }

    @Override
    public AbstractBubble createBubble(float locX, float locY) {
        return new Bubble4(getResources(), locX, locY);
    }

}
