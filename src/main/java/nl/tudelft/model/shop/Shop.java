package nl.tudelft.model.shop;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.level.ExtraTime;
import nl.tudelft.model.shop.level.SlowGameSpeed;


public class Shop {

    private LinkedList<ShopItem> inventory;
    private LinkedList<Player> players;
    private Level nextLevel;

    /**
     * Default constructor for a Shop.
     * @param pls : the players in the game.
     * @param nxtLevel : the next level in the game.
     */
    public Shop(LinkedList<Player> pls, Level nxtLevel) {
        players = pls;
        nextLevel = nxtLevel;
        inventory = new LinkedList<ShopItem>();
        inventory.add(new SlowGameSpeed(100, nextLevel));
        inventory.add(new ExtraTime(150, nextLevel));
    }
}
