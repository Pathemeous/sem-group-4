package nl.tudelft.semgroup4.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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
     * Parse the specified filePath with default encoding (UTF-8).
     * 
     * @return {@link JSONObject} - the JSON format of the parsed file.
     */
    public static JSONObject loadJSON(String filePath) throws IOException {
        return loadJSON(filePath, DEFAULT_ENCODING);
    }

    /**
     * Parse the specified filePath with the specified encoding.
     * 
     * @param filePath
     *            {@link String} - The path to this file.
     * @param encoding
     *            {@link Charset} - the encoding that should be used when parsing the file.
     * @return {@link JSONObject} - the JSON format of this parser's file.
     */
    public static JSONObject loadJSON(String filePath, Charset encoding) throws IOException {
        byte[] byteString = Files.readAllBytes(Paths.get(filePath));
        String jsonString = new String(byteString, encoding);
        return new JSONObject(jsonString);
    }

    /**
     * Writes the new JSON to the parser's file using default encoding (UTF-8).
     * 
     * @param json
     *            {@link JSONObject} - The JSON structure to write to the file.
     * @throws IOException
     *             - When writing the file goes wrong.
     * @throws JSONException
     *             - When the provided JSON does not conform to the formatting standards.
     */
    public static void save(JSONObject json, String filePath) throws IOException, JSONException {
        JSONObject.testValidity(json);

        BufferedWriter out =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),
                        DEFAULT_ENCODING));
        out.write(json.toString(4));
        out.close();
    }

}
