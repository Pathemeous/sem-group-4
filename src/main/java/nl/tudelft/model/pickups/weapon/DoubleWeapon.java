package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class DoubleWeapon extends Weapon {

    public DoubleWeapon(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponDouble(), resources.getWeaponImageRegular(), resources,
                locX, locY, false, 2);
    }
}
