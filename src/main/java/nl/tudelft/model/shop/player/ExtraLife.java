package nl.tudelft.model.shop.player;

import nl.tudelft.model.player.ExtraLifeDecorator;
import nl.tudelft.model.player.Player;

public class ExtraLife extends PlayerShopItem {

    /**
     * Constructor for ExtraLife.
     * @param price - The price of the item.
     */
    public ExtraLife(int price) {
        super(price);
    }

    /**
     * Activate the slow item.
     * @param buyer : The person who is buying the item.
     * @return {@link Player} the player.
     */
    public Player applyTo(Player buyer) {
        return new ExtraLifeDecorator(buyer);
//        buyer.setLives(buyer.getLives() + 1);
    }
}
