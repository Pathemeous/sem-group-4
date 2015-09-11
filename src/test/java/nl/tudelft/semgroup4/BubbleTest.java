package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Bubble;
import org.junit.Test;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 11/09/15.
 */
public class BubbleTest extends OpenGLTestCase {

    @Test
    public void testConstructor1() {
        boolean done = false;
        boolean goRight = false;
        do {
            for (int i = 1; i <= 6; i++) {
                Bubble bubble = new Bubble(0, 0, i, goRight);
                assertEquals(bubble.getLocX(), 0.0f, 0.0f);
                assertEquals(bubble.getLocY(), 0.0f, 0.0f);
                assertEquals(bubble.getSize(), i);
            }
            if (goRight) {
                done = true;
            }
            goRight = true;
        } while (!done);
    }

    @Test
    public void testConstructor2() {
        Bubble bubble = new Bubble(0, 0, 1);
        assertEquals(bubble.getLocX(), 0.0f, 0.0f);
        assertEquals(bubble.getLocY(), 0.0f, 0.0f);
        assertEquals(bubble.getSize(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        new Bubble(0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor4() {
        new Bubble(0, 0, 7);
    }

    @Test
    public void testUpdate() throws SlickException {
        Bubble bubble = new Bubble(0, 0, 1);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, never()).toAdd(any());
    }

    @Test
    public void testUpdate2() throws SlickException {
        Bubble bubble = new Bubble(0, 0, 1);

        bubble.setIsHit();

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, times(1)).toRemove(bubble);
    }

    @Test
    public void testUpdate3() throws SlickException {
        Bubble bubble = new Bubble(0, 0, 2);

        bubble.setIsHit();

        Modifiable modifiable = mock(Modifiable.class);

        bubble.update(modifiable, 0);

        verify(modifiable, times(1)).toRemove(bubble);
        verify(modifiable, atLeast(2)).toAdd(any());
        verify(modifiable, atMost(3)).toAdd(any());
    }

    @Test
    public void testSplitWithRandomAbove7() throws SlickException {
        Bubble bubble = new Bubble(0, 0, 2);

        Modifiable modifiable = mock(Modifiable.class);

        bubble.split(modifiable, 8);
    }

    @Test
    public void testSpeed1() {
        assertEquals(5.0f, new Bubble(0, 0, 1).getMaxSpeed(), 0.0f);
        assertEquals(6.0f, new Bubble(0, 0, 2).getMaxSpeed(), 0.0f);
        assertEquals(7.0f, new Bubble(0, 0, 3).getMaxSpeed(), 0.0f);
        assertEquals(8.0f, new Bubble(0, 0, 4).getMaxSpeed(), 0.0f);
        assertEquals(9.0f, new Bubble(0, 0, 5).getMaxSpeed(), 0.0f);
        assertEquals(10.0f, new Bubble(0, 0, 6).getMaxSpeed(), 0.0f);
    }

    @Test
    public void testSpeed2() {
        Bubble bubble = new Bubble(0, 0, 2);

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
        Bubble bubble = new Bubble(0, 0, 2);

        bubble.setVerticalSpeed(25.0f);

        assertEquals(25.0f, bubble.getVerticalSpeed(), 0.0f);

        bubble.setHorizontalSpeed(60.0f);

        assertEquals(60.0f, bubble.getHorizontalSpeed(), 0.0f);
    }

    @Test
    public void testFreeze() {
        Bubble bubble = new Bubble(0, 0, 2);

        bubble.freeze(true);
        bubble.freeze(false);
    }

    @Test
    public void testSlow() {
        Bubble bubble = new Bubble(0, 0, 2);

        bubble.slowBubbleDown(true);
        bubble.slowBubbleDown(false);
    }

}
