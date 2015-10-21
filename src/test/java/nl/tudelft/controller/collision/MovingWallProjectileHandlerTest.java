package nl.tudelft.controller.collision;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.wall.AbstractMovingWall;
import org.junit.Test;

public class MovingWallProjectileHandlerTest {


    @Test
    public void testCollision() {
        DefaultPlayerInteractionMap map = new DefaultPlayerInteractionMap();
        Game mockedGame = mock(Game.class);
        AbstractMovingWall mockedWall = mock(AbstractMovingWall.class);
        Projectile mockedProjectile = mock(Projectile.class);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, times(1)).setHitWall();
    }
}
