package nl.tudelft.semgroup4.logger;

import java.io.IOException;

/**
 * Created by justin on 17/09/15.
 */
public class ConsoleOutlet implements LoggerOutlet {

    public ConsoleOutlet() {
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
