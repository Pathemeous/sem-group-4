package nl.tudelft.model;

import org.newdawn.slick.Image;

public class Player {
	private int x;
	private int y;
	private Image image;
	
	public Player(int x, int y, Image image) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(x > 617) {
			x = 617;
		}
		if(x < 0) {
			x = 0;
		}		
	}
	
	public void setY(int velY) {
		y += velY;
	}
	
	public void setX(int velX) {
		x += velX;
	}
	
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
