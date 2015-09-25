package nl.tudelft.model.shop.player;

import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class ShopShield extends PlayerShopItem {

    /**
     * Constructor for ShopShield.
     * @param price - The price of the item.
     */
    public ShopShield(int price) {
        super(price);
    }

    /**
     * Activate the shopshield item.
     * @param buyer : The person who is buying the item.
     */
    public void applyTo(Player buyer) {
        Hit3ShieldPowerup powerup = new Hit3ShieldPowerup(new ResourcesWrapper(), 0, 0);
        powerup.activate(buyer);
    }
}