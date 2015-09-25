package nl.tudelft.model.shop.player;

import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.weapon.ShopWeapon;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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
        buyer.setWeapon(new ShopWeapon(new ResourcesWrapper(), 0, 0));
        buyer.setShopWeapon(true);
    }
}