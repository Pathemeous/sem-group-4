package nl.tudelft.controller.collision;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyFloat;
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
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.model.wall.AbstractMovingWall;
import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.HorMovingWall;
import nl.tudelft.model.wall.RegularWall;
import nl.tudelft.model.wall.VerMovingWall;
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

    @Test
    public void testMovingWallProjectileCollision() {
        AbstractMovingWall mockedWall = mock(AbstractMovingWall.class);
        Projectile mockedProjectile = mock(Projectile.class);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, times(1)).setHitWall();
    }

    @Test
    public void testWallProjectileCollsion1() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getY()).thenReturn(11f);

        RegularWall mockedWall = mock(RegularWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getY()).thenReturn(10f);

        Projectile mockedProjectile = mock(Projectile.class);
        when(mockedProjectile.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, never()).setHitWall();
    }

    @Test
    public void testWallProjectileCollsio21() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getY()).thenReturn(10f);

        RegularWall mockedWall = mock(RegularWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getY()).thenReturn(10f);

        Projectile mockedProjectile = mock(Projectile.class);
        when(mockedProjectile.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, never()).setHitWall();
    }

    @Test
    public void testWallProjectileCollsion3() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getY()).thenReturn(1f);

        RegularWall mockedWall = mock(RegularWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getY()).thenReturn(10f);

        Projectile mockedProjectile = mock(Projectile.class);
        when(mockedProjectile.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedProjectile);

        verify(mockedProjectile, times(1)).setHitWall();
    }

    @Test
    public void testVerMovingWallWallCollision1() {
        VerMovingWall mockedVerWall = mock(VerMovingWall.class);
        when(mockedVerWall.getSpeed()).thenReturn(1f);
        when(mockedVerWall.getLocY()).thenReturn(1f);
        when(mockedVerWall.getHeight()).thenReturn(1);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocY()).thenReturn(1f);
        when(mockedWall.getHeight()).thenReturn(1);

        map.collide(mockedGame, mockedVerWall, mockedWall);

        verify(mockedVerWall, never()).setSpeed(anyFloat());
    }

    @Test
    public void testVerMovingWallWallCollision2() {
        VerMovingWall mockedVerWall = mock(VerMovingWall.class);
        when(mockedVerWall.getSpeed()).thenReturn(1f);
        when(mockedVerWall.getLocY()).thenReturn(2f);
        when(mockedVerWall.getHeight()).thenReturn(1);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocY()).thenReturn(1f);
        when(mockedWall.getHeight()).thenReturn(1);

        map.collide(mockedGame, mockedVerWall, mockedWall);

        verify(mockedVerWall, times(1)).setSpeed(anyFloat());
    }

    @Test
    public void testVerMovingWallWallCollision3() {
        VerMovingWall mockedVerWall = mock(VerMovingWall.class);
        when(mockedVerWall.getSpeed()).thenReturn(1f);
        when(mockedVerWall.getLocY()).thenReturn(1f);
        when(mockedVerWall.getHeight()).thenReturn(1);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocY()).thenReturn(5f);
        when(mockedWall.getHeight()).thenReturn(1);

        map.collide(mockedGame, mockedVerWall, mockedWall);

        verify(mockedVerWall, times(1)).setSpeed(anyFloat());
    }

    @Test
    public void testHorMovingWallWallCollision1() {
        HorMovingWall mockedHorWall = mock(HorMovingWall.class);
        when(mockedHorWall.getSpeed()).thenReturn(1f);
        when(mockedHorWall.getLocX()).thenReturn(1f);
        when(mockedHorWall.getWidth()).thenReturn(1);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(1f);
        when(mockedWall.getWidth()).thenReturn(1);

        map.collide(mockedGame, mockedHorWall, mockedWall);

        verify(mockedHorWall, never()).setSpeed(anyFloat());
    }

    @Test
    public void testHorMovingWallWallCollision2() {
        HorMovingWall mockedHorWall = mock(HorMovingWall.class);
        when(mockedHorWall.getSpeed()).thenReturn(1f);
        when(mockedHorWall.getLocX()).thenReturn(2f);
        when(mockedHorWall.getWidth()).thenReturn(1);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(1f);
        when(mockedWall.getWidth()).thenReturn(1);

        map.collide(mockedGame, mockedHorWall, mockedWall);

        verify(mockedHorWall, times(1)).setSpeed(anyFloat());
    }

    @Test
    public void testHorMovingWallWallCollision3() {
        HorMovingWall mockedHorWall = mock(HorMovingWall.class);
        when(mockedHorWall.getSpeed()).thenReturn(1f);
        when(mockedHorWall.getLocX()).thenReturn(1f);
        when(mockedHorWall.getWidth()).thenReturn(1);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(5f);
        when(mockedWall.getWidth()).thenReturn(1);

        map.collide(mockedGame, mockedHorWall, mockedWall);

        verify(mockedHorWall, times(1)).setSpeed(anyFloat());
    }
}
