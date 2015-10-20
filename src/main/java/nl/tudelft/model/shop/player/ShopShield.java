package nl.tudelft.model.shop.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;

public class ShopShield extends PlayerShopItem {
    private Game game;
    /**
     * Constructor for ShopShield.
     * @param price - The price of the item.
     */
    public ShopShield(int price, Game game) {
        super(price);
        this.game = game;
    }

    /**
     * Activate the shopshield item.
     * @param buyer : The person who is buying the item.
     */
    public void applyTo(Player buyer) {
        Hit3ShieldPowerup powerup = new Hit3ShieldPowerup(new ResourcesWrapper(), 0, 0);
        powerup.activate(buyer);
        game.getCurLevel().toAdd(powerup);
    }
}
