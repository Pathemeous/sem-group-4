package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bubble extends GameObject {

	private int verticalSpeed;
	private int horizontalSpeed;
	
    public Bubble(Image image, int x, int y) {
        super(image,x,y);
        verticalSpeed = 50;
        horizontalSpeed = 4;
    }

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		move();
	}
	
	private void move() {
		int x = getLocX();
		int y = getLocY();
		
		setLocX( x + horizontalSpeed);
		setLocY( y - verticalSpeed);
		verticalSpeed -= 2;
	}
	
	public int getVerticalSpeed() {
		return verticalSpeed;
	}
	
	public int getHorizontalSpeed() {
		return horizontalSpeed;
	}
	
	public void setVerticalSpeed(int newSpeed) {
		verticalSpeed = newSpeed;
	}
	
	public void setHorizontalSpeed(int newSpeed) {
		horizontalSpeed = newSpeed;
	}
}
