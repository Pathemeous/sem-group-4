package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class GameObject {

    protected int locX;
	protected int locY;
    protected Image image;
	
	public GameObject(Image image, int x, int y) {
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

    public void setLocX(int locX) {
        this.locX = locX;
    }
    
    public int getLocX () {
    	return locX;
    }
    public int getLocY () {
    	return locY;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public Rectangle getBounds(){
		return new Rectangle(
                locX, locY,
                getImage().getWidth(), getImage().getHeight());
	}

}
