package nl.tudelft.view;

import nl.tudelft.controller.GameStateController;
import nl.tudelft.controller.Settings;
import nl.tudelft.controller.eventhandlers.PlayerInput;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {
    
    private GameStateController controller;
    private PauseScreen pauseScreen;
    private Input input;
    private final Game currentGame;
    private Dashboard dashboard;
    private boolean pauseScreenOpened = false;

    /**
     * Creates a new {@link GameState} with a specified {@link Game}.
     * 
     * <p>
     * Also gets the {@link PlayerInput} configuration from the {@link Settings} class.
     * </p>
     * 
     * @param game
     *            {@link Game} - The game that this GameState will manage.
     */
    public GameState(Game game) {
        controller = new GameStateController(game);
        currentGame = game;
    }

    /**
     * Starts the state.
     * 
     * @param container
     *            GameContainer - The container in which the game exists.
     * @param mainApp
     *            StateBasedGame - the main state manager
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        final ResourcesWrapper res = new ResourcesWrapper();
        //GL11.glEnable(GL11.GL_BLEND);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        input = container.getInput();
        MouseOverArea mouseOver =
                new MouseOverArea(container, res.getQuitText(), container.getHeight() / 2,
                        container.getHeight() / 2, res.getQuitText().getWidth(), res
                                .getQuitText().getHeight());

        pauseScreen = new PauseScreen(new ResourcesWrapper(), mouseOver);

        int dashboardMargin = 20;
        dashboard =
                new Dashboard(new ResourcesWrapper(), currentGame, dashboardMargin,
                        container.getWidth() - dashboardMargin, container.getHeight());
    }

    /**
     * Renders the state with every tick.
     * 
     * @param container
     *            GameContainer - The container in which the game exists.
     * @param game
     *            StateBasedGame - the main state manager
     * @param graphics
     *            Graphics - the Slick graphics.
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {

        currentGame.render(container, graphics);
        dashboard.render(container, graphics);

        controller.showPauseScreen(pauseScreenOpened, pauseScreen, graphics, 
                container, input, game, this);
    }

    /**
     * Updates the state with every tick.
     * 
     * @param container
     *            GameContainer - The container in which the game exists.
     * @param mainApp
     *            StateBasedGame - the main state manager
     * @param delta
     *            int - the amount of time that has passed since this method was last called.
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public void update(GameContainer container, StateBasedGame mainApp, int delta)
            throws SlickException {
        // checks if the escape key is pressed
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            pauseScreenOpened = controller.togglePauseMenu(pauseScreenOpened, input);
        }

        controller.updateGame(delta);
        dashboard.update(delta);
    }
    
    @Override
    public int getID() {
        return States.GameState;
    }

    public Game getGame() {
        return currentGame;
    }

    public void setGameStateController(GameStateController controller) {
        this.controller = controller;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public PauseScreen getPausescreen() {
        return pauseScreen;
    }

    public boolean isPauseScreenOpened() {
        return pauseScreenOpened;
    }

    public Input getInput() {
        return input;
    }
    
    @Override
    public void setInput(Input input) {
        this.input = input;
    }
}
