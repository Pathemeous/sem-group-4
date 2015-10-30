package nl.tudelft.model;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.util.SemRectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 * This class represents a framework implementation of the {@link GameObject} interface.
 * 
 * <p>
 * This class provides common functionality for this kind of objects, including positioning and
 * rendering.
 * </p>
 */
public abstract class AbstractGameObject implements GameObject {

    protected float locX;
    protected float locY;
    protected Image image;

    /**
     * Constructs a GameObject by setting its position and an image to render.
     * 
     * @param image
     *            Image - The sprite that this object should be rendered with.
     * @param locX
     *            float - the x-coordinate that this object should be rendered at.
     * @param locY
     *            float - the y-coordinate that this object should be rendered at.
     */
    public AbstractGameObject(Image image, float locX, float locY) {
        this.image = image;
        this.locX = locX;
        this.locY = locY;
    }

    @Override
    public abstract <T extends Modifiable> void update(T container, int delta)
            throws SlickException;

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        graphics.drawImage(getImage(), locX, locY);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void setLocX(float locX) {
        this.locX = locX;
    }

    @Override
    public float getLocX() {
        return locX;
    }

    @Override
    public float getLocY() {
        return locY;
    }

    @Override
    public void setLocY(float locY) {
        this.locY = locY;
    }

    /**
     * Returns a (rectangular) shape of the boundaries of this object by measuring the size of its
     * image.
     * 
     * <p>
     * This shape is the bounding box of this object and can be used to check for collisions.
     * </p>
     * 
     * @return {@link Shape} - The shape that represents the bounding box of this object.
     */
    @Override
    public Shape getBounds() {
        return new SemRectangle(locX, locY, getImage().getWidth(), getImage().getHeight());
    }

    /**
     * Determines the width of this object's image and returns it.
     * 
     * @return int - The width of this object.
     */
    @Override
    public int getWidth() {
        return this.getImage().getWidth();
    }

    /**
     * Determines the height of this object's image and returns it.
     * 
     * @return int - The height of this object.
     */
    @Override
    public int getHeight() {
        return this.getImage().getHeight();
    }

}
