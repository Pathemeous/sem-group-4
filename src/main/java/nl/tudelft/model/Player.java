package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

	public Player(Image image, int locX, int locY) {
		super(image, locX, locY);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
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
}
