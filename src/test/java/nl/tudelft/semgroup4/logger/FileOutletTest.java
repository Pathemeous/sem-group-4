package nl.tudelft.semgroup4.logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by justin on 17/09/15.
 */
public class FileOutletTest {

    private File testLogFile;

    /**
     * In this setup we create a dir to write log files to
     * @throws IOException When something goes wrong with IO
     */
    @Before
    public void setupTestFile() throws IOException {
        int index = 0;
        do {
            testLogFile = new File(System.getProperty("user.dir"), "testLogFile_" + index++);
        } while (testLogFile.exists());
    }

    /**
     * delete our temp folder
     */
    @After
    public void breakDownTestFile() {
        if (!testLogFile.delete()) {
            System.err.println("couldn't delete tmp dir: " + testLogFile.toString());
        }
    }

    @Test
    public void testSimpleLog() throws IOException {

        FileOutlet outlet = new FileOutlet(testLogFile);

        outlet.log("SomeMessage");

        assertTrue(testLogFile.exists());

        List<String> lines = Files.readAllLines(testLogFile.toPath());
        assertEquals(1, lines.size());

        assertEquals("SomeMessage", lines.get(0));

    }

    @Test
    public void testMultipleLog() throws IOException {

        FileOutlet outlet = new FileOutlet(testLogFile);

        outlet.log("SomeMessage1");
        outlet.log("SomeMessage2");
        outlet.log("SomeMessage3");
        outlet.log("SomeMessage4");

        assertTrue(testLogFile.exists());

        List<String> lines = Files.readAllLines(testLogFile.toPath());
        assertEquals(4, lines.size());

        assertEquals("SomeMessage1", lines.get(0));
        assertEquals("SomeMessage2", lines.get(1));
        assertEquals("SomeMessage3", lines.get(2));
        assertEquals("SomeMessage4", lines.get(3));

    }

}