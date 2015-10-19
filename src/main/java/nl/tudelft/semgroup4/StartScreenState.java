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

public class StartScreenState extends BasicGameState {

    private MouseOverArea mouseOverOnePlayer;
    private MouseOverArea mouseOverTwoPlayer;
    private MouseOverArea mouseOverOptions;
    private MouseOverArea mouseOverQuit;
    private MouseOverArea mouseOverHighScores;
    private Input input;

    /**
     * We need this to construct the Game instances.
     */
    private StateBasedGame mainApp;

    private StartScreenStateController controller;
    private ResourcesWrapper resources;

    /**
     * Starts a new {@link StartScreenState} and its controller.
     * 
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this object may use.
     */
    public StartScreenState(ResourcesWrapper resources) {
        controller = new StartScreenStateController(resources);
        this.resources = resources;
    }

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        this.mainApp = mainApp;
        this.input = container.getInput();

        mouseOverOnePlayer = controller.createPlayer1Button(container);
        mouseOverTwoPlayer = controller.createPlayer2Button(container);
        mouseOverOptions = controller.createOptionsButton(container);
        mouseOverQuit = controller.createQuitButton(container);
        mouseOverHighScores = controller.createHighscoresButton(container);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {
        graphics.drawImage(resources.getTitleScreenBackground(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getTitleScreenBackground().getWidth(),
                resources.getTitleScreenBackground().getHeight());

        controller.drawHighScoresButton(graphics);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        if (!resources.getTitleScreenMusic().playing()) {
            resources.playTitleScreen();
        }

        updatePlayer1Button(container, game);
        updatePlayer2Button(container, game);
        updateOptionsButton(container, game);
        updateExitButton(container, game);
        updateHighscoresButton(container, game);
    }

    /**
     * Checks if the High Scores button is pressed and performs all necessary actions if it is.
     * 
     * @param container
     *            {@link GameContainer} - The Slick2D GameContainer.
     * @param game
     *            {@link StateBasedGame} - The Slick2D StateBasedGame main class.
     * @throws SlickException
     *             If switching states causes an exception.
     */
    protected void updateHighscoresButton(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (controller.isAreaClicked(mouseOverHighScores, input)) {
            game.getState(States.HighscoresState).init(container, game);
            game.enterState(States.HighscoresState);
            Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User enters Highscores menu");
        }
    }

    /**
     * Checks if the 1 Player button is pressed and performs all necessary actions if it is.
     * 
     * @param container
     *            {@link GameContainer} - The Slick2D GameContainer.
     * @param game
     *            {@link StateBasedGame} - The Slick2D StateBasedGame main class.
     * @throws SlickException
     *             If switching states causes an exception.
     */
    protected void updatePlayer1Button(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (controller.isAreaClicked(mouseOverOnePlayer, input)) {
            Game singleplayerGame = controller.createSingleplayerGame(container, game);

            final GameState gameState = new GameState(singleplayerGame);

            game.addState(gameState);
            game.getState(States.GameState).init(container, game);
            resources.stopTitleScreen();
            game.enterState(States.GameState);
            Game.LOGGER
                    .log(LogSeverity.DEBUG, "StartMenu", "User starts a single player game");
        }
    }

    /**
     * Checks if the Player 2 button is pressed and performs all necessary actions if it is.
     * 
     * @param container
     *            {@link GameContainer} - The Slick2D GameContainer.
     * @param game
     *            {@link StateBasedGame} - The Slick2D StateBasedGame main class.
     * @throws SlickException
     *             If switching states causes an exception.
     */
    protected void updatePlayer2Button(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (controller.isAreaClicked(mouseOverTwoPlayer, input)) {
            Game multiplayerGame = controller.createMultiplayerGame(container, game);

            final GameState gameState = new GameState(multiplayerGame);

            game.addState(gameState);
            game.getState(States.GameState).init(container, game);
            resources.stopTitleScreen();
            game.enterState(States.GameState);
            Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User starts a multi-player game");
        }
    }

    /**
     * Checks if the Options button is pressed and performs all necessary actions if it is.
     * 
     * @param container
     *            {@link GameContainer} - The Slick2D GameContainer.
     * @param game
     *            {@link StateBasedGame} - The Slick2D StateBasedGame main class.
     * @throws SlickException
     *             If switching states causes an exception.
     */
    protected void updateOptionsButton(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (controller.isAreaClicked(mouseOverOptions, input)) {
            input.clearKeyPressedRecord();
            input.clearMousePressedRecord();
            game.enterState(States.OptionsState);
            Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User enters options menu");
        }
    }

    /**
     * Checks if the Exit button is pressed and performs all necessary actions if it is.
     * 
     * @param container
     *            {@link GameContainer} - The Slick2D GameContainer.
     * @param game
     *            {@link StateBasedGame} - The Slick2D StateBasedGame main class.
     * @throws SlickException
     *             If switching states causes an exception.
     */
    protected void updateExitButton(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (controller.isAreaClicked(mouseOverQuit, input)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User quits the application");
            resources.stopTitleScreen();
            container.exit();
        }
    }

    @Override
    public int getID() {
        return States.StartScreenState;
    }

    protected StartScreenStateController getController() {
        return this.controller;
    }

    protected void setController(StartScreenStateController controller) {
        this.controller = controller;
    }

    protected void setResources(ResourcesWrapper resources) {
        this.resources = resources;
    }

}
