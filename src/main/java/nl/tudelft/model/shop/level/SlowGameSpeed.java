package nl.tudelft.model.shop.level;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.bubble.Bubble;


public class SlowGameSpeed extends LevelShopItem {

    /**
     * Constructor for SlowGameSpeed.
     * @param price - The price of the item.
     * @param nextLevel - The level it will be applied to.
     */
    public SlowGameSpeed(int price, Level nextLevel) {
        super(price, nextLevel);
    }

    /**
     * Activate the slow item.
     * @param buyer : The person who is buying the item.
     */
    public void applyTo(Player buyer) {
        LinkedList<Bubble> bubbles = nextLevel.getBubbles();
        for (Bubble x : bubbles) {
            x.setSlow(true);
        }
    }
}
