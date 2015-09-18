package nl.tudelft.semgroup4.logger;

import java.io.IOException;

/**
 * Created by justin on 17/09/15.
 */
public class ConsoleOutlet implements LoggerOutlet {

    /**
     * The console outlet does not need any setup. The constructor is empty.
     */
    public ConsoleOutlet() {
        // There is nothing to do to set up the console stream.
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void close() throws IOException {
        // nothing to do
    }
}
