package nl.tudelft.model;

import static org.junit.Assert.assertEquals;

import nl.tudelft.semgroup4.Resources;
import org.junit.Test;
import org.newdawn.slick.SlickException;

/**
 * The tests for GameObject.
 *
 * @author Damian
 *
 */
public class GameObjectTest extends OpenGLTestCase {

    /**
     * tests the getbounds method of the gameObject,
     *  checks if the dimensions are correct.
     * @throws SlickException could be thrown when resources are not initialised.
     */
    @Test
    public void testGetBounds() throws SlickException {
        Resources.init();
        Wall wall = new Wall(Resources.vwallImage, 0, 0);
        assertEquals(wall.getHeight(),
                (int) wall.getBounds().getHeight());
        assertEquals(wall.getWidth(),
                (int) wall.getBounds().getWidth());
        assertEquals((int) wall.getLocX(),
                (int) wall.getBounds().getX());
        assertEquals((int) wall.getLocY(),
                (int) wall.getBounds().getY());
    }

}
