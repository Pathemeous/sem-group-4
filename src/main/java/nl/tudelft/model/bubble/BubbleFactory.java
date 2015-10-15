package nl.tudelft.model.bubble;

import nl.tudelft.model.bubble.AbstractBubble;

public interface BubbleFactory {

    public AbstractBubble createBubble(float locX, float locY);
    
    public AbstractBubble createBubble();
}
