package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;

import nl.tudelft.controller.util.Helpers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class BubbleTest {

    private ResourcesWrapper mockedResources;

    private Random mockedRandom;

    /**
     * sets up the test.
     *
     * <p>Note the mockedRandom, this is set to a new (non-mocked)
     * instance in the teardown to ensure not leaking into other tests.</p>
     */
    @Before
    public void setUp() {
        mockedResources = mock(ResourcesWrapper.class);
        mockedRandom = mock(Random.class);
        Helpers.setRandom(mockedRandom);
    }

    @After
    public void teardown() {
        // else it leaks into other tests
        Helpers.setRandom(new Random());
    }
    
    @Test
    public void testConstructor1() {
        AbstractBubble bubble = new Bubble6(mockedResources, 0, 0);

        assertEquals(bubble.getLocX(), 0.0f, 0.0f);
        assertEquals(bubble.getLocY(), 0.0f, 0.0f);
        assertEquals(bubble.getVerticalSpeed(), 0.0f, 0.0f);
        assertEquals(bubble.getHorizontalSpeed(), 2.0f, 0.0f);
    }

    @Test
    public void testConstructor2() {
        AbstractBubble bubble = new Bubble6(mockedResources, 0, 0);
        bubble.goLeft();
        
        assertEquals(bubble.getHorizontalSpeed(), -2.0f, 0.0f);
    }

    @Test
    public void testUpdate() throws SlickException {
        AbstractBubble bubble = new Bubble1(mockedResources, 0, 0);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, never()).toAdd(any());
    }

    @Test
    public void testUpdate2() throws SlickException {
        AbstractBubble bubble = new Bubble1(mockedResources, 0, 0);

        bubble.setIsHit();

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, times(1)).toRemove(bubble);
    }

    @Test
    public void testUpdate3() throws SlickException {
        Image mockedImg = mock(Image.class);
        AbstractBubble mockedBubble1 = mock(AbstractBubble.class);
        AbstractBubble mockedBubble2 = mock(AbstractBubble.class);
        when(mockedImg.getWidth()).thenReturn(10);
        when(mockedImg.getHeight()).thenReturn(10);
        when(mockedBubble1.getBounds()).thenReturn(new Rectangle(0, 0, 0, 0));
        when(mockedBubble2.getBounds()).thenReturn(new Rectangle(0, 0, 0, 0));
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);
        
        List<AbstractBubble> next = new ArrayList<>();
        next.add(mockedBubble1);
        next.add(mockedBubble2);
        
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        bubble.setNext(next);

        bubble.setIsHit();

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, times(1)).toRemove(bubble);
        verify(modifiable, atLeast(2)).toAdd(any());
        verify(modifiable, atMost(3)).toAdd(any());
    }

    @Test
    public void testSplitWithoutPickup() throws SlickException {
        Image mockedImg = mock(Image.class);
        AbstractBubble mockedBubble1 = mock(AbstractBubble.class);
        AbstractBubble mockedBubble2 = mock(AbstractBubble.class);
        when(mockedImg.getWidth()).thenReturn(10);
        when(mockedImg.getHeight()).thenReturn(10);
        when(mockedBubble1.getBounds()).thenReturn(new Rectangle(0, 0, 0, 0));
        when(mockedBubble2.getBounds()).thenReturn(new Rectangle(0, 0, 0, 0));
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);

        Random mockedRandom = mock(Random.class);
        Helpers.setRandom(mockedRandom);

        when(mockedRandom.nextInt(anyInt())).thenReturn(2); // nothing will happen

        List<AbstractBubble> next = new ArrayList<>();
        next.add(mockedBubble1);
        next.add(mockedBubble2);
        
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        bubble.setNext(next);

        Modifiable modifiable = mock(Modifiable.class);

        List<AbstractBubble> bubblesList = bubble.split(modifiable);
        assertTrue(bubblesList.contains(mockedBubble1));
        assertTrue(bubblesList.contains(mockedBubble2));
        assertEquals(2, bubblesList.size());
        
        verify(modifiable, times(2)).toAdd(any());
        verify(modifiable, times(1)).toRemove(bubble);
        verify(mockedRandom, times(2)).nextInt(10); // one for type, one for yes/no

    }

    @Test
    public void testSplitWithPickup() throws SlickException {
        Image mockedImg = mock(Image.class);
        AbstractBubble mockedBubble1 = mock(AbstractBubble.class);
        AbstractBubble mockedBubble2 = mock(AbstractBubble.class);
        when(mockedImg.getWidth()).thenReturn(10);
        when(mockedImg.getHeight()).thenReturn(10);
        when(mockedBubble1.getBounds()).thenReturn(new Rectangle(0, 0, 0, 0));
        when(mockedBubble2.getBounds()).thenReturn(new Rectangle(0, 0, 0, 0));
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImg);

        when(mockedRandom.nextInt(anyInt())).thenReturn(8); // pickup will drop

        List<AbstractBubble> next = new ArrayList<>();
        next.add(mockedBubble1);
        next.add(mockedBubble2);

        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        bubble.setNext(next);

        Modifiable modifiable = mock(Modifiable.class);

        List<AbstractBubble> bubblesList = bubble.split(modifiable);
        assertTrue(bubblesList.contains(mockedBubble1));
        assertTrue(bubblesList.contains(mockedBubble2));
        assertEquals(2, bubblesList.size());

        verify(modifiable, times(3)).toAdd(any()); // two bubbles, one pickup
        verify(modifiable, times(1)).toRemove(bubble);
        verify(mockedRandom, times(2)).nextInt(10); // one for type, one for yes/no

    }
    
    @Test
    public void testSplitWithBubble1() throws SlickException {        
        AbstractBubble bubble = new Bubble1(mockedResources, 0, 0);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.split(modifiable);
        
        verify(modifiable, times(0)).toAdd(any());
        verify(modifiable, times(1)).toRemove(bubble);

    }

    @Test
    public void testSpeed1() {        
        assertEquals(
                5.0f, new Bubble1(mockedResources,
                0, 0).getMaxSpeed(), 0.0f);
        assertEquals(
                6.0f, new Bubble2(mockedResources,
                0, 0).getMaxSpeed(), 0.0f);
        assertEquals(
                7.0f, new Bubble3(mockedResources,
                0, 0).getMaxSpeed(), 0.0f);
        assertEquals(
                8.0f, new Bubble4(mockedResources,
                        0, 0).getMaxSpeed(), 0.0f);
        assertEquals(
                9.0f, new Bubble5(mockedResources,
                        0, 0).getMaxSpeed(), 0.0f);
        assertEquals(
                10.0f, new Bubble6(mockedResources,
                        0, 0).getMaxSpeed(), 0.0f);
    }

    @Test
    public void testSpeed2() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);

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
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setVerticalSpeed(25.0f);

        assertEquals(25.0f, bubble.getVerticalSpeed(), 0.0f);

        bubble.setHorizontalSpeed(60.0f);

        assertEquals(60.0f, bubble.getHorizontalSpeed(), 0.0f);
    }

    @Test
    public void testFreeze() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setFrozen(true);
        assertTrue(bubble.isFrozen());
        bubble.setFrozen(false);
        assertFalse(bubble.isFrozen());
    }

    @Test
    public void testSlow() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);

        bubble.setSlow(true);
        assertTrue(bubble.isSlow());
        bubble.setSlow(false);
        assertFalse(bubble.isSlow());
    }
    
    @Test
    public void testGetNext() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        
        LinkedList<AbstractBubble> next = new LinkedList<>();
        
        bubble.setNext(next);
        
        assertEquals(next, bubble.getNext());
    }
    
    @Test
    public void testGetMaxverticalSpeed() {
        AbstractBubble bubble = new Bubble2(mockedResources, 0, 0);
        
        bubble.setMaxVerticalSpeed(2.0f);
        
        assertEquals(2.0f, bubble.getMaxVerticalSpeed(), 0.0f);
    }

    @Test
    public void testGetBounds1() {
        Image mockedImg = mock(Image.class);

        when(mockedImg.getWidth()).thenReturn(100);
        when(mockedImg.getHeight()).thenReturn(100);
        when(mockedResources.getBubbleImage2()).thenReturn(mockedImg);


        AbstractBubble bubble1 = new Bubble2(mockedResources, 0, 0);
        AbstractBubble bubble2 = new Bubble2(mockedResources, 0, 0);

        assertEquals(bubble1.getImage(), mockedImg);
        assertEquals(bubble2.getImage(), mockedImg);

        assertTrue(bubble1.getBounds().intersects(bubble2.getBounds()));

        bubble1.setLocX(-50);
        bubble2.setLocX(50);

        assertTrue(bubble1.getBounds().intersects(bubble2.getBounds()));

        bubble2.setLocX(-50);
        bubble2.setLocX(51);

        assertFalse(bubble1.getBounds().intersects(bubble2.getBounds()));

    }

}
