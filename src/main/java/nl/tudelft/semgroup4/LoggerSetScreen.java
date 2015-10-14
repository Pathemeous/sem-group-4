package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;

public class LoggerSetScreen {

    private final String[] text = {"CRITICAL", "ERROR",
        "WARNING", "DEBUG", "VERBOSE"};

    private final LogSeverity[] severities = {LogSeverity.CRITICAL,
        LogSeverity.ERROR, LogSeverity.WARNING, LogSeverity.DEBUG,
        LogSeverity.VERBOSE};

    private MouseOverArea[] areas = new MouseOverArea[5];

    private TrueTypeFont typeFont;
    private LoggerSetScreenController controller;

    /**
     * Constructs a LoggerSetScreen with the Resources.
     *
     * @param container
     *            {@link GameContainer} - the container in which the game runs.
     * @throws SlickException
     *            font can't be found.
     */
    public LoggerSetScreen(GameContainer container) throws SlickException {
        Font font = new Font("Calibri", Font.BOLD, 46);
        typeFont = new TrueTypeFont(font, true);

        this.controller = new LoggerSetScreenController();
        areas = controller.createMouseOverAreas(60, 80, container, text, typeFont);
    }

    /**
     * makes the background opaque and opens the keyset screen.
     *
     * @param graphics
     *            graphics to draw with
     * @param container
     *            to draw in
     * @param input
     *            to find out where click events happened
     * @param optionsState
     *            the options screen
     */
    public void show(Graphics graphics, GameContainer container, Input input,
                     OptionsState optionsState) {

        graphics.setAntiAlias(true);
        graphics.setColor(new Color(0f, 0f, 0f, 0.5f));
        graphics.fillRect(0, 0, container.getWidth(), container.getHeight());

        renderText(60, 80);

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            for (int i = 0; i < areas.length; i++) {
                if (areas[i].isMouseOver()) {
                    Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                            "Player has set the logger to " + text[i]);
                    Game.LOGGER.setSeverity(severities[i]);
                    optionsState.toggleLoggerSet();
                }
            }
        }
    }

    /**
     * Renders all the buttons.
     *
     * @param renderCoordX
     *                  the x coordinate to use
     * @param renderCoordY
     *                  the first y coordinate to use
     */
    public void renderText(float renderCoordX, float renderCoordY) {
        for (int i = 0; i < text.length; i++) {
            typeFont.drawString(renderCoordX,
                    renderCoordY + i * 50,
                    text[i],
                    controller.decideColor(severities[i]));
        }
    }
}
