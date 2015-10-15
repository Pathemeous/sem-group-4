package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble3Factory extends AbstractBubbleFactory {

    public Bubble3Factory(ResourcesWrapper resources) {
        super(resources);
    }

    @Override
    public AbstractBubble createBubble(float locX, float locY) {
        return new Bubble3(getResources(), locX, locY);
    }
    
    @Override
    public AbstractBubble createBubble() {
        return new Bubble3(getResources(), 0, 0);
    }

}
