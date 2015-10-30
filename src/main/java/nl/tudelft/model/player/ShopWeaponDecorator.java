package nl.tudelft.model.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;
import nl.tudelft.model.pickups.weapon.ShopWeapon;

public class ShopWeaponDecorator extends AbstractPlayerDecorator {
    
    private final AbstractWeapon shopWeapon = new ShopWeapon(new ResourcesWrapper(), 0, 0);

    public ShopWeaponDecorator(Player wrappedObject) {
        super(wrappedObject);
        shopWeapon.activate(wrappedObject);
    }
    
    @Override
    public AbstractWeapon getWeapon() {
        return shopWeapon;
    }

}
