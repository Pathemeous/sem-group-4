package nl.tudelft.model.pickups.weapon;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class ShopWeapon extends Weapon {

    public ShopWeapon(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupWeaponDouble(), resources.getWeaponImageRegular(),
                locX, locY, true, 3);
    }
}
