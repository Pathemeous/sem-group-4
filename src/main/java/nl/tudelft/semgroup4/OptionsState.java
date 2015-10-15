package nl.tudelft.semgroup4;

import javax.swing.plaf.nimbus.State;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourceWrapper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionsState extends BasicGameState {
    /**
     * Checks for mouseEvents.
     */
    private LoggerSetScreen loggerSetScreen;
    private MouseOverArea soundOnOff;
    private MouseOverArea backButton;
    private MouseOverArea loggerButton;
    private MouseOverArea keyButton;
    private Input input;
    private boolean loggerSetEnabled = false;
    private final ResourceWrapper resources = new ResourceWrapper();

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        soundOnOff = new MouseOverArea(container, resources.getOn(),
                container.getWidth() / 4 * 3,
                container.getHeight() / 4);
        backButton = new MouseOverArea(container, resources.getBackText(),
                container.getWidth() / 10, container.getHeight() / 10 * 9);
        loggerButton = new MouseOverArea(container, resources.getLoggerText(),
                container.getWidth() / 4,
                container.getHeight() / 3);
        keyButton = new MouseOverArea(container, resources.getKeyText(),
                container.getWidth() / 4,
                container.getHeight() / 3 + 60);
        input = container.getInput();
        loggerSetScreen = new LoggerSetScreen(resources, container);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        graphics.drawImage(resources.getOptionsText(),
                container.getWidth() / 2 - resources.getOptionsText().getWidth() / 2, 
                container.getHeight() / 6.0f);    
        graphics.drawImage(resources.getBackText(), container.getWidth() / 10.0f,
                container.getHeight() / 10 * 9);
        graphics.drawImage(resources.getSoundText(), container.getWidth() / 4.0f,
                container.getHeight() / 4.0f);
        graphics.drawImage(resources.getLoggerText(), container.getWidth() / 4.0f,
                container.getHeight() / 3.0f);
        graphics.drawImage(resources.getKeyText(), container.getWidth() / 4.0f,
                container.getHeight() / 3.0f + 60.0f);
        if (ResourceWrapper.musicOn) {
            graphics.drawImage(resources.getOn(), container.getWidth() / 4 * 3,
                    container.getHeight() / 4.0f); 
        }
        if (!ResourceWrapper.musicOn) {
            graphics.drawImage(resources.getOff(), container.getWidth() / 4 * 3,
                    container.getHeight() / 4.0f); 
        }
        if (loggerSetEnabled) {
            loggerSetScreen.show(graphics, container, input, this);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) {
        if (soundOnOff.isMouseOver()) { 
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                
                Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu", 
                        "User toggled sound " + (ResourceWrapper.musicOn ? "off" : "on") );
                ResourceWrapper.musicOn = !ResourceWrapper.musicOn;
            }
        }
        if (backButton.isMouseOver()) {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu", 
                        "User goes back to main menu" );
                game.enterState(States.StartScreenState);
            }
        }
        if (loggerButton.isMouseOver()) {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu",
                        "User goes to logger settings");
                toggleLoggerSet();
            }
        }
        if (keyButton.isMouseOver()) {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu",
                        "User goes to key binding settings");
                game.enterState(States.KeyBindState);
            }
        }
    }

    public void toggleLoggerSet() {
        loggerSetEnabled = !loggerSetEnabled;
    }



    @Override
    public int getID() {
        return States.OptionsState;
    }

}
