package nl.tudelft.semgroup4;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import nl.tudelft.model.Wall;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GameObjectTest extends OpenGLTestCase {
	
	 public GameObjectTest() {
	        super();
	    }

	    public static Test suite() {
	        return new TestSuite(GameObjectTest.class);
	    }
	    /**
	     * tests the getbounds method of the gameObject, checks if the dimensions are correct
	     * @throws SlickException
	     */
	    public void testGetBounds() throws SlickException {
	    	Resources.init();
	    	Wall wall = new Wall(Resources.vwallImage, 0, 0);
	    	assertEquals(wall.getHeight(), (int)wall.getBounds().getHeight());
	    	assertEquals(wall.getWidth(), (int)wall.getBounds().getWidth());
	    	assertEquals((int)wall.getLocX(), (int)wall.getBounds().getX());
	    	assertEquals((int)wall.getLocY(), (int)wall.getBounds().getY());	    	
	    }
	    
}
