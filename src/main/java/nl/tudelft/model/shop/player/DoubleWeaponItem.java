package nl.tudelft.model.shop.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;
import nl.tudelft.model.player.DoubleWeaponDecorator;
import nl.tudelft.model.player.Player;

public class DoubleWeaponItem extends PlayerShopItem {

    /**
     * Constructor for ShopWeaponItem.
     * 
     * @param price
     *            - The price of the item.
     */
    public DoubleWeaponItem(int price) {
        super(price);
    }

    /**
     * Activate the weapon item.
     * 
     * @param buyer
     *            : The person who is buying the item.
     * @return {@link Player} the player.
     */
    public Player applyTo(Player buyer) {
        // Weapon weapon = new DoubleWeapon(new ResourcesWrapper(), 0, 0);
        // buyer.setWeapon(weapon);
        // buyer.setShopWeapon(true);
        // weapon.activate(buyer);
        return new DoubleWeaponDecorator(buyer);
    }
}
