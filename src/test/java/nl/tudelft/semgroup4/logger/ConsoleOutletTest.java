package nl.tudelft.semgroup4.logger;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
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
        assertEquals("SomeMessage\n", outContent.toString());

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
                "SomeMessage1\nSomeMessage2\nSomeMessage3\nSomeMessage4\n",
                outContent.toString());
    }

}