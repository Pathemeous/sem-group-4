package nl.tudelft.semgroup4;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

public class PauseScreen {
    private MouseOverArea mouseOver;
    private Image pauseText = Resources.pauseText;
    private Image quitText = Resources.quitText;

    public PauseScreen(MouseOverArea mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * makes the background opaque and opens the pause screen
     * 
     * @param g
     *            graphics to draw with
     * @param container
     *            to draw in
     * @param input
     *            to find out where clickevents happened
     * @param game
     *            to be able to enter different game states from the menu
     * @param gameState
     *            to unpause the game when the main menu is entered
     */
    public void show(Graphics g, GameContainer container, Input input, StateBasedGame game,
            GameState gameState) {
        g.setColor(Color.yellow);
        g.setAntiAlias(true);
        Color color = new Color(0f, 0f, 0f, 0.5f);
        g.setColor(color);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());
        g.drawImage(pauseText, container.getWidth() / 2 - pauseText.getWidth() / 2,
                container.getHeight() / 3);
        g.drawImage(quitText, container.getWidth() / 2 - quitText.getWidth() / 2,
                container.getHeight() / 2);

        // checks to see if the user clicked on the "back to main menu button",
        // if so, the state is set to main menu and the gameState unpauses
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOver.isMouseOver()) {
                gameState.paused = false;
                game.enterState(0);
            }
        }
    }
}
