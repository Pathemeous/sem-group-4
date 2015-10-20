package nl.tudelft.controller.collision;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.pickups.weapon.Weapon;
import org.junit.Before;
import org.junit.Test;

public class ProjectileBubbleHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private Projectile mockedProjectile;
    private AbstractBubble mockedBubble;
    private Player mockedPlayer;
    private Weapon mockedWeapon;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedProjectile = mock(Projectile.class);
        mockedBubble = mock(AbstractBubble.class);
        mockedPlayer = mock(Player.class);
        mockedWeapon = mock(Weapon.class);
    }

    @Test
    public void testBubbleProjectileCollision1() {
        when(mockedProjectile.isHitBubble()).thenReturn(true);

        when(mockedWeapon.getPlayer()).thenReturn(mockedPlayer);
        when(mockedProjectile.getWeapon()).thenReturn(mockedWeapon);

        map.collide(mockedGame, mockedBubble, mockedProjectile);

        verify(mockedProjectile, never()).setHitBubble();
        verify(mockedBubble, never()).setIsHit();
        verify(mockedWeapon, never()).getPlayer();
        verify(mockedPlayer, never()).setScore(anyInt());
    }

    @Test
    public void testBubbleProjectileCollision2() {
        when(mockedProjectile.isHitBubble()).thenReturn(false);

        when(mockedWeapon.getPlayer()).thenReturn(mockedPlayer);
        when(mockedProjectile.getWeapon()).thenReturn(mockedWeapon);

        map.collide(mockedGame, mockedBubble, mockedProjectile);

        verify(mockedProjectile, times(1)).setHitBubble();
        verify(mockedBubble, times(1)).setIsHit();
        verify(mockedWeapon, times(1)).getPlayer();
        verify(mockedPlayer, times(1)).setScore(anyInt());
    }
}
