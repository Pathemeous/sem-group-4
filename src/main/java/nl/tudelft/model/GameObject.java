package nl.tudelft.model;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.Updateable;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public interface GameObject extends Updateable, Renderable {

    public Image getImage();
    
    public void setImage(Image image);
    
    public void setLocX(float locX);
    
    public float getLocX();
    
    public float getLocY();
    
    public void setLocY(float locY);
    
    public Shape getBounds();
    
    public int getWidth();
    
    public int getHeight();
}
