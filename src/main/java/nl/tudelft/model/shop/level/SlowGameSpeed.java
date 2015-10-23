package nl.tudelft.model.shop.level;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.player.Player;

public class SlowGameSpeed extends LevelShopItem {

    /**
     * Constructor for SlowGameSpeed.
     * 
     * @param price
     *            - The price of the item.
     * @param nextLevel
     *            - The level it will be applied to.
     */
    public SlowGameSpeed(int price, Level nextLevel) {
        super(price, nextLevel);
    }

    /**
     * Activate the slow item.
     * 
     * @param buyer
     *            : The person who is buying the item.
     * @return {@link Player} the player.
     */
    public Player applyTo(Player buyer) {
        LinkedList<AbstractBubble> bubbles = nextLevel.getBubbles();
        for (AbstractBubble x : bubbles) {
            x.setSlow(true);
        }
        nextLevel.setShopSlow(true);
        return buyer;
    }
}
