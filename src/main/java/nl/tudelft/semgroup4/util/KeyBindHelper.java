package nl.tudelft.semgroup4.util;

import java.io.IOException;
import java.util.HashMap;

import nl.tudelft.semgroup4.Settings;
import nl.tudelft.semgroup4.eventhandlers.InputKey;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput;

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
            json = JSONParser.loadJSON(FILENAME);
        } catch (IOException e) {
            json = JSONParser.loadJSON(DEFAULTS);
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

        HashMap<String, Integer> jsonMap = new HashMap<String, Integer>();
        jsonMap.put(PLAYER1_LEFT_KEY, player1Input.getLeftInput().getKeyCode());
        jsonMap.put(PLAYER1_RIGHT_KEY, player1Input.getRightInput().getKeyCode());
        jsonMap.put(PLAYER1_SHOOT_KEY, player1Input.getShootInput().getKeyCode());

        jsonMap.put(PLAYER2_LEFT_KEY, player2Input.getLeftInput().getKeyCode());
        jsonMap.put(PLAYER2_RIGHT_KEY, player2Input.getRightInput().getKeyCode());
        jsonMap.put(PLAYER2_SHOOT_KEY, player2Input.getShootInput().getKeyCode());

        return new JSONObject(jsonMap);
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
        JSONParser.save(json, FILENAME);
    }

    /**
     * Updates the Settings to the defaults as specified in the DEFAULTS file.
     * 
     * <p>
     * Overwrites any custom keybindings that were specified in the FILENAME file with their
     * default values.
     * </p>
     * 
     * @throws IOException
     *             If the defaults file cannot be found.
     */
    public static void loadDefaults() throws IOException {
        JSONObject json = JSONParser.loadJSON(DEFAULTS);
        updateSettings(json);
        JSONParser.save(json, FILENAME);
    }
}
