package nl.tudelft.controller.collision;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.model.pickups.powerup.AbstractPowerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import nl.tudelft.model.player.Player;

import org.junit.Before;
import org.junit.Test;

public class PlayerBubbleHandlerTest {

    private DefaultPlayerInteractionMap map;
    private AbstractGame mockedGame;
    private Player mockedPlayer;
    private AbstractBubble mockedBubble;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(AbstractGame.class);
        mockedPlayer = mock(Player.class);
        mockedBubble = mock(AbstractBubble.class);
    }

    /**
     * Test a collision when the player is already dead.
     */
    @Test
    public void testCollisionDead() {
        when(mockedPlayer.isAlive()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
    }

    /**
     * Test a collision when the player is invincible.
     * Bubbles are frozen.
     */
    @Test
    public void testCollisionInvincible1() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(true);

        when(mockedBubble.isFrozen()).thenReturn(true);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedPlayer, times(1)).isInvincible();
        verify(mockedBubble, never()).isFrozen();
    }

    /**
     * Test a collision when the player is invincible.
     * Bubbles aren't frozen.
     */
    @Test
    public void testCollisionInvincible2() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(true);

        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedPlayer, times(1)).isInvincible();
        verify(mockedBubble, never()).isFrozen();
    }

    /**
     * Test a collision when the bubbles are frozen.
     * Player isn't invincible.
     */
    @Test
    public void testCollisionFrozen() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);

        when(mockedBubble.isFrozen()).thenReturn(true);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedBubble, times(1)).isFrozen();
    }

    @Test
    public void testCollisionShield() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);
        when(mockedPlayer.hasShield()).thenReturn(true);

        ShieldPowerup mockedShield = mock(ShieldPowerup.class);
        when(mockedPlayer.getPowerup(AbstractPowerup.SHIELD)).thenReturn(mockedShield);

        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedShield, times(1)).setHit(true);
        verify(mockedBubble, times(1)).setIsHit();
    }

    @Test
    public void testCollisionHit3Shield() {
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);
        when(mockedPlayer.hasShield()).thenReturn(false);
        when(mockedPlayer.hasShopShield()).thenReturn(true);

        Hit3ShieldPowerup mockedShield = mock(Hit3ShieldPowerup.class);
        when(mockedPlayer.getPowerup(AbstractPowerup.SHOPSHIELD)).thenReturn(mockedShield);

        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedShield, times(1)).incrementHit();
        verify(mockedBubble, times(1)).setIsHit();
    }
}
