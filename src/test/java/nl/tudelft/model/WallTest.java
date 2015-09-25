package nl.tudelft.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 11/09/15.
 */
public class WallTest extends AbstractOpenGLTestCase {
    
    private Image mockedImage; 
    
    /**
     * Mock all required dependencies.
     */
    @Before
    public void setUp() {
        mockedImage = Mockito.mock(Image.class);
    }

    @Test
    public void testConstructor() throws SlickException {
        Image img = mockedImage;
        Wall wall = new Wall(img, 0, 0);
        assertEquals(wall.getLocX(), 0.0f, 0.0f);
        assertEquals(wall.getLocY(), 0.0f, 0.0f);
        assertEquals(wall.getImage(), img);
    }

    @Test
    public void testUpdate() throws SlickException {
        Wall wall = new Wall(mockedImage, 0, 0);
        wall.update(null, 0);
    }

}
