package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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
    private final ResourcesWrapper resources = new ResourcesWrapper();
    
    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        System.out.println("High scores initiated!");
        backButton = new MouseOverArea(container, resources.getBackText(),
                container.getWidth() / 10, container.getHeight() / 10 * 9);
        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {
        graphics.drawImage(resources.getBackText(), container.getWidth() / 10.0f,
                container.getHeight() / 10 * 9);
        
        Font font = new Font("Calibri", Font.BOLD, 46);
        TrueTypeFont typeFont = new TrueTypeFont(font, true);
        typeFont.drawString(container.getWidth() / 2 - 60.0f, 30.0f, "HIGHSCORES", Color.yellow);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) 
            throws SlickException {
        if (backButton.isMouseOver()) {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Game.LOGGER.log(LogSeverity.DEBUG, "HighscoresMenu", 
                        "User goes back to main menu" );
                game.enterState(0);
            }
        }
    }

    @Override
    public int getID() {
        return 4;
    }

}
