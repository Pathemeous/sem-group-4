package nl.tudelft.model;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.Updateable;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public interface GameObject extends Updateable, Renderable {

    Image getImage();
    
    void setImage(Image image);
    
    void setLocX(float locX);
    
    float getLocX();
    
    float getLocY();
    
    void setLocY(float locY);
    
    Shape getBounds();
    
    int getWidth();
    
    int getHeight();
}
