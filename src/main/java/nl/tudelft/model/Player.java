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
	
	public void setY(int volY) {
		y += volY;
	}
	
	public void setX(int volX) {
		x += volX;
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
