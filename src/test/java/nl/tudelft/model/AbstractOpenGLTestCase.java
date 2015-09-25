package nl.tudelft.model;

import java.io.File;

import org.junit.Before;
import org.lwjgl.LWJGLUtil;

/**
 * Extend this class when using anything OpenGL related. It initializes a display and OpenGL
 * context for each test.
 */
public abstract class AbstractOpenGLTestCase {

    /**
     * This sets up the OpenGL Context.
     *
     * @throws Exception
     *             when anything happens
     */
    @Before
    public void setUp() throws Exception {
        System.setProperty(
                "org.lwjgl.librarypath",
                new File(new File(System.getProperty("user.dir"), "target/natives"),
                        (LWJGLUtil.getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil
                                .getPlatformName()).getAbsolutePath());
    }

}
