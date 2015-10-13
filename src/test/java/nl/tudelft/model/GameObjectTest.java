package nl.tudelft.model;

import static org.junit.Assert.assertEquals;

import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.RegularWall;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * The tests for GameObject.
 *
 * @author Damian, Wouter
 *
 */
public class GameObjectTest {

    /**
     * tests the getbounds method of the gameObject,
     *  checks if the dimensions are correct.
     * @throws SlickException could be thrown when resources are not initialised.
     */
    @Test
    public void testGetBounds() throws SlickException {
        ResourcesWrapper mockedRes = Mockito.mock(ResourcesWrapper.class);
        Image mockedImage = Mockito.mock(Image.class);
        Mockito.when(mockedImage.getHeight()).thenReturn(10);
        Mockito.when(mockedImage.getWidth()).thenReturn(5);
        Mockito.when(mockedRes.getVwallImage()).thenReturn(mockedImage);
        
        Mockito.when(mockedRes.getVwallImage()).thenReturn(mockedImage);
        AbstractWall wall = new RegularWall(mockedRes.getVwallImage(), 0, 0);
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
