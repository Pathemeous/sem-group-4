package nl.tudelft.model.shop.player;

import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class DoubleWeaponItem extends PlayerShopItem {

    /**
     * Constructor for ShopWeaponItem.
     * @param price - The price of the item.
     */
    public DoubleWeaponItem(int price) {
        super(price);
    }

    /**
     * Activate the weapon item.
     * @param buyer : The person who is buying the item.
     */
    public void applyTo(Player buyer) {
        buyer.setWeapon(new DoubleWeapon(new ResourcesWrapper(), 0, 0));
        buyer.setShopWeapon(true);
    }
}