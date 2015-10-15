package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.bubble.Bubble6;
import nl.tudelft.model.bubble.Bubble6Factory;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.mockito.Mockito;
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
        
        Level level = Mockito.mock(Level.class);
        
        assertFalse(utility.isActive());
        
        utility.activate(level);
        
        assertTrue(utility.isActive());
        assertEquals(level, utility.getLevel());
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        FreezeUtility utility = new FreezeUtility(mockedResources, 0, 0);
        
        AbstractBubble bubble = new Bubble6Factory(mockedResources).createBubble();
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();
        bubbles.add(bubble);
        
        final Level level = Mockito.mock(Level.class);;
        
        assertEquals(0, utility.getFreezeCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(0, utility.getFreezeCounter());
        
        utility.activate(level);
        utility.update(mockedContainer, 0);
        
        assertEquals(1, utility.getFreezeCounter());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        FreezeUtility utility = new FreezeUtility(mockedResources, 0, 0);
        
        AbstractBubble bubble = new Bubble6Factory(mockedResources).createBubble();
        bubble.setFrozen(true);
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();
        bubbles.add(bubble);
        
        Level level = Mockito.mock(Level.class);;
        
        utility.activate(level);
        
        assertEquals(0, utility.getFreezeCounter());
        utility.setFreezeCounter(300);
        assertEquals(300, utility.getFreezeCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        utility.update(mockedContainer, 0);
        
        assertEquals(300, utility.getFreezeCounter());
        assertTrue(utility.willBeRemoved());
    }
}
