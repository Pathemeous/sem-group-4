package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Bubble extends GameObject {

	private float verticalSpeed;
	private float horizontalSpeed;
	private float maxVerticalSpeed;
	private float gravity;
	
    public Bubble(Image image, float x, float y) {
        super(image,x,y);
        verticalSpeed = 4.0f;
        maxVerticalSpeed = 10.0f;
        horizontalSpeed = 2.0f;
        gravity = 0.1f;
    }

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		move();
	}
	
	private void move() {
		float x = getLocX();
		float y = getLocY();
		
		float newX = 0;
		
//		if (Math.round(horizontalSpeed) == 0) {
//			newX = x + 1;
//		} else {
//			newX = x + (int)Math.round(horizontalSpeed);
//		}
		newX = x + horizontalSpeed;
		
		float newY = 0;
		newY = y - verticalSpeed;

		setLocX( newX );
		setLocY( newY );
		verticalSpeed -= gravity;
	}
	
	public float getVerticalSpeed() {
		return verticalSpeed;
	}
	
	public float getHorizontalSpeed() {
		return horizontalSpeed;
	}
	
	public float getMaxVerticalSpeed() {
		return maxVerticalSpeed;
	}
	
	public void setVerticalSpeed(float newSpeed) {
		verticalSpeed = newSpeed;
	}
	
	public void setHorizontalSpeed(float newSpeed) {
		horizontalSpeed = newSpeed;
	}
	
	@Override
	public Shape getBounds() {
		int width = getImage().getWidth();
		int height = getImage().getHeight();
		
		float centerPointX = getLocX() + 0.5f*width;
		float centerPointY = getLocY() + 0.5f*height;
		float radius = (float)0.5*width;
		
		return new Circle(centerPointX, centerPointY, radius);
	}
	
}
