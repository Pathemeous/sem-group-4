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
		
		int random = Helpers.randInt(1, 6);
		if(random < 4) {
			int randomWeaponNr = Helpers.randInt(1, 4);
			// new weapon
			switch(randomWeaponNr) {
			// regular weapon
			case 1: 
				pickup = new Weapon(Resources.weaponImageRegular, toDelete, toAdd, WeaponType.REGULAR);
				setImage(Resources.pickup_weapon_regular);
				break;
			// Double shoot
			case 2: 
				pickup = new Weapon(Resources.weaponImageRegular, toDelete, toAdd, WeaponType.DOUBLE);
				setImage(Resources.pickup_weapon_double);
				break;
			// Sticky weapon
			case 3: 
				pickup = new Weapon(Resources.weaponImageSticky, toDelete, toAdd, WeaponType.STICKY);
				setImage(Resources.pickup_weapon_sticky);
				break;
			// Flower weapon
			case 4: 
				pickup = new Weapon(Resources.weaponImageFlower, toDelete, toAdd, WeaponType.FLOWER);
				setImage(Resources.pickup_weapon_flowers);
				break;
			}
			
		} else if (random > 3) {
			// new powerup
			pickup = new Powerup();
			Powerup up = (Powerup)pickup;
			switch(up.getPowerType()) {
			case INVINCIBLE:
				setImage(Resources.pickup_power_invincible);
				break;
			case POINTS:
				setImage(Resources.pickup_power_points);
				break;
			case SHIELD:
				setImage(Resources.pickup_power_shield);
				break;
			case SPEEDUP:
				setImage(Resources.pickup_power_speedup);
				break;
			}
		} else {
			// new utility
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
