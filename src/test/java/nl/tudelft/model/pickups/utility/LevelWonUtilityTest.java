package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.AbstractBubble;

import org.junit.Test;
import org.newdawn.slick.Image;

public class LevelWonUtilityTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupUtilityLevelwon()).thenReturn(mockedImg);
        LevelWonUtility utility = new LevelWonUtility(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupUtilityLevelwon(), utility.getImage());
        assertEquals(0.0f, utility.getLocX(), 0.0f);
        assertEquals(0.0f, utility.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        LevelWonUtility utility = new LevelWonUtility(mockedResources, 0, 0);
        
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();
        Level mockedLevel = mock(Level.class);
        when(mockedLevel.getBubbles()).thenReturn(bubbles);
        
        assertFalse(utility.isActive());
        
        utility.activate(mockedLevel);
        
        assertTrue(utility.isActive());
        assertTrue(utility.willBeRemoved());
        verify(mockedLevel, times(1)).splitAllBubbles(bubbles, true);
    }
}
