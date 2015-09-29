package nl.tudelft.model.pickups.weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.AbstractOpenGLTestCase;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ProjectileTest extends AbstractOpenGLTestCase {

    private Weapon mockedWeapon;
    private ResourcesWrapper mockedResources;
    private Image mockedImage;
    
    /**
     * Mock weapon, resources, sound and image before every test method. 
     */
    @Before
    public void setUp() {
        mockedWeapon = mock(Weapon.class);
        mockedResources = mock(ResourcesWrapper.class);
        Sound mockedSound = mock(Sound.class);
        when(mockedResources.getWeaponFire()).thenReturn(mockedSound);
        mockedImage = mock(Image.class);
    }

    @Test
    public void testProjectile1() {
        when(mockedWeapon.getNumberOfProjectiles()).thenReturn(1);
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        assertEquals(0, projectile.getHeight());
        assertEquals(0, projectile.getWidth());
        assertEquals(projectile.getImage(), mockedImage);
        assertEquals(projectile.getWeapon(), mockedWeapon);
        assertEquals(1, projectile.getLocX(), 1);
        assertEquals(1, projectile.getLocY(), 1);
        assertTrue(projectile.getBounds() != null);
    }

    @Test
    public void testReset1() {
        Modifiable modifiable = mock(Modifiable.class);
        when(mockedWeapon.isSticky()).thenReturn(false);
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        projectile.reset(modifiable);
        verify(mockedWeapon, times(1)).remove(modifiable, projectile);
    }

    @Test
    public void testReset2() {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        projectile.setHitBubble();
        assertTrue(projectile.isHitBubble());
        when(mockedWeapon.isSticky()).thenReturn(true);
        Modifiable modifiable = mock(Modifiable.class);
        projectile.reset(modifiable);
        verify(mockedWeapon, times(1)).remove(modifiable, projectile);
    }

    @Test
    public void testReset3() {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        Modifiable modifiable = mock(Modifiable.class);
        when(mockedWeapon.isSticky()).thenReturn(true);
        assertEquals(projectile.getTickCount(), 0);
        projectile.reset(modifiable);
        assertEquals(projectile.getTickCount(), 1);
    }

    @Test
    public void testReset4() {
        Modifiable modifiable = mock(Modifiable.class);
        when(mockedWeapon.isSticky()).thenReturn(true);
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        assertEquals(projectile.getTickCount(), 0);
        projectile.reset(modifiable);
        assertEquals(projectile.getTickCount(), 1);
        projectile.reset(modifiable);
        projectile.setTickCount(90);
        projectile.reset(modifiable);
        verify(mockedWeapon, times(1)).remove(modifiable, projectile);
        assertEquals(projectile.getTickCount(), 0);
    }

    @Test
    public void testUpdate1() throws SlickException {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        Modifiable modifiable = mock(Modifiable.class);
        assertEquals(projectile.getLocY(), 1, 0);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getLocY(), 0, 0);
    }

    @Test
    public void testUpdate2() throws SlickException {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        projectile.setHitWall();
        when(mockedWeapon.isSticky()).thenReturn(true);
        assertEquals(projectile.getTickCount(), 0);
        Modifiable modifiable = mock(Modifiable.class);
        projectile.update(modifiable, 1);
        assertEquals(1, projectile.getTickCount());
    }

    @Test
    public void testUpdate3() throws SlickException {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        projectile.setHitWall();
        when(mockedWeapon.isSticky()).thenReturn(true);
        assertEquals(projectile.getTickCount(), 0);
        Modifiable modifiable = mock(Modifiable.class);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getTickCount(), 1);
        projectile.update(modifiable, 1);
        assertEquals(projectile.getTickCount(), 2);
    }

    @Test
    public void testUpdate4() throws SlickException {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        projectile.setHitBubble();
        Modifiable modifiable = mock(Modifiable.class);
        projectile.update(modifiable, 1);
        verify(mockedWeapon, times(1)).remove(any(), any());
    }
    
    @Test
    public void testFire() {
        Projectile projectile =
                new Projectile(mockedResources, mockedImage, 1, 1, 1, 1, 1, mockedWeapon);
        
        assertEquals(1, projectile.getLocX(), 0);
        
        projectile.fire();
        
        assertEquals(1.5, projectile.getLocX(), 0);
    }
}
