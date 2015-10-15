package nl.tudelft.semgroup4.util;

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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        mockedJSON = Mockito.mock(JSONObject.class);

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testLoadJSONNonExistentFileThrowsIOException() throws IOException {
        thrown.expect(IOException.class);
        JSONParser.loadJSON("NonExistentFilePath");
    }

    @Test
    public void testSave() throws IOException {
        Mockito.when(mockedJSON.toString())
                .thenReturn(
                        "{'Player1Left': 203,'Player1Right': 205,'Player1Shoot': 57,'Player2Left': 30,'Player2Right': 32,'Player2Shoot': 17}");
        JSONParser.save(mockedJSON, "jsonSavetestFile.txt");
    }

}
