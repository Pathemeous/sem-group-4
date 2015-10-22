package nl.tudelft.controller;

import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Game;
import nl.tudelft.settings.InputKey;
import nl.tudelft.settings.Settings;
import nl.tudelft.view.States;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;

public class KeyBindStateController {

    private Settings settings = Settings.getInstance();
    /**
     * Creates all the mouse over areas.
     * @param overCoordX
     *                  the x coordinate to use
     * @param overCoordY
     *                  the first y coordinate to use
     * @param container
     *                  {@link GameContainer} - the container in which the game runs.
     * @param text
     *                  an array of text for which buttons need to be created
     * @param typeFont
     *                  the font with which the buttons will be created
     * @param res
     *                  a resource wrapper which will create the image
     * @return areas
     *                  an array containing all created buttons
     * @throws SlickException
     *                  image can't be created
     */
    public MouseOverArea[] createMouseOverAreas(float overCoordX, float overCoordY,
            GameContainer container, String[] text,TrueTypeFont typeFont,
            ResourcesWrapper res) throws SlickException {

        MouseOverArea[] areas = new MouseOverArea[text.length];
        for (int i = 0; i < areas.length; i++) {
            Image button = res.createImage(typeFont.getWidth(text[i]), typeFont.getHeight());
            Shape shape = new Rectangle(overCoordX, overCoordY + i * 50,
                    typeFont.getWidth(text[0]), typeFont.getHeight());
            areas[i] = new MouseOverArea(container, button, shape);
        }
        return areas;
    }

    /**
     * Creates a mouse over area.
     * @param overCoordX
     *                  the x coordinate to use
     * @param overCoordY
     *                  the y coordinate to use
     * @param container
     *                  {@link GameContainer} - the container in which the game runs.
     * @param text
     *                  an array of text for which buttons need to be created
     * @param typeFont
     *                  the font with which the buttons will be created
     * @param res
     *                  a resource wrapper which will create the image
     * @return MouseOverArea
     *                  a MouseOVerArea
     * @throws SlickException
     *                  image can't be created
     */
    public MouseOverArea createButtonArea(float overCoordX, float overCoordY,
            GameContainer container, String text, TrueTypeFont typeFont,
            ResourcesWrapper res) throws SlickException {

        Image button = res.createImage(typeFont.getWidth(text), typeFont.getHeight());
        Shape shape = new Rectangle(overCoordX, overCoordY,
                typeFont.getWidth(text), typeFont.getHeight());
        return new MouseOverArea(container, button, shape);
    }

    /**
     * Set a key.
     * @param counter
     *                  which key to set
     * @param key
     *                  which key to change to
     */
    public void setKey(int counter, InputKey key) {
        switch (counter) {
            case 0: settings.setPlayer1LeftKey(key);
                    break;
            case 1: settings.setPlayer1RightKey(key);
                    break;
            case 2: settings.setPlayer1ShootKey(key);
                    break;
            case 3: settings.setPlayer2LeftKey(key);
                    break;
            case 4: settings.setPlayer2RightKey(key);
                    break;
            case 5: settings.setPlayer2ShootKey(key);
                    break;
            default:
                Game.LOGGER.log(LogSeverity.ERROR, "KeyBindStateController",
                    "Something went horribly wrong");
        }
    }

    /**
     * Get a key.
     * @param counter
     *                  which key to get
     * @return String
     *                  the key
     */
    public String getKey(int counter) {
        switch (counter) {
            case 0: return Keyboard.getKeyName(
                    settings.getPlayer1Input().getLeftInput().getKeyCode());
            case 1: return Keyboard.getKeyName(
                    settings.getPlayer1Input().getRightInput().getKeyCode());
            case 2: return Keyboard.getKeyName(
                    settings.getPlayer1Input().getShootInput().getKeyCode());
            case 3: return Keyboard.getKeyName(
                    settings.getPlayer2Input().getLeftInput().getKeyCode());
            case 4: return Keyboard.getKeyName(
                    settings.getPlayer2Input().getRightInput().getKeyCode());
            case 5: return Keyboard.getKeyName(
                    settings.getPlayer2Input().getShootInput().getKeyCode());
            default:
                Game.LOGGER.log(LogSeverity.ERROR, "KeyBindStateController",
                        "Something went horribly wrong");
                return "ERROR";
        }
    }

    /**
     * Get the ID for the state.
     * @return
     *              the ID for the state
     */
    public int getID() {
        return States.KeyBindState;
    }
}
