package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.bubble.Bubble6;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
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
        
        Level level = new Level(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), 
                new LinkedList<>(), 0, 0);
        
        assertFalse(utility.isActive());
        
        utility.activate(level);
        
        assertTrue(utility.isActive());
        assertEquals(level, utility.getLevel());
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SlowUtility utility = new SlowUtility(mockedResources, 0, 0);
        
        Bubble bubble = new Bubble6(mockedResources, 0, 0);
        LinkedList<Bubble> bubbles = new LinkedList<>();
        bubbles.add(bubble);
        
        final Level level = new Level(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), 
                bubbles, 0, 0);
        
        assertEquals(0, utility.getSlowCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(0, utility.getSlowCounter());
        
        utility.activate(level);
        utility.update(mockedContainer, 0);
        
        assertEquals(1, utility.getSlowCounter());
        assertTrue(bubbles.get(0).isSlow());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        SlowUtility utility = new SlowUtility(mockedResources, 0, 0);
        
        Bubble bubble = new Bubble6(mockedResources, 0, 0);
        bubble.setSlow(true);
        LinkedList<Bubble> bubbles = new LinkedList<>();
        bubbles.add(bubble);
        
        Level level = new Level(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), 
                bubbles, 0, 0);
        
        utility.activate(level);
        
        assertEquals(0, utility.getSlowCounter());
        utility.setSlowCounter(300);
        assertEquals(300, utility.getSlowCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(300, utility.getSlowCounter());
        assertTrue(utility.willBeRemoved());
        assertFalse(bubbles.get(0).isSlow());
    }
}
