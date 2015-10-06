package nl.tudelft.semgroup4;

import java.awt.Font;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.CompareHighscores;
import nl.tudelft.semgroup4.util.HighscoreEntry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    
    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        backButton = new MouseOverArea(container, resources.getBackText(),
                container.getWidth() / 10, container.getHeight() / 10 * 9);
        input = container.getInput();
        
        JSONParser parser = new JSONParser();
        
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("scores.json"));
            
            for (Object object : array) {
                JSONObject highscore = (JSONObject)object;
                
                String name = (String)highscore.get("name");
                long score = (long)highscore.get("score");
                highscores.add(new HighscoreEntry(name, score));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        
        highscores.sort(new CompareHighscores());
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {
        graphics.drawImage(resources.getBackText(), container.getWidth() / 10.0f,
                container.getHeight() / 10 * 9);
        
        Font font = new Font("Calibri", Font.BOLD, 46);
        TrueTypeFont typeFont = new TrueTypeFont(font, true);
        
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
            game.enterState(0);
        }
    }

    @Override
    public int getID() {
        return 4;
    }

}
