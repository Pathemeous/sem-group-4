package nl.tudelft.semgroup4;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.Shop;
import nl.tudelft.semgroup4.resources.Resources;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ShopState extends BasicGameState {
    private Shop shop;
    private LinkedList<Player> players;
    private Level nextLevel;
    private Image backGround;
    private final ResourcesWrapper resources = new ResourcesWrapper();

    public ShopState(LinkedList<Player> players, Level nextLevel) {
        this.players = players;
        this.nextLevel = nextLevel;
        backGround = resources.getShopBackGround();

    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        shop = new Shop(players, nextLevel);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.drawImage(resources.getShopBackGround(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getShopBackGround().getWidth(),
                resources.getShopBackGround().getHeight());

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 5;
    }

}
