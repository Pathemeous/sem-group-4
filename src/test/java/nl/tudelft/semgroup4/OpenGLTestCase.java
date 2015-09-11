package nl.tudelft.semgroup4;

import java.io.File;

import junit.framework.TestCase;

import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Extend this class when using anything OpenGL related.
 * It initializes a display and OpenGL context for each test.
 */
public abstract class OpenGLTestCase extends TestCase {

    @Override
    protected void setUp() throws Exception {
    	System.setProperty(
                "org.lwjgl.librarypath",
                new File(new File(System.getProperty("user.dir"), "target/natives"), (LWJGLUtil
                        .getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
                        .getAbsolutePath());
        super.setUp();
        System.setProperty(
                "org.lwjgl.librarypath",
                new File(new File(System.getProperty("user.dir"), "target/natives"), (LWJGLUtil
                        .getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
                        .getAbsolutePath());
        Display.setDisplayMode(new DisplayMode(1, 1));
        Display.create();
        Resources.init();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Display.destroy();
    }

}
