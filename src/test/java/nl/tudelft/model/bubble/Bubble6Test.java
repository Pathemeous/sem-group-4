package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;

public class Bubble6Test {
    
    @Test
    public void testBubble2Constructor() {
        Image mockedImg = mock(Image.class);
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        when(mockedResources.getBubbleImage6()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble6(mockedResources, 0, 0, true);
        
        assertEquals(10.0f, bubble.getMaxVerticalSpeed(), 0.0f);
        assertEquals(mockedImg, bubble.getImage());
        assertEquals(0.0f, bubble.getLocX(), 0.0f);
        assertEquals(0.0f, bubble.getLocY(), 0.0f);
        assertEquals(2, bubble.getNext().size());
        assertTrue(bubble.getNext().get(0) instanceof Bubble5);
    }
}
