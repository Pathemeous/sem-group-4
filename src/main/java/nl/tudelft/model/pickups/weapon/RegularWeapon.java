package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.Resources.Resources;

public class RegularWeapon extends Weapon {

    public RegularWeapon(float locX, float locY) {
        super(Resources.pickupWeaponRegular, Resources.weaponImageRegular, 
                locX, locY, false, 1);
    }

}
