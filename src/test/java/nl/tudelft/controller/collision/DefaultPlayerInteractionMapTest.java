package nl.tudelft.controller.collision;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.model.wall.AbstractWall;
import org.junit.Before;
import org.junit.Test;

import org.newdawn.slick.geom.Shape;


public class DefaultPlayerInteractionMapTest {

    private DefaultPlayerInteractionMap map;
    private Game mockedGame;

    /**
     * Create the interaction map used for testing.
     */
    @Before
    public void setUp() {
        map = new DefaultPlayerInteractionMap();
        mockedGame = mock(Game.class);
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

        map.collide(mockedGame, mockedPickup, mockedWall);

        verify(mockedPickup, times(1)).setLocY(anyInt());
        verify(mockedPickup, times(1)).setOnGround(anyBoolean());
    }

    @Test
    public void testUtilPlayerCollision() {
        Utility mockedUtility = mock(Utility.class);
        Player mockedPlayer = mock(Player.class);

        map.collide(mockedGame, mockedUtility, mockedPlayer);

        verify(mockedUtility, times(1)).activate(any());
    }

    @Test
    public void testWeaponPlayerCollision1() {
        Weapon mockedWeapon = mock(Weapon.class);
        Player mockedPlayer = mock(Player.class);

        when(mockedPlayer.isShopWeapon()).thenReturn(true);

        map.collide(mockedGame, mockedWeapon, mockedPlayer);

        verify(mockedWeapon, never()).activate(any());
    }

    @Test
    public void testWeaponPlayerCollision2() {
        Weapon mockedWeapon = mock(Weapon.class);
        Player mockedPlayer = mock(Player.class);

        when(mockedPlayer.isShopWeapon()).thenReturn(false);

        map.collide(mockedGame, mockedWeapon, mockedPlayer);

        verify(mockedWeapon, times(1)).activate(any());
    }

    @Test
    public void testPowerupPlayerCollision() {
        Powerup mockedPowerup = mock(Powerup.class);
        Player mockedPlayer = mock(Player.class);

        map.collide(mockedGame, mockedPowerup, mockedPlayer);

        verify(mockedPowerup, times(1)).activate(any());
    }
}
