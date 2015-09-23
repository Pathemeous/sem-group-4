package nl.tudelft.model.shop;

import nl.tudelft.model.Player;

public abstract class ShopItem {

    /**
     * Every shop item needs a price.
     */
    private int price;

    /**
     * Method to apply a shop item.
     * @param buyer : The person who is buying the item.
     */
    public abstract void applyTo(Player buyer);

    /**
     * Default constructor for ShopItem.
     * @param cost : The cost of an item.
     */
    public ShopItem(int cost) {
        price = cost;
    }
}
