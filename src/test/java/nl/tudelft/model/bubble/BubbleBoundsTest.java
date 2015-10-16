package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

/**
 * Created by justin on 16/10/15.
 */
@RunWith(Parameterized.class)
public class BubbleBoundsTest {

    Image mockedImg;

    AbstractBubble bubble1;
    AbstractBubble bubble2;

    boolean willIntersect;

    /**
     * Parameterized test that will check whether the getBounds of a Bubble
     * are correct.
     *
     *
     * @param locX1 x coordinate of the first bubble
     * @param locY1 y coordinate of the first bubble
     * @param locX2 x coordinate of the second bubble
     * @param locY2 y coordinate of the second bubble
     * @param willIntersect expected result
     */
    public BubbleBoundsTest(
            int locX1, int locY1,
            int locX2, int locY2,
            boolean willIntersect) {

        mockedImg = mock(Image.class);

        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);

        when(mockedImg.getWidth()).thenReturn(100);
        when(mockedImg.getHeight()).thenReturn(100);
        when(mockedResources.getBubbleImage2()).thenReturn(mockedImg);

        bubble1 = new Bubble2(mockedResources, 0, 0);
        bubble2 = new Bubble2(mockedResources, 0, 0);

        bubble1.setLocX(locX1);
        bubble1.setLocY(locY1);
        bubble2.setLocX(locX2);
        bubble2.setLocY(locY2);

        this.willIntersect = willIntersect;

    }

    @Test
    public void testImage() {
        assertEquals(bubble1.getImage(), mockedImg);
        assertEquals(bubble2.getImage(), mockedImg);
    }

    @Test
    public void testType() {
        assertTrue(bubble1.getBounds() instanceof Circle);
        assertTrue(bubble2.getBounds() instanceof Circle);
    }

    @Test
    public void testIntersect() {
        assertEquals(
                willIntersect,
                bubble1.getBounds().intersects(bubble2.getBounds()));
    }

    /**
     * Data for the test.
     *
     * <p>
     *     What we want it to check is whether the bubbles
     *     actually intersect as a circle. There are some parameters that,
     *     when used for rectangles, intersect but with circles wont intersect.
     * </p>
     *
     * @return A collection of parameters, used to instantiate this class.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {
                0, 0,
                0, 0,
                true
            },
            {
                100, 0,
                0, 0,
                true
            },
            {
                101, 0,
                0, 0,
                false
            },
            {
                0, 100,
                0, 0,
                true
            },
            {
                0, 101,
                0, 0,
                false
            },
            {
                70, 70,
                0, 0,
                true
            },
            {
                71, 71,
                0, 0,
                false
            },
        });
    }
}
