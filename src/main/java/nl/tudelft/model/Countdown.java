package nl.tudelft.model;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Countdown implements Renderable {
    
    private final Game game;
    private int countdownTimer = 180;
    private final TrueTypeFont typeFont;
    
    /**
     * Constructor of this class. Creates a countdown object.
     * @param game : the game that this countdown belongs to.
     * @param resources : resources wrapper.
     */
    public Countdown(Game game, ResourcesWrapper resources) {
        this.game = game;
        game.setPaused(true);
        typeFont = resources.getCountdownFont();
    }
    
    /**
     * Resets the countdown, so that it starts over.
     */
    public void reset() {
        game.setPaused(true);
        countdownTimer = 180;
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        if (countdownTimer > 0) {
            String count = Integer.toString(countdownTimer / 60 + 1);
            typeFont.drawString(container.getWidth() / 2 - 25, 100.0f, count, Color.black);
        }
    }
    
    /**
     * Updates the countdown. As long as the counter is higher than 0, one is subtracted from it.
     * When the counter equals 0, the game is unpaused.
     */
    public void update() {
        if (countdownTimer > 0) {
            countdownTimer--;
        } else if (countdownTimer == 0) {
            game.setPaused(false);
            countdownTimer--;
        } 
    }
    
    protected int getCounter() {
        return countdownTimer;
    }
    
    protected void setCounter(int counter) {
        countdownTimer = counter;
    }
}
