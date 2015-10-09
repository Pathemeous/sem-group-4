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

    /**
     * Loads the keybinds from the keybinds file (see FILENAME).
     * @return a list of {@link KeyBindingEntry}s
     * @throws IOException When reading fails, this exception is thrown.
     */
    public static List<KeyBindingEntry> load() throws IOException {

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
    public static List<KeyBindingEntry> loader(String toLoad) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(toLoad));
        String jsonString = new String(encoded, encoding);

        final List<KeyBindingEntry> keybinds = new ArrayList<>();

        JSONArray array = new JSONArray(jsonString);

        for (Object object : array) {
            JSONObject keybind = (JSONObject)object;

            String key = (String)keybind.get("key");
            int value = keybind.getInt("value");
            keybinds.add(new KeyBindingEntry(key, value));
        }

        save(keybinds);
        return sort(keybinds);
    }

    /**
     * Save a list of {@link KeyBindingEntry}s to the keybindings file. See FILENAME for filename.
     * All contents are overridden, this does NOT append to current scores.
     * @param keybinds a list of {@link KeyBindingEntry}
     * @throws IOException When writing fails somehow.
     */
    public static void save(List<KeyBindingEntry> keybinds) throws IOException {

        JSONArray array = new JSONArray();
        for (KeyBindingEntry entry : keybinds) {
            JSONObject entryJsonObject = new JSONObject();
            entryJsonObject.put("key", entry.getKey());
            entryJsonObject.put("value", entry.getValue());
            array.put(entryJsonObject);
        }

        OpenOption[] options = {
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
        };

        Files.write(new File(FILENAME).toPath(), array.toString().getBytes(), options);

    }

    /**
     * Sort the list of inputs based on what key they are bound to.
     * @param keybinds a list of (@Link KeyBindingEntry} which needs to be sorted.
     * @return The previous list, but sorted.
     */
    public static List<KeyBindingEntry> sort(List<KeyBindingEntry> keybinds) {
        KeyBindingEntry[] sorted = new KeyBindingEntry[6];
        for (KeyBindingEntry bind : keybinds) {
            if (bind.getKey().equals("Player1Left")) {
                sorted[0] = bind;
            }
            if (bind.getKey().equals("Player1Right")) {
                sorted[1] = bind;
            }
            if (bind.getKey().equals("Player1Shoot")) {
                sorted[2] = bind;
            }
            if (bind.getKey().equals("Player2Left")) {
                sorted[3] = bind;
            }
            if (bind.getKey().equals("Player2Right")) {
                sorted[4] = bind;
            }
            if (bind.getKey().equals("Player2Shoot")) {
                sorted[5] = bind;
            }
        }
        return Arrays.asList(sorted);
    }

}
