package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ShieldPowerupTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupPowerShield()).thenReturn(mockedImg);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupPowerShield(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        assertFalse(player.hasPowerup(Powerup.SHIELD));
        assertFalse(powerup.isActive());
        assertFalse(player.hasPowerup(Powerup.INVINCIBLE));
        
        powerup.activate(player);
        
        assertEquals(powerup, player.getPowerup(Powerup.SHIELD));
        assertTrue(powerup.isActive());
    }
    
    @Test
    public void testActivate2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        InvinciblePowerup invincibleMocked = mock(InvinciblePowerup.class);
        
        assertFalse(player.hasPowerup(Powerup.SHIELD));
        assertFalse(powerup.isActive());
        
        player.setPowerup(Powerup.INVINCIBLE, invincibleMocked);
        assertTrue(player.hasPowerup(Powerup.INVINCIBLE));
        
        powerup.activate(player);
        
        assertFalse(player.hasShield());
        assertTrue(powerup.isActive());
        assertTrue(powerup.willBeRemoved());
    }
    
    @Test
    public void testActivate3() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        assertFalse(player.hasPowerup(Powerup.SHIELD));
        assertFalse(powerup.isActive());
        assertFalse(player.hasPowerup(Powerup.INVINCIBLE));
        
        powerup.activate(player);
        
        assertEquals(powerup, player.getPowerup(Powerup.SHIELD));
        assertTrue(powerup.isActive());
        
        ShieldPowerup powerup2 = new ShieldPowerup(mockedResources, 0, 0);
        
        assertTrue(player.hasPowerup(Powerup.SHIELD));
        
        powerup2.activate(player);
        
        assertEquals(powerup2, player.getPowerup(Powerup.SHIELD));
        assertTrue(powerup2.isActive());
        assertTrue(powerup.willBeRemoved());
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        
        assertFalse(powerup.isHit());
        assertEquals(0, powerup.getRemovalCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        
        powerup.update(mockedContainer, 0);
        
        assertEquals(0, powerup.getRemovalCounter());
        
        powerup.setHit(true);
        powerup.update(mockedContainer, 0);
        assertEquals(1, powerup.getRemovalCounter());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        Player player = new Player(mockedResources, 0, 0, true);
        
        powerup.activate(player);
        
        assertFalse(powerup.isHit());
        assertEquals(0, powerup.getRemovalCounter());
        
        powerup.setRemovalCounter(120);        
        Modifiable mockedContainer = mock(Modifiable.class);
        
        powerup.update(mockedContainer, 0);
        
        assertEquals(120, powerup.getRemovalCounter());
        
        assertTrue(powerup.willBeRemoved());
        assertFalse(player.hasShield());
        assertFalse(player.hasPowerup(Powerup.SHIELD));
    }
}
