package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LevelFactoryTest extends AbstractOpenGLTestCase {
    
    Game mockedGame;
    ResourcesWrapper mockedResources;
    
    /**
     * Creates all mocked dependencies.
     */
    @Before
    public void setUp() {
        mockedGame = mock(Game.class);
        mockedResources = mock(ResourcesWrapper.class);
        Image mockedImage = mock(Image.class);
        when(mockedResources.getVwallImage()).thenReturn(mockedImage);
        when(mockedResources.getWallImage()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage2()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage3()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage4()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage5()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage6()).thenReturn(mockedImage);
    }

    /**
     * Test to check the level factory constructor.
     */
    @Test
    public void testLevelFactory() {
        LevelFactory factory = new LevelFactory(mockedGame, mockedResources);
        assertEquals(factory.getGame(), mockedGame);
    }

    /**
     * Test to check the getter for all levels.
     * @throws SlickException - Resource not found.
     */
    @Test
    public void testGetAllLevels() throws SlickException {
        LevelFactory factory = new LevelFactory(mockedGame, mockedResources);
        LinkedList<Level> list = new LinkedList<Level>();
        assertEquals(list.size(), 0);
        list = factory.getAllLevels();
        assertEquals(list.size(), 4);
    }

    /**
     * Test for the level 1 getter.
     */
    @Test
    public void testGetLevel1() {
        LevelFactory factory = new LevelFactory(mockedGame, mockedResources);
        Level lvl = factory.getLevel(1);
        assertEquals(lvl.getId(), 1);
    }

    /**
     * Test for the level 2 getter.
     */
    @Test
    public void testGetLevel2() {
        LevelFactory factory = new LevelFactory(mockedGame, mockedResources);
        Level lvl = factory.getLevel(2);
        assertEquals(lvl.getId(), 2);
    }

    /**
     * Test for the level 3 getter.
     */
    @Test
    public void testGetLevel3() {
        LevelFactory factory = new LevelFactory(mockedGame, mockedResources);
        Level lvl = factory.getLevel(3);
        assertEquals(lvl.getId(), 3);
    }

    /**
     * Test for the level 4 getter.
     */
    @Test
    public void testGetLevel4() {
        LevelFactory factory = new LevelFactory(mockedGame, mockedResources);
        Level lvl = factory.getLevel(4);
        assertEquals(lvl.getId(), 4);
    }

}
