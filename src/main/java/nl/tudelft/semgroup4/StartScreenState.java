package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.model.MultiplayerGame;
import nl.tudelft.model.Player;
import nl.tudelft.model.SingleplayerGame;
import nl.tudelft.semgroup4.eventhandlers.PlayerEventHandler;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
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
    private boolean alreadyAdded = false;

    private Font font;
    private TrueTypeFont typeFont;
    private Image highScoreButton;
    private String highscores;
    private Shape shape;

    /**
     * We need this to construct the Game instances.
     */
    private StateBasedGame mainApp;

    private StartScreenStateController controller;

    public StartScreenState() {
        controller = new StartScreenStateController(this, new ResourcesWrapper());
    }

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        this.mainApp = mainApp;
        font = new Font("Verdana", Font.BOLD, 36);
        typeFont = new TrueTypeFont(font, true);
        highscores = "HIGHSCORES";
        highScoreButton = new Image(typeFont.getWidth(highscores), typeFont.getHeight());
        shape = new Rectangle(650, 650, typeFont.getWidth(highscores), typeFont.getHeight());
        shape = shape.transform(Transform.createRotateTransform(6, 650, 650));

        input = container.getInput();
        final ResourcesWrapper resources = new ResourcesWrapper();
        // initializes all the areas where the buttons are to see if the mouse is on one of those
        // areas
        mouseOverOnePlayer =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 391,
                        364, 88);
        mouseOverTwoPlayer =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 476,
                        364, 88);
        mouseOverOptions =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 573,
                        364, 88);
        mouseOverQuit =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 672,
                        364, 88);
        mouseOverHighScores = new MouseOverArea(container, highScoreButton, shape);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {
        final ResourcesWrapper resources = new ResourcesWrapper();
        graphics.drawImage(resources.getTitleScreenBackground(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getTitleScreenBackground().getWidth(),
                resources.getTitleScreenBackground().getHeight());

        // draws the highscore button
        graphics.rotate(650, 650, 340);
        typeFont.drawString(650, 650, highscores, Color.red);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        final ResourcesWrapper resources = new ResourcesWrapper();
        if (!resources.getTitleScreenMusic().playing()) {
            resources.playTitleScreen();
        }

        //
        // checks if the left mouse button is pressed and where it was pressed to determine
        // what action to perform

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOverHighScores.isMouseOver()) {
                game.getState(States.HighscoresState).init(container, game);
                game.enterState(States.HighscoresState);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User enters options menu");
            } else if (mouseOverOnePlayer.isMouseOver()) {
                final ResourcesWrapper res = new ResourcesWrapper();
                final Player player =
                        new Player(new ResourcesWrapper(), container.getWidth() / 2,
                                container.getHeight() - res.getPlayerImageStill().getHeight()
                                        - 5 * res.getWallImage().getHeight(), true);
                final Game singleplayerGame =
                        new SingleplayerGame(mainApp, container.getWidth(),
                                container.getHeight(), res, player);
                singleplayerGame.toAdd(player.getWeapon());
                PlayerEventHandler player1Handler = new PlayerEventHandler(player);
                Settings.getPlayer1Input().addObserver(player1Handler);

                final GameState gameState = new GameState(singleplayerGame);

                game.addState(gameState);
                game.getState(States.GameState).init(container, game);
                resources.stopTitleScreen();
                game.enterState(States.GameState);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                        "User starts a single player game");
            } else if (mouseOverTwoPlayer.isMouseOver()) {

                final ResourcesWrapper res = new ResourcesWrapper();
                Player firstPlayer =
                        new Player(new ResourcesWrapper(), container.getWidth() / 2,
                                container.getHeight() - res.getPlayerImageStill().getHeight()
                                        - 5 * res.getWallImage().getHeight(), true);
                PlayerEventHandler player1Handler = new PlayerEventHandler(firstPlayer);
                Settings.getPlayer1Input().addObserver(player1Handler);

                Player secondPlayer =
                        new Player(new ResourcesWrapper(), container.getWidth() / 2 + 100,
                                container.getHeight() - res.getPlayerImageStill().getHeight()
                                        - 5 * res.getWallImage().getHeight(), false);
                PlayerEventHandler player2Handler = new PlayerEventHandler(secondPlayer);
                Settings.getPlayer2Input().addObserver(player2Handler);

                final Game multiplayerGame =
                        new MultiplayerGame(mainApp, container.getWidth(),
                                container.getHeight(), new ResourcesWrapper(), firstPlayer,
                                secondPlayer);

                multiplayerGame.toAdd(firstPlayer.getWeapon());
                multiplayerGame.toAdd(firstPlayer.getWeapon());

                final GameState gameState = new GameState(multiplayerGame);

                game.addState(gameState);
                game.getState(States.GameState).init(container, game);
                resources.stopTitleScreen();
                game.enterState(States.GameState);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                        "User starts a multi-player game");
            } else if (mouseOverOptions.isMouseOver()) {
                input.clearKeyPressedRecord();
                input.clearMousePressedRecord();
                game.enterState(States.OptionsState);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User enters options menu");
            } else if (mouseOverQuit.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User quits the application");
                resources.stopTitleScreen();
                container.exit();
            }
        }

    }

    public boolean isAlreadyAdded() {
        return alreadyAdded;
    }

    public void setAlreadyAdded(boolean alreadyAdded) {
        this.alreadyAdded = alreadyAdded;
    }

    @Override
    public int getID() {
        return States.StartScreenState;
    }

}
