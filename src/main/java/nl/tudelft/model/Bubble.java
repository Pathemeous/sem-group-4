package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Bubble extends GameObject {

	private int verticalSpeed;
	private int horizontalSpeed;
	
    public Bubble(Image image, int x, int y) {
        super(image,x,y);
        verticalSpeed = 4;
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
		
		//setLocX( x + horizontalSpeed);
		setLocY( y - verticalSpeed);
//		verticalSpeed -= 2;
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
	
//	@Override
//	public Shape getBounds() {
//		int width = getImage().getWidth();
//		int height = getImage().getHeight();
//		int centerPointX = getLocX() + (int)(0.5*(double)width);
//		int centerPointY = getLocY() + (int)(0.5*(double)height);
//		float radius = (float)0.5*width;
////		System.out.println("X: "+getLocX() + ", Y: "+getLocY() + ", width: "+width + ", height: "+height);
////		System.out.println("Centerpoint X: "+centerPointX);
////		System.out.println("Centerpoint Y: "+centerPointY);
////		System.out.println("Radius: "+radius);
//		
//		return new Circle(centerPointX, centerPointY, radius);
//	}
	
}
