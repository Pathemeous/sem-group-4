package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class CountdownController implements Renderable {
    
    private Game game;
    private int countdown = 180;
    private TrueTypeFont typeFont;
    
    /**
     * Constructor of this class. Creates a countdown object.
     * @param game : the game that this countdown belongs to.
     * @param resources : resources wrapper.
     */
    public CountdownController(Game game, ResourcesWrapper resources) {
        this.game = game;
        game.setPaused(true);
        typeFont = resources.getCountdownFont();
    }
    
    /**
     * Resets the countdown, so that it starts over.
     */
    public void reset() {
        game.setPaused(true);
        countdown = 180;
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        if (countdown > 0) {
            String count = Integer.toString(countdown / 60 + 1);
            typeFont.drawString(container.getWidth() / 2 - 25, 100.0f, count, Color.black);
        }
    }
    
    /**
     * Updates the countdown. As long as the counter is higher than 0, one is subtracted from it.
     * When the counter equals 0, the game is unpaused.
     */
    public void update() {
        if (countdown > 0) {
            countdown--;
        } else if (countdown == 0) {
            game.setPaused(false);
            countdown--;
        } 
    }
    
    protected int getCounter() {
        return countdown;
    }
    
    protected void setCounter(int counter) {
        countdown = counter;
    }
}
