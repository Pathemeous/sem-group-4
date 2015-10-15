package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.resources.ResourceWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;

public class LifePowerupTest {
    
    @Test
    public void testConstructor() {
        ResourceWrapper mockedResources = mock(ResourceWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupUtilityLife()).thenReturn(mockedImg);
        LifePowerup powerup = new LifePowerup(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupUtilityLife(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourceWrapper mockedResources = mock(ResourceWrapper.class);
        LifePowerup powerup = new LifePowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        assertEquals(3, player.getLives());
        assertFalse(powerup.isActive());
        
        powerup.activate(player);
        
        assertEquals(4, player.getLives());
        assertTrue(powerup.isActive());
        assertTrue(powerup.willBeRemoved());
    }
}
