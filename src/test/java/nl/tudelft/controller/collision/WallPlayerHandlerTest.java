package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.wall.AbstractWall;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Shape;

public class WallPlayerHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private Shape mockedShape1;
    private Shape mockedShape2;
    private Player mockedPlayer;
    private AbstractWall mockedWall;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedShape1 = mock(Shape.class);
        mockedShape2 = mock(Shape.class);
        mockedPlayer = mock(Player.class);
        mockedWall = mock(AbstractWall.class);
    }

    @Test
    public void testWallPlayerCollision1() {
        when(mockedShape1.getX()).thenReturn(11f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getX()).thenReturn(11f);

        when(mockedPlayer.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedPlayer);

        verify(mockedPlayer, times(1)).setLocX(anyFloat());
    }

    @Test
    public void testWallPlayerCollision2() {
        when(mockedShape1.getX()).thenReturn(1f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getX()).thenReturn(11f);

        when(mockedPlayer.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedPlayer);

        verify(mockedPlayer, times(1)).setLocX(anyFloat());
    }

    @Test
    public void testWallPlayerCollision3() {
        when(mockedShape1.getX()).thenReturn(11f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getX()).thenReturn(1f);

        when(mockedPlayer.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedPlayer);

        verify(mockedPlayer, times(1)).setLocX(anyFloat());
    }
}
