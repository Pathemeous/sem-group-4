package nl.tudelft.model;

import java.awt.Rectangle;

public class GameObject {	
	private int x_location;
	private int y_location;
	private int speed;
	
	public Rectangle getBounds(int width, int height){
		return new Rectangle(getX_location(), y_location, width, height);
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
