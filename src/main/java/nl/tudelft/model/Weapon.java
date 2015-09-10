package nl.tudelft.model;

import java.util.ArrayList;
import java.util.LinkedList;

import nl.tudelft.model.pickups.Pickup.WeaponType;
import nl.tudelft.model.pickups.PickupContent;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Weapon extends PickupContent implements Updateable {

    private ArrayList<Projectile> projectiles;
    private Image img;
    private LinkedList<GameObject> del, add;
    private WeaponType type;
    private boolean sticky;
    private int maxCount;

    public Weapon(Image image, LinkedList<GameObject> toDel, LinkedList<GameObject> toAdd, WeaponType type) {
    	this.type = type;
    	sticky = false;
    	
    	switch(type) {
    	case REGULAR: 
    		maxCount = 1; 
    		break;
    	case DOUBLE: 
    		maxCount = 2; 
    		break;
    	case STICKY: 
    		maxCount = 1; 
    		sticky = true;
    		break;
    	case FLOWER: 
    		maxCount = 10; 
    		break;
    	}
    	
    	
        img = image;
        del = toDel;
        add = toAdd;
        projectiles = new ArrayList<Projectile>();
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        // TODO Auto-generated method stub
        
    }
    
    public WeaponType getType() {
    	return type;
    }
    
    public int getMaxCount() {
    	return maxCount;
    }
    
    public boolean isSticky () {
    	return sticky;
    }

    public void fire(int locX, int locY, int width, int height) {
        if(projectiles.size() < maxCount) {
        	Projectile proj = new Projectile(img, locX, locY, width, height, 6, this);
            add.add(proj);
            projectiles.add(proj);
        }
    }

    public void remove(Projectile proj) {
    	projectiles.remove(proj);
        del.add(proj);
    }
}
