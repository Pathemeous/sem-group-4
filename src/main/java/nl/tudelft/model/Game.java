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
    private int prevLives = 0;
    private int curLives;
    
    /**
     * Creates a Game with its levels and players.
     * Note that the levels and players must both contain at least one object.
     * 
     * @param levels ArrayList<Level> - List containing all levels that the game consists of.
     * @param players ArrayList<Player> - List containing all players that take part in this game.
     * @throws IllegalArgumentException - If <code>levels</code> or <code>players</code> is empty.
     */
    public Game(ArrayList<Level> levels, ArrayList<Player> players) throws IllegalArgumentException {
        this.levels = levels;
        this.players = players;
        
        this.levelIt = this.levels.iterator();
        
        if (!this.levelIt.hasNext() || this.players.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.curLevel = this.levelIt.next();
        this.curLives = this.getPlayerLives();        
    }
    
    /**
     * Sets the current level.
     * @param level Level object to set as the current level.
     */
    private void setCurLevel(Level level) {
        this.curLevel = level;
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Returns the amount of lives that the players have left.
     * 
     * When the players run out of lives, the game is over.
     * 
     * 
     * @return int - the total amount of lives left until the game is over.
     */
    private int getPlayerLives() {
        int result = 0;
        for (Player player : players) {
            // TODO: add player.getLives() method.
        }
        return result;
    }
    
    public void levelCompleted() {
        if (levelIt.hasNext()) {
            setCurLevel(levelIt.next());
        } else {
            gameCompleted();
        }
    }
    
    private void gameCompleted() {
        // TODO
    }
    
    public void gameOver() {
        // TODO
    }

    
}
