package nl.tudelft.model.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;
import nl.tudelft.model.pickups.weapon.Weapon;

public class DoubleWeaponDecorator extends AbstractPlayerDecorator {

    private final Weapon doubleWeapon = new DoubleWeapon(new ResourcesWrapper(), 0, 0);

    public DoubleWeaponDecorator(Player wrappedObject) {
        super(wrappedObject);
    }
    
    @Override
    public Weapon getWeapon() {
        return doubleWeapon;
    }

}
