package nl.tudelft.model;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.Updateable;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * This interface represents the basic functionality that any object in the game field has.
 * 
 * <p>
 * Each object is {@link Updateable} (with the game clock cycle) and {@link Renderable}
 * (displayable on the scene).
 * </p>
 */
public interface GameObject extends Updateable, Renderable {

    /**
     * Sets the image representing the {@link GameObject} to a new value.
     * 
     * @param image
     *            {@link Image} - The new image.
     */
    void setImage(Image image);

    /**
     * Returns the image that represents the {@link GameObject}.
     * 
     * @return {@link Image} - The image that is used to represent this object.
     */
    Image getImage();

    /**
     * Sets the new x-coordinate for the upper-left corner of this object.
     * 
     * @param locX
     *            float - The x-coordinate for the upper-left corner of this object.
     */
    void setLocX(float locX);

    /**
     * Returns the x-coordinate for the upper-left corner of this object.
     * 
     * @return float - the x-coordinate.
     */
    float getLocX();

    /**
     * Sets the new y-coordinate for the upper-left corner of this object.
     * 
     * @param locX
     *            float - The y-coordinate for the upper-left corner of this object.
     */
    void setLocY(float locY);

    /**
     * Returns the y-coordinate for the upper-left corner of this object.
     * 
     * @return float - the y-coordinate.
     */
    float getLocY();

    /**
     * Returns a {@link Shape} that represents the bounding box of this object.
     * 
     * <p>
     * The bounding box can be used to find collisions and overlaps.
     * </p>
     * 
     * @return {@link Shape} - The bounding box of this object.
     */
    Shape getBounds();

    /**
     * Returns the width of this object's bounding box
     * 
     * @return int - the object's width.
     */
    int getWidth();

    /**
     * Returns the height of this object's bounding box
     * 
     * @return int - the object's height.
     */
    int getHeight();
}
