package nl.tudelft.model.bubble;

import nl.tudelft.controller.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble6Factory extends AbstractBubbleFactory {

    public Bubble6Factory(ResourcesWrapper resources) {
        super(resources);
    }

    @Override
    public AbstractBubble createBubble(float locX, float locY) {
        return new Bubble6(getResources(), locX, locY);
    }

}
