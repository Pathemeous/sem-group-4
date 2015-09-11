package nl.tudelft.semgroup4;

import org.junit.After;
import org.junit.Before;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Extend this class when using anything OpenGL related.
 * It initializes a display and OpenGL context for each test.
 */
public abstract class OpenGLTestCase {

    /**
     * This sets up the OpenGL Context.
     *
     * @throws Exception when anything happens
     */
    @Before
    public void setUp() throws Exception {
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
