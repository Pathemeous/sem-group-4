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

public class FreezeUtilityTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupUtilityFreeze()).thenReturn(mockedImg);
        FreezeUtility utility = new FreezeUtility(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupUtilityFreeze(), utility.getImage());
        assertEquals(0.0f, utility.getLocX(), 0.0f);
        assertEquals(0.0f, utility.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        FreezeUtility utility = new FreezeUtility(mockedResources, 0, 0);
        
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
        FreezeUtility utility = new FreezeUtility(mockedResources, 0, 0);
        
        Bubble bubble = new Bubble6(mockedResources, 0, 0);
        LinkedList<Bubble> bubbles = new LinkedList<>();
        bubbles.add(bubble);
        
        Level level = new Level(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), 
                bubbles, 0, 0);
        
        assertEquals(0, utility.getFreezeCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(0, utility.getFreezeCounter());
        
        utility.activate(level);
        utility.update(mockedContainer, 0);
        
        assertEquals(1, utility.getFreezeCounter());
        assertTrue(bubbles.get(0).isFrozen());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        FreezeUtility utility = new FreezeUtility(mockedResources, 0, 0);
        
        Bubble bubble = new Bubble6(mockedResources, 0, 0);
        bubble.setFrozen(true);
        LinkedList<Bubble> bubbles = new LinkedList<>();
        bubbles.add(bubble);
        
        Level level = new Level(new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), 
                bubbles, 0, 0);
        
        utility.activate(level);
        
        assertEquals(0, utility.getFreezeCounter());
        utility.setFreezeCounter(300);
        assertEquals(300, utility.getFreezeCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(300, utility.getFreezeCounter());
        assertTrue(utility.willBeRemoved());
        assertFalse(bubbles.get(0).isFrozen());
    }
}
