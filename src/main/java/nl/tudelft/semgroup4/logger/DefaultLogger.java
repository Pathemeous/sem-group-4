package nl.tudelft.semgroup4.logger;

import java.io.File;
import java.io.IOException;

public class DefaultLogger extends AbstractLogger {

    public DefaultLogger() throws IOException {
        super(new ConsoleOutlet(), 
                new FileOutlet(new File(System.getProperty("user.dir"), "log.txt")));
    }

}