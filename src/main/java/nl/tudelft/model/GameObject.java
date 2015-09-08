package nl.tudelft.model;

import nl.tudelft.semgroup4.util.SEMRectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {

    protected float locX;
	protected float locY;
    protected Image image;
	
	public GameObject(Image image, float x, float y) {
		this.image = image;
		this.locX = x;
		this.locY = y;
	}

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawImage(getImage(), locX, locY);
    }

    public abstract void update(GameContainer container, int delta) throws SlickException;

    /**
     * @return the image
     */
    protected Image getImage() {
        return image;
    }

    /**
     * @param image
     */
    protected void setImage(Image image) {
        this.image = image;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }
    
    public float getLocX () {
    	return locX;
    }
    public float getLocY () {
    	return locY;
    }

    public void setLocY(float locY) {
        this.locY = locY;
    }

    public Shape getBounds() {
		return new SEMRectangle(
                locX, locY,
                getImage().getWidth(), getImage().getHeight());
	}

}
