package nl.tudelft.semgroup4.logger;

import java.io.Closeable;

/**
 * Created by justin on 17/09/15.
 */
public interface LoggerOutlet extends Closeable {

    void log(String message);

}
