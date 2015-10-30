package nl.tudelft.model.pickups.weapon;

import nl.tudelft.controller.resources.ResourcesWrapper;

public class DoubleWeapon extends AbstractWeapon {

    public DoubleWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponDouble(), resources.getWeaponImageRegular(), resources,
                locX, locY, false, 2);
    }
}
