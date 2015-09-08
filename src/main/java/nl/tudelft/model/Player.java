package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

	private final Input input;

	private Weapon weapon;

	private final Image imageLeft;
	private final Image imageRight;
	private final Image imageStill;

	public Player(Image image, Image imageLeft, Image imageRight, int locX, int locY, Input input, Weapon weapon) {
		super(image, locX, locY);
		this.input = input;
		this.weapon = weapon;

		this.imageStill = image;
		this.imageLeft = imageLeft;
		this.imageRight= imageRight;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(input.isKeyDown(Input.KEY_LEFT)) {
			setImage(imageLeft);
			setLocX((int) (getBounds().getX() - 4));
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			setImage(imageRight);
            setLocX((int) (getBounds().getX() + 4));
		}
		if(input.isKeyDown(Input.KEY_SPACE)) {
			weapon.fire(this.locX, this.locY, this.getWidth());
		}
		if(!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))){
			setImage(imageStill);
		}
	}

	public Weapon getWeapon() {
		return this.weapon;
	}
}
