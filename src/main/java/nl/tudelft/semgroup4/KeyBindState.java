package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
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
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class KeyBindState extends BasicGameState {

    private MouseOverArea backButton;
    private MouseOverArea saveButton;
    private Image backImage;
    private Image saveImage;
    private Shape backShape;
    private Shape saveShape;
    private String back = "Back";
    private String save = "Save";
    private Input input;
    private final ResourcesWrapper resources = new ResourcesWrapper();
    private static final Font font = new Font("Calibri", Font.BOLD, 46);
    private static final TrueTypeFont typeFont = new TrueTypeFont(font, true);

    @Override
    public void init(GameContainer container, StateBasedGame mainApp)
            throws SlickException {

        backImage = new Image(typeFont.getWidth(back), typeFont.getHeight());
        backShape = new Rectangle(container.getWidth() / 10,
                container.getHeight() / 10 * 9,
                typeFont.getWidth(back), typeFont.getHeight());
        backButton = new MouseOverArea(container, backImage, backShape);

        saveImage = new Image(typeFont.getWidth(save), typeFont.getHeight());
        saveShape = new Rectangle((container.getWidth()) - (container.getWidth() / 10),
                container.getHeight() / 10 * 9,
                typeFont.getWidth(save), typeFont.getHeight());
        saveButton = new MouseOverArea(container, saveImage, saveShape);

        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {
        typeFont.drawString(container.getWidth() / 10,
                container.getHeight() / 10 * 9,
                back,
                Color.yellow);
        typeFont.drawString((container.getWidth()) - (container.getWidth() / 10),
                container.getHeight() / 10 * 9,
                save,
                Color.yellow);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (backButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User goes back to options menu");
                game.enterState(States.OptionsState);
            }
            if (saveButton.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "KeyBindMenu",
                        "User saves the keybinds to file");
                System.out.println("TODO: Add Saving");
            }
        }
    }

    @Override
    public int getID() {
        return States.KeyBindState;
    }
}
