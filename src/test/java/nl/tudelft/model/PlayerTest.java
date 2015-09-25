package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.tudelft.model.pickups.powerup.InvinciblePowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import nl.tudelft.model.pickups.powerup.SpeedPowerup;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;
import nl.tudelft.model.pickups.weapon.RegularWeapon;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Resources.Resources;
import nl.tudelft.semgroup4.util.SemRectangle;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;


/**
 * tests cases for the player class.
 * @author Damian
 */
public class PlayerTest extends AbstractOpenGLTestCase {

    private Input input = new Input(0);
    private Player player;
    
    private static final int SPEED = 4;
    
    @Before
    public final void setUp() throws Exception {
        super.setUp();
        player = new Player(0, 0, input, true);
    }

    @Test
    public final void testConstructor() {
        assertTrue(player.isFirstPlayer());
        assertEquals(0, player.getScore());
        assertEquals(3, player.getLives());
        assertEquals(SPEED, player.getSpeed());
    }

    @Test
    public final void testReset() {
        Weapon weapon = new DoubleWeapon(0, 0);
        Powerup powerup = new InvinciblePowerup(0, 0);
        player.setWeapon(weapon);
        player.setPowerup(Powerup.INVINCIBLE, powerup);
        player.reset();
        assertTrue(player.getPowerup(Powerup.INVINCIBLE) == null);
        assertTrue(player.getWeapon() instanceof RegularWeapon);
    }

    @Test
    public final void testClearPowerups() {
        Powerup invincible = new InvinciblePowerup(0, 0);
        Powerup shield = new ShieldPowerup(0, 0);
        Powerup speed = new SpeedPowerup(0, 0);
        
        player.setPowerup(Powerup.INVINCIBLE, invincible);
        player.setPowerup(Powerup.SHIELD, shield);
        player.setPowerup(Powerup.SPEED, speed);
        
        assertEquals(invincible, player.getPowerup(Powerup.INVINCIBLE));
        assertEquals(shield, player.getPowerup(Powerup.SHIELD));
        assertEquals(speed, player.getPowerup(Powerup.SPEED));
        
        player.clearAllPowerups();
        
        assertEquals(null, player.getPowerup(Powerup.INVINCIBLE));
        assertEquals(null, player.getPowerup(Powerup.SHIELD));
        assertEquals(null, player.getPowerup(Powerup.SPEED));
    }
    
    @Test
    public final void testHasPowerup() {
        Powerup invincible = new InvinciblePowerup(0, 0);
        Powerup shield = new ShieldPowerup(0, 0);
        Powerup speed = new SpeedPowerup(0, 0);
        
        player.setPowerup(Powerup.INVINCIBLE, invincible);
        player.setPowerup(Powerup.SHIELD, shield);
        player.setPowerup(Powerup.SPEED, speed);
        
        assertTrue(player.hasPowerup(Powerup.INVINCIBLE));
        assertTrue(player.hasPowerup(Powerup.SHIELD));
        assertTrue(player.hasPowerup(Powerup.SPEED));
    }
    
    @Test
    public final void testRemovePowerup() {
        Powerup invincible = new InvinciblePowerup(0, 0);
        Powerup shield = new ShieldPowerup(0, 0);
        Powerup speed = new SpeedPowerup(0, 0);
        
        player.setPowerup(Powerup.INVINCIBLE, invincible);
        player.setPowerup(Powerup.SHIELD, shield);
        player.setPowerup(Powerup.SPEED, speed);
        
        player.removePowerup(Powerup.INVINCIBLE);
        player.removePowerup(Powerup.SHIELD);
        player.removePowerup(Powerup.SPEED);
        
        assertEquals(null, player.getPowerup(Powerup.INVINCIBLE));
        assertEquals(null, player.getPowerup(Powerup.SHIELD));
        assertEquals(null, player.getPowerup(Powerup.SPEED));
    }
    
    @Test
    public final void testIsInvincible() {
        assertFalse(player.isInvincible());
        
        Powerup invincible = new InvinciblePowerup(0, 0);
        player.setPowerup(Powerup.INVINCIBLE, invincible);
        
        assertTrue(player.isInvincible());
    }
    
    @Test
    public final void testHasShield() {
        assertFalse(player.hasShield());
        
        Powerup shield = new ShieldPowerup(0, 0);
        player.setPowerup(Powerup.SHIELD, shield);
        
        assertTrue(player.hasShield());
    }
    
    @Test
    public final void testApplySpeedup() {
        assertEquals(SPEED, player.getSpeed());
        
        player.applySpeedup();
        
        assertEquals(2 * SPEED, player.getSpeed());
    }
    
    @Test
    public final void testDefaultSpeed() {
        player.applySpeedup();
        player.setDefaultSpeed();
        
        assertEquals(SPEED, player.getSpeed());
    }

    @Test
    public final void testLastGettersSetters() {
        assertTrue(player.isFirstPlayer());
        player.setAnimationCurrent(Resources.playerWalkLeft);
        assertEquals(Resources.playerWalkLeft, player.getAnimationCurrent());
    }

    @Test
    public final void testGetBounds() {
        player.setImage(Resources.playerImageStill);
        SemRectangle rectangle = new SemRectangle(0, 0,
                player.getWidth() - 20, player.getHeight() - 15);
        assertEquals((int)rectangle.getHeight(), (int)player.getBounds().getHeight());
        assertEquals((int)rectangle.getWidth(), (int)player.getBounds().getWidth());
    }
}
