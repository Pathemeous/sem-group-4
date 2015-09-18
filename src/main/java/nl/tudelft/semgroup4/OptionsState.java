package nl.tudelft.semgroup4;

import nl.tudelft.semgroup4.util.Audio;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionsState extends BasicGameState {
    /**
     * Checks for mouseEvents.
     */
    private MouseOverArea soundOnOff;
    private MouseOverArea backButton;
    private Input input;

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        soundOnOff = new MouseOverArea(container, Resources.on,
                container.getWidth() / 4 * 3,
                container.getHeight() / 4);
        backButton = new MouseOverArea(container, Resources.backText,
                container.getWidth() / 10, container.getHeight() / 10 * 9);
        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        graphics.drawImage(Resources.optionsText,
                container.getWidth() / 2 - Resources.optionsText.getWidth() / 2, 
                container.getHeight() / 6.0f);    
        graphics.drawImage(Resources.backText, container.getWidth() / 10.0f,
                container.getHeight() / 10 * 9);
        graphics.drawImage(Resources.soundText, container.getWidth() / 4.0f,
                container.getHeight() / 4.0f); 
        if (Audio.musicOn) {
            graphics.drawImage(Resources.on, container.getWidth() / 4 * 3,
                    container.getHeight() / 4.0f); 
        }
        if (!Audio.musicOn) {
            graphics.drawImage(Resources.off, container.getWidth() / 4 * 3,
                    container.getHeight() / 4.0f); 
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) {
        if (soundOnOff.isMouseOver()) { 
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Audio.musicOn = !Audio.musicOn;
            }
        }
        if (backButton.isMouseOver()) {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                game.enterState(0);
            }
        }
    }



    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 3;
    }

}
