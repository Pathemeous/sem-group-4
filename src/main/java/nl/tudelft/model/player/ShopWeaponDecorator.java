package nl.tudelft.model.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.weapon.ShopWeapon;
import nl.tudelft.model.pickups.weapon.Weapon;

public class ShopWeaponDecorator extends AbstractPlayerDecorator {
    
    private final Weapon shopWeapon = new ShopWeapon(new ResourcesWrapper(), 0, 0);

    public ShopWeaponDecorator(Player wrappedObject) {
        super(wrappedObject);
        shopWeapon.activate(wrappedObject);
    }
    
    @Override
    public Weapon getWeapon() {
        return shopWeapon;
    }

}
