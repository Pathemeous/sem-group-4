package nl.tudelft.model.weapon;

import nl.tudelft.semgroup4.Resources;

public class RegularWeapon extends Weapon {

    public RegularWeapon(float locX, float locY) {
        super(Resources.pickupWeaponRegular, Resources.weaponImageRegular, 
                locX, locY, false, 1);
    }

}
