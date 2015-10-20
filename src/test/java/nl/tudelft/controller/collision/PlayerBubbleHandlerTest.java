package nl.tudelft.controller.collision;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import org.junit.Before;
import org.junit.Test;

public class PlayerBubbleHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private Player mockedPlayer;
    private AbstractBubble mockedBubble;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedPlayer = mock(Player.class);
        mockedBubble = mock(AbstractBubble.class);
    }

    @Test
    public void testBubblePlayerCollision1() {
        when(mockedPlayer.isAlive()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
    }

    @Test
    public void testBubblePlayerCollision2() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(true);

        when(mockedBubble.isFrozen()).thenReturn(true);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedPlayer, times(1)).isInvincible();
        verify(mockedBubble, never()).isFrozen();
    }

    @Test
    public void testBubblePlayerCollision3() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(true);

        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedPlayer, times(1)).isInvincible();
        verify(mockedBubble, never()).isFrozen();
    }

    @Test
    public void testBubblePlayerCollision4() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);

        when(mockedBubble.isFrozen()).thenReturn(true);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedBubble, times(1)).isFrozen();
    }

    @Test
    public void testBubblePlayerCollision5() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);
        when(mockedPlayer.hasShield()).thenReturn(true);

        ShieldPowerup mockedShield = mock(ShieldPowerup.class);
        when(mockedPlayer.getPowerup(Powerup.SHIELD)).thenReturn(mockedShield);

        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedShield, times(1)).setHit(true);
        verify(mockedBubble, times(1)).setIsHit();
    }

    @Test
    public void testBubblePlayerCollision6() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);
        when(mockedPlayer.hasShield()).thenReturn(false);
        when(mockedPlayer.hasShopShield()).thenReturn(true);

        Hit3ShieldPowerup mockedShield = mock(Hit3ShieldPowerup.class);
        when(mockedPlayer.getPowerup(Powerup.SHOPSHIELD)).thenReturn(mockedShield);

        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedShield, times(1)).incrementHit();
        verify(mockedBubble, times(1)).setIsHit();
    }
}
