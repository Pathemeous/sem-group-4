package nl.tudelft.semgroup4;


import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
    private int width;
    private OptionsStateController controller;
    protected boolean loggerSetEnabled = false;
    private ResourcesWrapper resources = new ResourcesWrapper();

    /**
     * Create a new OptionsScreen with its controller.
     */
    public OptionsState() {
        controller = new OptionsStateController(this, resources);
    }

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        input = container.getInput();
        width = container.getWidth() / 4;

        soundOnOff = new MouseOverArea(container, resources.getOn(),
                width * 3,
                container.getHeight() / 4);
        backButton = new MouseOverArea(container, resources.getBackText(),
                width / 2,
                container.getHeight() / 10 * 9);
        loggerButton = new MouseOverArea(container, resources.getLoggerText(),
                width,
                container.getHeight() / 3);
        keyButton = new MouseOverArea(container, resources.getKeyText(),
                width,
                container.getHeight() / 3 + 60);

        loggerSetScreen = new LoggerSetScreen(container, resources);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        optionsRender(container, graphics);
        backRender(container, graphics);
        soundRender(container, graphics);
        loggerRender(container, graphics);
        keyRender(container, graphics);

        controller.showLoggerScreen(loggerSetEnabled, graphics, container,
                container.getInput(), loggerSetScreen);
    }

    /**
     * Render the options button.
     * @param container
     *                 the container in which the game is rendered
     * @param graphics
     *                 the graphics which are used for rendering
     */
    private void optionsRender(GameContainer container, Graphics graphics) {
        graphics.drawImage(resources.getOptionsText(),
                container.getWidth() / 2 - resources.getOptionsText().getWidth() / 2,
                container.getHeight() / 6.0f);
    }

    /**
     * Render the back button.
     * @param container
     *                 the container in which the game is rendered
     * @param graphics
     *                 the graphics which are used for rendering
     */
    private void backRender(GameContainer container, Graphics graphics) {
        graphics.drawImage(resources.getBackText(), width / 2,
                container.getHeight() / 10 * 9);
    }

    /**
     * Render the sound buttons.
     * @param container
     *                 the container in which the game is rendered
     * @param graphics
     *                 the graphics which are used for rendering
     */
    private void soundRender(GameContainer container, Graphics graphics) {
        float height = container.getHeight() / 4.0f;
        graphics.drawImage(resources.getSoundText(), width, height);
        graphics.drawImage(controller.getMusicImage(), width * 3, height);
    }

    /**
     * Render the logger button.
     * @param container
     *                 the container in which the game is rendered
     * @param graphics
     *                 the graphics which are used for rendering
     */
    private void loggerRender(GameContainer container, Graphics graphics) {
        graphics.drawImage(resources.getLoggerText(), width,
                container.getHeight() / 3.0f);
    }

    /**
     * Render the keybindings button.
     * @param container
     *                 the container in which the game is rendered
     * @param graphics
     *                 the graphics which are used for rendering
     */
    private void keyRender(GameContainer container, Graphics graphics) {
        graphics.drawImage(resources.getKeyText(), width,
                container.getHeight() / 3.0f + 60.0f);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) {
        soundUpdate();
        backUpdate(game);
        loggerUpdate();
        keyUpdate(game);
    }

    /**
     * Poll the sound button.
     */
    private void soundUpdate() {
        if (soundOnOff.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu",
                    "User toggled sound " + (ResourcesWrapper.musicOn ? "off" : "on") );
            controller.toggleMusic();
        }
    }

    /**
     * Poll the back button.
     * @param game
     *             the game for which the state can be changed.
     */
    private void backUpdate(StateBasedGame game) {
        if (backButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu",
                    "User goes back to main menu" );
            game.enterState(States.StartScreenState);
        }
    }

    /**
     * Poll the loger button.
     */
    private void loggerUpdate() {
        if (loggerButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu",
                    "User goes to logger settings");
            toggleLoggerSet();
        }
    }

    /**
     * Poll the KeyBindings button.
     * @param game
     *             the game for which the state can be changed.
     */
    private void keyUpdate(StateBasedGame game) {
        if (keyButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu",
                    "User goes to key binding settings");
            game.enterState(States.KeyBindState);
        }
    }

    /**
     * Toggle the logger screen enabled boolean.
     * Handled by the controller.
     */
    public void toggleLoggerSet() {
        controller.toggleLoggerScreen();
    }

    @Override
    public int getID() {
        return controller.getID();
    }

    protected boolean isLoggerSetEnabled() {
        return loggerSetEnabled;
    }

    protected void setLoggerSetEnabled(boolean state) {
        loggerSetEnabled = state;
    }

    /**
     * Set the controller (only used for testing).
     * @param controller
     *                  the controller which will be used for testing
     */
    protected void setController(OptionsStateController controller) {
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
     * Get the logger set screen (only used for testing).
     * @return loggerSetScreen
     *                  the logger set screen
     */
    protected LoggerSetScreen getLoggerSetScreen() {
        return loggerSetScreen;
    }

    /**
     * Set all MouseOverAreas to the same area (only used for testing).
     * @param area
     *                  the mocked area which will be set.
     */
    protected void setAreas(MouseOverArea area) {
        soundOnOff = area;
        backButton = area;
        loggerButton = area;
        keyButton = area;
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
