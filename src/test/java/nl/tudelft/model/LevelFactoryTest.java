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

}
