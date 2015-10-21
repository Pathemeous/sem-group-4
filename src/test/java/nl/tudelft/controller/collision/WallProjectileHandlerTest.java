package nl.tudelft.controller.collision;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.wall.RegularWall;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Shape;

public class WallProjectileHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private Shape mockedShape1;
    private Shape mockedShape2;
    private RegularWall mockedWall;
    private Projectile mockedProjectile;


    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedShape1 = mock(Shape.class);
        mockedShape2 = mock(Shape.class);
        mockedWall = mock(RegularWall.class);
        mockedProjectile = mock(Projectile.class);
    }

    /**
     * Projectile over wall.
     */
    @Test
    public void testCollisionHigher() {
        when(mockedShape1.getY()).thenReturn(11f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedProjectile.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, never()).setHitWall();
    }

    @Test
    public void testCollisionSame() {
        when(mockedShape1.getY()).thenReturn(10f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedProjectile.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, never()).setHitWall();
    }

    /**
     * Projectile under wall.
     */
    @Test
    public void testCollisionLower() {
        when(mockedShape1.getY()).thenReturn(1f);

        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        when(mockedShape2.getY()).thenReturn(10f);

        when(mockedProjectile.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, times(1)).setHitWall();
    }
}
