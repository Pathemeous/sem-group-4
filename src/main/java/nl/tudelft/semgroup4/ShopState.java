package nl.tudelft.semgroup4;

import java.util.LinkedList;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.Shop;
import nl.tudelft.model.shop.ShopItem;
import nl.tudelft.model.shop.level.ExtraTime;
import nl.tudelft.model.shop.level.SlowGameSpeed;
import nl.tudelft.model.shop.player.DoubleWeaponItem;
import nl.tudelft.model.shop.player.ExtraLife;
import nl.tudelft.model.shop.player.ImprovedSpeed;
import nl.tudelft.model.shop.player.ShopShield;
import nl.tudelft.model.shop.player.ShopWeaponItem;
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
    private Image buyText;
    private Image player1;
    private Image player2;
    private Image item1Slow;
    private Image item2Life;
    private Image item3Speed;
    private Image item4DoubleShot;
    private Image item5Shield;
    private Image item6Time;
    private Image item7Special;

    private MouseOverArea continueArea;
    private MouseOverArea player1Area;
    private MouseOverArea player2Area;

    private MouseOverArea item1AreaSlow;
    private MouseOverArea item2AreaLife;
    private MouseOverArea item3AreaSpeed;
    private MouseOverArea item4AreaDoubleShot;
    private MouseOverArea item5AreaShield;
    private MouseOverArea item6AreaTime;
    private MouseOverArea item7AreaSpecial;
    private MouseOverArea buyArea;

    private boolean player1Selected = true;
    private ShopItem selectedItem;
    private Player selectedPlayer;



    public ShopState() {        
        backGround = resources.getShopBackGround();
        continueText = resources.getContinueText();
        shopText = resources.getShopText();
        buyText = resources.getBuy();

        item1Slow = resources.getPickupUtilitySlow();
        item2Life = resources.getPickupUtilityLife();
        item3Speed = resources.getPickupPowerSpeedup();
        item4DoubleShot = resources.getPickupWeaponDouble();
        item5Shield = resources.getPickupPowerShield();
        item6Time = resources.getPickupUtilityTime();
        item7Special = resources.getSpecialWeapon();

        player1 = resources.getPlayer1();
        player2 = resources.getPlayer2();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException { 
        input = container.getInput();
        continueArea = new MouseOverArea(container, continueText,
                container.getWidth() / 10 * 8, 
                container.getHeight() - continueText.getHeight()); 

        player1Area = new MouseOverArea(container, player1, container.getWidth() / 10,
                container.getHeight() / 10 * 3);
        player2Area = new MouseOverArea(container, player2,container.getWidth() / 10,
                container.getHeight() / 10 * 4);        
        buyArea = new MouseOverArea(container, buyText, container.getWidth() / 10 * 7,
                container.getHeight() - buyText.getHeight());

        item1AreaSlow = new MouseOverArea(container, item1Slow, container.getWidth() / 10 * 5,
                container.getHeight() / 2);
        item2AreaLife= new MouseOverArea(container, item2Life, container.getWidth() / 10 * 7,
                container.getHeight() / 2);
        item3AreaSpeed= new MouseOverArea(container, item3Speed, container.getWidth() / 10 * 9,
                container.getHeight() / 2);
        item4AreaDoubleShot= new MouseOverArea(container, item4DoubleShot, container.getWidth() / 10 * 5,
                container.getHeight() / 10 * 7);
        item5AreaShield= new MouseOverArea(container, item5Shield, container.getWidth() / 10 * 7,
                container.getHeight() /  10 * 7);
        item6AreaTime= new MouseOverArea(container, item6Time, container.getWidth() / 10 * 9,
                container.getHeight() / 10 * 7);
        item7AreaSpecial= new MouseOverArea(container, item7Special, container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 3);

    }

    public void setup(LinkedList<Player> players, Game game) {
        shop = new Shop(players, game);
        this.players = players;
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
        g.drawImage(buyText, container.getWidth() / 10 * 7,
                container.getHeight() - buyText.getHeight());
        g.drawImage(shopText, container.getWidth() / 2 - shopText.getWidth() /2,
                container.getHeight() / 10);


        g.drawImage(player1, container.getWidth() / 10,
                container.getHeight() / 10 * 3);   
        if(players.size() == 2) {
            g.drawImage(player2, container.getWidth() / 10,
                    container.getHeight() / 10 * 4);
        }


        g.drawImage(item1Slow, container.getWidth() / 10 * 5,
                container.getHeight()/ 2);
        g.drawImage(item2Life, container.getWidth() / 10 * 7,
                container.getHeight() / 2);
        g.drawImage(item3Speed, container.getWidth() / 10 * 9,
                container.getHeight() / 2);
        g.drawImage(item4DoubleShot, container.getWidth() / 10 * 5,
                container.getHeight() / 10 * 7);
        g.drawImage(item5Shield, container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 7);
        g.drawImage(item6Time, container.getWidth() / 10 * 9,
                container.getHeight() / 10 * 7);
        g.drawImage(item7Special, container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 3);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if(continueArea.isMouseOver()) {               
                game.enterState(1);
            } 
            if(player1Area.isMouseOver()) {
                selectedPlayer = players.getFirst();
            }
            if(player2Area.isMouseOver()) {
                selectedPlayer = players.get(1);
            }
            if(buyArea.isMouseOver()) {
                if(player1Selected) {
                    if(selectedItem != null) {
                        if(selectedItem.getPrice() < selectedPlayer.getMoney()) {
                            players.getFirst().setMoney(players.getFirst().getMoney() - selectedItem.getPrice());
                            System.out.println("bought " + selectedItem.getClass().toString());
                            System.out.println("players money" + players.getFirst().getMoney());
                        }
                    }
                }
            }
            if(item1AreaSlow.isMouseOver()) {
                selectedItem = new SlowGameSpeed(100, this.game.getCurLevel());
                System.out.println(selectedItem.getClass().toString());
            }
            if(item2AreaLife.isMouseOver()) {
                selectedItem = new ExtraLife(100);
                System.out.println(selectedItem.getClass().toString());
            }
            if(item3AreaSpeed.isMouseOver()) {
                selectedItem = new ImprovedSpeed(100);
                System.out.println(selectedItem.getClass().toString());
            }
            if(item4AreaDoubleShot.isMouseOver()) {
                selectedItem = new DoubleWeaponItem(100);
                System.out.println(selectedItem.getClass().toString());
            }
            if(item5AreaShield.isMouseOver()) {
                selectedItem = new ShopShield(100, this.game);
                System.out.println(selectedItem.getClass().toString());
            }
            if(item6AreaTime.isMouseOver()) {
                selectedItem = new ExtraTime(100, this.game.getCurLevel());
                System.out.println(selectedItem.getClass().toString());
            }
            if(item7AreaSpecial.isMouseOver()) {
                selectedItem = new ShopWeaponItem(100);
                System.out.println(selectedItem.getClass().toString());
            }

        }


    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 5;
    }

}
