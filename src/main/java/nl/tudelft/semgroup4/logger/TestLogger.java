package nl.tudelft.semgroup4.logger;

/**
 * Created by justin on 17/09/15.
 */
public class TestLogger implements Logger {
    public TestLogger(LoggerOutlet consoleOutlet, LoggerOutlet fileOutlet) {
    }

    @Override
    public void log(LogSeverity level, String tag, String message) {

    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setLogToConsole(boolean logToConsole) {

    }

    @Override
    public boolean getLogToConsole() {
        return false;
    }

    @Override
    public void setLogToFile(boolean logToFile) {

    }

    @Override
    public boolean getLogToFile() {
        return false;
    }
}
