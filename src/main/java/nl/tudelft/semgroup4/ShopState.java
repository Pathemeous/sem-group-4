package nl.tudelft.semgroup4;

import java.awt.Font;
import java.util.LinkedList;

import nl.tudelft.model.Game;
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
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ShopState extends BasicGameState {

    private Shop shop;
    private LinkedList<Player> players;
    private Game game;
    private final ResourcesWrapper resources = new ResourcesWrapper();  
    private Input input;

    private final TrueTypeFont ttf = new TrueTypeFont(new Font("Verdana", Font.BOLD, 30), true);

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

    private ShopItem selectedItem;
    private Player selectedPlayer;

    /**
     * Default constructor of ShopState.
     *
     * <p>Here most of the member variables are set and the class is
     * configured.</p>
     *
     */
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

        item1AreaSlow = new MouseOverArea(
                container,
                item1Slow,
                container.getWidth() / 10 * 5,
                container.getHeight() / 2);
        item2AreaLife = new MouseOverArea(
                container,
                item2Life,
                container.getWidth() / 10 * 7,
                container.getHeight() / 2);
        item3AreaSpeed = new MouseOverArea(
                container,
                item3Speed,
                container.getWidth() / 10 * 9,
                container.getHeight() / 2);
        item4AreaDoubleShot = new MouseOverArea(
                container,
                item4DoubleShot,
                container.getWidth() / 10 * 5,
                container.getHeight() / 10 * 7);
        item5AreaShield = new MouseOverArea(
                container,
                item5Shield,
                container.getWidth() / 10 * 7,
                container.getHeight() /  10 * 7);
        item6AreaTime = new MouseOverArea(
                container,
                item6Time,
                container.getWidth() / 10 * 9,
                container.getHeight() / 10 * 7);
        item7AreaSpecial = new MouseOverArea(
                container,
                item7Special,
                container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 3);

    }

    /**
     * The ShopState is initialized together with all other states.
     * This method should be called right before the state
     * is brought forward, to setup the shop for the current session.
     * @param game the game that the players play in.
     */
    public void setup(Game game) {
        this.players = game.getPlayers();
        shop = new Shop(game);

        this.game = game;
        selectedPlayer = players.getFirst();
        selectedPlayer.setMoney(1000);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {
        graphics.drawImage(resources.getShopBackGround(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getShopBackGround().getWidth(),
                resources.getShopBackGround().getHeight());
        graphics.drawImage(continueText, container.getWidth() / 10 * 8,
                container.getHeight() - continueText.getHeight());
        graphics.drawImage(buyText, container.getWidth() / 10 * 7,
                container.getHeight() - buyText.getHeight());
        graphics.drawImage(shopText, container.getWidth() / 2 - shopText.getWidth() / 2,
                container.getHeight() / 10);


        graphics.drawImage(player1, container.getWidth() / 10,
                container.getHeight() / 10 * 3);   
        if (players.size() == 2) {
            graphics.drawImage(player2, container.getWidth() / 10,
                    container.getHeight() / 10 * 4);
        }

        graphics.drawImage(item1Slow,
                container.getWidth() / 10 * 5,
                container.getHeight() / 2);
        final String item1SlowPrice = String.format("$ %d", shop.getInventory().get(0).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 5 - (ttf.getWidth(item1SlowPrice) / 2),
                container.getHeight() / 2 + item1Slow.getHeight(),
                item1SlowPrice,
                selectedItem == shop.getInventory().get(0) ? Color.red : Color.white);
        graphics.drawImage(item2Life,
                container.getWidth() / 10 * 7,
                container.getHeight() / 2);
        final String item2LifePrice = String.format("$ %d", shop.getInventory().get(2).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 7 - (ttf.getWidth(item2LifePrice) / 2),
                container.getHeight() / 2 + item1Slow.getHeight(),
                item2LifePrice,
                selectedItem == shop.getInventory().get(2) ? Color.red : Color.white);
        graphics.drawImage(item3Speed,
                container.getWidth() / 10 * 9,
                container.getHeight() / 2);
        final String item3SpeedPrice = String.format("$ %d", shop.getInventory().get(3).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 9 - (ttf.getWidth(item3SpeedPrice) / 2),
                container.getHeight() / 2 + item1Slow.getHeight(),
                item3SpeedPrice,
                selectedItem == shop.getInventory().get(3) ? Color.red : Color.white);
        graphics.drawImage(item4DoubleShot,
                container.getWidth() / 10 * 5,
                container.getHeight() / 10 * 7);
        final String item4DoubleShotPrice = String.format("$ %d", shop.getInventory().get(4).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 5 - (ttf.getWidth(item4DoubleShotPrice) / 2),
                container.getHeight() / 10 * 7 + item1Slow.getHeight(),
                item4DoubleShotPrice,
                selectedItem == shop.getInventory().get(4) ? Color.red : Color.white);
        graphics.drawImage(item5Shield,
                container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 7);
        final String item5ShieldPrice = String.format("$ %d", shop.getInventory().get(6).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 7 - (ttf.getWidth(item5ShieldPrice) / 2),
                container.getHeight() / 10 * 7 + item1Slow.getHeight(),
                item5ShieldPrice,
                selectedItem == shop.getInventory().get(6) ? Color.red : Color.white);
        graphics.drawImage(item6Time,
                container.getWidth() / 10 * 9,
                container.getHeight() / 10 * 7);
        final String item6TimePrice = String.format("$ %d", shop.getInventory().get(1).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 9 - (ttf.getWidth(item6TimePrice) / 2),
                container.getHeight() / 10 * 7 + item1Slow.getHeight(),
                item6TimePrice,
                selectedItem == shop.getInventory().get(1) ? Color.red : Color.white);
        graphics.drawImage(item7Special,
                container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 3);
        final String item7SpecialPrice = String.format("$ %d", shop.getInventory().get(5).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 7 - (ttf.getWidth(item7SpecialPrice) / 2),
                container.getHeight() / 10 * 3 + item1Slow.getHeight(),
                item7SpecialPrice,
                selectedItem == shop.getInventory().get(5) ? Color.red : Color.white);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (continueArea.isMouseOver()) {
                game.enterState(1);
            } 
            if (player1Area.isMouseOver()) {
                selectedPlayer = players.getFirst();
            }
            if (player2Area.isMouseOver()) {
                selectedPlayer = players.get(1);
            }
            if (buyArea.isMouseOver()
                    && selectedItem != null
                    && selectedItem.getPrice() < selectedPlayer.getMoney()) {
                if (selectedPlayer.isFirstPlayer()) {
                    players.getFirst().setMoney(
                            players.getFirst().getMoney() - selectedItem.getPrice());
                    selectedItem.applyTo(selectedPlayer);
                } else {
                    players.get(1).setMoney(players.get(1).getMoney() - selectedItem.getPrice());
                }
                System.out.println("bought " + selectedItem.getClass().toString());
                System.out.println("players money" + players.getFirst().getMoney());
            }
            if (item1AreaSlow.isMouseOver()) {
                selectedItem = shop.getInventory().get(0);
                System.out.println(selectedItem.getClass().toString());
            }
            if (item2AreaLife.isMouseOver()) {
                selectedItem = shop.getInventory().get(2);
                System.out.println(selectedItem.getClass().toString());
            }
            if (item3AreaSpeed.isMouseOver()) {
                selectedItem = shop.getInventory().get(3);
                System.out.println(selectedItem.getClass().toString());
            }
            if (item4AreaDoubleShot.isMouseOver()) {
                selectedItem = shop.getInventory().get(4);
                System.out.println(selectedItem.getClass().toString());
            }
            if (item5AreaShield.isMouseOver()) {
                selectedItem = shop.getInventory().get(6);
                System.out.println(selectedItem.getClass().toString());
            }
            if (item6AreaTime.isMouseOver()) {
                selectedItem = shop.getInventory().get(1);
                System.out.println(selectedItem.getClass().toString());
            }
            if (item7AreaSpecial.isMouseOver()) {
                selectedItem = shop.getInventory().get(5);
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
