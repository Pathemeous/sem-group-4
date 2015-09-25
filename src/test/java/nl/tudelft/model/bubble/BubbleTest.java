package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.AbstractOpenGLTestCase;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Audio;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 11/09/15.
 */
public class BubbleTest extends AbstractOpenGLTestCase {
    
    @Before
    public void setUp() {
        Audio.musicOn = false;
    }
    
    @Test
    public void testConstructor1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble6(mockedResources, 0, 0);
        
        assertEquals(bubble.getLocX(), 0.0f, 0.0f);
        assertEquals(bubble.getLocY(), 0.0f, 0.0f);
        assertEquals(bubble.getVerticalSpeed(), 0.0f, 0.0f);
        assertEquals(bubble.getHorizontalSpeed(), 2.0f, 0.0f);
        assertTrue(bubble.goesRight());
    }

    @Test
    public void testConstructor2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble6(mockedResources, 0, 0, false);
        
        assertFalse(bubble.goesRight());
        assertEquals(bubble.getHorizontalSpeed(), -2.0f, 0.0f);
    }

    @Test
    public void testUpdate() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble1(mockedResources, 0, 0);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, never()).toAdd(any());
    }

    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble1(mockedResources, 0, 0);

        bubble.setIsHit();

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, times(1)).toRemove(bubble);
    }

    @Test
    public void testUpdate3() throws SlickException {
        Image mockedImg = mock(Image.class);
        when(mockedImg.getWidth()).thenReturn(10);
        when(mockedImg.getHeight()).thenReturn(10);
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setIsHit();

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, times(1)).toRemove(bubble);
        verify(modifiable, atLeast(2)).toAdd(any());
        verify(modifiable, atMost(3)).toAdd(any());
    }

    @Test
    public void testSplitWithRandomAbove7() throws SlickException {
        Image mockedImg = mock(Image.class);
        when(mockedImg.getWidth()).thenReturn(10);
        when(mockedImg.getHeight()).thenReturn(10);
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble2(mockedResources, 0, 0);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.split(modifiable, 8);
    }

    @Test
    public void testSpeed1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        
        assertEquals(5.0f, new Bubble1(mockedResources, 0, 0).getMaxSpeed(), 0.0f);
        assertEquals(6.0f, new Bubble2(mockedResources, 0, 0).getMaxSpeed(), 0.0f);
        assertEquals(7.0f, new Bubble3(mockedResources, 0, 0).getMaxSpeed(), 0.0f);
        assertEquals(8.0f, new Bubble4(mockedResources, 0, 0).getMaxSpeed(), 0.0f);
        assertEquals(9.0f, new Bubble5(mockedResources, 0, 0).getMaxSpeed(), 0.0f);
        assertEquals(10.0f, new Bubble6(mockedResources, 0, 0).getMaxSpeed(), 0.0f);
    }

    @Test
    public void testSpeed2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble2(mockedResources, 0, 0);

        assertEquals(6.0f, bubble.getMaxSpeed(), 0.0f);

        bubble.setHorizontalSpeed(3.0f);

        assertEquals(6.0f, bubble.getMaxSpeed(), 0.0f);

        bubble.setHorizontalSpeed(7.0f);

        assertEquals(7.0f, bubble.getMaxSpeed(), 0.0f);

        bubble.setVerticalSpeed(8.0f);

        assertEquals(7.0f, bubble.getMaxSpeed(), 0.0f);
    }

    @Test
    public void testSpeed3() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setVerticalSpeed(25.0f);

        assertEquals(25.0f, bubble.getVerticalSpeed(), 0.0f);

        bubble.setHorizontalSpeed(60.0f);

        assertEquals(60.0f, bubble.getHorizontalSpeed(), 0.0f);
    }

    @Test
    public void testFreeze() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setFrozen(true);
        bubble.setFrozen(false);
    }

    @Test
    public void testSlow() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Bubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setSlow(true);
        bubble.setSlow(false);
    }

}
