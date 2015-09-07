package nl.tudelft.model;

import org.newdawn.slick.Image;

public class Player extends GameObject {
	
	private int score;
	private int lives;

	
	public Player(Image image, int x_location, int y_location, int width, int height, int speed) {
		super(image, x_location, y_location, width, height, speed);
		this.score = 0;
		this.lives = 3;
	}
	
	public void tick() {
		if(x_location > 1154) {
			x_location = 1154;
		}
		if(x_location < 0) {
			x_location = 0;
		}		
	}

	/**
	 * Increments the y_location of the player with a certain value.
	 * 
	 * @param velY The amount of pixels that the player changes.
	 */
	public void setY(int velY) {
		y_location+= velY;
	}
	
	/**
	 * Increments the x_location of the player with a certain value.
	 * 
	 * @param velX The amount of pixels that the player changes.
	 */
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
	
	/**
	 * Get the amount of lives that the player has.
	 * 
	 * @return int the amount of lives the player has left.
	 */
	public int getLives() {
		return this.lives;
	}
	
	/**
	 * Get the score of the player.
	 * 
	 * @return int the current score of the player.
	 */
	public int getScore() {
		return this.score;
	}

}
