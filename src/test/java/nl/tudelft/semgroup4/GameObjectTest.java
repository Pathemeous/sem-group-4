/**
 *
 */
package nl.tudelft.semgroup4;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import nl.tudelft.model.Wall;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * 
 * @author Damian
 *
 */
public class GameObjectTest extends OpenGLTestCase {
	/**
	 * construct gameobjecttest.
	 */
	 public GameObjectTest() {
	        super();
	    }
	 /**
	  *
	  * @return test suite
	  */
	    public static Test suite() {
	        return new TestSuite(GameObjectTest.class);
	    }

	    public void setUp() throws LWJGLException, SlickException{
	        System.setProperty(
	                "org.lwjgl.librarypath",
	                new File(new File(System.getProperty("user.dir"),
	                		"target/natives"), (LWJGLUtil
	                        .getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
	                        .getAbsolutePath());
	        Display.setDisplayMode(new DisplayMode(1, 1));
	        Display.create();
	        Resources.init();
	    }

	    /**
	     * tests the getbounds method of the gameObject,
	     *  checks if the dimensions are correct.
	     * @throws SlickException
	     */
	    public void testGetBounds() throws SlickException {
	    	Resources.init();
	    	Wall wall = new Wall(Resources.vwallImage, 0, 0);
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
