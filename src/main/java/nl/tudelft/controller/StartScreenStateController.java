package nl.tudelft.controller;

import java.awt.Font;

import nl.tudelft.controller.eventhandlers.PlayerEventHandler;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Game;
import nl.tudelft.model.MultiplayerGame;
import nl.tudelft.model.Player;
import nl.tudelft.model.SingleplayerGame;

import nl.tudelft.view.StartScreenState;
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
import org.newdawn.slick.state.StateBasedGame;

/**
 * Controller that performs logic for the StartScreenState.
 * 
 * @author Pathemeous
 *
 */
public class StartScreenStateController {

    private ResourcesWrapper resources;

    private static final String HIGHSCORE_TEXT = "HIGHSCORES";
    private TrueTypeFont typeFont;

    /**
     * Creates a new controller for a specific {@link StartScreenState}.
     * 
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this controller may use.
     */
    public StartScreenStateController(ResourcesWrapper resources) {
        this.resources = resources;

        Font font = new Font("Verdana", Font.BOLD, 36);
        typeFont = resources.createFont(font, true);
    }

    /**
     * Creates the HighScore button with the appropriate text.
     *
     * @param container
     *                  the container
     *
     * @return
     *                  the HighscoresButton
     * @throws SlickException
     *             If creating the {@link Image} goes wrong.
     */
    public MouseOverArea createHighscoresButton(GameContainer container)
            throws SlickException {
        Shape shape =
                new Rectangle(650, 650, typeFont.getWidth(HIGHSCORE_TEXT),
                        typeFont.getHeight());
        shape = shape.transform(Transform.createRotateTransform(6, 650, 650));
        Image highScoreButton =
                resources.createImage(typeFont.getWidth(HIGHSCORE_TEXT), typeFont.getHeight());

        shape =
                new Rectangle(650, 650, typeFont.getWidth(HIGHSCORE_TEXT),
                        typeFont.getHeight());
        shape = shape.transform(Transform.createRotateTransform(6, 650, 650));

        return new MouseOverArea(container, highScoreButton, shape);
    }

    /**
     * Draws the text of the HighsScoresButton on the screen.
     * 
     * @param graphics
     *            {@link Graphics} - The graphics that this method may use to draw.
     */
    public void drawHighScoresButton(Graphics graphics) {
        graphics.rotate(650, 650, 340);
        typeFont.drawString(650, 650, HIGHSCORE_TEXT, Color.red);
    }

    /**
     * Creates a new {@link SingleplayerGame}.
     * 
     * @param container
     *            {@link GameContainer} - The state's Gamecontainer.
     * @param mainApp
     *            - {@link StateBasedGame} - The StateBasedGame to pass to the Game object.
     * @return {@link Game} - A game object.
     */
    public Game createSingleplayerGame(GameContainer container, StateBasedGame mainApp) {
        final Player player =
                new Player(new ResourcesWrapper(), container.getWidth() / 2,
                        container.getHeight() - resources.getPlayerImageStill().getHeight()
                                - 5 * resources.getWallImage().getHeight(), true);
        final Game singleplayerGame =
                new SingleplayerGame(mainApp, container.getWidth(), container.getHeight(),
                        resources, player);
        singleplayerGame.getCurLevel().toAdd(player.getWeapon());
        PlayerEventHandler player1Handler = new PlayerEventHandler(player);
        Settings.getPlayer1Input().addObserver(player1Handler);

        return singleplayerGame;
    }

    /**
     * Creates a new {@link MultiplayerGame}.
     * 
     * @param container
     *            {@link GameContainer} - The state's Gamecontainer.
     * @param mainApp
     *            - {@link StateBasedGame} - The StateBasedGame to pass to the Game object.
     * @return {@link Game} - A game object.
     */
    public Game createMultiplayerGame(GameContainer container, StateBasedGame mainApp) {
        Player firstPlayer =
                new Player(new ResourcesWrapper(), container.getWidth() / 2,
                        container.getHeight() - resources.getPlayerImageStill().getHeight()
                                - 5 * resources.getWallImage().getHeight(), true);
        PlayerEventHandler player1Handler = new PlayerEventHandler(firstPlayer);
        Settings.getPlayer1Input().addObserver(player1Handler);

        Player secondPlayer =
                new Player(new ResourcesWrapper(), container.getWidth() / 2 + 100,
                        container.getHeight() - resources.getPlayerImageStill().getHeight()
                                - 5 * resources.getWallImage().getHeight(), false);
        PlayerEventHandler player2Handler = new PlayerEventHandler(secondPlayer);
        Settings.getPlayer2Input().addObserver(player2Handler);

        final Game multiplayerGame =
                new MultiplayerGame(mainApp, container.getWidth(), container.getHeight(),
                        new ResourcesWrapper(), firstPlayer, secondPlayer);

        multiplayerGame.getCurLevel().toAdd(firstPlayer.getWeapon());
        multiplayerGame.getCurLevel().toAdd(firstPlayer.getWeapon());

        return multiplayerGame;
    }

    /**
     * Creates the correct {@link MouseOverArea} for the 1 Player button.
     * 
     * @param container
     *            {@link GameContainer} - the container to draw in.
     * @return {@link MouseOverArea} - The MouseOverArea for this button.
     */
    public MouseOverArea createPlayer1Button(GameContainer container) {
        return new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 391,
                364, 88);
    }

    /**
     * Creates the correct {@link MouseOverArea} for the 2 Player button.
     * 
     * @param container
     *            {@link GameContainer} - the container to draw in.
     * @return {@link MouseOverArea} - The MouseOverArea for this button.
     */
    public MouseOverArea createPlayer2Button(GameContainer container) {
        return new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 476,
                364, 88);
    }

    /**
     * Creates the correct {@link MouseOverArea} for the options button.
     * 
     * @param container
     *            {@link GameContainer} - the container to draw in.
     * @return {@link MouseOverArea} - The MouseOverArea for this button.
     */
    public MouseOverArea createOptionsButton(GameContainer container) {
        return new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 573,
                364, 88);
    }

    /**
     * Creates the correct {@link MouseOverArea} for the quit button.
     * 
     * @param container
     *            {@link GameContainer} - the container to draw in.
     * @return {@link MouseOverArea} - The MouseOverArea for this button.
     */
    public MouseOverArea createQuitButton(GameContainer container) {
        return new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 672,
                364, 88);
    }

    public boolean isAreaClicked(MouseOverArea buttonArea, Input input) {
        return input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && buttonArea.isMouseOver();
    }

    public ResourcesWrapper getResources() {
        return resources;
    }

    public void setResources(ResourcesWrapper resources) {
        this.resources = resources;
    }

    public void setFont(TrueTypeFont font) {
        this.typeFont = font;
    }

}
