package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class RegularWeapon extends Weapon {

    public RegularWeapon(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponRegular(), resources.getWeaponImageRegular(),
                resources, locX, locY, false, 1);
    }

}
