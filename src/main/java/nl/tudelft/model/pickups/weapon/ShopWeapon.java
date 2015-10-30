package nl.tudelft.model.pickups.weapon;

import nl.tudelft.controller.resources.ResourcesWrapper;

public class ShopWeapon extends AbstractWeapon {

    public ShopWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponDouble(), resources.getWeaponImageRegular(),
                resources, locX, locY, true, 3);
    }
}
