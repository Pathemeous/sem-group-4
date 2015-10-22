package nl.tudelft.model.shop.player;

import nl.tudelft.model.player.Player;
import nl.tudelft.model.shop.ShopItem;

public abstract class PlayerShopItem extends ShopItem {

    /**
     * Method to apply a shop item.
     * @param buyer : The person who is buying the item.
     */
    public abstract void applyTo(Player buyer);

    /**
     * Default constructor for PlayerShopItem.
     * @param price : The price of the item;
     */
    public PlayerShopItem(int price) {
        super(price);
    }
}
