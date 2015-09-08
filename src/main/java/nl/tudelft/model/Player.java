package nl.tudelft.model;

import org.newdawn.slick.*;

public class Player extends GameObject {

	private final Input input;

	private Weapon weapon;

	private Animation walkLeft;
	private Animation walkRight;
	private Animation standStill;
	private Animation animation;

	public Player(Image image, Animation animation, Animation imageLeft, Animation imageRight, int locX, int locY, Input input, Weapon weapon) {
		super(image, locX, locY);
		this.input = input;
		this.weapon = weapon;

		this.animation = animation;
		this.walkLeft = imageLeft;
		this.walkRight= imageRight;
		this.standStill = animation;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(input.isKeyDown(Input.KEY_LEFT)) {
			setAnimation(walkLeft);
			setLocX((int) (getBounds().getX() - 4));
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			setAnimation(walkRight);
            setLocX((int) (getBounds().getX() + 4));
		}
		if(input.isKeyDown(Input.KEY_SPACE)) {
			weapon.fire(this.locX, this.locY, this.getWidth());
		}
		if(!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))){
			setAnimation(standStill);
		}
	}

	public Animation getAnimation() {
		return this.animation;
	}

	public void setAnimation(Animation an) {
		this.animation = an;
	}
}
