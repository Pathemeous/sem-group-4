package nl.tudelft.model.shop.player;

import nl.tudelft.model.player.ImprovedSpeedDecorator;
import nl.tudelft.model.player.Player;

public class ImprovedSpeed extends PlayerShopItem {

    /**
     * Constructor for ImprovedSpeed.
     * @param price - The price of the item.
     */
    public ImprovedSpeed(int price) {
        super(price);
    }

    /**
     * Activate the speed item.
     * @param buyer : The person who is buying the item.
     * @return {@link Player} the player.
     */
    public Player applyTo(Player buyer) {
//        buyer.applySpeedup();
//        buyer.setShopSpeed(true);
        return new ImprovedSpeedDecorator(buyer);
    }
}
