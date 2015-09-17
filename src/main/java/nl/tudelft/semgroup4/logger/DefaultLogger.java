package nl.tudelft.semgroup4.logger;

public class DefaultLogger implements Logger {

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
