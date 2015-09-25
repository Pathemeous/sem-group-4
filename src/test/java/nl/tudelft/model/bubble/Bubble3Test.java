package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;

public class Bubble3Test {
    
    @Test
    public void testBubble3Constructor() {
        Image mockedImg = mock(Image.class);
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        when(mockedResources.getBubbleImage3()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble3(mockedResources, 0, 0);
        
        assertEquals(7.0f, bubble.getMaxVerticalSpeed(), 0.0f);
        assertEquals(mockedImg, bubble.getImage());
        assertEquals(0.0f, bubble.getLocX(), 0.0f);
        assertEquals(0.0f, bubble.getLocY(), 0.0f);
        assertEquals(2, bubble.getNext().size());
        assertTrue(bubble.getNext().get(0) instanceof Bubble2);
    }
}
