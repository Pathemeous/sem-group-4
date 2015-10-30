package nl.tudelft.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.controller.util.SemRectangle;
import nl.tudelft.model.Level;
import nl.tudelft.model.pickups.powerup.InvinciblePowerup;
import nl.tudelft.model.pickups.powerup.AbstractPowerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import nl.tudelft.model.pickups.powerup.SpeedPowerup;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;
import nl.tudelft.model.pickups.weapon.RegularWeapon;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/**
 * tests cases for the player class.
 * @author Damian
 */
public class PlayerTest {

    private Player player;
    private ResourcesWrapper mockedResources;
    
    private static final int SPEED = 4;
    
    /**
     * Mock all required dependencies.
     */
    @Before
    public final void setUp() {
        mockedResources = mock(ResourcesWrapper.class);
        Image mockedImage = mock(Image.class);
        when(mockedResources.getPlayerImageStill()).thenReturn(mockedImage);
        player = new ConcretePlayer(mockedResources, 0, 0, true);
    }

    @Test
    public final void testConstructor() {
        assertTrue(player.isFirstPlayer());
        assertEquals(0, player.getScore());
        assertEquals(3, player.getLives());
        assertEquals(SPEED, player.getSpeed());
    }

    @Test
    public final void testReset() {
        AbstractWeapon weapon = new DoubleWeapon(mockedResources, 0, 0);
        AbstractPowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        player.setWeapon(weapon);
        player.setPowerup(AbstractPowerup.INVINCIBLE, powerup);
        player.reset();
        assertTrue(player.getPowerup(AbstractPowerup.INVINCIBLE) == null);
        assertTrue(player.getWeapon() instanceof RegularWeapon);
    }

    @Test
    public final void testResetShopWeapon() {
        AbstractWeapon weapon = mock(AbstractWeapon.class);
        ArrayList projectiles = mock(ArrayList.class);

        when(weapon.getProjectiles()).thenReturn(projectiles);

        player.setWeapon(weapon);
        player.setShopWeapon(true);

        player.reset();

        verify(projectiles, times(1)).clear();
    }

    @Test
    public final void testMoveLeft() {
        final float x = player.getLocX();

        player.moveLeft();

        assertEquals(x - player.getSpeed(), player.getLocX(), 0.0f);
    }

    @Test
    public final void testMoveRight() {
        final float x = player.getLocX();

        player.moveRight();

        assertEquals(x + player.getSpeed(), player.getLocX(), 0.0f);
    }

    @Test
    public final void testAnimations() {
        Animation walkLeft = mock(Animation.class);
        Animation walkRight = mock(Animation.class);

        when(mockedResources.getPlayerWalkLeft()).thenReturn(walkLeft);
        when(mockedResources.getPlayerWalkRight()).thenReturn(walkRight);

        // re-init player apply the mocked resources
        player = new ConcretePlayer(mockedResources, 0, 0, true);


        assertEquals(null, player.getAnimationCurrent());

        player.moveLeft();

        assertEquals(walkLeft, player.getAnimationCurrent());

        player.moveRight();

        assertEquals(walkRight, player.getAnimationCurrent());

        player.stopMoving();

        assertEquals(null, player.getAnimationCurrent());

    }

    @Test
    public final void testFireWeapon() {
        AbstractWeapon weapon = mock(AbstractWeapon.class);

        player.setWeapon(weapon);

        player.shoot();


        verify(weapon, times(1)).fire(any(), anyInt(), anyInt(), anyInt(), anyInt());

    }

