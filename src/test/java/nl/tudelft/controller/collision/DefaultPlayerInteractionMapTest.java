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


public class DefaultPlayerInteractionMapTest {

    private DefaultPlayerInteractionMap map;

    /**
     * Create the interaction map used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
    }

    @Test
    public void testPickupWallCollision1() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getY()).thenReturn(9f);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getY()).thenReturn(10f);

        Pickup mockedPickup = mock(Pickup.class);
        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        Game mockedGame = mock(Game.class);
        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, never()).setLocY(anyInt());
        verify(mockedPickup, never()).setOnGround(anyBoolean());
    }

    @Test
    public void testPickupWallCollision2() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getY()).thenReturn(10f);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getY()).thenReturn(10f);

        Pickup mockedPickup = mock(Pickup.class);
        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        Game mockedGame = mock(Game.class);
        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

    @Test
    public void testPickupWallCollision3() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getY()).thenReturn(11f);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getY()).thenReturn(10f);

        Pickup mockedPickup = mock(Pickup.class);
        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        Game mockedGame = mock(Game.class);
        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

}
