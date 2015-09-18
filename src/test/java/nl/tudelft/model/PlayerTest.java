package nl.tudelft.model;

import static org.junit.Assert.assertEquals;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Powerup;
import nl.tudelft.model.pickups.Powerup.PowerType;
import org.junit.Test;

/**
 * tests cases for the player class.
 * @author Damian
 */
public class PlayerTest extends OpenGLTestCase {

    private final int speed = 4;
    private final int playerScore = 100;
    private final int playerLives = 3;
    private final int playerTotalLives = 4;

    @Test
    public final void testConstructor() {
        Player player = new Player(0, 0, null, true);
        Weapon weapon = new Weapon(null, Pickup.WeaponType.REGULAR);
        assertEquals(player.getWeapon().getType(), weapon.getType());
        assertEquals(player.getSpeed(), speed);
        assertEquals(player.getPowerUps().size(), 0);
    }

    @Test
    public final void testReset() {
        Player player = new Player(0, 0, null, true);
        Weapon weapon = new Weapon(null, Pickup.WeaponType.FLOWER);
        Powerup powerup = new Powerup(9);
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

    @Test
    public final void testRemoveSpeedUp() {
        Player player = new Player(0, 0, null, true);
        player.setSpeed(2 * speed);
        player.removeSpeedup();
        assertEquals(player.getSpeed(), speed);
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

        Player player = new Player(0, 0, null, true);

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
