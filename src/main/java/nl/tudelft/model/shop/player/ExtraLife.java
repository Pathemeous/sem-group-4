package nl.tudelft.model.shop.player;

import nl.tudelft.model.Player;

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
     */
    public void applyTo(Player buyer) {
        buyer.setLives(buyer.getLives()+1);
    }
}
