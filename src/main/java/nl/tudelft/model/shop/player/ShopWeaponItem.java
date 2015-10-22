package nl.tudelft.model.shop.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.weapon.ShopWeapon;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.model.player.Player;

public class ShopWeaponItem extends PlayerShopItem {

    /**
     * Constructor for ShopWeaponItem.
     * @param price - The price of the item.
     */
    public ShopWeaponItem(int price) {
        super(price);
    }

    /**
     * Activate the weapon item.
     * @param buyer : The person who is buying the item.
     */
    public void applyTo(Player buyer) {
        Weapon weapon = new ShopWeapon(new ResourcesWrapper(), 0, 0);
        buyer.setWeapon(weapon);
        buyer.setShopWeapon(true);
        weapon.activate(buyer);
    }
}
