package nl.tudelft.model;

import java.util.LinkedList;

import org.newdawn.slick.SlickException;

import junit.framework.Test;
import junit.framework.TestSuite;
import nl.tudelft.model.LevelFactory;
import nl.tudelft.semgroup4.OpenGLTestCase;
import nl.tudelft.semgroup4.Resources;
import static org.mockito.Mockito.*;

public class LevelFactoryTest extends OpenGLTestCase {

    public static Test suite() {
        return new TestSuite(LevelFactoryTest.class);
    }

    public void testLevelFactory() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        assertEquals(factory.getGame(), mockedGame);
    }

    public void testGetAllLevels() throws SlickException {
        Resources.init();
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        LinkedList<Level> list = new LinkedList<Level>();
        assertEquals(list.size(), 0);
        list = factory.getAllLevels();
        assertEquals(list.size(), 4);
    }

    public void testGetLevel1() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(1);
        assertEquals(lvl.getId(), 1);
    }

    public void testGetLevel2() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(2);
        assertEquals(lvl.getId(), 2);
    }

    public void testGetLevel3() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(3);
        assertEquals(lvl.getId(), 3);
    }

    public void testGetLevel4() {
        Game mockedGame = mock(Game.class);
        LevelFactory factory = new LevelFactory(mockedGame);
        Level lvl = factory.getLevel(4);
        assertEquals(lvl.getId(), 4);
    }

}
