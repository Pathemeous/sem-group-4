package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.ResourcesWrapper;
import nl.tudelft.semgroup4.Updateable;
import nl.tudelft.semgroup4.util.SemRectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class AbstractGameObject implements Updateable, Renderable {

    protected float locX;
    protected float locY;
    protected Image image;
    protected ResourcesWrapper resourcesWrapper = new ResourcesWrapper();

    /**
     * Constructs a GameObject by setting its position.
     * 
     * @param locX
     *            float - the x-coordinate that this object should be rendered at.
     * @param locY
     *            float - the y-coordinate that this object should be rendered at.
     */
    public AbstractGameObject(float locX, float locY) {
        this.locX = locX;
        this.locY = locY;
        this.image = setDefaultImage();
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        graphics.drawImage(getImage(), locX, locY);
    }

    @Override
    public abstract <T extends Modifiable> void update(T container, int delta)
            throws SlickException;

    /**
     * called by the constructor to set the image. This method should be implemented to fetch the
     * correct image from the resourcesWrapper.
     * 
     * @return Image - The image to use for this GameObject.
     */
    abstract protected Image setDefaultImage();

    /**
     * Method that allows injection of the resourcesWrapper on demand.
     * 
     * @param wrapper
     *            {@link ResourcesWrapper} - the wrapper to use instead of the default.
     */
    public void setResourcesWrapper(ResourcesWrapper wrapper) {
        this.resourcesWrapper = wrapper;
    }

    /**
     * Accesses the image field.
     * 
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image
     *            Image - the new image.
     */
    protected void setImage(Image image) {
        this.image = image;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }

    public float getLocX() {
        return locX;
    }

    public float getLocY() {
        return locY;
    }

    public void setLocY(float locY) {
        this.locY = locY;
    }

    public Shape getBounds() {
        return new SemRectangle(locX, locY, getImage().getWidth(), getImage().getHeight());
    }

    public int getWidth() {
        return this.getImage().getWidth();
    }

    public int getHeight() {
        return this.getImage().getHeight();
    }

}
