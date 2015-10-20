package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.wall.AbstractWall;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Shape;

public class BubbleWallHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private AbstractWall mockedWall;
    private AbstractBubble mockedBubble;
    private Shape mockedShape1;
    private Shape mockedShape2;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedWall = mock(AbstractWall.class);
        mockedBubble = mock(AbstractBubble.class);
        mockedShape1 = mock(Shape.class);
        mockedShape2 = mock(Shape.class);
    }

    @Test
    public void testBubbleWallCollision1() {
        when(mockedWall.getLocX()).thenReturn(1f);

        when(mockedBubble.getMaxSpeed()).thenReturn(1f);
        when(mockedBubble.getLocX()).thenReturn(10f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);
        when(mockedShape1.getHeight()).thenReturn(0f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setHorizontalSpeed(anyFloat());
    }

    @Test
    public void testBubbleWallCollision2() {
        when(mockedWall.getLocX()).thenReturn(0f);
        when(mockedWall.getLocY()).thenReturn(1f);

        when(mockedBubble.getMaxSpeed()).thenReturn(0f);
        when(mockedBubble.getLocX()).thenReturn(0f);
        when(mockedBubble.getLocY()).thenReturn(10f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);
        when(mockedShape1.getHeight()).thenReturn(0f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setVerticalSpeed(anyFloat());
    }

    @Test
    public void testBubbleWallCollision3() {
        when(mockedWall.getLocX()).thenReturn(0f);
        when(mockedWall.getLocY()).thenReturn(10f);

        when(mockedBubble.getMaxSpeed()).thenReturn(0f);
        when(mockedBubble.getLocX()).thenReturn(0f);
        when(mockedBubble.getLocY()).thenReturn(1f);

        when(mockedBubble.getBounds()).thenReturn(mockedShape1);
        when(mockedShape1.getWidth()).thenReturn(0f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setVerticalSpeed(anyFloat());
    }

    @Test
    public void testBubbleWallCollision4() {
        when(mockedWall.getLocX()).thenReturn(1f);
        when(mockedWall.getLocY()).thenReturn(0f);

        when(mockedBubble.getMaxSpeed()).thenReturn(0f);
        when(mockedBubble.getLocX()).thenReturn(0f);
        when(mockedBubble.getLocY()).thenReturn(0f);

        when(mockedBubble.getBounds()).thenReturn(mockedShape1);
        when(mockedShape1.getWidth()).thenReturn(0f);

        when(mockedWall.getBounds()).thenReturn(mockedShape2);
        when(mockedShape2.getWidth()).thenReturn(1f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setHorizontalSpeed(anyFloat());
    }
}
