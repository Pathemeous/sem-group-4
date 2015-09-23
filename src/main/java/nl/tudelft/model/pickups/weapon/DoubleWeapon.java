package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.Resources.Resources;

public class DoubleWeapon extends Weapon {
    
    public DoubleWeapon(float locX, float locY) {
        super(Resources.pickupWeaponDouble, Resources.weaponImageRegular, 
                locX, locY, false, 2);
    }
}
