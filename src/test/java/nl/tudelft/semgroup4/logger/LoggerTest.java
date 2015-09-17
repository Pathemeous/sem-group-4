package nl.tudelft.semgroup4.logger;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        logger.setLogToConsole(true);
        logger.setLogToFile(true);

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
        logger.setLogToConsole(true);
        logger.setLogToFile(true);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
        verify(fileOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
    }

    @Test
    public void testLogDisabled() {
        logger.setEnabled(false);
        logger.setLogToConsole(true);
        logger.setLogToFile(true);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(0)).log(any());
        verify(fileOutlet, times(0)).log(any());
    }

    @Test
    public void testLogEnabledFile() {
        logger.setEnabled(true);
        logger.setLogToConsole(false);
        logger.setLogToFile(true);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(0)).log(any());
        verify(fileOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
    }

    @Test
    public void testLogEnabledConsole() {
        logger.setEnabled(true);
        logger.setLogToConsole(true);
        logger.setLogToFile(false);

        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");

        verify(consoleOutlet, times(1)).log(endsWith("[DEBUG] SomeTag: SomeMessage"));
        verify(fileOutlet, times(0)).log(any());
    }

}
