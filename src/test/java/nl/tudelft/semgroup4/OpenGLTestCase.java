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

    @Before
    public void setUp() throws Exception {
        Display.setDisplayMode(new DisplayMode(1, 1));
        Display.create();
        Resources.init();
    }

    @After
    public void tearDown() throws Exception {
        Display.destroy();
    }

}
