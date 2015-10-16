package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

public class PauseScreen {
    private final MouseOverArea mouseOver;
    private final Image pauseText;
    private final Image quitText;

    /**
     * Constructs a PauseScreen with the correct MouseOverArea and Resources.
     * 
     * @param resources
     *            {@link ResourcesWrapper} - a new ResourcesWrapper.
     * @param mouseOver
     *            {@link MouseOverArea} - the Slick MouseOverArea to use.
     */
    public PauseScreen(ResourcesWrapper resources, MouseOverArea mouseOver) {
        this.mouseOver = mouseOver;
        this.pauseText = resources.getPauseText();
        this.quitText = resources.getQuitText();
    }

    /**
     * makes the background opaque and opens the pause screen.
     * 
     * @param graphics
     *            graphics to draw with
     * @param container
     *            to draw in
     * @param input
     *            to find out where click events happened
     * @param game
     *            to be able to enter different game states from the menu
     * @param gameState
     *            to unpause the game when the main menu is entered
     */
    public void show(Graphics graphics, GameContainer container, Input input,
            StateBasedGame game, GameState gameState) {
        setup(graphics);
        draw(graphics, container);
        update(input, game, gameState);
    }

    /**
     * Checks for player input and acts according to mouse clicks in the right
     * location, which will unpause the screen.
     * 
     * @param input
     *            input to chec for mouse clicks
     * @param game
     *            game to enter an other state
     * @param gameState
     *            gamestate to set to false
     */
    private void update(Input input, StateBasedGame game, GameState gameState) {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOver.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "Game",
                        "Player left the game, to the main menu");
                gameState.getGame().setPaused(false);
                game.enterState(States.StartScreenState);
            }
        }
    }

    /**
     * Draws the elements of the pause screen in the container.
     * 
     * @param graphics
     *            graphics used to draw the elements
     * @param container
     *            container in which to draw in
     */
    private void draw(Graphics graphics, GameContainer container) {
        graphics.fillRect(0, 0, container.getWidth(), container.getHeight());
        graphics.drawImage(pauseText,
                container.getWidth() / 2 - pauseText.getWidth() / 2,
                container.getHeight() / 3.0f);
        graphics.drawImage(quitText,
                container.getWidth() / 2 - quitText.getWidth() / 2,
                container.getHeight() / 2.0f);
    }

    /**
     * Sets up the graphics for displaying the right background color.
     * 
     * @param graphics
     *            graphics used to set the color.
     */
    private void setup(Graphics graphics) {
        graphics.setAntiAlias(true);
        Color color = new Color(0f, 0f, 0f, 0.5f);
        graphics.setColor(color);
    }
}
