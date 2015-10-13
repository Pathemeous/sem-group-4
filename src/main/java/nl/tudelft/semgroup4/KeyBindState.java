package nl.tudelft.semgroup4;

import java.awt.Font;
import java.io.IOException;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import nl.tudelft.semgroup4.util.KeyBindHelper;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class KeyBindState extends BasicGameState {

    private MouseOverArea backButton;
    private MouseOverArea saveButton;
    private MouseOverArea defButton;
    private MouseOverArea player1LeftButton;
    private MouseOverArea player1RightButton;
    private MouseOverArea player1ShootButton;
    private MouseOverArea player2LeftButton;
    private MouseOverArea player2RightButton;
    private MouseOverArea player2ShootButton;

    private Image backImage;
    private Image saveImage;
    private Image defImage;
    private Image player1LeftImage;
    private Image player1RightImage;
    private Image player1ShootImage;
    private Image player2LeftImage;
    private Image player2RightImage;
    private Image player2ShootImage;

    private Shape backShape;
    private Shape saveShape;
    private Shape defShape;
    private Shape player1LeftShape;
    private Shape player1RightShape;
    private Shape player1ShootShape;
    private Shape player2LeftShape;
    private Shape player2RightShape;
    private Shape player2ShootShape;

    private String back = "Back";
    private String save = "Save";
    private String defaults = "Defaults";
    private String instructions = "To set a key, first press a key on your keyboard."
            + "\nThen click on the key you want to replace.";
    private Input input;
    private final ResourcesWrapper resources = new ResourcesWrapper();
    private static final Font font = new Font("Calibri", Font.BOLD, 46);
    private static final Font smallFont = new Font("Calibri", Font.BOLD, 30);
    private static final TrueTypeFont typeFont = new TrueTypeFont(font, true);
    private static final TrueTypeFont typeFontSmall = new TrueTypeFont(smallFont, true);
    private static final float screenCoordXName = 60;
    private static final float screenCoordXValue = 650;
    private static float screenCoordY;

    @Override
    public void init(GameContainer container, StateBasedGame mainApp)
            throws SlickException {

        backImage = new Image(typeFont.getWidth(back), typeFont.getHeight());
        backShape = new Rectangle(container.getWidth() / 10,
                container.getHeight() / 10 * 9,
                typeFont.getWidth(back), typeFont.getHeight());
        backButton = new MouseOverArea(container, backImage, backShape);

        defImage = new Image(typeFont.getWidth(back), typeFont.getHeight());
        defShape = new Rectangle(60, 430,
                typeFont.getWidth(defaults), typeFont.getHeight());
        defButton = new MouseOverArea(container, defImage, defShape);

        saveImage = new Image(typeFont.getWidth(save), typeFont.getHeight());
        saveShape = new Rectangle((container.getWidth()) - (container.getWidth() / 10),
                container.getHeight() / 10 * 9,
                typeFont.getWidth(save), typeFont.getHeight());
        saveButton = new MouseOverArea(container, saveImage, saveShape);

        player1LeftImage =
                new Image(typeFont.getWidth(KeyBindHelper.PLAYER1_LEFT_KEY),
                        typeFont.getHeight());
        player1LeftShape =
                new Rectangle(60, 80,
                        typeFont.getWidth(KeyBindHelper.PLAYER1_LEFT_KEY),
                        typeFont.getHeight());
        player1LeftButton = new MouseOverArea(container, player1LeftImage, player1LeftShape);

        player1RightImage =
                new Image(typeFont.getWidth(KeyBindHelper.PLAYER1_RIGHT_KEY),
                        typeFont.getHeight());
        player1RightShape =
                new Rectangle(60, 130,
                        typeFont.getWidth(KeyBindHelper.PLAYER1_RIGHT_KEY),
                        typeFont.getHeight());
        player1RightButton = new MouseOverArea(container, player1RightImage, player1RightShape);

        player1ShootImage =
                new Image(typeFont.getWidth(KeyBindHelper.PLAYER1_SHOOT_KEY),
                        typeFont.getHeight());
        player1ShootShape =
                new Rectangle(60, 180,
                        typeFont.getWidth(KeyBindHelper.PLAYER1_SHOOT_KEY),
                        typeFont.getHeight());
        player1ShootButton  = new MouseOverArea(container, player1ShootImage, player1ShootShape);

        player2LeftImage =
                new Image(typeFont.getWidth(KeyBindHelper.PLAYER2_LEFT_KEY),
                        typeFont.getHeight());
        player2LeftShape =
                new Rectangle(60, 230,
                        typeFont.getWidth(KeyBindHelper.PLAYER2_LEFT_KEY),
                        typeFont.getHeight());
        player2LeftButton = new MouseOverArea(container, player2LeftImage, player2LeftShape);

        player2RightImage =
                new Image(typeFont.getWidth(KeyBindHelper.PLAYER2_RIGHT_KEY),
                        typeFont.getHeight());
        player2RightShape =
                new Rectangle(60, 280,
                        typeFont.getWidth(KeyBindHelper.PLAYER2_RIGHT_KEY),
                        typeFont.getHeight());
        player2RightButton = new MouseOverArea(container, player2RightImage, player2RightShape);

        player2ShootImage =
                new Image(typeFont.getWidth(KeyBindHelper.PLAYER2_SHOOT_KEY),
                        typeFont.getHeight());
        player2ShootShape =
                new Rectangle(60, 330,
                        typeFont.getWidth(KeyBindHelper.PLAYER2_SHOOT_KEY),
                        typeFont.getHeight());
        player2ShootButton  = new MouseOverArea(container, player2ShootImage, player2ShootShape);

        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {

        input.disableKeyRepeat();
        screenCoordY = 80;

        typeFontSmall.drawString(screenCoordXName, 500,
                instructions,
                Color.green);

        typeFont.drawString(container.getWidth() / 10,
                container.getHeight() / 10 * 9,
                back,
                Color.yellow);
        typeFont.drawString((container.getWidth()) - (container.getWidth() / 10),
                container.getHeight() / 10 * 9,
                save,
                Color.yellow);
        
        typeFont.drawString(screenCoordXName, screenCoordY, KeyBindHelper.PLAYER1_LEFT_KEY, 
                Color.yellow);
        typeFont.drawString(screenCoordXValue, screenCoordY,
                Keyboard.getKeyName(Settings.getPlayer1LeftValue()),
                Color.yellow);
        screenCoordY += 50;
        
        typeFont.drawString(screenCoordXName, screenCoordY, KeyBindHelper.PLAYER1_RIGHT_KEY, 
                Color.yellow);
        typeFont.drawString(screenCoordXValue, screenCoordY,
                Keyboard.getKeyName(Settings.getPlayer1RightValue()),
                Color.yellow);
        screenCoordY += 50;
        
        typeFont.drawString(screenCoordXName, screenCoordY, KeyBindHelper.PLAYER1_SHOOT_KEY, 
                Color.yellow);
        typeFont.drawString(screenCoordXValue, screenCoordY,
                Keyboard.getKeyName(Settings.getPlayer1ShootValue()),
                Color.yellow);
        screenCoordY += 50;
        
        typeFont.drawString(screenCoordXName, screenCoordY, KeyBindHelper.PLAYER2_LEFT_KEY, 
                Color.yellow);
        typeFont.drawString(screenCoordXValue, screenCoordY,
                Keyboard.getKeyName(Settings.getPlayer2LeftValue()),
                Color.yellow);
        screenCoordY += 50;
        
        typeFont.drawString(screenCoordXName, screenCoordY, KeyBindHelper.PLAYER2_RIGHT_KEY, 
                Color.yellow);
        typeFont.drawString(screenCoordXValue, screenCoordY,
                Keyboard.getKeyName(Settings.getPlayer2RightValue()),
                Color.yellow);
        screenCoordY += 50;
        
        typeFont.drawString(screenCoordXName, screenCoordY, KeyBindHelper.PLAYER2_SHOOT_KEY, 
                Color.yellow);
        typeFont.drawString(screenCoordXValue, screenCoordY,
                Keyboard.getKeyName(Settings.getPlayer2ShootValue()),
                Color.yellow);
        screenCoordY += 50;
        
        typeFont.drawString(screenCoordXName, 430,
                defaults,
                Color.red);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (backButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User goes back to options menu");
                game.enterState(States.OptionsState);
            }
            if (defButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User resets keys to default");
                Settings.setDefault();
            }
            if (saveButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User saves the keybinds to file");
                try {
                    KeyBindHelper.save(Settings.getSettings());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (player1LeftButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User set the left button for player 1");
                Settings.setPlayer1Left(Keyboard.getEventKey());
            }
            if (player1RightButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User set the right button for player 1");
                Settings.setPlayer1Right(Keyboard.getEventKey());
            }
            if (player1ShootButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User set the shoot button for player 1");
                Settings.setPlayer1Shoot(Keyboard.getEventKey());
            }

            if (player2LeftButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User set the left button for player 2");
                Settings.setPlayer2Left(Keyboard.getEventKey());
            }
            if (player2RightButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User set the right button for player 2");
                Settings.setPlayer2Right(Keyboard.getEventKey());
            }
            if (player2ShootButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User set the shoot button for player 2");
                Settings.setPlayer2Shoot(Keyboard.getEventKey());
            }
        }
    }

    @Override
    public int getID() {
        return States.KeyBindState;
    }
}
