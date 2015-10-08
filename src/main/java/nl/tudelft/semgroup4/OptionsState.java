package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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
    private KeySetScreen keySetScreen;
    private MouseOverArea soundOnOff;
    private MouseOverArea backButton;
    private Input input;
    private boolean keySetEnabled = false;
    private final ResourcesWrapper resources = new ResourcesWrapper();

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        soundOnOff = new MouseOverArea(container, resources.getOn(),
                container.getWidth() / 4 * 3,
                container.getHeight() / 4);
        backButton = new MouseOverArea(container, resources.getBackText(),
                container.getWidth() / 10, container.getHeight() / 10 * 9);
        input = container.getInput();
        MouseOverArea keyCancel =
                new MouseOverArea(container, resources.getQuitText(), container.getHeight() / 2,
                container.getHeight() / 2, resources.getQuitText().getWidth(), resources
                .getQuitText().getHeight());
        keySetScreen = new KeySetScreen(resources, keyCancel);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) {
        graphics.drawImage(resources.getOptionsText(),
                container.getWidth() / 2 - resources.getOptionsText().getWidth() / 2, 
                container.getHeight() / 6.0f);    
        graphics.drawImage(resources.getBackText(), container.getWidth() / 10.0f,
                container.getHeight() / 10 * 9);
        graphics.drawImage(resources.getSoundText(), container.getWidth() / 4.0f,
                container.getHeight() / 4.0f); 
        if (ResourcesWrapper.musicOn) {
            graphics.drawImage(resources.getOn(), container.getWidth() / 4 * 3,
                    container.getHeight() / 4.0f); 
        }
        if (!ResourcesWrapper.musicOn) {
            graphics.drawImage(resources.getOff(), container.getWidth() / 4 * 3,
                    container.getHeight() / 4.0f); 
        }
        if (keySetEnabled) {
            keySetScreen.show(graphics, container, input, this);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) {
        if (soundOnOff.isMouseOver()) { 
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                
                Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu", 
                        "User toggled sound " + (ResourcesWrapper.musicOn ? "off" : "on") );
                ResourcesWrapper.musicOn = !ResourcesWrapper.musicOn;
            }
        }
        if (backButton.isMouseOver()) {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Game.LOGGER.log(LogSeverity.DEBUG, "OptionsMenu", 
                        "User goes back to main menu" );
                game.enterState(0);
            }
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            toggleKeySet();
        }
    }

    public void toggleKeySet() {
        keySetEnabled = !keySetEnabled;
    }



    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 3;
    }

}
