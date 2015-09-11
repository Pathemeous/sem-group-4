/**
 *asdasd.
 */
package nl.tudelft.semgroup4;

import java.io.File;

import nl.tudelft.model.Player;
import nl.tudelft.model.Weapon;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Powerup;
import nl.tudelft.model.pickups.Powerup.PowerType;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;

import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * tests cases for the player class.
 * @author Damian
 *
 */
public class PlayerTest extends OpenGLTestCase {
    /**
     *
     */
	private final int speed = 4;
	/**
	 *
	 */
	private final int playerScore = 100;
	/**
	 *
	 */
	private final int playerLives = 3;
	/**
	 *
	 */
	private final int playerTotalLives = 4;
	/**
	 *
	 */
	 public PlayerTest() {
	     super();
	 }
	 /**
	  *
	  * @return test Suite
	  */
	 public static Test suite() {
	     return new TestSuite(PlayerTest.class);
	 }
	 /**
	  * setup for the test cases.
	  * @throws LWJGLEXception, slickException
	  */
	 public final void setUp() throws LWJGLException, SlickException{
	        System.setProperty(
	                "org.lwjgl.librarypath",
new File(new File(System.getProperty("user.dir"), "target/natives"), (LWJGLUtil
.getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
	                        .getAbsolutePath());
	        Display.setDisplayMode(new DisplayMode(1, 1));
	        Display.create();
	        Resources.init();
	    }
	 /**
	  * tests the constructor of the player class.
	  */
	 public final void testConstructor() {
		 Player player = new Player(0, 0, null, true);
		 Weapon weapon = new Weapon(null, Pickup.WeaponType.REGULAR);
		 assertEquals(player.getWeapon().getType(), weapon.getType());
		 assertEquals(player.getSpeed(), speed);
		 assertEquals(player.getPowerUps().size(), 0);
	 }
	 /**
	  * tests the reset method of the player class.
	  */
	 public final void testReset() {
		 Player player = new Player(0, 0, null, true);
		 Weapon weapon = new Weapon(null, Pickup.WeaponType.FLOWER);
		 Powerup powerup = new Powerup();
		 powerup.setPowerType(PowerType.INVINCIBLE);
		 player.getPowerUps().add(powerup);
		 player.setWeapon(weapon);
		 player.setFireCounter(speed);
		 player.reset();
		 assertEquals(player.getFireCounter(), 0);
		 assertEquals(player.getPowerUps().size(), 0);
		 assertEquals(player.getWeapon().getType(),
				 Pickup.WeaponType.REGULAR);
	 }

	 /**
	  *
	  */
	 public final void testRemoveSpeedUp() {
		 Player player = new Player(0, 0, null, true);
		 player.setSpeed(2 * speed);
		 player.removeSpeedup();
		 assertEquals(player.getSpeed(), speed);
	 }
	 /**
	  * test the addPowerUp method of the player class.
	  */
	 public final void testAddPowerUp() {
		 Player player = new Player(0, 0, null, true);
		 Powerup shield = new Powerup();
		 shield.setPowerType(PowerType.SHIELD);
		 Powerup invincible = new Powerup();
		 invincible.setPowerType(PowerType.INVINCIBLE);
		 Powerup speedUp = new Powerup();
		 speedUp.setPowerType(PowerType.SPEEDUP);
		 Powerup score = new Powerup();
		 score.setPowerType(PowerType.POINTS);
		 Powerup lives = new Powerup();
		 lives.setPowerType(PowerType.LIFE);

		 player.addPowerup(shield);
		 assertEquals(player.getPowerUps().size(), 1);
		 player.addPowerup(invincible);
		 assertEquals(player.getPowerUps().size(), 1);
		 player.addPowerup(invincible);
		 assertEquals(player.getPowerUps().size(), 1);
		 player.addPowerup(speedUp);
		 assertEquals(player.getPowerUps().size(), 1);
		 assertEquals(player.getSpeed(), 2 * speed);
		 assertEquals(player.getScore(), 0);
		 player.addPowerup(score);
		 assertEquals(player.getScore(), playerScore);
		 assertEquals(player.getLives(), playerLives);
		 player.addPowerup(lives);
		 assertEquals(player.getLives(), playerTotalLives);
	 }
}
