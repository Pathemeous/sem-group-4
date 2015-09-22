package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.Resources;

public class Bubble6 extends Bubble {
    
    public Bubble6(float locX, float locY) {
        this(locX, locY, true);
    }
    
    public Bubble6(float locX, float locY, boolean goRight) {
        super(Resources.bubbleImage6, locX, locY, goRight);
        
        setMaxVerticalSpeed(10.0f);
        
        getNext().add(new Bubble5(locX, locY, true));
        getNext().add(new Bubble5(locX, locY, false));
    }

}
