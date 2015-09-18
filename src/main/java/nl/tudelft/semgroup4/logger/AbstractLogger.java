package nl.tudelft.semgroup4.logger;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by justin on 17/09/15.
 */
public abstract class AbstractLogger implements Logger {

    private static final String LOG_FORMAT = "%s [%s] %s: %s";

    final LoggerOutlet consoleOutlet;
    final LoggerOutlet fileOutlet;
    private final Date currentDate = new Date();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private LogSeverity severity = LogSeverity.VERBOSE;

    private boolean enabled = true;
    private boolean loggingToConsole = true;
    private boolean loggingToFile = true;

    public AbstractLogger(LoggerOutlet consoleOutlet, LoggerOutlet fileOutlet) {
        this.consoleOutlet = consoleOutlet;
        this.fileOutlet = fileOutlet;
    }


    @Override
    public void log(LogSeverity level, String tag, String message) {
        if (isEnabled()) {
            if (severity.compareTo(level) >=  0) {
                currentDate.setTime(System.currentTimeMillis());
                final String line = String.format(LOG_FORMAT,
                        dateFormatter.format(currentDate),
                        level.name(),
                        tag,
                        message);
                if (isLoggingToConsole()) {
                    consoleOutlet.log(line);
                }
                if (isLoggingToFile()) {
                    fileOutlet.log(line);
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        for (Closeable c : new Closeable[]{
            consoleOutlet,
            fileOutlet,
        }) {
            c.close();
        }
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
    public void setLoggingToConsole(boolean logToConsole) {
        this.loggingToConsole = logToConsole;
    }

    @Override
    public boolean isLoggingToConsole() {
        return loggingToConsole;
    }

    @Override
    public void setLoggingToFile(boolean logToFile) {
        this.loggingToFile = logToFile;
    }

    @Override
    public boolean isLoggingToFile() {
        return loggingToFile;
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
