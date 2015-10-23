package nl.tudelft.controller.logger;

import java.io.Closeable;

/**
 * Created by justin on 17/09/15.
 */
public interface Logger extends Closeable {

    void log(LogSeverity level, String tag, String message);

    void setEnabled(boolean enabled);

    boolean isEnabled();

    void setLoggingToConsole(boolean logToConsole);

    boolean isLoggingToConsole();

    void setLoggingToFile(boolean logToFile);

    boolean isLoggingToFile();

    void setSeverity(LogSeverity severity);

    LogSeverity getSeverity();

}
