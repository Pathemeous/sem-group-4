package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Player;

import org.junit.Test;
import org.newdawn.slick.Image;

public class PointsPowerupTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupPowerPoints()).thenReturn(mockedImg);
        PointsPowerup powerup = new PointsPowerup(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupPowerPoints(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        PointsPowerup powerup = new PointsPowerup(mockedResources, 0, 0);
        
        Player player = new Player(mockedResources, 0, 0, true);
        
        assertEquals(0, player.getScore());
        assertFalse(powerup.isActive());
        
        powerup.activate(player);
        
        assertEquals(100, player.getScore());
        assertTrue(powerup.isActive());
        assertTrue(powerup.willBeRemoved());
    }
}
