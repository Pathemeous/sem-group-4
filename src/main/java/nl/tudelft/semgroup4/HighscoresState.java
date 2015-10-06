package nl.tudelft.semgroup4;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HighscoresState extends BasicGameState {
    
    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        System.out.println("High scores initiated!");
    }

    @Override
    public void render(GameContainer container, StateBasedGame mainApp, Graphics graphics)
            throws SlickException {
        Font font = new Font("Calibri", Font.BOLD, 36);
        TrueTypeFont typeFont = new TrueTypeFont(font, true);
        
        typeFont.drawString(container.getWidth() / 2 - 20.0f, 30.0f, "Highscores", Color.yellow);
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
        
    }

    @Override
    public int getID() {
        return 4;
    }

}
