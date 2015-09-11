package nl.tudelft.semgroup4;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PlayerTest extends OpenGLTestCase{
	
	 public PlayerTest() {
	     super();
	 }

	 public static Test suite() {	    	
	     return new TestSuite(PlayerTest.class);
	 }
	 
	 public void setUp() throws LWJGLException, SlickException{
	        System.setProperty(
	                "org.lwjgl.librarypath",
	                new File(new File(System.getProperty("user.dir"), "target/natives"), (LWJGLUtil
	                        .getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
	                        .getAbsolutePath());
	        Display.setDisplayMode(new DisplayMode(1, 1));
	        Display.create();
	        Resources.init();
	    }
	 
}
