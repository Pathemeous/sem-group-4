package nl.tudelft.model;

import org.newdawn.slick.SlickException;

import junit.framework.Test;
import junit.framework.TestSuite;
import nl.tudelft.model.Weapon;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.OpenGLTestCase;
import nl.tudelft.semgroup4.Resources;
import static org.mockito.Mockito.*;

public class WeaponTest extends OpenGLTestCase {

    public static Test suite() {
        return new TestSuite(WeaponTest.class);
    }

    public void testGetPlayer() {
        Player player = mock(Player.class);
        assertNotNull(player);
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        assertEquals(weapon.getPlayer(), null);
        weapon.setPlayer(player);
        assertEquals(weapon.getPlayer(), player);
    }

    public void testGetType() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        assertEquals(weapon.getType(), Pickup.WeaponType.REGULAR);
        assertFalse(weapon.getType().equals(Pickup.WeaponType.FLOWER));
    }

    public void testGetMaxCount() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        Weapon weapon2 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.FLOWER);
        Weapon weapon3 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.DOUBLE);
        Weapon weapon4 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.STICKY);
        assertEquals(weapon.getMaxCount(), 1);
        assertEquals(weapon2.getMaxCount(), 10);
        assertEquals(weapon3.getMaxCount(), 2);
        assertEquals(weapon4.getMaxCount(), 1);
    }

    public void testIsSticky() {
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.DOUBLE);
        Weapon weapon1 = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.STICKY);
        assertFalse(weapon.isSticky());
        assertTrue(weapon1.isSticky());
    }

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

    public void testFire2() throws SlickException {
        Resources.init();
        Weapon weapon = new Weapon(Resources.weaponImageRegular, Pickup.WeaponType.REGULAR);
        Game mockedContainer = mock(Game.class);
        assertEquals(weapon.getNumberOfProjectiles(), 0);
        assertEquals(weapon.getMaxCount(), 1);
        weapon.fire(mockedContainer, 0, 0, 0, 0);
        assertEquals(weapon.getNumberOfProjectiles(), 1);
    }

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
