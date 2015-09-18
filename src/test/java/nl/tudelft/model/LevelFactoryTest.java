package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;

import nl.tudelft.semgroup4.Resources;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class LevelFactoryTest extends OpenGLTestCase {

    /**
     * Test to check the level factory constructor.
     */
    @Test
    public void testLevelFactory() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        assertEquals(factory.getGame(), mockedGame);
    }

    /**
     * Test to check the getter for all levels.
     * @throws SlickException - Resource not found.
     */
    @Test
    public void testGetAllLevels() throws SlickException {
        Resources.init();
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
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
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(1);
        assertEquals(lvl.getId(), 1);
    }

    /**
     * Test for the level 2 getter.
     */
    @Test
    public void testGetLevel2() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(2);
        assertEquals(lvl.getId(), 2);
    }

    /**
     * Test for the level 3 getter.
     */
    @Test
    public void testGetLevel3() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(3);
        assertEquals(lvl.getId(), 3);
    }

    /**
     * Test for the level 4 getter.
     */
    @Test
    public void testGetLevel4() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(4);
        assertEquals(lvl.getId(), 4);
    }

}
