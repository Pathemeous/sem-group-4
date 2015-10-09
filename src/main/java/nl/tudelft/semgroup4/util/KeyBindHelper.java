package nl.tudelft.semgroup4.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class KeyBindHelper {

    public static final String FILENAME = "keyBindings.json";
    public static final String DEFAULTS = "defaults.json";
    private static String toLoad = FILENAME;
    public static final Charset encoding = Charset.forName("UTF-8");
    public static final String PLAYER1_LEFT_KEY  = "Player1Left";
    public static final String PLAYER2_LEFT_KEY  = "Player2Left";
    public static final String PLAYER1_RIGHT_KEY  = "Player1Right";
    public static final String PLAYER2_RIGHT_KEY  = "Player2Right";
    public static final String PLAYER1_SHOOT_KEY  = "Player1Shoot";
    public static final String PLAYER2_SHOOT_KEY  = "Player2Shoot";

    /**
     * Loads the keybinds from the keybinds file (see FILENAME).
     * @return a list of {@link KeyBindingEntry}s
     * @throws IOException When reading fails, this exception is thrown.
     */
    public static JSONObject load() throws IOException {

        // when file does not exist, create empty one
        if (!new File(FILENAME).exists()) {
            toLoad = DEFAULTS;
        }

        return loader(toLoad);
    }

    /**
     * Loads keys from a certain file.
     * @param toLoad - the file which we will load from
     * @return - the keybindings
     * @throws IOException - something went wrong.
     */
    public static JSONObject loader(String toLoad) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(toLoad));
        String jsonString = new String(encoded, encoding);

        JSONObject keybinds = new JSONObject(jsonString);

        save(keybinds);
        return keybinds;
    }

    /**
     * Save a list of {@link KeyBindingEntry}s to the keybindings file. See FILENAME for filename.
     * All contents are overridden, this does NOT append to current scores.
     * @param keybinds a list of {@link KeyBindingEntry}
     * @throws IOException When writing fails somehow.
     */
    public static void save(JSONObject keybinds) throws IOException {
        OpenOption[] options = {
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
        };

        Files.write(new File(FILENAME).toPath(), keybinds.toString().getBytes(), options);
    }
}
