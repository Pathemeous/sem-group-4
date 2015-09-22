package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.awt.AWTException;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.powerup.Powerup.PowerType;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.util.SemRectangle;

import org.junit.Test;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


/**
 * tests cases for the player class.
 * @author Damian
 */
public class PlayerTest extends AbstractOpenGLTestCase {

    private Input input = new Input(0);
    Player player = new Player(0, 0, input, true);

    private static final int SPEED = 4;
    private static final int PLAYER_SCORE = 100;
    private static final int PLAYER_LIVES = 3;
    private static final int TOTAL_LIVES = 4;


    @Test
    public final void testConstructor() {
        Weapon weapon = new Weapon(null, Pickup.WeaponType.REGULAR);
        assertEquals(weapon.getType(), player.getWeapon().getType());
        assertEquals(SPEED, player.getSpeed());
        assertEquals(0, player.getPowerUps().size());
    }

    @Test
    public final void testReset() {
        Weapon weapon = new Weapon(null, Pickup.WeaponType.FLOWER);
        Powerup powerup = new Powerup(9);
        powerup.setPowerType(PowerType.INVINCIBLE);
        player.getPowerUps().add(powerup);
        player.setWeapon(weapon);
        player.setFireCounter(SPEED);
        player.reset();
        assertEquals(0, player.getFireCounter());
        assertEquals(0, player.getPowerUps().size());
        assertEquals(Pickup.WeaponType.REGULAR, player.getWeapon().getType());
    }

    @Test
    public final void testRemoveSpeedUp() {
        player.setSpeed(2 * SPEED);
        player.removeSpeedup();
        assertEquals(SPEED, player.getSpeed());
    }


    @Test
    public final void testAddPowerUp() {
        Powerup shield = new Powerup(7);
        shield.setPowerType(PowerType.SHIELD);
        Powerup invincible = new Powerup(9);
        invincible.setPowerType(PowerType.INVINCIBLE);
        Powerup speedUp = new Powerup(5);
        speedUp.setPowerType(PowerType.SPEEDUP);
        Powerup score = new Powerup(2);
        score.setPowerType(PowerType.POINTS);
        Powerup lives = new Powerup(10);
        lives.setPowerType(PowerType.LIFE);

        player.addPowerup(shield);
        assertEquals(1, player.getPowerups().size());
        player.addPowerup(invincible);
        assertEquals(1, player.getPowerups().size());
        player.addPowerup(invincible);
        assertEquals(1, player.getPowerups().size());
        player.addPowerup(speedUp);
        assertEquals(false, player.hasSpeedup());
        assertEquals(1, player.getPowerups().size());
        assertEquals(2 * SPEED, player.getSpeed());
        assertEquals(0, player.getScore());
        player.addPowerup(score);
        assertEquals(PLAYER_SCORE, player.getScore());
        assertEquals(PLAYER_LIVES, player.getLives());
        player.addPowerup(lives);
        assertEquals(TOTAL_LIVES, player.getLives());
    }

    @Test
    public final void testUpdate() throws SlickException, AWTException {
        Modifiable modifiable = mock(Modifiable.class); 
        player.setFireCounter(1);        
        player.setInvincibilityCounter(200);
        player.setSpeedupCounter(400);
        player.setRemovingShieldCounter(1);
        player.addScore(100);

        player.update(modifiable, 1);

        assertEquals(100, player.getScore());
        assertEquals(true, player.removingShield());
        assertEquals(2, player.getRemovingShieldCounter());
        assertEquals(401, player.getSpeedupCounter());
        assertEquals(2, player.getFireCounter());
        assertEquals(201, player.getInvincibilityCounter());        
    }

    @Test
    public final void testUpdate2() throws SlickException {
        Modifiable modifiable = mock(Modifiable.class);
        player.setFireCounter(0);        
        player.setInvincibilityCounter(1000);
        player.setSpeedupCounter(800);
        player.setShieldInactive();
        player.removeLife();

        player.update(modifiable, 10);

        assertEquals(2, player.getLives());
        assertEquals(2, player.getRemovingShieldCounter());
        assertEquals(0, player.getFireCounter());
        assertEquals(0, player.getInvincibilityCounter()); 
        assertEquals(0, player.getSpeedupCounter());
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
