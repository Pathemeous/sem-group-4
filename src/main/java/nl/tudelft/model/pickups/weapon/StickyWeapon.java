package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.Resources.Resources;

public class StickyWeapon extends Weapon {
    
    public StickyWeapon(float locX, float locY) {
        super(Resources.pickupWeaponSticky, Resources.weaponImageSticky, 
                locX, locY, true, 1);
    }
}
