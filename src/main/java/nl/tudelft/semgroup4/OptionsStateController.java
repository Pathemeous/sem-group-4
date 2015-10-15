package nl.tudelft.semgroup4;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class OptionsStateController {

    private OptionsState state;
    private ResourcesWrapper res;

    /**
     * Create a new controller
     * @param state
     *              the OptionsState to which this controller belongs.
     * @param res
     *              the ResourcesWrapper used by the OptionsState.
     */
    protected OptionsStateController(OptionsState state, ResourcesWrapper res) {
        this.state = state;
        this.res = res;
    }

    /**
     * Get the music image depending on if sound is enabled or not.
     * @return - Image
     *              the image belonging to the curront state of the sound
     */
    protected Image getMusicImage() {
        if (res.musicOn) {
            return res.getOn();
        } else {
            return res.getOff();
        }
    }

    /**
     * Show the logger screen.
     * @param enabled
     *              if the logger screen is enabled or not
     * @param graphics
     *              the graphics used for rendering
     * @param container
     *              the container in which the game will be rendered
     * @param input
     *              the input used for pressing buttons
     * @param screen
     *              the logger set screen to display
     */
    protected void showLoggerScreen(boolean enabled, Graphics graphics,
        GameContainer container, Input input, LoggerSetScreen screen) {
        if (enabled) {
            screen.show(graphics, container, input, state);
        }
    }

    /**
     * Toggles the loggerSetEnabled.
     */
    protected void toggleLoggerScreen() {
        state.loggerSetEnabled = !state.loggerSetEnabled;
    }

    /**
     * Gets the ID for OptionsState
     * @return - int
     *              the ID for OptionsState
     */
    protected int getId() {
        return States.OptionsState;
    }

    /**
     * Toggles the music on and off.
     */
    protected void toggleMusic() {
        res.musicOn = !res.musicOn;
    }
}
