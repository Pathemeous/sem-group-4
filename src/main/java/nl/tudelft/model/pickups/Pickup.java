package nl.tudelft.model.pickups;

import java.util.LinkedList;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Weapon;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.util.Helpers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Pickup extends GameObject {
	
	private boolean inBubble;
	private boolean onGround;
	private PickupContent pickup;
	private LinkedList<GameObject> toDelete;
	private LinkedList<GameObject> toAdd;
	private int tickCount;
	
	public enum WeaponType {
		REGULAR, DOUBLE, STICKY, FLOWER
	}
	
	public Pickup(Image image, float x, float y, LinkedList<GameObject> toDelete, LinkedList<GameObject> toAdd) {
		super(image, x, y);
		inBubble = true;
		onGround = false;
		this.toDelete = toDelete;
		this.toAdd = toAdd;
		
		int random = Helpers.randInt(1, 3);
		if(random < 4) {
			int randomWeaponNr = Helpers.randInt(1, 4);
			// new weapon
			switch(randomWeaponNr) {
			// regular weapon
			case 1: pickup = new Weapon(Resources.weaponImage, toDelete, toAdd, WeaponType.REGULAR); break;
			// Double shoot
			case 2: pickup = new Weapon(Resources.weaponImage, toDelete, toAdd, WeaponType.DOUBLE); break;
			// Sticky weapon
			case 3: pickup = new Weapon(Resources.weaponImage, toDelete, toAdd, WeaponType.STICKY); break;
			// Flower weapon
			case 4: pickup = new Weapon(Resources.weaponImage, toDelete, toAdd, WeaponType.FLOWER); break;
			}
			
		} else if (random > 7) {
			// new utility
		} else {
			// new powerup
			pickup = new Powerup();
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if(!inBubble) {
			setLocY (getLocY() + 1);
		}
		if(onGround) {
			tickCount ++; 
			
			if(tickCount == 180) {
				remove();
			}
		}
	}
	
	public void remove() {
		toDelete.add(this);
	}
	
	public PickupContent getContent() {
		return pickup;
	}
	
	public void setInBubble(boolean inBubble) {
		this.inBubble = inBubble;
	}
	public boolean isInBubble() {
		return inBubble;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

}
