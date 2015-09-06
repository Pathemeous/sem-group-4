package nl.tudelft.model;

import java.awt.Rectangle;

import org.newdawn.slick.Image;

public class GameObject {	
	public int x_location;
	public int y_location;
	public int speed;
	public Image image;
	public int width;
	public int height;
	
	public GameObject(Image image, int x, int y, int width, int height, int speed) {
		this.image = image;
		this.x_location = x;
		this.y_location = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
	}
	

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	public Rectangle getBounds(){
		return new Rectangle(x_location, y_location, width, height);
	}
	
	public int getX_location() {
		return x_location;
	}
	public void setX_location(int x_location) {
		this.x_location = x_location;
	}
	public int getY_location() {
		return y_location;
	}
	public void setY_location(int y_location) {
		this.y_location = y_location;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
