package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 * Bubble is a subclass of GameObject and represents a single bubble in the game.
 * @author Casper
 *
 */
public class Bubble extends GameObject {

	private float verticalSpeed;
	private float horizontalSpeed;
	private float maxVerticalSpeed;
	private float gravity;
	private int size;
	private BubbleManager manager;
	
	/**
	 * Constructor for Bubble. Initiliazes the image of bubble with the given x and y, and sets the speed and gravity on 
	 * predetermined values. Only the maxVerticalSpeed is based on the size of the bubble (which ranges from 1 through 6)
	 * @param image : image of the bubble
	 * @param x : the x location of the top left corner of the bubble
	 * @param y : the y location of the top left corner of the bubble
	 * @param size : the size of the bubble; can range from 1-6
	 */
    public Bubble(Image image, float x, float y, int size, BubbleManager manager) {
        super(image,x,y);
        this.manager = manager;
        this.size = size;
        verticalSpeed = 0.0f;
        horizontalSpeed = 2.0f;
        gravity = 0.1f;
        
        switch(size) {
        case 6: 
        	maxVerticalSpeed = 9.0f; 
        	break;
        case 5: 
        	maxVerticalSpeed = 8.0f; 
        	break;
        case 4: 
        	maxVerticalSpeed = 7.0f; 
        	break;
        case 3: 
        	maxVerticalSpeed = 6.0f; 
        	break;
        case 2: 
        	maxVerticalSpeed = 5.0f; 
        	break;
        case 1: 
        	maxVerticalSpeed = 5.0f; 
        	break;
        default: 
        	maxVerticalSpeed = 0.0f; 
        }
    }
    
    /**
     * This method is called every tick, to update the ball.
     */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		move();
	}
	
	/**
	 * Method which is called to make the ball move. It moves in a direction based on its horizontalspeed and verticalspeed.
	 * The verticalspeed is afterwards decreased with the gravity.
	 */
	private void move() {
		float x = getLocX();
		float y = getLocY();
		
		float newX = 0;
		newX = x + horizontalSpeed;
		
		float newY = 0;
		newY = y - verticalSpeed;

		setLocX( newX );
		setLocY( newY );
		verticalSpeed -= gravity;
	}
	
	/**
	 * 
	 * @return the vertical speed of the bubble
	 */
	public float getVerticalSpeed() {
		return verticalSpeed;
	}
	
	/**
	 * 
	 * @return the horizontal speed of the bubble
	 */
	public float getHorizontalSpeed() {
		return horizontalSpeed;
	}
	
	/**
	 * 
	 * @return the maximum vertical speed the bubble can reach
	 */
	public float getMaxVerticalSpeed() {
		return maxVerticalSpeed;
	}
	
	/**
	 * Sets the vertical speed of the bubble
	 * @param newSpeed : the new speed
	 */
	public void setVerticalSpeed(float newSpeed) {
		verticalSpeed = newSpeed;
	}
	
	/**
	 * Sets the horizontal speed of the bubble
	 * @param newSpeed : the new speed
	 */
	public void setHorizontalSpeed(float newSpeed) {
		horizontalSpeed = newSpeed;
	}
	
	/**
	 * @return the maximum speed the bubble can have at any time in any direction
	 */
	public float getMaxSpeed() {
		if(horizontalSpeed > maxVerticalSpeed) {
			return horizontalSpeed;
		} 
		return maxVerticalSpeed;
	}
	
	public BubbleManager getBubbleManager() {
		return manager;
	}
	
	public int getSize() {
		return size;
	}
	
	/**
	 * Overrides the getbounds method of GameObject, Bubble's superclass, so the shape of the bubble is 
	 * an instance of Circle instead of Rectangle
	 */
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
