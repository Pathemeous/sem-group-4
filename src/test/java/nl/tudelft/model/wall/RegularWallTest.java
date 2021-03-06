package nl.tudelft.model.wall;

import static org.junit.Assert.assertEquals;

import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.RegularWall;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 11/09/15.
 */
public class RegularWallTest {
    
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
        AbstractWall wall = new RegularWall(img, 0, 0);
        assertEquals(wall.getLocX(), 0.0f, 0.0f);
        assertEquals(wall.getLocY(), 0.0f, 0.0f);
        assertEquals(wall.getImage(), img);
    }

    @Test
    public void testUpdate() throws SlickException {
        AbstractWall wall = new RegularWall(mockedImage, 0, 0);
        assertEquals(wall.getLocX(), 0.0f, 0.0f);
        assertEquals(wall.getLocY(), 0.0f, 0.0f);
        assertEquals(wall.getImage(), mockedImage);
        
        wall.update(null, 0);
        
        assertEquals(wall.getLocX(), 0.0f, 0.0f);
        assertEquals(wall.getLocY(), 0.0f, 0.0f);
        assertEquals(wall.getImage(), mockedImage);
    }

}
