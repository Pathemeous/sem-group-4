package nl.tudelft.model.shop.level;

import nl.tudelft.model.Level;
import nl.tudelft.model.player.Player;

public class ExtraTime extends LevelShopItem {

    /**
     * Constructor for ExtraTime.
     * @param price - The price of the item.
     * @param nextLevel - The level it will be applied to.
     */
    public ExtraTime(int price, Level nextLevel) {
        super(price, nextLevel);
    }

    /**
     * Activate the extra time item.
     * @param buyer : The person who is buying the item.
     */
    public void applyTo(Player buyer) {
        nextLevel.setMaxTime(nextLevel.getMaxTime() + 20);
        nextLevel.setTime(nextLevel.getMaxTime() + 20);
    }
}
