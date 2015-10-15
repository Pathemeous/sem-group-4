package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class StickyWeapon extends Weapon {

    public StickyWeapon(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponSticky(), resources.getWeaponImageSticky(), resources,
                locX, locY, true, 1);
    }
}
