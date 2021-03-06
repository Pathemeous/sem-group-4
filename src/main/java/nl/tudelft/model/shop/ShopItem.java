package nl.tudelft.model.shop;

import nl.tudelft.model.player.Player;

public abstract class ShopItem {

    /**
     * Every shop item needs a price.
     */
    private int price;

    /**
     * Method to apply a shop item.
     * @param buyer : The person who is buying the item.
     */
    public abstract Player applyTo(Player buyer);

    /**
     * Default constructor for ShopItem.
     * @param cost : The cost of an item.
     */
    public ShopItem(int cost) {
        price = cost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int cost) {
        price = cost;
    }
}
