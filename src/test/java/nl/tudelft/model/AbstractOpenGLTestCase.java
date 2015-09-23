package nl.tudelft.model;

import java.io.File;

import nl.tudelft.semgroup4.Resources.Resources;

import org.junit.After;
import org.junit.Before;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Extend this class when using anything OpenGL related.
 * It initializes a display and OpenGL context for each test.
 */
public abstract class AbstractOpenGLTestCase {

    /**
     * This sets up the OpenGL Context.
     *
     * @throws Exception when anything happens
     */
    @Before
    public void setUp() throws Exception {
        System.setProperty(
                "org.lwjgl.librarypath",
                new File(new File(System.getProperty("user.dir"), "target/natives"), (LWJGLUtil
                        .getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
                        .getAbsolutePath());
        Display.setDisplayMode(new DisplayMode(1, 1));
        Display.create();
        Resources.init();
    }

    /**
     * This tears down the OpenGL Context.
     *
     * @throws Exception when anything happens
     */
    @After
    public void tearDown() throws Exception {
        Display.destroy();
    }

}
