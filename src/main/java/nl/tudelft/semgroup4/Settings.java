package nl.tudelft.semgroup4;

import java.io.IOException;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.eventhandlers.InputKey;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.util.KeyBindHelper;

public abstract class Settings {

    static PlayerInput player1Input;
    static PlayerInput player2Input;

    /**
     * Initializes all the settings.
     */
    public static void init() {
        KeyBindHelper.load();
    }

    /**
     * Returns the current input for player 1.
     * 
     * @return {@link PlayerInput} - The input for player 1.
     */
    public static PlayerInput getPlayer1Input() {
        return player1Input;
    }

    /**
     * Sets the new input for player 2.
     * 
     * @param input
     *            {@link PlayerInput} - The new input for player 1.
     */
    public static void setPlayer1Input(PlayerInput input) {
        player1Input = input;
    }

    /**
     * Returns the current input for player 2.
     * 
     * @return {@link PlayerInput} - The input for player 2.
     */
    public static PlayerInput getPlayer2Input() {
        return player2Input;
    }

    /**
     * Sets the new input for player 2.
     * 
     * @param input
     *            {@link PlayerInput} - The new input for player 2.
     */
    public static void setPlayer2Input(PlayerInput input) {
        player2Input = input;
    }

    /**
     * Sets a new key for moving left for player 1.
     * 
     * @param newInput
     *            {@link InputKey} - The new key to use.
     */
    public static void setPlayer1LeftKey(InputKey newInput) {
        player1Input.setLeftInput(newInput);
    }

    /**
     * Sets a new key for moving right for player 1.
     * 
     * @param newInput
     *            {@link InputKey} - The new key to use.
     */
    public static void setPlayer1RightKey(InputKey newInput) {
        player1Input.setRightInput(newInput);
    }

    /**
     * Sets a new key for shooting for player 1.
     * 
     * @param newInput
     *            {@link InputKey} - The new key to use.
     */
    public static void setPlayer1ShootKey(InputKey newInput) {
        player1Input.setShootInput(newInput);
    }

    /**
     * Sets a new key for moving left for player 2.
     * 
     * @param newInput
     *            {@link InputKey} - The new key to use.
     */
    public static void setPlayer2LeftKey(InputKey newInput) {
        player2Input.setLeftInput(newInput);
    }

    /**
     * Sets a new key for moving right for player 2.
     * 
     * @param newInput
     *            {@link InputKey} - The new key to use.
     */
    public static void setPlayer2RightKey(InputKey newInput) {
        player2Input.setRightInput(newInput);
    }

    /**
     * Sets a new key for shooting for player 2.
     * 
     * @param newInput
     *            {@link InputKey} - The new key to use.
     */
    public static void setPlayer2ShootKey(InputKey newInput) {
        player2Input.setShootInput(newInput);
    }

    /**
     * Saves the current settings to an output file.
     * 
     * <p>
     * This saves the settings in between sessions.
     * </p>
     */
    public static void save() {
        try {
            KeyBindHelper.save();
        } catch (IOException e) {
            Game.LOGGER.log(LogSeverity.CRITICAL, "File",
                    "Saving the settings to a file failed.");
        }
    }

    /**
     * Reset the settings to their defaults.
     */
    public static void setDefaults() {
        try {
            KeyBindHelper.loadDefaults();
        } catch (IOException e) {
            Game.LOGGER.log(LogSeverity.CRITICAL, "File",
                    "Loading the default settings failed.");
        }
    }
}
