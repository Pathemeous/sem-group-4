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
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
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

    @Test
    public void testWallPlayerCollision1() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getX()).thenReturn(11f);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getX()).thenReturn(11f);

        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedPlayer);

        verify(mockedPlayer, times(1)).setLocX(anyFloat());
    }

    @Test
    public void testWallPlayerCollision2() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getX()).thenReturn(1f);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getX()).thenReturn(11f);

        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedPlayer);

        verify(mockedPlayer, times(1)).setLocX(anyFloat());
    }

    @Test
    public void testWallPlayerCollision3() {
        Shape mockedShape1 = mock(Shape.class);
        when(mockedShape1.getX()).thenReturn(11f);

        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape1);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedShape2.getX()).thenReturn(1f);

        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.getBounds()).thenReturn(mockedShape2);

        map.collide(mockedGame, mockedWall, mockedPlayer);

        verify(mockedPlayer, times(1)).setLocX(anyFloat());
    }

    @Test
    public void testBubbleProjectileCollision1() {
        Projectile mockedProjectile = mock(Projectile.class);
        when(mockedProjectile.isHitBubble()).thenReturn(true);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);

        Player mockedPlayer = mock(Player.class);
        Weapon mockedWeapon = mock(Weapon.class);
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
        Projectile mockedProjectile = mock(Projectile.class);
        when(mockedProjectile.isHitBubble()).thenReturn(false);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);

        Player mockedPlayer = mock(Player.class);
        Weapon mockedWeapon = mock(Weapon.class);
        when(mockedWeapon.getPlayer()).thenReturn(mockedPlayer);
        when(mockedProjectile.getWeapon()).thenReturn(mockedWeapon);

        map.collide(mockedGame, mockedBubble, mockedProjectile);

        verify(mockedProjectile, times(1)).setHitBubble();
        verify(mockedBubble, times(1)).setIsHit();
        verify(mockedWeapon, times(1)).getPlayer();
        verify(mockedPlayer, times(1)).setScore(anyInt());
    }

    @Test
    public void testBubblePlayerCollision1() {
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.isAlive()).thenReturn(false);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
    }

    @Test
         public void testBubblePlayerCollision2() {
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(true);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.isFrozen()).thenReturn(true);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedPlayer, times(1)).isInvincible();
        verify(mockedBubble, never()).isFrozen();
    }

    @Test
    public void testBubblePlayerCollision3() {
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(true);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedPlayer, times(1)).isInvincible();
        verify(mockedBubble, never()).isFrozen();
    }

    @Test
    public void testBubblePlayerCollision4() {
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.isFrozen()).thenReturn(true);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedPlayer, times(1)).isAlive();
        verify(mockedBubble, times(1)).isFrozen();
    }

    @Test
    public void testBubblePlayerCollision5() {
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);
        when(mockedPlayer.hasShield()).thenReturn(true);

        ShieldPowerup mockedShield = mock(ShieldPowerup.class);
        when(mockedPlayer.getPowerup(Powerup.SHIELD)).thenReturn(mockedShield);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedShield, times(1)).setHit(true);
        verify(mockedBubble, times(1)).setIsHit();
    }

    @Test
    public void testBubblePlayerCollision6() {
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.isAlive()).thenReturn(true);
        when(mockedPlayer.isInvincible()).thenReturn(false);
        when(mockedPlayer.hasShield()).thenReturn(false);
        when(mockedPlayer.hasShopShield()).thenReturn(true);

        Hit3ShieldPowerup mockedShield = mock(Hit3ShieldPowerup.class);
        when(mockedPlayer.getPowerup(Powerup.SHOPSHIELD)).thenReturn(mockedShield);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.isFrozen()).thenReturn(false);

        map.collide(mockedGame, mockedBubble, mockedPlayer);

        verify(mockedShield, times(1)).incrementHit();
        verify(mockedBubble, times(1)).setIsHit();
    }

    @Test
    public void testBubbleWallCollision1() {
        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(1f);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.getMaxSpeed()).thenReturn(1f);
        when(mockedBubble.getLocX()).thenReturn(10f);

        Shape mockedShape = mock(Shape.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape);
        when(mockedShape.getHeight()).thenReturn(0f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setHorizontalSpeed(anyFloat());
    }

    @Test
    public void testBubbleWallCollision2() {
        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(0f);
        when(mockedWall.getLocY()).thenReturn(1f);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.getMaxSpeed()).thenReturn(0f);
        when(mockedBubble.getLocX()).thenReturn(0f);
        when(mockedBubble.getLocY()).thenReturn(10f);

        Shape mockedShape = mock(Shape.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape);
        when(mockedShape.getHeight()).thenReturn(0f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setVerticalSpeed(anyFloat());
    }

    @Test
    public void testBubbleWallCollision3() {
        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(0f);
        when(mockedWall.getLocY()).thenReturn(10f);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.getMaxSpeed()).thenReturn(0f);
        when(mockedBubble.getLocX()).thenReturn(0f);
        when(mockedBubble.getLocY()).thenReturn(1f);

        Shape mockedShape = mock(Shape.class);
        when(mockedBubble.getBounds()).thenReturn(mockedShape);
        when(mockedShape.getWidth()).thenReturn(0f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setVerticalSpeed(anyFloat());
    }

    @Test
    public void testBubbleWallCollision4() {
        AbstractWall mockedWall = mock(AbstractWall.class);
        when(mockedWall.getLocX()).thenReturn(1f);
        when(mockedWall.getLocY()).thenReturn(0f);

        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        when(mockedBubble.getMaxSpeed()).thenReturn(0f);
        when(mockedBubble.getLocX()).thenReturn(0f);
        when(mockedBubble.getLocY()).thenReturn(0f);

        Shape mockedShape1 = mock(Shape.class);
        when(mockedBubble.getBounds()).thenReturn(mockedShape1);
        when(mockedShape1.getWidth()).thenReturn(0f);

        Shape mockedShape2 = mock(Shape.class);
        when(mockedWall.getBounds()).thenReturn(mockedShape2);
        when(mockedShape2.getWidth()).thenReturn(1f);

        map.collide(mockedGame, mockedBubble, mockedWall);

        verify(mockedBubble, times(1)).setHorizontalSpeed(anyFloat());
    }
}
