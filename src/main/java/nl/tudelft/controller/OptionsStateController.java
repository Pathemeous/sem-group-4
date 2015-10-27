package nl.tudelft.controller;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.view.LoggerSetScreen;
import nl.tudelft.view.OptionsState;
import nl.tudelft.view.States;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class OptionsStateController {

    private final OptionsState state;
    private final ResourcesWrapper resources;

    /**
     * Create a new controller
     * @param state
     *              the OptionsState to which this controller belongs.
     * @param res
     *              the ResourcesWrapper used by the OptionsState.
     */
    public OptionsStateController(OptionsState state, ResourcesWrapper res) {
        this.state = state;
        this.resources = res;
    }

    /**
     * Get the music image depending on if sound is enabled or not.
     * @return - Image
     *              the image belonging to the curront state of the sound
     */
    public Image getMusicImage() {
        if (resources.isMusicOn()) {
            return resources.getOn();
        } else {
            return resources.getOff();
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
    public void showLoggerScreen(boolean enabled, Graphics graphics,
        GameContainer container, Input input, LoggerSetScreen screen) {
        if (enabled) {
            screen.show(graphics, container, input, state);
        }
    }

    /**
     * Toggles the loggerSetEnabled.
     */
    public void toggleLoggerScreen() {
        state.setLoggerSetEnabled(!state.isLoggerSetEnabled());
    }

    /**
     * Gets the ID for OptionsState.
     * @return - int
     *              the ID for OptionsState
     */
    public int getID() {
        return States.OptionsState;
    }

    /**
     * Toggles the music on and off.
     */
    public void toggleMusic() {
        resources.setMusicOn(!resources.isMusicOn());
    }

    /**
     * Get the ResourcesWrapper (only used for testing).
     * @return - ResourcesWrapper
     *              the ResourcesWrapper used by the controller
     */
    public ResourcesWrapper getResources() {
        return resources;
    }
}
