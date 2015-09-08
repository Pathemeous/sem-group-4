package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

	private final Input input;
	private final Image imageLeft;
	private final Image imageRight;
	private final Image imageStill;

	public Player(Image image, Image imageLeft, Image imageRight, int locX, int locY, Input input) {
		super(image, locX, locY);
		this.input = input;

		this.imageStill = image;
		this.imageLeft = imageLeft;
		this.imageRight= imageRight;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyDown(Input.KEY_LEFT)) {
			setImage(imageLeft);
			setLocX((int) (getBounds().getX() - 4));
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			setImage(imageRight);
            setLocX((int) (getBounds().getX() + 4));
		}
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			System.out.println("PEW PEW");
		}
		if(!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))){
			setImage(imageStill);
		}
	}
}
