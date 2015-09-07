package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends GameObject {

	public Wall(Image image, int locX, int locY) {
		super(image, locX, locY);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

	}

}
