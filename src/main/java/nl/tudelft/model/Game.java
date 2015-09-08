package nl.tudelft.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Game implements Updateable {
    
    private ArrayList<Level> levels;
    private Iterator<Level> levelIt;
    private ArrayList<Player> players;
    private Level curLevel;
    private int prevLives;
    private int curLives;
    
    /**
     * Creates a Game with its levels and players.
     * 
     * @param levels ArrayList<Level> - List containing all levels that the game consists of.
     * @param players ArrayList<Player> - List containing all players that take part in this game.
     * @throws IllegalArgumentException - Iff <code>levels</code> is empty.
     */
    public Game(ArrayList<Level> levels, ArrayList<Player> players) throws IllegalArgumentException {
        this.levels = levels;
        this.players = players;
        
        this.levelIt = this.levels.iterator();
        this.curLevel = this.levelIt.next();
        
        
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // TODO Auto-generated method stub
        
    }

}
