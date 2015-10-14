package nl.tudelft.model.bubble;

import nl.tudelft.model.bubble.Bubble;

public interface BubbleFactory {

    public Bubble createBubble(float locX, float locY);
    
    public Bubble createBubble();
}
