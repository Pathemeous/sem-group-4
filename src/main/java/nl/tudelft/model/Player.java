package nl.tudelft.model;

import org.newdawn.slick.Image;

public class Player extends GameObject {

	
	public Player(Image image, int x_location, int y_location, int width, int height, int speed) {
		super(image, x_location, y_location, width, height, speed);
	}
	
	public void setY(int velY) {
		y_location+= velY;
	}
	
	public void setX(int velX) {
		x_location+= velX;
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
		return x_location;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y_location;
	}

}
