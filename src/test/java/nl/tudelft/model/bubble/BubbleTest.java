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

import java.util.LinkedList;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 11/09/15.
 */
public class BubbleTest {

    private ResourcesWrapper mockedResources;
    private BubbleFactoryFactory mockedBubbleFactoryFactory;
    
    @Before
    public void setUp() {
        mockedResources = mock(ResourcesWrapper.class);
        mockedBubbleFactoryFactory = mock(BubbleFactoryFactory.class);
    }
    
    @Test
    public void testConstructor1() {
        Bubble bubble = new Bubble6(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);
        
        assertEquals(bubble.getLocX(), 0.0f, 0.0f);
        assertEquals(bubble.getLocY(), 0.0f, 0.0f);
        assertEquals(bubble.getVerticalSpeed(), 0.0f, 0.0f);
        assertEquals(bubble.getHorizontalSpeed(), 2.0f, 0.0f);
        assertTrue(bubble.goesRight());
    }

    @Test
    public void testConstructor2() {
        Bubble bubble = new Bubble6(mockedResources, mockedBubbleFactoryFactory, 0, 0, false);
        
        assertFalse(bubble.goesRight());
        assertEquals(bubble.getHorizontalSpeed(), -2.0f, 0.0f);
    }

    @Test
    public void testUpdate() throws SlickException {
        Bubble bubble = new Bubble1(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, never()).toAdd(any());
    }

    @Test
    public void testUpdate2() throws SlickException {
        Bubble bubble = new Bubble1(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

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
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        
        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

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
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        when(mockedBubbleFactoryFactory.getSize1()).thenReturn(null);
        
        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        Modifiable modifiable = mock(Modifiable.class);

        LinkedList<Bubble> newBubbles = bubble.split(modifiable, 8);
        
        verify(modifiable, times(3)).toAdd(any());
        verify(modifiable, times(1)).toRemove(bubble);
        
        assertEquals(newBubbles, bubble.getNext());
    }
    
    @Test
    public void testSplitWithRandomBelow7() throws SlickException {
        Image mockedImg = mock(Image.class);
        when(mockedImg.getWidth()).thenReturn(10);
        when(mockedImg.getHeight()).thenReturn(10);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);

        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        Modifiable modifiable = mock(Modifiable.class);

        LinkedList<Bubble> newBubbles = bubble.split(modifiable, 6);
        
        verify(modifiable, times(2)).toAdd(any());
        verify(modifiable, times(1)).toRemove(bubble);
        
        assertEquals(newBubbles, bubble.getNext());
    }
    
    @Test
    public void testSplitWithBubble1() throws SlickException {        
        Bubble bubble = new Bubble1(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        Modifiable modifiable = mock(Modifiable.class);

        LinkedList<Bubble> newBubbles = bubble.split(modifiable, 6);
        
        verify(modifiable, times(0)).toAdd(any());
        verify(modifiable, times(1)).toRemove(bubble);
        
        assertEquals(newBubbles, bubble.getNext());
    }

    @Test
    public void testSpeed1() {        
        assertEquals(
                5.0f, new Bubble1(mockedResources, mockedBubbleFactoryFactory,
                0, 0, true).getMaxSpeed(), 0.0f);
        assertEquals(
                6.0f, new Bubble2(mockedResources, mockedBubbleFactoryFactory,
                0, 0, true).getMaxSpeed(), 0.0f);
        assertEquals(
                7.0f, new Bubble3(mockedResources, mockedBubbleFactoryFactory,
                0, 0, true).getMaxSpeed(), 0.0f);
        assertEquals(
                8.0f, new Bubble4(mockedResources, mockedBubbleFactoryFactory,
                0, 0, true).getMaxSpeed(), 0.0f);
        assertEquals(
                9.0f, new Bubble5(mockedResources, mockedBubbleFactoryFactory,
                0, 0, true).getMaxSpeed(), 0.0f);
        assertEquals(
                10.0f, new Bubble6(mockedResources, mockedBubbleFactoryFactory,
                0, 0, true).getMaxSpeed(), 0.0f);
    }

    @Test
    public void testSpeed2() {
        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

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
        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        bubble.setVerticalSpeed(25.0f);

        assertEquals(25.0f, bubble.getVerticalSpeed(), 0.0f);

        bubble.setHorizontalSpeed(60.0f);

        assertEquals(60.0f, bubble.getHorizontalSpeed(), 0.0f);
    }

    @Test
    public void testFreeze() {
        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        bubble.setFrozen(true);
        assertTrue(bubble.isFrozen());
        bubble.setFrozen(false);
        assertFalse(bubble.isFrozen());
    }

    @Test
    public void testSlow() {
        Bubble bubble = new Bubble2(mockedResources, mockedBubbleFactoryFactory, 0, 0, true);

        bubble.setSlow(true);
        assertTrue(bubble.isSlow());
        bubble.setSlow(false);
        assertFalse(bubble.isSlow());
    }

}
