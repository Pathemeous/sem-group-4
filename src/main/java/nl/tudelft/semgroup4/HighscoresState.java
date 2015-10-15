package nl.tudelft.semgroup4;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.HighscoreEntry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HighscoresState extends BasicGameState {
    
    private MouseOverArea backButton;
    private Input input;
    private HighscoresStateController controller;
    private ResourcesWrapper resources = new ResourcesWrapper();
    private List<HighscoreEntry> highscores = new ArrayList<>();
    private TrueTypeFont typeFont;

    /**
     * Create a new HighscoresState with its controller.
     */
    public HighscoresState() {
        controller = new HighscoresStateController();
    }

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        input = container.getInput();

        backButton = new MouseOverArea(container, resources.getBackText(),
                container.getWidth() / 10, container.getHeight() / 10 * 9);

        highscores = controller.getScores();

        Font font = new Font("Calibri", Font.BOLD, 46);
        typeFont = resources.createFont(font, true);

    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {

        renderBackground(container, graphics);

        float horizontalLoc = 60.0f;
        float verticalLoc = 30.0f;

        renderText(horizontalLoc, verticalLoc, "HIGHSCORES", Color.yellow);
        
        for (int i = 0; i < 10; i++) {
            verticalLoc += 50;

            String position = Integer.toString(i + 1) + ".";
            renderText(horizontalLoc, verticalLoc, position, Color.white);

            float containerLoc = container.getWidth() - 250;

            renderText(horizontalLoc + 100, verticalLoc,
                    controller.getPlayerName(i, highscores), Color.orange);

            renderText(containerLoc, verticalLoc,
                    controller.getPlayerScore(i, highscores), Color.red);
        }
    }

    /**
     * Render the background.
     * @param container
     *                  the container in which to render
     * @param graphics
     *                  the graphics with which to render
     */
    private void renderBackground(GameContainer container, Graphics graphics) {
        graphics.drawImage(resources.getBackText(),
                container.getWidth()  / 10.0f,
                container.getHeight() / 10 * 9);
    }

    /**
     * Render text.
     * @param horizontalLoc
     *                  the horizontal location at which to render
     * @param verticalLoc
     *                  the vertical location at which to render
     * @param string
     *                  the string to render
     * @param color
     *                  the color with which to render the string
     */
    private void renderText(float horizontalLoc, float verticalLoc,
                            String string, Color color) {
        typeFont.drawString(horizontalLoc, verticalLoc,
                string, color);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) 
            throws SlickException {
        if (backButton.isMouseOver() 
                && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "HighscoresMenu", 
                    "User goes back to main menu" );
            game.enterState(States.StartScreenState);
        }
    }

    @Override
    public int getID() {
        return controller.getID();
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
     * Set the controller (only used for testing).
     * @param controller
     *                  the controller which will be used for testing
     */
    protected void setController(HighscoresStateController controller) {
        this.controller = controller;
    }

    /**
     * Set the mouse over area (only used for testing).
     * @param area
     *                  the MouseOverArea which will be set
     */
    protected void setBackButton(MouseOverArea area) {
        this.backButton = area;
    }

    /**
     * Set the input for testing.
     * @param input
     *                  the input which will be set
     */
    protected void setInputForTesting(Input input) {
        this.input = input;
    }
}
