package nl.tudelft.semgroup4;

import junit.framework.TestCase;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Extend this class when using anything OpenGL related.
 * It initializes a display and OpenGL context for each test.
 */
public abstract class OpenGLTestCase extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
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
