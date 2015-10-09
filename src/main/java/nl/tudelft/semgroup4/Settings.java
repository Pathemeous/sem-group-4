package nl.tudelft.semgroup4;

import java.io.IOException;

import nl.tudelft.semgroup4.eventhandlers.InputKey;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput;
import nl.tudelft.semgroup4.util.KeyBindHelper;

import org.json.JSONObject;

public abstract class Settings {

    private static JSONObject completeKeyBindings;

    /**
     * Initialises all the keys
     * This has to be in a try catch block. The file will always be found
     * unless a user has deleted the json.
     */
    public static void init() {
        try {
            completeKeyBindings = KeyBindHelper.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @return : returns the current settings.
     */
    public static JSONObject getSettings() {
        return completeKeyBindings;
    }

    /**
     * Create the inputs for player 1.
     * @return a PlayerInput for player 1.
     */
    public static PlayerInput getPlayer1Input() {
        return new PlayerInput(
                new InputKey(getPlayer1LeftValue()),
                new InputKey(getPlayer1RightValue()),
                new InputKey(getPlayer1ShootValue())
        );
    }

    /**
     * Create the inputs for player 2.
     * @return a PlayerInput for player 2.
     */
    public static PlayerInput getPlayer2Input() {
        return new PlayerInput(
                new InputKey(getPlayer2LeftValue()),
                new InputKey(getPlayer2RightValue()),
                new InputKey(getPlayer2ShootValue())
        );
    }
    
    public static int getPlayer1LeftValue() {
        return completeKeyBindings.getInt(KeyBindHelper.PLAYER1_LEFT_KEY);
    }
    
    public static int getPlayer2LeftValue() {
        return completeKeyBindings.getInt(KeyBindHelper.PLAYER2_LEFT_KEY);
    }
    
    public static int getPlayer1RightValue() {
        return completeKeyBindings.getInt(KeyBindHelper.PLAYER1_RIGHT_KEY);
    }
    
    public static int getPlayer2RightValue() {
        return completeKeyBindings.getInt(KeyBindHelper.PLAYER2_RIGHT_KEY);
    }
    
    public static int getPlayer1ShootValue() {
        return completeKeyBindings.getInt(KeyBindHelper.PLAYER1_SHOOT_KEY);
    }
    
    public static int getPlayer2ShootValue() {
        return completeKeyBindings.getInt(KeyBindHelper.PLAYER2_SHOOT_KEY);
    }

    public static void setPlayer1Left(int value) {
        completeKeyBindings.put(KeyBindHelper.PLAYER1_LEFT_KEY, value);
    }

    public static void setPlayer1Right(int value) {
        completeKeyBindings.put(KeyBindHelper.PLAYER1_RIGHT_KEY, value);
    }

    public static void setPlayer1Shoot(int value) {
        completeKeyBindings.put(KeyBindHelper.PLAYER1_SHOOT_KEY, value);
    }

    public static void setPlayer2Left(int value) {
        completeKeyBindings.put(KeyBindHelper.PLAYER2_LEFT_KEY, value);
    }

    public static void setPlayer2Right(int value) {
        completeKeyBindings.put(KeyBindHelper.PLAYER2_RIGHT_KEY, value);
    }

    public static void setPlayer2Shoot(int value) {
        completeKeyBindings.put(KeyBindHelper.PLAYER2_SHOOT_KEY, value);
    }

    /**
     * Reset the settings to their defaults.
     */
    public static void setDefault() {
        try {
            completeKeyBindings = KeyBindHelper.loader("defaults.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
