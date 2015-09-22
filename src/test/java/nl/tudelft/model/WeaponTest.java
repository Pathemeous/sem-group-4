package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.weapon.Projectile;
import nl.tudelft.model.weapon.Weapon;
import nl.tudelft.semgroup4.Resources;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class WeaponTest extends AbstractOpenGLTestCase {

    /**
     * Test for the player getter.
     */
    @Test
    public void testGetPlayer() {
        Player player = mock(Player.class);
        assertNotNull(player);
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        assertEquals(weapon.getPlayer(), null);
        weapon.setPlayer(player);
        assertEquals(weapon.getPlayer(), player);
    }

    /**
     * Test for the weapon type getter.
     */
    @Test
    public void testGetType() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        assertEquals(weapon.getType(), Pickup.WeaponType.REGULAR);
        assertFalse(weapon.getType().equals(Pickup.WeaponType.FLOWER));
    }

    /**
     * Test for the max shots getter.
     */
    @Test
    public void testGetMaxCount() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        assertEquals(weapon.getMaxCount(), 1);
        Weapon weapon2 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.FLOWER);
        assertEquals(weapon2.getMaxCount(), 10);
        Weapon weapon3 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.DOUBLE);
        assertEquals(weapon3.getMaxCount(), 2);
        Weapon weapon4 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.STICKY);
        assertEquals(weapon4.getMaxCount(), 1);
    }

    /**
     * Test for the is sticky getter.
     */
    @Test
    public void testIsSticky() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.DOUBLE);
        Weapon weapon1 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.STICKY);
        assertFalse(weapon.isSticky());
        assertTrue(weapon1.isSticky());
    }

    /**
     * First test for fire.
     */
    @Test
    public void testFire1() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        Projectile projectile = mock(Projectile.class);
        assertEquals(weapon.getNumberOfProjectiles(), 0);
        assertEquals(weapon.getMaxCount(), 1);
        weapon.getProjectiles().add(projectile);
        Game mockedContainer = mock(Game.class);
        weapon.fire(mockedContainer, 0, 0, 0, 0);
        verify(mockedContainer, never()).toAdd(any());
        verify(projectile, never()).fire();
    }

    /**
     * Second test for fire.
     * @throws SlickException - Resource not found.
     */
    @Test
    public void testFire2() throws SlickException {
        Resources.init();
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        Game mockedContainer = mock(Game.class);
        assertEquals(weapon.getNumberOfProjectiles(), 0);
        assertEquals(weapon.getMaxCount(), 1);
        weapon.fire(mockedContainer, 0, 0, 0, 0);
        assertEquals(weapon.getNumberOfProjectiles(), 1);
    }

    /**
     * Test for remove.
     */
    @Test
    public void testRemove() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        Projectile projectile = mock(Projectile.class);
        assertFalse(weapon.getProjectiles().contains(projectile));
        weapon.getProjectiles().add(projectile);
        assertTrue(weapon.getProjectiles().contains(projectile));
        Game mockedContainer = mock(Game.class);
        weapon.remove(mockedContainer, projectile);
        verify(mockedContainer, times(1)).toRemove(projectile);
        assertEquals(weapon.getNumberOfProjectiles(), 0);
    }
}
