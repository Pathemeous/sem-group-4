package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble2Factory extends AbstractBubbleFactory {

    public Bubble2Factory(ResourcesWrapper resources) {
        super(resources);
    }

    @Override
    public AbstractBubble createBubble(float locX, float locY) {
        return new Bubble2(getResources(), locX, locY);
    }

}
