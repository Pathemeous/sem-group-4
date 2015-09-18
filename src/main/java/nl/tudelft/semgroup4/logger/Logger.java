package nl.tudelft.semgroup4.logger;

import java.io.Closeable;

/**
 * Created by justin on 17/09/15.
 */
public interface Logger extends Closeable {

    void log(LogSeverity level, String tag, String message);

    void setEnabled(boolean enabled);

    boolean isEnabled();

    void setLogToConsole(boolean logToConsole);

    boolean getLogToConsole();

    void setLogToFile(boolean logToFile);

    boolean getLogToFile();

    void setSeverity(LogSeverity severity);

    LogSeverity getSeverity();

}
