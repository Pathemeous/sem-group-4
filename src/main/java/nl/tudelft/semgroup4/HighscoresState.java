package nl.tudelft.semgroup4;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.CompareHighscores;
import nl.tudelft.semgroup4.util.HighscoreEntry;
import nl.tudelft.semgroup4.util.HighscoresHelper;

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
    private final List<HighscoreEntry> highscores = new ArrayList<>();
    private static final Font font = new Font("Calibri", Font.BOLD, 46);
    private static final TrueTypeFont typeFont = new TrueTypeFont(font, true);


    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        backButton = new MouseOverArea(container, resources.getBackText(),
                container.getWidth() / 10, container.getHeight() / 10 * 9);
        input = container.getInput();

        highscores.clear();
        try {
            highscores.addAll(HighscoresHelper.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        highscores.sort(new CompareHighscores());
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {
        graphics.drawImage(resources.getBackText(),
                container.getWidth()  / 10.0f,
                container.getHeight() / 10 * 9);

        float horizontalLoc = 60.0f;
        float verticalLoc = 30.0f;
        
        typeFont.drawString(horizontalLoc, verticalLoc, 
                "HIGHSCORES", Color.yellow);
        
        for (int i = 0; i < 10; i++) {
            verticalLoc += 50;
            String position = Integer.toString(i + 1) + ".";
            typeFont.drawString(horizontalLoc, verticalLoc, 
                    position, Color.white);
            
            if (highscores.size() > i) {
                typeFont.drawString(horizontalLoc + 100, verticalLoc, 
                        highscores.get(i).getName(), Color.orange);
                
                String score = Long.toString(highscores.get(i).getScore());
                typeFont.drawString(container.getWidth() - 250, verticalLoc, 
                        score, Color.red);
            } else {
                typeFont.drawString(horizontalLoc + 100, verticalLoc, 
                        "-", Color.orange);
                typeFont.drawString(container.getWidth() - 250, verticalLoc, 
                        "-", Color.red);
            }
        }
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
        return States.HighscoresState;
    }

}
