package nl.tudelft.semgroup4.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class helps with writing and reading the highscores file.
 * It provides two helper methods to perform these actions.
 * See {@link #save(List)} and {@link #load()}
 */
public class HighscoresHelper {

    public static String FILENAME = "scores.json";
    public static Charset encoding = Charset.forName("UTF-8");

    /**
     * Loads the highscores from the highscores file (see FILENAME).
     * @return a list of {@link HighscoreEntry}s
     * @throws IOException When reading fails, this exception is thrown.
     */
    public static List<HighscoreEntry> load() throws IOException {

        // when file does not exist, create empty one
        if (!new File(FILENAME).exists()) {
            save(new ArrayList<>());
        }

        byte[] encoded = Files.readAllBytes(Paths.get(FILENAME));
        String jsonString = new String(encoded, encoding);

        final List<HighscoreEntry> highscores = new ArrayList<>();

        JSONArray array = new JSONArray(jsonString);

        for (Object object : array) {
            JSONObject highscore = (JSONObject)object;

            String name = (String)highscore.get("name");
            long score = highscore.getLong("score");
            highscores.add(new HighscoreEntry(name, score));
        }

        return highscores;
    }

    /**
     * Save a list of {@link HighscoreEntry}s to the highscores file. See FILENAME for filename.
     * All contents are overridden, this does NOT append to current scores.
     * @param highscores a list of {@link HighscoreEntry}
     * @throws IOException When writing fails somehow.
     */
    public static void save(List<HighscoreEntry> highscores) throws IOException {

        JSONArray array = new JSONArray();
        for (HighscoreEntry entry : highscores) {
            JSONObject entryJsonObject = new JSONObject();
            entryJsonObject.put("name", entry.getName());
            entryJsonObject.put("score", entry.getScore());
            array.put(entryJsonObject);
        }

        OpenOption[] options = {
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
        };

        Files.write(new File(FILENAME).toPath(), array.toString().getBytes(), options);

    }

}
