package nl.tudelft.model.pickups.weapon;

import nl.tudelft.controller.resources.ResourcesWrapper;

public class StickyWeapon extends AbstractWeapon {

    public StickyWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponSticky(), resources.getWeaponImageSticky(), resources,
                locX, locY, true, 1);
    }
}
