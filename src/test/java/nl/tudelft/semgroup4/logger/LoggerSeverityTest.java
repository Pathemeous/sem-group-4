package nl.tudelft.semgroup4.logger;

import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by justin on 18/09/15.
 */
@RunWith(Parameterized.class)
public class LoggerSeverityTest {

    /**
     * The parameters for this test.
     * @return Object array of parameters
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {LogSeverity.VERBOSE, new LogSeverity[]{
                LogSeverity.VERBOSE,
                LogSeverity.DEBUG,
                LogSeverity.WARNING,
                LogSeverity.ERROR,
                LogSeverity.CRITICAL,
            }},
            {LogSeverity.DEBUG, new LogSeverity[]{
                LogSeverity.DEBUG,
                LogSeverity.WARNING,
                LogSeverity.ERROR,
                LogSeverity.CRITICAL,
            }},
            {LogSeverity.WARNING, new LogSeverity[]{
                LogSeverity.WARNING,
                LogSeverity.ERROR,
                LogSeverity.CRITICAL,
            }},
            {LogSeverity.ERROR, new LogSeverity[]{
                LogSeverity.ERROR,
                LogSeverity.CRITICAL,
            }},
            {LogSeverity.CRITICAL, new LogSeverity[]{
                LogSeverity.CRITICAL,
            }},
        });
    }

    private final LogSeverity severity;
    private final List<LogSeverity> expectedList = new LinkedList<>();

    private Logger logger;
    private LoggerOutlet consoleOutlet;
    private LoggerOutlet fileOutlet;

    /**
     * We need a constructor to be able to initilize with parameters.
     *
     * @param severity the severity level of the logger
     * @param expected an array of expected severities that should be logged
     */
    public LoggerSeverityTest(LogSeverity severity, LogSeverity[] expected) {
        this.severity = severity;
        for (LogSeverity s : expected) {
            this.expectedList.add(s);
        }
    }



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
    public void testSeverity() {
        logger.setEnabled(true);
        logger.setLoggingToConsole(true);
        logger.setLoggingToFile(true);
        logger.setSeverity(severity);

        logger.log(LogSeverity.CRITICAL, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.ERROR, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.WARNING, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.DEBUG, "SomeTag", "SomeMessage");
        logger.log(LogSeverity.VERBOSE, "SomeTag", "SomeMessage");

        for (LogSeverity severity : LogSeverity.values()) {
            int timesCount = expectedList.contains(severity) ? 1 : 0;

            verify(consoleOutlet, times(timesCount)).log(
                    endsWith(String.format("[%s] SomeTag: SomeMessage", severity.name())));
            verify(fileOutlet, times(timesCount)).log(
                    endsWith(String.format("[%s] SomeTag: SomeMessage", severity.name())));

        }
    }


}
