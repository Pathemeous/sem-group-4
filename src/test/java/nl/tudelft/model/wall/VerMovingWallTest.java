package nl.tudelft.model.wall;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class VerMovingWallTest {
    
    private Image mockedImage; 
    
    /**
     * Mock all required dependencies.
     */
    @Before
    public void setUp() {
        mockedImage = Mockito.mock(Image.class);
    }
    
    @Test
    public void testConstructor() {
        MovingWall wall = new VerMovingWall(mockedImage, 0.0f, 0.0f, 2.0f);
        
        assertEquals(0.0f, wall.getLocX(), 0.0f);
        assertEquals(0.0f, wall.getLocY(), 0.0f);
        assertEquals(mockedImage, wall.getImage());
        assertEquals(2.0f, wall.getSpeed(), 0.0f);
    }
    
    @Test
    public void testUpdate() throws SlickException {
        MovingWall wall = new VerMovingWall(mockedImage, 0.0f, 0.0f, 2.0f);
        
        assertEquals(0.0f, wall.getLocY(), 0.0f);
        assertEquals(0.0f, wall.getLocX(), 0.0f);
        assertEquals(2.0f, wall.getSpeed(), 0.0f);
        
        wall.update(null, 0);
        
        assertEquals(2.0f, wall.getLocY(), 0.0f);
        assertEquals(0.0f, wall.getLocX(), 0.0f);
        assertEquals(2.0f, wall.getSpeed(), 0.0f);
    }
}
