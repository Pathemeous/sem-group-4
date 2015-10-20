package nl.tudelft.controller.util;

import java.io.File;
import java.io.IOException;

import nl.tudelft.controller.Settings;
import nl.tudelft.controller.eventhandlers.InputKey;
import nl.tudelft.controller.eventhandlers.PlayerInput;

import org.json.JSONObject;

/**
 * A Helper that converts KeyBindings information to JSON and vice versa.
 * 
 * <p>
 * This class can be used to save and load keybindings.
 * </p>
 * 
 * @author Pathemeous
 *
 */
public class KeyBindHelper {

    public static final String FILENAME = "keyBindings.json";
    public static final String DEFAULTS = "defaults.json";
    public static final String PLAYER1_LEFT_KEY = "Player1Left";
    public static final String PLAYER2_LEFT_KEY = "Player2Left";
    public static final String PLAYER1_RIGHT_KEY = "Player1Right";
    public static final String PLAYER2_RIGHT_KEY = "Player2Right";
    public static final String PLAYER1_SHOOT_KEY = "Player1Shoot";
    public static final String PLAYER2_SHOOT_KEY = "Player2Shoot";

    /**
     * Attempts to load the input settings as specified in the FILENAME file. If this file is not
     * found, the DEFAULTS file is used instead. The input settings found in either of these files
     * is written to the Settings class.
     * 
     * @throws IOException
     *             If the Default key bindings were not found.
     */
    public static void load() throws IOException {
        JSONObject json;

        try {
            File customFile = new File(FILENAME);
            json = JSONParser.loadJSON(customFile);
        } catch (IOException e) {
            File defaultsFile = new File(DEFAULTS);
            json = JSONParser.loadJSON(defaultsFile);
        }

        updateSettings(json);
    }

    /**
     * Returns the input for player 1 as specified by the json at the current time.
     * 
     * @return {@link PlayerInput} - The input for player 1.
     */
    private static PlayerInput getPlayer1Input(JSONObject json) {
        return new PlayerInput(new InputKey(json.getInt(PLAYER1_LEFT_KEY)), new InputKey(
                json.getInt(PLAYER1_RIGHT_KEY)), new InputKey(json.getInt(PLAYER1_SHOOT_KEY)));
    }

    /**
     * Returns the input for player 2 as specified by the json at the current time.
     * 
     * @return {@link PlayerInput} - The input for player 2.
     */
    private static PlayerInput getPlayer2Input(JSONObject json) {
        return new PlayerInput(new InputKey(json.getInt(PLAYER2_LEFT_KEY)), new InputKey(
                json.getInt(PLAYER2_RIGHT_KEY)), new InputKey(json.getInt(PLAYER2_SHOOT_KEY)));
    }

    /**
     * Converts the input settings to JSON.
     * 
     * @return {@link JSONObject} - the JSON representation of the input settings.
     */
    private static JSONObject toJSON() {
        PlayerInput player1Input = Settings.getPlayer1Input();
        PlayerInput player2Input = Settings.getPlayer2Input();

        JSONObject json = new JSONObject();
        json.put(PLAYER1_LEFT_KEY, player1Input.getLeftInput().getKeyCode());
        json.put(PLAYER1_RIGHT_KEY, player1Input.getRightInput().getKeyCode());
        json.put(PLAYER1_SHOOT_KEY, player1Input.getShootInput().getKeyCode());

        json.put(PLAYER2_LEFT_KEY, player2Input.getLeftInput().getKeyCode());
        json.put(PLAYER2_RIGHT_KEY, player2Input.getRightInput().getKeyCode());
        json.put(PLAYER2_SHOOT_KEY, player2Input.getShootInput().getKeyCode());

        return json;
    }

    private static void updateSettings(JSONObject json) {
        Settings.setPlayer1Input(getPlayer1Input(json));
        Settings.setPlayer2Input(getPlayer2Input(json));
    }

    /**
     * Attempts to save the input settings in JSON at the custom keybinds location.
     * 
     * @throws IOException
     *             When saving the file goes wrong.
     */
    public static void save() throws IOException {
        JSONObject json = toJSON();
        File saveFile = new File(FILENAME);
        JSONParser.save(json, saveFile);
    }

    /**
     * Updates the Settings to the defaults as specified in the DEFAULTS file.
     * 
     * <p>
     * Does not overwrite any custom keybindings that were specified in the FILENAME file. To
     * achieve this, a separate {@link KeyBindHelper#save()} call must be made.
     * </p>
     * 
     * @throws IOException
     *             If the defaults file cannot be found.
     */
    public static void loadDefaults() throws IOException {
        File defaultFile = new File(DEFAULTS);
        JSONObject json = JSONParser.loadJSON(defaultFile);
        updateSettings(json);
    }
}
