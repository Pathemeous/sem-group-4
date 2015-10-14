package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Controls the LoggerSetScreen.
 * This class contains all the logic performed by the logger set screen.
 * @author Bram
 */
public class LoggerSetScreenController {

    /**
     * Decide the color for the buttons in the logger set screen.
     *
     * @param logSeverity
     *                  The severity equal to the button
     * @return a color
     *                  The color to which the button will be set
     */
    protected Color decideColor(LogSeverity logSeverity) {
        if (logSeverity == Game.LOGGER.getSeverity()) {
            return Color.green;
        } else {
            return Color.yellow;
        }
    }

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
}
