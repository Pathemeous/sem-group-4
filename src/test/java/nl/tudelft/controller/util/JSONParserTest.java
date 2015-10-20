package nl.tudelft.controller.util;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class JSONParserTest {

    private JSONObject mockedJSON;
    private File defaultFile;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Prepare all dependencies and file setups.
     */
    @Before
    public void setUp() {
        mockedJSON = Mockito.mock(JSONObject.class);
        defaultFile = new File("ParserTestFile.txt");
    }

    /**
     * Remove any generated files.
     */
    @After
    public void tearDown() {
        mockedJSON = Mockito.mock(JSONObject.class);
        if (defaultFile.exists()) {
            defaultFile.delete();
        }
    }

    @Test
    public void testLoadJSONNonExistentFileThrowsIOException() throws IOException {
        thrown.expect(IOException.class);
        File nonExistentFile = new File("NonExistentFilePath");
        JSONParser.loadJSON(nonExistentFile);
    }

    @Test
    public void testSave() throws IOException {
        Mockito.when(mockedJSON.toString(Mockito.anyInt())).thenReturn(
                "{'Player1Left': 203,'Player1Right': 205,'Player1Shoot': 57}");
        JSONParser.save(mockedJSON, defaultFile);
    }
}
