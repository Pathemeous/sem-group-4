package nl.tudelft.semgroup4.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Allows parsing a file as a {@link JSONObject} and writing to it.
 * 
 * @author Pathemeous
 *
 */
public final class JSONParser {

    private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

    /**
     * Private constructor to avoid instantiation.
     */
    private JSONParser() {
    }

    /**
     * Parse the specified file with default encoding (UTF-8).
     * 
     * @param file
     *            {@link File} - The file containing the desired JSON.
     * @return {@link JSONObject} - the JSON format of the parsed file.
     * @throws IOException
     *             When the file is not found.
     */
    public static JSONObject loadJSON(File file) throws IOException {
        byte[] byteString = Files.readAllBytes(file.toPath());
        String jsonString = new String(byteString, DEFAULT_ENCODING);
        return new JSONObject(jsonString);
    }

    /**
     * Writes the new JSON to the parser's file using default encoding (UTF-8).
     * 
     * @param json
     *            {@link JSONObject} - The JSON structure to write to the file.
     * @param file
     *            {@link File} - The desired file to save in.
     * @throws IOException
     *             When writing the file goes wrong.
     * @throws JSONException
     *             When the provided JSON does not conform to the formatting standards.
     */
    public static void save(JSONObject json, File file) throws IOException, JSONException {
        JSONObject.testValidity(json);

        BufferedWriter out =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
                        DEFAULT_ENCODING));
        out.write(json.toString(4));
        out.close();
    }

}
