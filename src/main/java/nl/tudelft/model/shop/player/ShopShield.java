package nl.tudelft.model.shop.player;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.model.player.Player;

public class ShopShield extends PlayerShopItem {
    private AbstractGame game;
    /**
     * Constructor for ShopShield.
     * @param price - The price of the item.
     */
    public ShopShield(int price, AbstractGame game) {
        super(price);
        this.game = game;
    }

    /**
     * Activate the shopshield item.
     * @param buyer : The person who is buying the item.
     * @return {@link Player} the player.
     */
    public Player applyTo(Player buyer) {
        Hit3ShieldPowerup powerup = new Hit3ShieldPowerup(new ResourcesWrapper(), 0, 0);
        powerup.activate(buyer);
        game.getCurLevel().toAdd(powerup);
        return buyer;
    }
}
