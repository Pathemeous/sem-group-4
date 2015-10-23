package nl.tudelft.model.shop;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.player.Player;
import nl.tudelft.model.shop.level.ExtraTime;
import nl.tudelft.model.shop.level.SlowGameSpeed;
import nl.tudelft.model.shop.player.DoubleWeaponItem;
import nl.tudelft.model.shop.player.ExtraLife;
import nl.tudelft.model.shop.player.ImprovedSpeed;
import nl.tudelft.model.shop.player.ShopShield;
import nl.tudelft.model.shop.player.ShopWeaponItem;


public class Shop {

    private LinkedList<ShopItem> inventory;
    private Game game;

    /**
     * Default constructor for a Shop.
     * @param game the game that the shop lives in.
     */
    public Shop(Game game) {
        this.game = game;
        inventory = new LinkedList<>();
        inventory.add(new SlowGameSpeed(80, game.getCurLevel()));
        inventory.add(new ExtraTime(50, game.getCurLevel()));
        inventory.add(new ExtraLife(150));
        inventory.add(new ImprovedSpeed(100));
        inventory.add(new DoubleWeaponItem(70));
        inventory.add(new ShopWeaponItem(100));
        inventory.add(new ShopShield(100, game));
    }

    /**
     * Create a method to discount items.
     * @return res : the item that has the discount applied.
     */
    public ShopItem discount() {
        Random rand = new Random();
        ShopItem res = inventory.get(rand.nextInt(inventory.size()));
        res.setPrice((int) (0.5 * res.getPrice()));
        return res;
    }

    /**
     * Shop is associated with a Game object.
     * This method can be used to retrieve that object.
     *
     * @return the associated Game object.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * returns the inventory of the shop.
     * @return the inventory
     */
    public LinkedList<ShopItem> getInventory() {
        return inventory;
    }


}
