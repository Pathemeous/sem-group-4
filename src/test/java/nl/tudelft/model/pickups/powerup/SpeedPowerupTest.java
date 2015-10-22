package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpeedPowerupTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupPowerSpeedup()).thenReturn(mockedImg);
        SpeedPowerup powerup = new SpeedPowerup(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupPowerSpeedup(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SpeedPowerup powerup = new SpeedPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        assertFalse(player.hasPowerup(Powerup.SPEED));
        assertFalse(powerup.isActive());
        
        powerup.activate(player);
        
        assertEquals(powerup, player.getPowerup(Powerup.SPEED));
        assertTrue(powerup.isActive());
        assertEquals(8, player.getSpeed());
    }
    
    @Test
    public void testActivate2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SpeedPowerup powerup = new SpeedPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        assertFalse(player.hasPowerup(Powerup.SPEED));
        assertFalse(powerup.isActive());
        
        powerup.activate(player);
        
        assertEquals(powerup, player.getPowerup(Powerup.SPEED));
        assertTrue(powerup.isActive());
        assertEquals(8, player.getSpeed());
        
        SpeedPowerup powerup2 = new SpeedPowerup(mockedResources, 0, 0);
        
        powerup2.activate(player);
        
        assertTrue(powerup.willBeRemoved());
        
        assertEquals(powerup2, player.getPowerup(Powerup.SPEED));
        assertTrue(powerup2.isActive());
        assertEquals(8, player.getSpeed());
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SpeedPowerup powerup = new SpeedPowerup(mockedResources, 0, 0);
        
        assertEquals(0, powerup.getSpeedCount());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        
        powerup.update(mockedContainer, 0);
        
        assertEquals(0, powerup.getSpeedCount());
        
        powerup.setActive(true);
        powerup.update(mockedContainer, 0);
        assertEquals(1, powerup.getSpeedCount());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SpeedPowerup powerup = new SpeedPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        powerup.activate(player);
        powerup.setSpeedCount(599);
        
        assertEquals(8, player.getSpeed());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        
        powerup.update(mockedContainer, 0);
        
        assertEquals(600, powerup.getSpeedCount());
        assertTrue(powerup.willBeRemoved());
        assertEquals(4, player.getSpeed());
        assertFalse(player.hasPowerup(Powerup.SPEED));
    }
}
