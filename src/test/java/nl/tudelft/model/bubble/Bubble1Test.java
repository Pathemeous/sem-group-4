package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.semgroup4.resources.ResourceWrapper;

import org.junit.Test;
import org.newdawn.slick.Image;

public class Bubble1Test {
    
    @Test
    public void testConstructor() {
        Image mockedImg = mock(Image.class);
        ResourceWrapper mockedResources = mock(ResourceWrapper.class);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble1(mockedResources, 0, 0);
        
        assertEquals(5.0f, bubble.getMaxVerticalSpeed(), 0.0f);
        assertEquals(mockedImg, bubble.getImage());
        assertEquals(0.0f, bubble.getLocX(), 0.0f);
        assertEquals(0.0f, bubble.getLocY(), 0.0f);
    }
}
