package nl.tudelft.model.pickups.weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.AbstractOpenGLTestCase;
import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.resources.Resources;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class WeaponTest extends AbstractOpenGLTestCase {
    
    private ResourcesWrapper mockedResources;
    private Player mockedPlayer;
    
    /**
     * Creates the required dependencies for Weapon.
     */
    @Before
    public void setUp() {
        mockedResources = mock(ResourcesWrapper.class);
        mockedPlayer = mock(Player.class);
    }
    
    @Test
    public void testActivate() {        
        Weapon weapon1 = new RegularWeapon(mockedResources, 0, 0);
        weapon1.activate(mockedPlayer);

        verify(mockedPlayer, times(1)).setWeapon(weapon1);
        Weapon weapon2 = new DoubleWeapon(mockedResources, 0, 0);
        weapon2.activate(mockedPlayer);

        verify(mockedPlayer, times(1)).setWeapon(weapon2);
    }

    @Test
    public void testUpdate() throws SlickException {
        Weapon weapon = new RegularWeapon(mockedResources, 0, 0);
        assertEquals(0, weapon.getFireCounter());
        weapon.update(null, 0);
        assertEquals(0, weapon.getFireCounter());
        
        weapon.setFireCounter(1);
        weapon.update(null, 0);
        assertEquals(2, weapon.getFireCounter());
    }
    
    /**
     * Test for the player getter.
     */
    @Test
    public void testGetPlayer() {
        Player player = mock(Player.class);
        assertNotNull(player);
        Weapon weapon = new RegularWeapon(mockedResources, 0, 0);
        assertEquals(weapon.getPlayer(), null);
        weapon.setPlayer(player);
        assertEquals(weapon.getPlayer(), player);
    }

    /**
     * Test for the max shots getter.
     */
    @Test
    public void testGetMaxCount() {
        Weapon weapon = new RegularWeapon(mockedResources, 0, 0);
        assertEquals(weapon.getMaxCount(), 1);
        Weapon weapon2 = new FlowerWeapon(mockedResources, 0, 0);
        assertEquals(weapon2.getMaxCount(), 10);
        Weapon weapon3 = new DoubleWeapon(mockedResources, 0, 0);
        assertEquals(weapon3.getMaxCount(), 2);
        Weapon weapon4 = new StickyWeapon(mockedResources, 0, 0);
        assertEquals(weapon4.getMaxCount(), 1);
    }

    /**
     * Test for the is sticky getter.
     */
    @Test
    public void testIsSticky() {
        Weapon weapon = new DoubleWeapon(mockedResources, 0, 0);
        Weapon weapon1 = new StickyWeapon(mockedResources, 0, 0);
        assertFalse(weapon.isSticky());
        assertTrue(weapon1.isSticky());
    }

    /**
     * First test for fire.
     */
    @Test
    public void testFire1() {
        Weapon weapon = new RegularWeapon(mockedResources, 0, 0);
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
        Weapon weapon = new RegularWeapon(mockedResources, 0, 0);
        weapon.activate(mockedPlayer);
        Game mockedContainer = mock(Game.class);
        assertEquals(weapon.getNumberOfProjectiles(), 0);
        assertEquals(weapon.getMaxCount(), 1);
        weapon.fire(mockedContainer, 0, 0, 0, 0);
        assertEquals(weapon.getNumberOfProjectiles(), 1);
    }
//
//    /**
//     * Test for remove.
//     */
//    @Test
//    public void testRemove() {
//        Weapon weapon = new RegularWeapon(0, 0);
//        Projectile projectile = mock(Projectile.class);
//        assertFalse(weapon.getProjectiles().contains(projectile));
//        weapon.getProjectiles().add(projectile);
//        assertTrue(weapon.getProjectiles().contains(projectile));
//        Game mockedContainer = mock(Game.class);
//        weapon.remove(mockedContainer, projectile);
//        verify(mockedContainer, times(1)).toRemove(projectile);
//        assertEquals(weapon.getNumberOfProjectiles(), 0);
//    }
}
