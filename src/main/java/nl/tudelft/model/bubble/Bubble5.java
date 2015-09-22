package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.Resources;

public class Bubble5 extends Bubble {
    
    public Bubble5(float locX, float locY, boolean goRight)
            throws IllegalArgumentException {
        super(Resources.bubbleImage5, locX, locY, goRight);
        
        setMaxVerticalSpeed(9.0f);
        
        
    }
    
}
