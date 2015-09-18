package nl.tudelft.semgroup4.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by justin on 17/09/15.
 */
public class FileOutlet implements LoggerOutlet {

    private final PrintWriter printWriter;

    public FileOutlet(File file) throws IOException {
        this.printWriter = new PrintWriter(file, "UTF-8");
    }

    @Override
    public void log(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    @Override
    public void close() throws IOException {
        printWriter.close();
    }
}
