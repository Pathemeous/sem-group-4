package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Level;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SlowUtilityTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupUtilitySlow()).thenReturn(mockedImg);
        SlowUtility utility = new SlowUtility(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupUtilitySlow(), utility.getImage());
        assertEquals(0.0f, utility.getLocX(), 0.0f);
        assertEquals(0.0f, utility.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SlowUtility utility = new SlowUtility(mockedResources, 0, 0);
        
        Level level = Mockito.mock(Level.class);
        
        assertFalse(utility.isActive());
        
        utility.activate(level);
        
        assertTrue(utility.isActive());
        assertEquals(level, utility.getLevel());
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SlowUtility utility = new SlowUtility(mockedResources, 0, 0);
        
        final Level level = Mockito.mock(Level.class);
        
        assertEquals(0, utility.getSlowCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(0, utility.getSlowCounter());
        
        utility.activate(level);
        utility.update(mockedContainer, 0);
        
        assertEquals(1, utility.getSlowCounter());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SlowUtility utility = new SlowUtility(mockedResources, 0, 0);
        
        Level level = Mockito.mock(Level.class);
        
        utility.activate(level);
        
        assertEquals(0, utility.getSlowCounter());
        utility.setSlowCounter(300);
        assertEquals(300, utility.getSlowCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(300, utility.getSlowCounter());
        assertTrue(utility.willBeRemoved());
    }
}
