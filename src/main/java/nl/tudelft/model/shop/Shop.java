package nl.tudelft.model.shop;

import java.util.LinkedList;
import java.util.Random;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.level.ExtraTime;
import nl.tudelft.model.shop.level.SlowGameSpeed;
import nl.tudelft.model.shop.player.ExtraLife;
import nl.tudelft.model.shop.player.ImprovedSpeed;


public class Shop {

    private LinkedList<ShopItem> inventory;
    private LinkedList<Player> players;
    private Game game;

    /**
     * Default constructor for a Shop.
     * @param pls : the players in the game.
     * @param nxtLevel : the next level in the game.
     */
    public Shop(LinkedList<Player> pls, Game game) {
        this.game = game;
        players = pls;
        inventory = new LinkedList<ShopItem>();
        inventory.add(new SlowGameSpeed(80, game.getLevelIt().next()));
        inventory.add(new ExtraTime(50, game.getLevelIt().next()));
        inventory.add(new ExtraLife(150));
        inventory.add(new ImprovedSpeed(100));
    }

    /**
     * Create a method to discount items.
     * @return res : the item that has the discount applied.
     */
    public ShopItem discount() {
        Random rand = new Random(inventory.size());
        ShopItem res = inventory.get(rand.nextInt());
        res.setPrice((int) (0.5 * res.getPrice()));
        return res;
    }
}
