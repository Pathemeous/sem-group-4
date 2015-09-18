package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import nl.tudelft.semgroup4.Resources;

import org.junit.Test;
import org.newdawn.slick.Image;


public class ProjectileTest extends OpenGLTestCase {

    @Test
    public void testProjectile() {
        Weapon weapon = mock(Weapon.class);
        Image img = Resources.weaponImageRegular.copy();;
        Projectile projectile = new Projectile(img, 1, 1, 1, 1, 1, weapon);
        assertEquals(projectile.getHeight(), 800);
        assertEquals(projectile.getWidth(), 8);
        assertEquals(projectile.getImage(), img);
        assertEquals(projectile.getWeapon(), weapon);
        assertEquals(projectile.getLocX(), 1, 0);
        assertEquals(projectile.getLocY(), 1, 0);
        assertTrue(projectile.getBounds() != null);
    }
}
