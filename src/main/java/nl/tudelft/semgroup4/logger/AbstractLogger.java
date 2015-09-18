package nl.tudelft.semgroup4.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by justin on 17/09/15.
 */
public abstract class AbstractLogger implements Logger {

    private static final String logFormat = "%s [%s] %s: %s";

    final LoggerOutlet consoleOutlet;
    final LoggerOutlet fileOutlet;

    private LogSeverity severity = LogSeverity.ERROR;

    private boolean enabled = true;
    private boolean logToConsole = true;
    private boolean logToFile = true;

    public AbstractLogger(LoggerOutlet consoleOutlet, LoggerOutlet fileOutlet) {
        this.consoleOutlet = consoleOutlet;
        this.fileOutlet = fileOutlet;
    }

    private final Date currentDate = new Date();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void log(LogSeverity level, String tag, String message) {
        if (isEnabled()) {
            if (severity.compareTo(level) >=  0) {
                currentDate.setTime(System.currentTimeMillis());
                final String line = String.format(logFormat,
                        dateFormatter.format(currentDate),
                        level.name(),
                        tag,
                        message);
                if (getLogToConsole()) {
                    consoleOutlet.log(line);
                }
                if (getLogToFile()) {
                    fileOutlet.log(line);
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        fileOutlet.close();
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setLogToConsole(boolean logToConsole) {
        this.logToConsole = logToConsole;
    }

    @Override
    public boolean getLogToConsole() {
        return logToConsole;
    }

    @Override
    public void setLogToFile(boolean logToFile) {
        this.logToFile = logToFile;
    }

    @Override
    public boolean getLogToFile() {
        return logToFile;
    }

    @Override
    public void setSeverity(LogSeverity severity) {
        this.severity = severity;
    }

    @Override
    public LogSeverity getSeverity() {
        return severity;
    }
}
