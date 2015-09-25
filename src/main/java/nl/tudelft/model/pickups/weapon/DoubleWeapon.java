package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class DoubleWeapon extends Weapon {
    
    public DoubleWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponDouble(), resources.getWeaponImageRegular(), 
                locX, locY, false, 2);
    }
}
