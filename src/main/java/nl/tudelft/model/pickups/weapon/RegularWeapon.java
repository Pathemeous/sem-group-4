package nl.tudelft.model.pickups.weapon;

import nl.tudelft.controller.resources.ResourcesWrapper;

public class RegularWeapon extends Weapon {

    public RegularWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponRegular(), resources.getWeaponImageRegular(),
                resources, locX, locY, false, 1);
    }

}
