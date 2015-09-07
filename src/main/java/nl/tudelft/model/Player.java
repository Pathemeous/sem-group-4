package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {
	
	private int score = 0;
	private int lives = 3;
	private final Input input;

	public Player(Image image, int locX, int locY, Input input) {
		super(image, locX, locY);
		this.input = input;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(input.isKeyDown(Input.KEY_LEFT)) {
			setImage(new Image("src/main/resources/img/player_left.png"));
			setLocX((int) (getBounds().getX() - 4));
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			setImage(new Image("src/main/resources/img/player_right.png"));
            setLocX((int) (getBounds().getX() + 4));
		}
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			System.out.println("PEW PEW");
		}
		if(!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))){
			setImage(new Image("src/main/resources/img/player_still.png"));
		}
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
