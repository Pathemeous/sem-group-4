package nl.tudelft.model.pickups;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import nl.tudelft.model.GameObject;


public class Pickup extends GameObject {
	
	private boolean inBubble;
	
	public Pickup(Image image, float x, float y) {
		super(image, x, y);
		inBubble = true;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if(!inBubble) {
			setLocY (getLocY() + 1);
		}	
	}
	
	public void setInBubble(boolean inBubble) {
		this.inBubble = inBubble;
	}
	public boolean isInBubble() {
		return inBubble;
	}

}
