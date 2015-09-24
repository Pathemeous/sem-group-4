package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.Resources.ResourcesWrapper;

public class FlowerWeapon extends Weapon {
    
    public FlowerWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponFlowers(), resources.getWeaponImageFlower(), 
                locX, locY, false, 10);
    }
}
