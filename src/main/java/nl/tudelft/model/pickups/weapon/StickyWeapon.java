package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class StickyWeapon extends Weapon {

    public StickyWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponSticky(), resources.getWeaponImageSticky(), resources,
                locX, locY, true, 1);
    }
}
