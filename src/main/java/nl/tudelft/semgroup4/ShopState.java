package nl.tudelft.semgroup4;

import java.awt.Font;
import java.util.LinkedList;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.Shop;
import nl.tudelft.model.shop.ShopItem;
import nl.tudelft.semgroup4.logger.Logger;
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
    private Input input;

    private TrueTypeFont ttf;
    private ResourcesWrapper wrapper;

    private Image backGround;
    private Image continueText;
    private Image shopText;
    private Image buyText;
    private Image player1On;
    private Image player1Off;
    private Image player2On;
    private Image player2Off;
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
    private ShopStateController controller;

    /**
     * Default constructor of ShopState.
     *
     * <p>
     * Here most of the member variables are set and the class is configured.
     * </p>
     *
     * @param wrapper
     *            used to get resources
     */
    public ShopState(ResourcesWrapper wrapper) {
        controller = new ShopStateController(this);
        Font font = new Font("Verdana", Font.BOLD, 30);
        ttf = wrapper.createFont(font, true);

        this.wrapper = wrapper;
        backGround = wrapper.getShopBackGround();
        continueText = wrapper.getContinueText();
        shopText = wrapper.getShopText();
        buyText = wrapper.getBuy();

        item1Slow = wrapper.getPickupUtilitySlow();
        item2Life = wrapper.getPickupUtilityLife();
        item3Speed = wrapper.getPickupPowerSpeedup();
        item4DoubleShot = wrapper.getPickupWeaponDouble();
        item5Shield = wrapper.getPickupPowerShield();
        item6Time = wrapper.getPickupUtilityTime();
        item7Special = wrapper.getSpecialWeapon();

        player1On = wrapper.getPlayer1On();
        player1Off = wrapper.getPlayer1Off();
        player2On = wrapper.getPlayer2On();
        player2Off = wrapper.getPlayer2Off();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        input = container.getInput();
        initContinueButton(container);
        initPlayerButtons(container);
        initBuyButton(container);
        initItems(container);

    }

    /**
     * The ShopState is initialized together with all other states. This method
     * should be called right before the state is brought forward, to setup the
     * shop for the current session.
     * 
     * @param game
     *            the game that the players play in.
     */
    public void setup(Game game) {
        shop = new Shop(game);

        selectedPlayer = shop.getGame().getPlayers()[0];
    }

    @Override
    public void render(GameContainer container, StateBasedGame game,
            Graphics graphics) throws SlickException {
        drawBackground(container, graphics);
        drawButtons(container, graphics);
        drawPlayerIndicators(container, graphics);
        drawPlayersMoney(container, graphics);

        drawItemSlow(container, graphics);
        drawItem2Life(container, graphics);
        drawItem3Speed(container, graphics);
        drawItem4Double(container, graphics);
        drawItem5Shield(container, graphics);
        drawItem6Time(container, graphics);
        drawItem7Special(container, graphics);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (continueArea.isMouseOver()) {
                // game.enterState(this.shop.getGame().getPlayers().length == 1
                // ? 1 : 2);
                controller.enterState(game);
            }
            checkSelectedItem();

        }

    }

    /**
     * Initializes all mouseOverAreas for the items.
     * 
     * @param container
     *            container in which to init the items in
     */
    private void initItems(GameContainer container) {
        item1AreaSlow = new MouseOverArea(container, item1Slow,
                container.getWidth() / 10 * 5, container.getHeight() / 2);
        item2AreaLife = new MouseOverArea(container, item2Life,
                container.getWidth() / 10 * 7, container.getHeight() / 2);
        item3AreaSpeed = new MouseOverArea(container, item3Speed,
                container.getWidth() / 10 * 9, container.getHeight() / 2);
        item4AreaDoubleShot = new MouseOverArea(container, item4DoubleShot,
                container.getWidth() / 10 * 5, container.getHeight() / 10 * 7);
        item5AreaShield = new MouseOverArea(container, item5Shield,
                container.getWidth() / 10 * 7, container.getHeight() / 10 * 7);
        item6AreaTime = new MouseOverArea(container, item6Time,
                container.getWidth() / 10 * 9, container.getHeight() / 10 * 7);
        item7AreaSpecial = new MouseOverArea(container, item7Special,
                container.getWidth() / 10 * 7, container.getHeight() / 10 * 3);

    }

    /**
     * initializes the buy button.
     * 
     * @param container
     *            container in which to init the button
     */
    private void initBuyButton(GameContainer container) {
        buyArea = new MouseOverArea(container, buyText,
                container.getWidth() / 10 * 7, container.getHeight()
                        - buyText.getHeight());
    }

    /**
     * intializes the player buttons.
     * 
     * @param container
     *            container in which to init the player buttons
     */
    private void initPlayerButtons(GameContainer container) {
        player1Area = new MouseOverArea(container, player1On,
                container.getWidth() / 10, container.getHeight() / 10 * 3);
        player2Area = new MouseOverArea(container, player2On,
                container.getWidth() / 10, container.getHeight() / 10 * 4);

    }

    /**
     * initializes the continue button.
     * 
     * @param container
     *            container in which to init the continue button
     */
    private void initContinueButton(GameContainer container) {
        System.out.println(continueText);
        System.out.println(container);
        continueArea = new MouseOverArea(container, continueText,
                container.getWidth() / 10 * 8, container.getHeight()
                        - continueText.getHeight());
    }

    /**
     * Draws the slow upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItemSlow(GameContainer container, Graphics graphics) {
        graphics.drawImage(item1Slow, container.getWidth() / 10 * 5,
                container.getHeight() / 2);
        final String item1SlowPrice = String.format("$ %d", shop.getInventory()
                .get(0).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 5
                        - (ttf.getWidth(item1SlowPrice) / 2),
                container.getHeight() / 2 + item1Slow.getHeight(),
                item1SlowPrice,
                selectedItem == shop.getInventory().get(0) ? Color.red
                        : Color.white);
    }

    /**
     * Draws the slow upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItem2Life(GameContainer container, Graphics graphics) {
        graphics.drawImage(item2Life, container.getWidth() / 10 * 7,
                container.getHeight() / 2);
        final String item2LifePrice = String.format("$ %d", shop.getInventory()
                .get(2).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 7
                        - (ttf.getWidth(item2LifePrice) / 2),
                container.getHeight() / 2 + item1Slow.getHeight(),
                item2LifePrice,
                selectedItem == shop.getInventory().get(2) ? Color.red
                        : Color.white);
    }

    /**
     * Draws the Speed upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItem3Speed(GameContainer container, Graphics graphics) {
        graphics.drawImage(item3Speed, container.getWidth() / 10 * 9,
                container.getHeight() / 2);
        final String item3SpeedPrice = String.format("$ %d", shop
                .getInventory().get(3).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 9
                        - (ttf.getWidth(item3SpeedPrice) / 2),
                container.getHeight() / 2 + item1Slow.getHeight(),
                item3SpeedPrice,
                selectedItem == shop.getInventory().get(3) ? Color.red
                        : Color.white);
    }

    /**
     * Draws the Double upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItem4Double(GameContainer container, Graphics graphics) {
        graphics.drawImage(item4DoubleShot, container.getWidth() / 10 * 5,
                container.getHeight() / 10 * 7);
        final String item4DoubleShotPrice = String.format("$ %d", shop
                .getInventory().get(4).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 5
                        - (ttf.getWidth(item4DoubleShotPrice) / 2),
                container.getHeight() / 10 * 7 + item1Slow.getHeight(),
                item4DoubleShotPrice, selectedItem == shop.getInventory()
                        .get(4) ? Color.red : Color.white);
    }

    /**
     * Draws the Shield upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItem5Shield(GameContainer container, Graphics graphics) {
        graphics.drawImage(item5Shield, container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 7);
        final String item5ShieldPrice = String.format("$ %d", shop
                .getInventory().get(6).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 7
                        - (ttf.getWidth(item5ShieldPrice) / 2),
                container.getHeight() / 10 * 7 + item1Slow.getHeight(),
                item5ShieldPrice,
                selectedItem == shop.getInventory().get(6) ? Color.red
                        : Color.white);
    }

    /**
     * Draws the TIme upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItem6Time(GameContainer container, Graphics graphics) {
        graphics.drawImage(item6Time, container.getWidth() / 10 * 9,
                container.getHeight() / 10 * 7);

        final String item6TimePrice = String.format("$ %d", shop.getInventory()
                .get(1).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 9
                        - (ttf.getWidth(item6TimePrice) / 2),
                container.getHeight() / 10 * 7 + item1Slow.getHeight(),
                item6TimePrice,
                selectedItem == shop.getInventory().get(1) ? Color.red
                        : Color.white);
    }

    /**
     * Draws the Special upgrade.
     * 
     * @param container
     *            container in which to draw the upgrade
     * @param graphics
     *            graphics used to draw the upgrade
     */
    private void drawItem7Special(GameContainer container, Graphics graphics) {
        graphics.drawImage(item7Special, container.getWidth() / 10 * 7,
                container.getHeight() / 10 * 3);
        final String item7SpecialPrice = String.format("$ %d", shop
                .getInventory().get(5).getPrice());
        ttf.drawString(
                container.getWidth() / 10 * 7
                        - (ttf.getWidth(item7SpecialPrice) / 2),
                container.getHeight() / 10 * 3 + item1Slow.getHeight(),
                item7SpecialPrice,
                selectedItem == shop.getInventory().get(5) ? Color.red
                        : Color.white);
    }

    /**
     * draws the money of each player.
     * 
     * @param container
     *            container in which to draw the money in
     * @param graphics
     *            graphics used to display the elements
     */
    private void drawPlayersMoney(GameContainer container, Graphics graphics) {
        final String playerOneMoney = String.format("$ %d", shop.getGame()
                .getPlayers()[0].getMoney());
        ttf.drawString(
                container.getWidth() / 10 - (ttf.getWidth(playerOneMoney)),
                (container.getHeight() / 10 * 3) + player1Off.getHeight() / 2
                        - (ttf.getHeight(playerOneMoney) / 2), playerOneMoney,
                Color.yellow);

        if (shop.getGame().getPlayers().length == 2) {
            final String playerTwoMoney = String.format("$ %d", shop.getGame()
                    .getPlayers()[1].getMoney());
            ttf.drawString(
                    container.getWidth() / 10 - (ttf.getWidth(playerTwoMoney)),
                    (container.getHeight() / 10 * 4) + player1Off.getHeight()
                            / 2 - (ttf.getHeight(playerTwoMoney) / 2),
                    playerTwoMoney, Color.yellow);
        }
    }

    /**
     * draws the indicators for each player to see which one is selected.
     * 
     * @param container
     *            container in which to draw the indicators in
     * @param graphics
     *            graphics use to draw the indicators
     */
    private void drawPlayerIndicators(GameContainer container, Graphics graphics) {
        if (selectedPlayer.isFirstPlayer()) {
            graphics.drawImage(player1On, container.getWidth() / 10,
                    container.getHeight() / 10 * 3);
            if (shop.getGame().getPlayers().length == 2) {
                graphics.drawImage(player2Off, container.getWidth() / 10,
                        container.getHeight() / 10 * 4);
            }
        } else {
            graphics.drawImage(player1Off, container.getWidth() / 10,
                    container.getHeight() / 10 * 3);
            graphics.drawImage(player2On, container.getWidth() / 10,
                    container.getHeight() / 10 * 4);
        }
    }

    /**
     * draws the buttons for continueing and buying an upgrade.
     * 
     * @param container
     *            container to draw the buttons in
     * @param graphics
     *            graphics used to draw the buttons
     */
    private void drawButtons(GameContainer container, Graphics graphics) {
        graphics.drawImage(continueText, container.getWidth() / 10 * 8,
                container.getHeight() - continueText.getHeight());
        graphics.drawImage(buyText, container.getWidth() / 10 * 7,
                container.getHeight() - buyText.getHeight());
        graphics.drawImage(shopText,
                container.getWidth() / 2 - shopText.getWidth() / 2,
                container.getHeight() / 10);
    }

    /**
     * Draws the backround.
     * 
     * @param container
     *            container to draw background in
     * @param graphics
     *            graphics used to draw background in
     */
    private void drawBackground(GameContainer container, Graphics graphics) {
        graphics.drawImage(wrapper.getShopBackGround(), 0, 0, container
                .getWidth(), container.getHeight(), 0, 0, wrapper
                .getShopBackGround().getWidth(), wrapper.getShopBackGround()
                .getHeight());

    }

    /**
     * checks selected player and item according to place clicked.
     */
    private void checkSelectedItem() {
        if (player1Area.isMouseOver()) {
            controller.selectPlayer(0);
        }
        if (player2Area.isMouseOver()) {
            controller.selectPlayer(1);
        }
        if (buyArea.isMouseOver()) {
            controller.applyUpgrade();
        }
        if (item1AreaSlow.isMouseOver()) {
            controller.selectItem(0);
        }
        if (item2AreaLife.isMouseOver()) {
            controller.selectItem(2);
        }
        if (item3AreaSpeed.isMouseOver()) {
            controller.selectItem(3);
        }
        if (item4AreaDoubleShot.isMouseOver()) {
            controller.selectItem(4);
        }
        if (item5AreaShield.isMouseOver()) {
            controller.selectItem(6);
        }
        if (item6AreaTime.isMouseOver()) {
            controller.selectItem(1);
        }
        if (item7AreaSpecial.isMouseOver()) {
            controller.selectItem(5);
        }

    }

    @Override
    public int getID() {
        return States.ShopState;
    }

    /**
     * Returns the shop.
     * 
     * @return the shop
     */
    protected Shop getShop() {
        return shop;
    }

    /**
     * Sets the Shop.
     * 
     * @param shop
     *            the shop to set
     */
    protected void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Returns the Selected Item.
     * 
     * @return the selectedItem
     */
    protected ShopItem getSelectedItem() {
        return selectedItem;
    }

    /**
     * Sets the selected Item.
     * 
     * @param selectedItem
     *            the selectedItem to set
     */
    protected void setSelectedItem(ShopItem selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * Returns the selected player.
     * 
     * @return the selectedPlayer
     */
    protected Player getSelectedPlayer() {
        return selectedPlayer;
    }

    /**
     * Sets the selected player.
     * 
     * @param selectedPlayer
     *            the selectedPlayer to set
     */
    protected void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    /**
     * Sets the wrapper.
     * 
     * @param wrapper
     *            the wrapper to set
     */
    protected void setWrapper(ResourcesWrapper wrapper) {
        this.wrapper = wrapper;
    }

    /**
     * Sets the input.
     * 
     * @param input
     *            the input to set
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * Sets all mouseOver areas, FOR TESTING PURPOSES ONLY.
     * 
     * @param mouseOverArea
     *            mouseover to set all mouseOvers to, useful for testing
     */
    protected void setMouseOvers(MouseOverArea mouseOverArea) {
        item1AreaSlow = mouseOverArea;
        item2AreaLife = mouseOverArea;
        item3AreaSpeed = mouseOverArea;
        item4AreaDoubleShot = mouseOverArea;
        item5AreaShield = mouseOverArea;
        item6AreaTime = mouseOverArea;
        item7AreaSpecial = mouseOverArea;

        player1Area = mouseOverArea;
        player2Area = mouseOverArea;

        buyArea = mouseOverArea;
        continueArea = mouseOverArea;
    }

    /**
     * Sets the controller.
     * 
     * @param controller
     *            the controller to set
     */
    protected void setController(ShopStateController controller) {
        this.controller = controller;
    }

}
