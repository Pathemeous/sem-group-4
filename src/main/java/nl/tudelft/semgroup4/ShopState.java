package nl.tudelft.semgroup4;

import java.util.LinkedList;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.Shop;
import nl.tudelft.semgroup4.resources.Resources;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ShopState extends BasicGameState {
    private Shop shop;
    private LinkedList<Player> players;
    private Game game;
    private final ResourcesWrapper resources = new ResourcesWrapper();  
    private Input input;

    private Image backGround;
    private Image continueText;
    private Image shopText;
    private Image player1;
    private Image player2;
    private Image item1;
    private Image item2;
    private Image item3;
    private Image item4;
    private Image item5;
    private Image item6;
    private Image item7;

    private MouseOverArea continueArea;
    private MouseOverArea player1Area;
    private MouseOverArea player2Area;
    private MouseOverArea item1Area;
    private MouseOverArea item2Area;
    private MouseOverArea item3Area;
    private MouseOverArea item4Area;
    private MouseOverArea item5Area;
    private MouseOverArea item6Area;
    private MouseOverArea item7Area;


    public ShopState() {        
        backGround = resources.getShopBackGround();
        continueText = resources.getContinueText();
        shopText = resources.getShopText();
        input = new Input(0);
        item1 = resources.getPickupUtilitySlow();
        item2 = resources.getPickupUtilityLife();
        item3 = resources.getPickupPowerSpeedup();
        item4 = resources.getPickupWeaponDouble();
        item5 = resources.getPickupPowerShield();
        item6 = resources.getPickupUtilityTime();
        


    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {  
        System.out.println("asdas");
        
        continueArea = new MouseOverArea(container, continueText,
                container.getWidth() / 10 * 8, 
                container.getHeight() - continueText.getHeight());
        
        

    }
    
    public void setup(LinkedList<Player> players, Game game) {
        shop = new Shop(players, game);
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.drawImage(resources.getShopBackGround(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getShopBackGround().getWidth(),
                resources.getShopBackGround().getHeight());
        g.drawImage(continueText, container.getWidth() / 10 * 8 ,
                container.getHeight() - continueText.getHeight());
        g.drawImage(shopText, container.getWidth() / 2 - shopText.getWidth() /2,
                container.getHeight() / 10);


        g.drawImage(item1, container.getWidth() / 10 * 5,
                container.getHeight()/ 2);
        g.drawImage(item2, container.getWidth() / 10 * 7,
                container.getHeight() / 2);
        g.drawImage(item3, container.getWidth() / 10 * 9,
                container.getHeight() / 2);
        g.drawImage(item4, container.getWidth() / 10 * 5,
                container.getHeight() / 10 * 7);
        g.drawImage(item5, container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 7);
        g.drawImage(item6, container.getWidth() / 10 * 9,
                container.getHeight() / 10 * 7);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if(continueArea.isMouseOver()) {
                this.game.nextLevel();
                System.out.println("asdasd");
            }
        }


    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 5;
    }

}
