package nl.tudelft.controller.logger;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleOutletTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testSimpleLog() {

        ConsoleOutlet outlet = new ConsoleOutlet();

        outlet.log("SomeMessage");

        // note the newline!
        assertEquals("SomeMessage" + System.lineSeparator() + "", outContent.toString());

    }

    @Test
    public void testMultipleLog() {

        ConsoleOutlet outlet = new ConsoleOutlet();

        outlet.log("SomeMessage1");
        outlet.log("SomeMessage2");
        outlet.log("SomeMessage3");
        outlet.log("SomeMessage4");

        // note the newlines!
        assertEquals(
                "SomeMessage1" + System.lineSeparator() + "SomeMessage2"
                        + System.lineSeparator() + "SomeMessage3" + System.lineSeparator()
                        + "SomeMessage4" + System.lineSeparator() + "", outContent.toString());
    }

    @Test
    public void testClose() throws IOException {
        ConsoleOutlet outlet = new ConsoleOutlet();
        outlet.close();
    }

}
