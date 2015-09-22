package nl.tudelft.model.pickups.weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.model.AbstractOpenGLTestCase;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;

import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ProjectileTest extends AbstractOpenGLTestCase {

    @Test
    public void testProjectile1() {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        when(weapon.getNumberOfProjectiles()).thenReturn(1);
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        assertEquals(projectile.getHeight(), 800);
        assertEquals(projectile.getWidth(), 8);
        assertEquals(projectile.getImage(), img);
        assertEquals(projectile.getWeapon(), weapon);
        assertEquals(projectile.getLocX(), 1, 0);
        assertEquals(projectile.getLocY(), 1, 0);
        assertTrue(projectile.getBounds() != null);
    }

    @Test
    public void testReset1() {
        Modifiable modifiable = mock(Modifiable.class);
        Weapon weapon = mock(Weapon.class);
        when(weapon.isSticky()).thenReturn(false);
        Image img = Resources.weaponImageRegular.copy();
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        projectile.reset(modifiable);
        verify(weapon, times(1)).remove(any(), any());
    }

    @Test
    public void testReset2() {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        projectile.setHitBubble();
        assertTrue(projectile.isHitBubble());
        when(weapon.isSticky()).thenReturn(true);
        Modifiable modifiable = mock(Modifiable.class);
        projectile.reset(modifiable);
        verify(weapon, times(1)).remove(any(), any());
    }

    @Test
    public void testReset3() {
        Modifiable modifiable = mock(Modifiable.class);
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        when(weapon.isSticky()).thenReturn(true);
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        assertEquals(projectile.getTickCount(), 0);
        projectile.reset(modifiable);
        assertEquals(projectile.getTickCount(), 1);
    }

    @Test
    public void testReset4() {
        Modifiable modifiable = mock(Modifiable.class);
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        when(weapon.isSticky()).thenReturn(true);
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        assertEquals(projectile.getTickCount(), 0);
        projectile.reset(modifiable);
        assertEquals(projectile.getTickCount(), 1);
        projectile.reset(modifiable);
        projectile.setTickCount(90);
        projectile.reset(modifiable);
        verify(weapon, times(1)).remove(any(), any());
        assertEquals(projectile.getTickCount(), 0);
    }

    @Test
    public void testUpdate1() throws SlickException {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        Modifiable modifiable = mock(Modifiable.class);
        assertEquals(projectile.getLocY(), 1, 0);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getLocY(), 0, 0);
    }

    @Test
    public void testUpdate2() throws SlickException {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        projectile.setHitWall();
        when(weapon.isSticky()).thenReturn(true);
        assertEquals(projectile.getTickCount(), 0);
        Modifiable modifiable = mock(Modifiable.class);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getTickCount(), 1);
    }

    @Test
    public void testUpdate3() throws SlickException {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        projectile.setHitWall();
        when(weapon.isSticky()).thenReturn(true);
        assertEquals(projectile.getTickCount(), 0);
        Modifiable modifiable = mock(Modifiable.class);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getTickCount(), 1);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getTickCount(), 2);
    }

    @Test
    public void testUpdate4() throws SlickException {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        projectile.setHitBubble();
        Modifiable modifiable = mock(Modifiable.class);
        projectile.update(modifiable, 1);
        verify(weapon, times(1)).remove(any(), any());
    }
}
