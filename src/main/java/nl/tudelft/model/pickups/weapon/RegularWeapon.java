package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class RegularWeapon extends Weapon {

    public RegularWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponRegular(), resources.getWeaponImageRegular(), 
                locX, locY, false, 1);
    }

}
