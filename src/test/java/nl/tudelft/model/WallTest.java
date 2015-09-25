package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import nl.tudelft.semgroup4.Resources.Resources;

import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 11/09/15.
 */
public class WallTest extends AbstractOpenGLTestCase {

    @Test
    public void testConstructor() throws SlickException {
        Image img = Resources.wallImage.copy();
        Wall wall = new Wall(img, 0, 0);
        assertEquals(wall.getLocX(), 0.0f, 0.0f);
        assertEquals(wall.getLocY(), 0.0f, 0.0f);
        assertEquals(wall.getImage(), img);
    }

    @Test
    public void testUpdate() throws SlickException {
        Wall wall = new Wall(Resources.vwallImage, 0, 0);
        wall.update(null, 0);
    }

}
