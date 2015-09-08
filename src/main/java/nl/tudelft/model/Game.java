package nl.tudelft.model;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Game implements Updateable {
    
    private ArrayList<Level> levels;
    private Level curLevel;
    private int prevLives;
    private int curLives;
    
    public Game(ArrayList<Level> levels) {
        this.levels = levels;
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // TODO Auto-generated method stub
        
    }

}
