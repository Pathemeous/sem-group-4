package nl.tudelft.model.weapon;

import nl.tudelft.semgroup4.Resources;

public class FlowerWeapon extends Weapon {
    
    public FlowerWeapon(float locX, float locY) {
        super(Resources.pickupWeaponFlowers, Resources.weaponImageFlower, 
                locX, locY, false, 10);
    }
}
