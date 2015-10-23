package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import nl.tudelft.controller.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;

public class Bubble2Test {
    
    private Image mockedImg;
    private ResourcesWrapper mockedResources;
    
    /**
     * Creates mockedResources and a mocked image, which is used in 
     * the bubble creation.
     */
    @Before
    public void setup() {
        mockedImg = mock(Image.class);
        mockedResources = mock(ResourcesWrapper.class);
        when(mockedResources.getBubbleImage2()).thenReturn(mockedImg);
    }
    
    @Test
    public void testConstructor() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        
        assertEquals(6.0f, bubble.getMaxVerticalSpeed(), 0.0f);
        assertEquals(mockedImg, bubble.getImage());
        assertEquals(0.0f, bubble.getLocX(), 0.0f);
        assertEquals(0.0f, bubble.getLocY(), 0.0f);
    }
    
    @Test
    public void testCreateNextBubbles() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        
        List<AbstractBubble> next = bubble.createNextBubbles();
        
        assertEquals(2, next.size());
        assertTrue(next.get(0) instanceof Bubble1);
    }
    
    @Test
    public void testInitMaxVerticalSpeed() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        
        float speed = bubble.initMaxVerticalSpeed();
        
        assertEquals(6.0f, speed, 0.0f);
    }
}
