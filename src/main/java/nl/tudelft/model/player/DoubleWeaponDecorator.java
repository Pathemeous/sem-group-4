package nl.tudelft.model.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;

public class DoubleWeaponDecorator extends AbstractPlayerDecorator {

    private final AbstractWeapon doubleWeapon = new DoubleWeapon(new ResourcesWrapper(), 0, 0);

    public DoubleWeaponDecorator(Player wrappedObject) {
        super(wrappedObject);
        doubleWeapon.activate(wrappedObject);
    }
    
    @Override
    public AbstractWeapon getWeapon() {
        return doubleWeapon;
    }

}
