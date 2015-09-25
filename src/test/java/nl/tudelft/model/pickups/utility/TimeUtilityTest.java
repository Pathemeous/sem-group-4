package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;

public class TimeUtilityTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupUtilityTime()).thenReturn(mockedImg);
        TimeUtility utility = new TimeUtility(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupUtilityTime(), utility.getImage());
        assertEquals(0.0f, utility.getLocX(), 0.0f);
        assertEquals(0.0f, utility.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        TimeUtility utility = new TimeUtility(mockedResources, 0, 0);
        
        Level level = new Level(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), 
                new LinkedList<>(), 50000, 0);
        
        assertFalse(utility.isActive());
        assertEquals(50000, level.getTime());
        
        utility.activate(level);
        
        assertTrue(utility.isActive());
        assertEquals(50000, level.getTime());
        
        level.setTime(20000);
        
        TimeUtility utility2 = new TimeUtility(mockedResources, 0, 0);
        utility2.activate(level);
        
        assertEquals(40000, level.getTime());
    }
}
