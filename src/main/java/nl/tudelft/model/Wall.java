package nl.tudelft.model;

import org.newdawn.slick.Image;

public class Wall extends GameObject {
//	private int x_location;
//	private int y_location;
//	private Image image;
//	private int width;
//	private int height;


	public Wall(Image image, int x_location, int y_location, int width, int height, int speed){
//		this.image = image;
//		this.x_location = x;
//		this.y_location = y;
//		this.width = width;
//		this.height = height;
		super(image, x_location, y_location, width, height, speed);
	}


	/**
	 * @return the x_location
	 */
	public int getX_location() {
		return x_location;
	}


	/**
	 * @param x_location the x_location to set
	 */
	public void setX_location(int x_location) {
		this.x_location = x_location;
	}


	/**
	 * @return the y_location
	 */
	public int getY_location() {
		return y_location;
	}


	/**
	 * @param y_location the y_location to set
	 */
	public void setY_location(int y_location) {
		this.y_location = y_location;
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
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
