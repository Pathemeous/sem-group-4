package nl.tudelft.model.shop.level;

import nl.tudelft.model.Level;
import nl.tudelft.model.player.Player;
import nl.tudelft.model.shop.ShopItem;

public abstract class LevelShopItem extends ShopItem {

    /**
     * You need to apply a levelItem to the next level,
     * which needs to be recorded.
     */
    protected Level nextLevel;

    /**
     * Method to apply a shop item.
     * @param buyer : The person who is buying the item.
     */
    public abstract void applyTo(Player buyer);

    /**
     * Default constructor for LevelShopItem.
     * @param price : The price of the item.
     */
    public LevelShopItem(int price, Level nxtLevel) {
        super(price);
        nextLevel = nxtLevel;
    }
}
