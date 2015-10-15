package nl.tudelft.semgroup4.util;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JSONParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoadJSONNonExistentFileThrowsIOException() throws IOException {
        thrown.expect(IOException.class);
        JSONParser.loadJSON("NonExistentFilePath");
    }
}
