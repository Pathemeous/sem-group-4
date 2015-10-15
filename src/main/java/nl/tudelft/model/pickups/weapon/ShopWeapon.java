package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class ShopWeapon extends Weapon {

    public ShopWeapon(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponDouble(), resources.getWeaponImageRegular(),
                resources, locX, locY, true, 3);
    }
}
