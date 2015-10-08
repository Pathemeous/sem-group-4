package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;

public class Bubble1Test {
    
    @Test
    public void testBubble1Constructor() {
        Image mockedImg = mock(Image.class);
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble1(mockedResources, 0, 0, true);
        
        assertEquals(5.0f, bubble.getMaxVerticalSpeed(), 0.0f);
        assertEquals(mockedImg, bubble.getImage());
        assertEquals(0.0f, bubble.getLocX(), 0.0f);
        assertEquals(0.0f, bubble.getLocY(), 0.0f);
    }
}
