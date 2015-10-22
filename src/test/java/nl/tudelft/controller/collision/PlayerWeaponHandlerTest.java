package nl.tudelft.controller.collision;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.model.player.Player;

import org.junit.Before;
import org.junit.Test;

public class PlayerWeaponHandlerTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;
    private Weapon mockedWeapon;
    private Player mockedPlayer;

    /**
     * Create the interaction map and mocks used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
        mockedWeapon = mock(Weapon.class);
        mockedPlayer = mock(Player.class);
    }

    @Test
    public void testCollisionHasShopweapon() {
        when(mockedPlayer.isShopWeapon()).thenReturn(true);

        map.collide(mockedGame, mockedWeapon, mockedPlayer);

        verify(mockedWeapon, never()).activate(any());
    }

    @Test
    public void testCollisionNormal() {
        when(mockedPlayer.isShopWeapon()).thenReturn(false);

        map.collide(mockedGame, mockedWeapon, mockedPlayer);

        verify(mockedWeapon, times(1)).activate(any());
    }
}
