package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.VerMovingWall;
import org.junit.Before;
import org.junit.Test;

public class VerWallWallHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private VerMovingWall mockedVerWall;
    private AbstractWall mockedWall;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedVerWall = mock(VerMovingWall.class);
        mockedWall = mock(AbstractWall.class);
    }

    @Test
    public void testCollisionSame() {
        when(mockedVerWall.getSpeed()).thenReturn(1f);
        when(mockedVerWall.getLocY()).thenReturn(1f);
        when(mockedVerWall.getHeight()).thenReturn(1);

        when(mockedWall.getLocY()).thenReturn(1f);
        when(mockedWall.getHeight()).thenReturn(1);

        map.collide(mockedGame, mockedVerWall, mockedWall);

        verify(mockedVerWall, never()).setSpeed(anyFloat());
    }

    /**
     * Moving wall is lower than the static wall.
     */
    @Test
    public void testCollisionLower() {
        when(mockedVerWall.getSpeed()).thenReturn(1f);
        when(mockedVerWall.getLocY()).thenReturn(2f);
        when(mockedVerWall.getHeight()).thenReturn(1);

        when(mockedWall.getLocY()).thenReturn(1f);
        when(mockedWall.getHeight()).thenReturn(1);

        map.collide(mockedGame, mockedVerWall, mockedWall);

        verify(mockedVerWall, times(1)).setSpeed(anyFloat());
    }

    /**
     * Moving wall is higher than the static wall.
     */
    @Test
    public void testCollisionHigher() {
        when(mockedVerWall.getSpeed()).thenReturn(1f);
        when(mockedVerWall.getLocY()).thenReturn(1f);
        when(mockedVerWall.getHeight()).thenReturn(1);

        when(mockedWall.getLocY()).thenReturn(5f);
        when(mockedWall.getHeight()).thenReturn(1);

        map.collide(mockedGame, mockedVerWall, mockedWall);

        verify(mockedVerWall, times(1)).setSpeed(anyFloat());
    }
}
