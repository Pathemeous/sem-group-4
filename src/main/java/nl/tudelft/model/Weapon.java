package nl.tudelft.model;

import java.util.ArrayList;

import nl.tudelft.model.pickups.Pickup.WeaponType;
import nl.tudelft.model.pickups.PickupContent;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Updateable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Weapon extends PickupContent implements Updateable {

    private ArrayList<Projectile> projectiles;
    private Image img;
    private WeaponType type;
    private boolean sticky;
    private int maxCount;

    public Weapon(Image image, WeaponType type) {
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

    public <T extends Modifiable> void fire(T container, int locX, int locY, int width, int height) {
        if(projectiles.size() < maxCount) {
        	Projectile proj = new Projectile(img, locX, locY, width, height, 6, this);
            proj.fire();
            container.toAdd(proj);
            projectiles.add(proj);
        }
    }

    public <T extends Modifiable> void remove(T container, Projectile proj) {
    	projectiles.remove(proj);
        container.toRemove(proj);
    }
}
