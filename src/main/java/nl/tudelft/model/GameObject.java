package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Renderable;
import nl.tudelft.semgroup4.Updateable;
import nl.tudelft.semgroup4.util.SemRectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject implements Updateable, Renderable {

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
    public GameObject(Image image, float locX, float locY) {
        this.image = image;
        this.locX = locX;
        this.locY = locY;
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        graphics.drawImage(getImage(), locX, locY);
    }

    @Override
    public abstract <T extends Modifiable> void update(T container, int delta)
            throws SlickException;

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
