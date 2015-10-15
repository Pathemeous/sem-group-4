package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class FlowerWeapon extends Weapon {

    public FlowerWeapon(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponFlowers(), resources.getWeaponImageFlower(), resources,
                locX, locY, false, 10);
    }
}
