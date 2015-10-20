package nl.tudelft.model.pickups.weapon;

import nl.tudelft.controller.resources.ResourcesWrapper;

public class FlowerWeapon extends Weapon {

    public FlowerWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponFlowers(), resources.getWeaponImageFlower(), resources,
                locX, locY, false, 10);
    }
}
