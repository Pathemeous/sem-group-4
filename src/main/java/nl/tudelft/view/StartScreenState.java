package nl.tudelft.view;

import nl.tudelft.controller.StartScreenStateController;
import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractGame;

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

    /**
     * Turns on the music if it wasn't on yet and checks which button was pressed if a mouse click
     * is registered.
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        if (!resources.getTitleScreenMusic().playing()) {
            resources.playTitleScreen();
        }

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            updatePlayer1Button(container, game);
            updatePlayer2Button(container, game);
            updateOptionsButton(container, game);
            updateExitButton(container, game);
            updateHighscoresButton(container, game);
        }
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
    public void updateHighscoresButton(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (mouseOverHighScores.isMouseOver()) {
            game.getState(States.HighscoresState).init(container, game);
            game.enterState(States.HighscoresState);
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                    "User enters Highscores menu");
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
    public void updatePlayer1Button(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (mouseOverOnePlayer.isMouseOver()) {
            AbstractGame singleplayerGame = controller.createSingleplayerGame(container, game);
            enterGameState(container, game, singleplayerGame);
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                    "User starts a single player game");
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
    public void updatePlayer2Button(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (mouseOverTwoPlayer.isMouseOver()) {
            AbstractGame multiplayerGame = controller.createMultiplayerGame(container, game);
            enterGameState(container, game, multiplayerGame);
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                    "User starts a multi-player game");
        }
    }

    /**
     * Adds a new {@link GameState} and enters it.
     * 
     * @param container
     *            {@link GameContainer} - This session's GameContainer.
     * @param game
     *            {@link StateBasedGame} - The Main Game object of Slick2D.
     * @param createdGame
     *            {@link AbstractGame} - The Game to enter the {@link GameState} with.
     * @throws SlickException
     *             When state switching goes wrong.
     */
    public void enterGameState(GameContainer container, StateBasedGame game,
            AbstractGame createdGame) throws SlickException {
        final GameState gameState = new GameState(createdGame);
        game.addState(gameState);
        game.getState(States.GameState).init(container, game);
        resources.stopTitleScreen();
        game.enterState(States.GameState);
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
    public void updateOptionsButton(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (mouseOverOptions.isMouseOver()) {
            input.clearKeyPressedRecord();
            input.clearMousePressedRecord();
            game.enterState(States.OptionsState);
            AbstractGame.LOGGER
                    .log(LogSeverity.DEBUG, "StartMenu", "User enters options menu");
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
    public void updateExitButton(GameContainer container, StateBasedGame game)
            throws SlickException {
        if (mouseOverQuit.isMouseOver()) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                    "User quits the application");
            resources.stopTitleScreen();
            container.exit();
        }
    }

    @Override
    public int getID() {
        return States.StartScreenState;
    }

    public StartScreenStateController getController() {
        return this.controller;
    }

    public void setController(StartScreenStateController controller) {
        this.controller = controller;
    }

    public void setResources(ResourcesWrapper resources) {
        this.resources = resources;
    }

    /**
     * Sets the new input for testing purposes only.
     * 
     * @param input
     *            {@link Input} - A mock to use as input.
     */
    protected void setInputForTesting(Input input) {
        this.input = input;
    }

}
