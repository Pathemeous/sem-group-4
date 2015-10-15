package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourceWrapper;
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

public class LoggerSetScreen {

    private final String critical = "CRITICAL";
    private final String error = "ERROR";
    private final String warning = "WARNING";
    private final String debug = "DEBUG";
    private final String verbose = "VERBOSE";
    private final String cancel = "CANCEL";

    private MouseOverArea overCan;
    private MouseOverArea overCrit;
    private MouseOverArea overErr;
    private MouseOverArea overWarn;
    private MouseOverArea overDeb;
    private MouseOverArea overVerb;

    private TrueTypeFont typeFont;
    private Font font;

    private Image canButton;
    private Image critButton;
    private Image errButton;
    private Image warnButton;
    private Image debButton;
    private Image verbButton;

    private Shape canShape;
    private Shape critShape;
    private Shape errShape;
    private Shape warnShape;
    private Shape debShape;
    private Shape verbShape;

    /**
     * Constructs a LoggerSetScreen with the Resources.
     *
     * @param res
     *            {@link ResourceWrapper} - a new ResourcesWrapper.
     * @param container
     *            {@link GameContainer} - the container in which the game runs.
     * @throws SlickException
     *            font can't be found.
     */
    public LoggerSetScreen(ResourceWrapper res, GameContainer container) throws SlickException {
        font = new Font("Calibri", Font.BOLD, 46);
        typeFont = new TrueTypeFont(font, true);

        this.canButton = new Image(typeFont.getWidth(cancel), typeFont.getHeight());
        canShape = new Rectangle(60, 330, typeFont.getWidth(cancel), typeFont.getHeight());
        overCan = new MouseOverArea(container, canButton, canShape);

        this.critButton = new Image(typeFont.getWidth(critical), typeFont.getHeight());
        critShape = new Rectangle(60, 80, typeFont.getWidth(critical), typeFont.getHeight());
        overCrit = new MouseOverArea(container, critButton, critShape);

        this.errButton = new Image(typeFont.getWidth(error), typeFont.getHeight());
        errShape = new Rectangle(60, 130, typeFont.getWidth(error), typeFont.getHeight());
        overErr = new MouseOverArea(container, errButton, errShape);

        this.warnButton = new Image(typeFont.getWidth(warning), typeFont.getHeight());
        warnShape = new Rectangle(60, 180, typeFont.getWidth(warning), typeFont.getHeight());
        overWarn = new MouseOverArea(container, warnButton, warnShape);

        this.debButton = new Image(typeFont.getWidth(debug), typeFont.getHeight());
        debShape = new Rectangle(60, 230, typeFont.getWidth(debug), typeFont.getHeight());
        overDeb = new MouseOverArea(container, debButton, debShape);

        this.verbButton = new Image(typeFont.getWidth(verbose), typeFont.getHeight());
        verbShape = new Rectangle(60, 280, typeFont.getWidth(verbose), typeFont.getHeight());
        overVerb = new MouseOverArea(container, verbButton, verbShape);
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
        graphics.setColor(Color.yellow);
        graphics.setAntiAlias(true);
        Color color = new Color(0f, 0f, 0f, 0.5f);
        graphics.setColor(color);
        graphics.fillRect(0, 0, container.getWidth(), container.getHeight());
        typeFont.drawString(60, 80, critical, Color.yellow);
        typeFont.drawString(60, 130, error, Color.yellow);
        typeFont.drawString(60, 180, warning, Color.yellow);
        typeFont.drawString(60, 230, debug, Color.yellow);
        typeFont.drawString(60, 280, verbose, Color.yellow);
        typeFont.drawString(60, 330, cancel, Color.red);

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (overCan.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                        "Player has cancelled setting the logger");
                optionsState.toggleLoggerSet();
            }
            if (overCrit.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                        "Player has set the logger to critical");
                Game.LOGGER.setSeverity(LogSeverity.CRITICAL);
                optionsState.toggleLoggerSet();
            }
            if (overErr.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                        "Player has set the logger to error");
                Game.LOGGER.setSeverity(LogSeverity.ERROR);
                optionsState.toggleLoggerSet();
            }
            if (overWarn.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                        "Player has set the logger to warning");
                Game.LOGGER.setSeverity(LogSeverity.WARNING);
                optionsState.toggleLoggerSet();
            }
            if (overDeb.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                        "Player has set the logger to debug");
                Game.LOGGER.setSeverity(LogSeverity.DEBUG);
                optionsState.toggleLoggerSet();
            }

            if (overVerb.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "LoggerSetScreen",
                        "Player has set the logger to verbose");
                Game.LOGGER.setSeverity(LogSeverity.VERBOSE);
                optionsState.toggleLoggerSet();
            }
        }
    }

}
