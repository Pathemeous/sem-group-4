package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.wall.AbstractWall;
import org.junit.Before;
import org.junit.Test;

import org.newdawn.slick.geom.Shape;


public class PickupWallHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private Shape mockedShape1;
    private Shape mockedShape2;
    private AbstractWall mockedWall;
    private Pickup mockedPickup;

    /**
     * Create the interaction map used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedShape1 = mock(Shape.class);
        mockedShape2 = mock(Shape.class);
        mockedWall = mock(AbstractWall.class);
        mockedPickup = mock(Pickup.class);
    }

    @Test
    public void testPickupWallCollision1() {
        when(mockedShape1.getY()).thenReturn(9f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, never()).setLocY(anyInt());
        verify(mockedPickup, never()).setOnGround(anyBoolean());
    }

    @Test
    public void testPickupWallCollision2() {
        when(mockedShape1.getY()).thenReturn(10f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

    @Test
    public void testPickupWallCollision3() {
        when(mockedShape1.getY()).thenReturn(11f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

}