    @Test
    public final void testClearPowerups() {
        AbstractPowerup invincible = new InvinciblePowerup(mockedResources, 0, 0);
        AbstractPowerup shield = new ShieldPowerup(mockedResources, 0, 0);
        AbstractPowerup speed = new SpeedPowerup(mockedResources, 0, 0);
        
        player.setPowerup(AbstractPowerup.INVINCIBLE, invincible);
        player.setPowerup(AbstractPowerup.SHIELD, shield);
        player.setPowerup(AbstractPowerup.SPEED, speed);
        
        assertEquals(invincible, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertEquals(shield, player.getPowerup(AbstractPowerup.SHIELD));
        assertEquals(speed, player.getPowerup(AbstractPowerup.SPEED));
        
        player.clearAllPowerups();
        
        assertEquals(null, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertEquals(null, player.getPowerup(AbstractPowerup.SHIELD));
        assertEquals(null, player.getPowerup(AbstractPowerup.SPEED));
    }
    
    @Test
    public final void testHasPowerup() {
        AbstractPowerup invincible = new InvinciblePowerup(mockedResources, 0, 0);
        AbstractPowerup shield = new ShieldPowerup(mockedResources, 0, 0);
        AbstractPowerup speed = new SpeedPowerup(mockedResources, 0, 0);
        
        player.setPowerup(AbstractPowerup.INVINCIBLE, invincible);
        player.setPowerup(AbstractPowerup.SHIELD, shield);
        player.setPowerup(AbstractPowerup.SPEED, speed);
        
        assertTrue(player.hasPowerup(AbstractPowerup.INVINCIBLE));
        assertTrue(player.hasPowerup(AbstractPowerup.SHIELD));
        assertTrue(player.hasPowerup(AbstractPowerup.SPEED));
    }
    
    @Test
    public final void testRemovePowerup() {
        AbstractPowerup invincible = new InvinciblePowerup(mockedResources, 0, 0);
        AbstractPowerup shield = new ShieldPowerup(mockedResources, 0, 0);
        AbstractPowerup speed = new SpeedPowerup(mockedResources, 0, 0);
        
        player.setPowerup(AbstractPowerup.INVINCIBLE, invincible);
        player.setPowerup(AbstractPowerup.SHIELD, shield);
        player.setPowerup(AbstractPowerup.SPEED, speed);
        
        player.removePowerup(AbstractPowerup.INVINCIBLE);
        player.removePowerup(AbstractPowerup.SHIELD);
        player.removePowerup(AbstractPowerup.SPEED);
        
        assertEquals(null, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertEquals(null, player.getPowerup(AbstractPowerup.SHIELD));
        assertEquals(null, player.getPowerup(AbstractPowerup.SPEED));
    }
    
    @Test
    public final void testIsInvincible() {
        assertFalse(player.isInvincible());
        
        AbstractPowerup invincible = new InvinciblePowerup(mockedResources, 0, 0);
        player.setPowerup(AbstractPowerup.INVINCIBLE, invincible);
        
        assertTrue(player.isInvincible());
    }
    
    @Test
    public final void testHasShield() {
        assertFalse(player.hasShield());
        
        AbstractPowerup shield = new ShieldPowerup(mockedResources, 0, 0);
        player.setPowerup(AbstractPowerup.SHIELD, shield);
        
        assertTrue(player.hasShield());
    }
    
    @Test
    public final void testDefaultSpeed() {
        player.setSpeed(10);
        player.setDefaultSpeed();
        
        assertEquals(SPEED, player.getSpeed());
    }

    @Test
    public final void testLastGettersSetters() {
        Animation mockedAnimation = mock(Animation.class);
        assertTrue(player.isFirstPlayer());
        player.setAnimationCurrent(mockedAnimation);
        assertEquals(mockedAnimation, player.getAnimationCurrent());
    }

    @Test
    public final void testGetBounds() {
        Image mockedImage = mock(Image.class);
        player.setImage(mockedImage);
        SemRectangle rectangle = new SemRectangle(0, 0,
                player.getWidth() - 20, player.getHeight() - 15);
        assertEquals((int) rectangle.getHeight(), (int) player.getBounds().getHeight());
        assertEquals((int)rectangle.getWidth(), (int)player.getBounds().getWidth());
    }

    @Test
    public final void testLives() {
        assertEquals(3, player.getLives());
        player.removeLife();
        assertEquals(2, player.getLives());
    }

    @Test
    public final void testDie() {
        assertEquals(0, player.getScore());
        assertEquals(3, player.getLives());
        assertTrue(player.isAlive());

        player.die();
        assertEquals(-1000, player.getScore());
        assertEquals(2, player.getLives());
        assertTrue(player.isAlive());

        player.die();
        assertEquals(-2000, player.getScore());
        assertEquals(1, player.getLives());
        assertTrue(player.isAlive());

        player.die();
        assertEquals(-3000, player.getScore());
        assertEquals(0, player.getLives());
        assertFalse(player.isAlive());
    }

    @Test
    public final void testClearSpeed() {
        AbstractPowerup powerup = mock(AbstractPowerup.class);
        player.setPowerup(AbstractPowerup.SPEED, powerup);
        assertEquals(powerup, player.getPowerup(AbstractPowerup.SPEED));

        player.clearAllPowerups();

        assertEquals(null, player.getPowerup(AbstractPowerup.SPEED));
    }

    @Test
    public final void testClearShield() {
        AbstractPowerup powerup = mock(AbstractPowerup.class);
        player.setPowerup(AbstractPowerup.SHIELD, powerup);
        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHIELD));
        assertTrue(player.hasShield());

        player.clearAllPowerups();

        assertEquals(null, player.getPowerup(AbstractPowerup.SHIELD));
        assertFalse(player.hasShield());
    }

    @Test
    public final void testClearInvincible() {
        AbstractPowerup powerup = mock(AbstractPowerup.class);
        player.setPowerup(AbstractPowerup.INVINCIBLE, powerup);
        assertEquals(powerup, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertTrue(player.isInvincible());

        player.clearAllPowerups();

        assertEquals(null, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertFalse(player.isInvincible());
    }

    @Test
    public final void testClearShopSield() {
        AbstractPowerup powerup = mock(AbstractPowerup.class);
        player.setPowerup(AbstractPowerup.SHOPSHIELD, powerup);
        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHOPSHIELD));
        assertTrue(player.hasShopShield());

        player.clearAllPowerups();

        assertEquals(null, player.getPowerup(AbstractPowerup.SHOPSHIELD));
        assertFalse(player.hasShopShield());
    }

    @Test
    public final void testSpeed() {
        player.setSpeed(4);
        assertEquals(4, player.getSpeed());

        player.setSpeed(3);
        assertEquals(3, player.getSpeed());

        player.setSpeed(2);
        assertEquals(2, player.getSpeed());

        player.setSpeed(1);
        assertEquals(1, player.getSpeed());
    }

    @Test
    public final void testShopWeapon() {
        assertFalse(player.isShopWeapon());
        player.setShopWeapon(true);
        assertTrue(player.isShopWeapon());
    }

    @Test
    public final void testShopSpeed() {
        assertFalse(player.isShopSpeed());
        player.setShopSpeed(true);
        assertTrue(player.isShopSpeed());
    }

    @Test
    public final void testRender1() throws SlickException {
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Graphics mockedGraphics = mock(Graphics.class);

        player.setAnimationCurrent(null);
        player.render(mockedGameContainer, mockedGraphics);

        verify(mockedGraphics, times(1)).drawImage(any(), anyFloat(), anyFloat());
    }

    @Test
    public final void testRender2() throws SlickException {
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Graphics mockedGraphics = mock(Graphics.class);
        Animation mockedAnimation = mock(Animation.class);

        player.setAnimationCurrent(mockedAnimation);
        player.render(mockedGameContainer, mockedGraphics);

        verify(mockedGraphics, times(1)).drawAnimation(any(), anyFloat(), anyFloat());

    }

    @Test
    public final void testUpdate1() throws SlickException {
        Level mockedLevel = mock(Level.class);

        player.setLives(0);
        player.update(mockedLevel, 1);

        verify(mockedLevel, times(1)).toRemove(player);
    }

    @Test
    public final void testUpdate2() throws SlickException {
        Level mockedLevel = mock(Level.class);

        player.update(mockedLevel, 1);

        verify(mockedLevel, times(1)).toAdd(any());
    }
    
    @Test
    public void testGetLeftAnimation() {
        Animation mockedAnimation = Mockito.mock(Animation.class);
        Mockito.when(mockedResources.getPlayerWalkLeft()).thenReturn(mockedAnimation);
        player = new ConcretePlayer(mockedResources, 0, 0, true);
        
        Animation result = player.getAnimationLeft();
        assertEquals(mockedAnimation, result);
        
    }
    
    @Test
    public void testGetRightAnimation() {
        Animation mockedAnimation = Mockito.mock(Animation.class);
        Mockito.when(mockedResources.getPlayerWalkRight()).thenReturn(mockedAnimation);
        player = new ConcretePlayer(mockedResources, 0, 0, true);
        
        Animation result = player.getAnimationRight();
        assertEquals(mockedAnimation, result);
    }

}
