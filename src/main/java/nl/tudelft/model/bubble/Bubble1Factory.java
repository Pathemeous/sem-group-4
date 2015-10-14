package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class Bubble1Factory extends AbstractBubbleFactory {

    public Bubble1Factory(ResourcesWrapper resources) {
        super(resources);
    }

    @Override
    public Bubble createBubble(float locX, float locY) {
        return new Bubble1(getResources(), locX, locY);
    }
    
    @Override
    public Bubble createBubble() {
        return new Bubble1(getResources(), 0, 0);
    }

}
