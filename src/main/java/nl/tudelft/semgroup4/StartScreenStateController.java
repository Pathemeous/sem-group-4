package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Controller that performs logic for the StartScreenState.
 * 
 * @author Pathemeous
 *
 */
public class StartScreenStateController {

    private StartScreenState state;
    private ResourcesWrapper resources;

    private final String highScoreText = "HIGHSCORES";
    private TrueTypeFont typeFont;

    /**
     * Creates a new controler for a specific {@link StartScreenState}.
     * 
     * @param state
     *            {@link StartScreenState} - The state that this controller is assigned to.
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this controller may use.
     */
    public StartScreenStateController(StartScreenState state, ResourcesWrapper resources) {
        this.state = state;
        this.resources = resources;
    }

    /**
     * Creates the HighScore button with the appropriate text.
     * 
     * @throws SlickException
     *             If creating the {@link Image} goes wrong.
     */
    protected MouseOverArea createHighscoresButton(GameContainer container)
            throws SlickException {
        Shape shape =
                new Rectangle(650, 650, typeFont.getWidth(highScoreText),
                        typeFont.getHeight());
        shape = shape.transform(Transform.createRotateTransform(6, 650, 650));
        Font font = new Font("Verdana", Font.BOLD, 36);
        typeFont = resources.createFont(font, true);
        Image highScoreButton =
                new Image(typeFont.getWidth(highScoreText), typeFont.getHeight());

        shape =
                new Rectangle(650, 650, typeFont.getWidth(highScoreText),
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
    protected void drawHighScoresButton(Graphics graphics) {
        graphics.rotate(650, 650, 340);
        typeFont.drawString(650, 650, highScoreText, Color.red);
    }

}
