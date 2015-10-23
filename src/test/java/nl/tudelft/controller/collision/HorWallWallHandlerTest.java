package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.HorMovingWall;
import org.junit.Before;
import org.junit.Test;


public class HorWallWallHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private HorMovingWall mockedHorWall;
    private AbstractWall mockedWall;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedHorWall = mock(HorMovingWall.class);
        mockedWall = mock(AbstractWall.class);
    }

    @Test
    public void testCollisionSame() {
        when(mockedHorWall.getSpeed()).thenReturn(1f);
        when(mockedHorWall.getLocX()).thenReturn(1f);
        when(mockedHorWall.getWidth()).thenReturn(1);

        when(mockedWall.getLocX()).thenReturn(1f);
        when(mockedWall.getWidth()).thenReturn(1);

        map.collide(mockedGame, mockedHorWall, mockedWall);

        verify(mockedHorWall, never()).setSpeed(anyFloat());
    }

    /**
     * Moving wall is to the left of static wall.
     */
    @Test
    public void testCollisionLeft() {
        when(mockedHorWall.getSpeed()).thenReturn(1f);
        when(mockedHorWall.getLocX()).thenReturn(2f);
        when(mockedHorWall.getWidth()).thenReturn(1);

        when(mockedWall.getLocX()).thenReturn(1f);
        when(mockedWall.getWidth()).thenReturn(1);

        map.collide(mockedGame, mockedHorWall, mockedWall);

        verify(mockedHorWall, times(1)).setSpeed(anyFloat());
    }

    /**
     * Moving wall is to the right of static wall.
     */
    @Test
    public void testCollisionRight() {
        when(mockedHorWall.getSpeed()).thenReturn(1f);
        when(mockedHorWall.getLocX()).thenReturn(1f);
        when(mockedHorWall.getWidth()).thenReturn(1);

        when(mockedWall.getLocX()).thenReturn(5f);
        when(mockedWall.getWidth()).thenReturn(1);

        map.collide(mockedGame, mockedHorWall, mockedWall);

        verify(mockedHorWall, times(1)).setSpeed(anyFloat());
    }
}
