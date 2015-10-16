package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.eventhandlers.InputKey;
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

    private MouseOverArea[] areas = new MouseOverArea[6];
    private String[] keyStrings = {KeyBindHelper.PLAYER1_LEFT_KEY,
        KeyBindHelper.PLAYER1_RIGHT_KEY, KeyBindHelper.PLAYER1_SHOOT_KEY,
        KeyBindHelper.PLAYER2_LEFT_KEY, KeyBindHelper.PLAYER2_RIGHT_KEY,
        KeyBindHelper.PLAYER2_SHOOT_KEY};

    private final String back = "Back";
    private final String save = "Save";
    private final String defaults = "Defaults";
    private final String instructions = "To set a key, first press a key on your keyboard."
            + "Then click on the key you want to replace.";

    private MouseOverArea backButton;
    private MouseOverArea saveButton;
    private MouseOverArea defButton;

    private KeyBindStateController controller;
    private Input input;

    private ResourcesWrapper resources = new ResourcesWrapper();
    private static TrueTypeFont typeFont;
    private static TrueTypeFont typeFontSmall;

    private static final float screenCoordXName = 60;
    private static final float screenCoordXValue = 650;


    public KeyBindState() {
        controller = new KeyBindStateController();
    }

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {

        Font font = new Font("Calibri", Font.BOLD, 46);
        typeFont = resources.createFont(font, true);
        Font smallFont = new Font("Calibri", Font.BOLD, 30);
        typeFontSmall = resources.createFont(smallFont, true);

        areas = controller.createMouseOverAreas(60, 80, container, keyStrings, typeFont, resources);

        backButton =
                controller.createButtonArea(container.getWidth() / 10,
                container.getHeight() / 10 * 9, container, back, typeFont, resources);

        saveButton =
                controller.createButtonArea((container.getWidth()) - (container.getWidth() / 10),
                container.getHeight() / 10 * 9, container, save, typeFont, resources);

        defButton =
                controller.createButtonArea(60, 430, container, defaults, typeFont, resources);

        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {

        input.disableKeyRepeat();

        typeFontSmall.drawString(screenCoordXName, 500, instructions, Color.green);

        renderText(container.getWidth() / 10, container.getHeight() / 10 * 9, back,
                Color.yellow);
        renderText((container.getWidth()) - (container.getWidth() / 10),
                container.getHeight() / 10 * 9, save, Color.yellow);

        renderButtons(80, Color.yellow);

        renderText(screenCoordXName, 430, defaults, Color.red);
    }

    /**
     * Render text.
     * @param screenXCoord
     *                  the X location at which to render
     * @param screenYCoord
     *                  the Y location at which to render
     * @param text
     *                  the text to render
     * @param color
     *                  the color in which to render
     */
    private void renderText(float screenXCoord, float screenYCoord, String text,
                            Color color) {
        typeFont.drawString(screenXCoord, screenYCoord, text, color);
    }

    /**
     * Render all mouse buttons.
     * @param screenYCoord
     *                  the Y location at which to start rendering
     * @param color
     *                  the color the buttons will be
     */
    private void renderButtons(float screenYCoord, Color color) {
        for (int i = 0; i < keyStrings.length; i++) {
            renderText(screenCoordXName, screenYCoord + i * 50, keyStrings[i], color);
            renderText(screenCoordXValue, screenYCoord + i * 50,
                    controller.getKey(i), color);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            for (int i = 0; i < areas.length; i++) {
                if (areas[i].isMouseOver()) {
                    Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                            "User set" + keyStrings[i]);
                    controller.setKey(i, new InputKey(Keyboard.getEventKey()));
                }
            }
            if (backButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User goes back to options menu");
                game.enterState(States.OptionsState);
            }
            if (defButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User resets keys to default");
                Settings.setDefaults();
            }
            if (saveButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User saves the keybinds to file");
                Settings.save();
            }
        }
    }

    @Override
    public int getID() {
        return controller.getID();
    }

    /**
     * Set the first mouse over area (only used for testing).
     * @param overArea
     *              the area to which to set
     */
    protected void setAreasForTesting(MouseOverArea overArea) {
        areas[0] = overArea;
    }

    /**
     * Set the controller (only used for testing).
     * @param controller
     *                  the controller which will be used for testing
     */
    protected void setController(KeyBindStateController controller) {
        this.controller = controller;
    }

    /**
     * Set the wrapper (only used for testing).
     * @param wrapper
     *                  the wrapper which will be used for testing
     */
    protected void setWrapper(ResourcesWrapper wrapper) {
        this.resources = wrapper;
    }

    /**
     * Set all MouseOverAreas to the same area (only used for testing).
     * @param area
     *                  the mocked area which will be set.
     */
    protected void setAreas(MouseOverArea area) {
        defButton = area;
        backButton = area;
        saveButton = area;
    }

    /**
     * Set the Input for testing (only used for testing).
     * @param input
     *                  the input which will be set
     */
    protected void setInputForTesting(Input input) {
        this.input = input;
    }
}
