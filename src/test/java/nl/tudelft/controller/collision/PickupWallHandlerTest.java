package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.pickups.AbstractPickup;
import nl.tudelft.model.wall.AbstractWall;
import org.junit.Before;
import org.junit.Test;

import org.newdawn.slick.geom.Shape;


public class PickupWallHandlerTest {

    private DefaultPlayerInteractionMap map;
    private AbstractGame mockedGame;
    private Shape mockedShape1;
    private Shape mockedShape2;
    private AbstractWall mockedWall;
    private AbstractPickup mockedPickup;

    /**
     * Create the interaction map used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(AbstractGame.class);
        mockedShape1 = mock(Shape.class);
        mockedShape2 = mock(Shape.class);
        mockedWall = mock(AbstractWall.class);
        mockedPickup = mock(AbstractPickup.class);
    }

    /**
     * Pickup is under wall.
     */
    @Test
    public void testCollisionUnder() {
        when(mockedShape1.getY()).thenReturn(9f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, never()).setLocY(anyInt());
        verify(mockedPickup, never()).setOnGround(anyBoolean());
    }

    @Test
    public void testCollisionSame() {
        when(mockedShape1.getY()).thenReturn(10f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

    /**
     * Pickup is over wall.
     */
    @Test
    public void testCollisionOver() {
        when(mockedShape1.getY()).thenReturn(11f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedPickup.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

}
