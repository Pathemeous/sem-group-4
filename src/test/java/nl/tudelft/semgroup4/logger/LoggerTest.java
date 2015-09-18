package nl.tudelft.semgroup4.logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by justin on 17/09/15.
 */
public class LoggerTest {

    private Logger logger;
    private LoggerOutlet consoleOutlet;
    private LoggerOutlet fileOutlet;

    /**
     * Setting up the outlets. These are dependencies for the Logger so they
     * are mocked.
     */
    @Before
    public void setup() {

        consoleOutlet = mock(ConsoleOutlet.class);
        fileOutlet = mock(FileOutlet.class);

        logger = new TestLogger(consoleOutlet, fileOutlet);
    }

    @Test
    public void testLogFormat() {
        logger.setEnabled(true);
        logger.setLoggingToConsole(true);
        logger.setLoggingToFile(true);
        logger.setSeverity(LogSeverity.VERBOSE);

        logger.log(LogSeverity.CRITICAL, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.ERROR, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.WARNING, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.VERBOSE, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(1)).log(endsWith("[CRITICAL] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[CRITICAL] SomeTag: SomeMessage"));
        verify(consoleOutlet, times(1)).log(endsWith("[ERROR] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[ERROR] SomeTag: SomeMessage"));
        verify(consoleOutlet, times(1)).log(endsWith("[WARNING] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[WARNING] SomeTag: SomeMessage"));
        verify(consoleOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
        verify(consoleOutlet, times(1)).log(endsWith("[VERBOSE] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[VERBOSE] SomeTag: SomeMessage"));
    }

    @Test
    public void testLogEnabledAll() {
        logger.setEnabled(true);
        logger.setLoggingToConsole(true);
        logger.setLoggingToFile(true);
        logger.setSeverity(LogSeverity.DEBUG);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
    }

    @Test
    public void testLogDisabled() {
        logger.setEnabled(false);
        logger.setLoggingToConsole(true);
        logger.setLoggingToFile(true);
        logger.setSeverity(LogSeverity.DEBUG);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(0)).log(any());
        verify(fileOutlet, times(0)).log(any());
    }

    @Test
    public void testLogEnabledFile() {
        logger.setEnabled(true);
        logger.setLoggingToConsole(false);
        logger.setLoggingToFile(true);
        logger.setSeverity(LogSeverity.DEBUG);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(0)).log(any());
        verify(fileOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
    }

    @Test
    public void testLogEnabledConsole() {
        logger.setEnabled(true);
        logger.setLoggingToConsole(true);
        logger.setLoggingToFile(false);
        logger.setSeverity(LogSeverity.DEBUG);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
        verify(fileOutlet, times(0)).log(any());
    }

    @Test
    public void testSettersAndGetters1() {
        logger.setEnabled(true);
        assertTrue(logger.isEnabled());
    }

    @Test
    public void testSettersAndGetters2() {
        logger.setLoggingToFile(true);
        assertTrue(logger.isLoggingToFile());
    }

    @Test
    public void testSettersAndGetters3() {
        logger.setLoggingToConsole(true);
        assertTrue(logger.isLoggingToConsole());
    }

    @Test
    public void testSettersAndGetters4() {
        logger.setSeverity(LogSeverity.VERBOSE);
        assertEquals(LogSeverity.VERBOSE, logger.getSeverity());
    }

    @Test
    public void testSettersAndGetters5() {
        logger.setSeverity(LogSeverity.DEBUG);
        assertEquals(LogSeverity.DEBUG, logger.getSeverity());
    }

    @Test
    public void testSettersAndGetters6() {
        logger.setSeverity(LogSeverity.WARNING);
        assertEquals(LogSeverity.WARNING, logger.getSeverity());
    }

    @Test
    public void testSettersAndGetters7() {
        logger.setSeverity(LogSeverity.ERROR);
        assertEquals(LogSeverity.ERROR, logger.getSeverity());
    }

    @Test
    public void testSettersAndGetters8() {
        logger.setSeverity(LogSeverity.CRITICAL);
        assertEquals(LogSeverity.CRITICAL, logger.getSeverity());
    }

    @Test
    public void testClose() throws IOException {
        logger.close();
    }

}
