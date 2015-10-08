package nl.tudelft.semgroup4;

import java.io.IOException;
import java.util.List;

import nl.tudelft.semgroup4.eventhandlers.InputKey;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput;
import nl.tudelft.semgroup4.util.KeyBindHelper;
import nl.tudelft.semgroup4.util.KeyBindingEntry;

public class Settings {

    static List<KeyBindingEntry> completeKeyBindings;

    /**
     * Private constructor to avoid instantiation of the settings class.
     */
    private Settings() {

    }
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
     * Create the inputs for player 1.
     * @return a PlayerInput for player 1.
     */
    public static PlayerInput getPlayer1Input() {
        return new PlayerInput(
                new InputKey(completeKeyBindings.get(0).getValue()),
                new InputKey(completeKeyBindings.get(1).getValue()),
                new InputKey(completeKeyBindings.get(2).getValue())
        );
    }

    /**
     * Create the inputs for player 2.
     * @return a PlayerInput for player 2.
     */
    public static PlayerInput getPlayer2Input() {
        return new PlayerInput(
                new InputKey(completeKeyBindings.get(3).getValue()),
                new InputKey(completeKeyBindings.get(4).getValue()),
                new InputKey(completeKeyBindings.get(5).getValue())
        );
    }

    public void setCompleteKeyBindings(List<KeyBindingEntry> keys) {
        completeKeyBindings = keys;
    }

    public void setPlayer1Left(int value) {
        completeKeyBindings.get(0).setValue(value);
    }

    public void setPlayer1Right(int value) {
        completeKeyBindings.get(1).setValue(value);
    }

    public void setPlayer1Shoot(int value) {
        completeKeyBindings.get(2).setValue(value);
    }

    public void setPlayer2Left(int value) {
        completeKeyBindings.get(3).setValue(value);
    }

    public void setPlayer2Right(int value) {
        completeKeyBindings.get(4).setValue(value);
    }

    public void setPlayer2Shoot(int value) {
        completeKeyBindings.get(5).setValue(value);
    }
}
