package nl.tudelft.semgroup4.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by justin on 17/09/15.
 */
public class FileOutlet implements LoggerOutlet {

    public FileOutlet(File file) throws IOException {
    }

    @Override
    public void log(String message) {

    }
}
