package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.controller.util.SemRectangle;
import nl.tudelft.model.player.ConcretePlayer;
import nl.tudelft.model.player.Player;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ShieldPowerupTest {

    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupPowerShield()).thenReturn(mockedImg);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);

        assertEquals(mockedResources.getPickupPowerShield(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }

    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);

        Player player = new ConcretePlayer(mockedResources, 0, 0, true);

        assertFalse(player.hasPowerup(AbstractPowerup.SHIELD));
        assertFalse(powerup.isActive());
        assertFalse(player.hasPowerup(AbstractPowerup.INVINCIBLE));

        powerup.activate(player);

        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHIELD));
        assertTrue(powerup.isActive());
    }

    @Test
    public void testActivate2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);

        Player player = new ConcretePlayer(mockedResources, 0, 0, true);
        InvinciblePowerup invincibleMocked = mock(InvinciblePowerup.class);

        assertFalse(player.hasPowerup(AbstractPowerup.SHIELD));
        assertFalse(powerup.isActive());

        player.setPowerup(AbstractPowerup.INVINCIBLE, invincibleMocked);
        assertTrue(player.hasPowerup(AbstractPowerup.INVINCIBLE));

        powerup.activate(player);

        assertFalse(player.hasShield());
        assertTrue(powerup.isActive());
        assertTrue(powerup.willBeRemoved());
    }

    @Test
    public void testActivate3() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);

        Player player = new ConcretePlayer(mockedResources, 0, 0, true);

        assertFalse(player.hasPowerup(AbstractPowerup.SHIELD));
        assertFalse(powerup.isActive());
        assertFalse(player.hasPowerup(AbstractPowerup.INVINCIBLE));

        powerup.activate(player);

        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHIELD));
        assertTrue(powerup.isActive());

        ShieldPowerup powerup2 = new ShieldPowerup(mockedResources, 0, 0);

        assertTrue(player.hasPowerup(AbstractPowerup.SHIELD));

        powerup2.activate(player);

        assertEquals(powerup2, player.getPowerup(AbstractPowerup.SHIELD));
        assertTrue(powerup2.isActive());
        assertTrue(powerup.willBeRemoved());
    }

    @Test
    public void testActivate4() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Hit3ShieldPowerup powerup = new Hit3ShieldPowerup(mockedResources, 0, 0);

        Player player = new ConcretePlayer(mockedResources, 0, 0, true);

        assertFalse(player.hasPowerup(AbstractPowerup.SHIELD));
        assertFalse(powerup.isActive());

        powerup.activate(player);

        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHOPSHIELD));
        assertTrue(powerup.isActive());

        ShieldPowerup powerup2 = new ShieldPowerup(mockedResources, 0, 0);

        assertTrue(player.hasPowerup(AbstractPowerup.SHOPSHIELD));

        powerup2.activate(player);

        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHOPSHIELD));
        assertTrue(powerup2.willBeRemoved());
    }

    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);

        assertFalse(powerup.isHit());
        assertEquals(0, powerup.getRemovalCounter());

        Modifiable mockedContainer = mock(Modifiable.class);

        powerup.update(mockedContainer, 0);

        assertEquals(0, powerup.getRemovalCounter());

        powerup.setHit(true);
        powerup.update(mockedContainer, 0);
        assertEquals(1, powerup.getRemovalCounter());
    }

    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        Player player = new ConcretePlayer(mockedResources, 0, 0, true);

        powerup.activate(player);

        assertFalse(powerup.isHit());
        assertEquals(0, powerup.getRemovalCounter());

        powerup.setRemovalCounter(120);
        Modifiable mockedContainer = mock(Modifiable.class);

        powerup.update(mockedContainer, 0);

        assertEquals(120, powerup.getRemovalCounter());

        assertTrue(powerup.willBeRemoved());
        assertFalse(player.hasShield());
        assertFalse(player.hasPowerup(AbstractPowerup.SHIELD));
    }

    @Test
    public void testRender1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, never()).setColor(any());
        verify(mockedGraphics, never()).draw(any());
    }

    @Test
    public void testRender2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        Player mockedPlayer = mock(Player.class);
        powerup.activate(mockedPlayer);
        SemRectangle mockedBounds = mock(SemRectangle.class);
        when(mockedPlayer.getBounds()).thenReturn(mockedBounds);
        powerup.setRemovalCounter(2);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, times(2)).setColor(any());
        verify(mockedGraphics, times(1)).draw(any());
    }

    @Test
    public void testRender3() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        ShieldPowerup powerup = new ShieldPowerup(mockedResources, 0, 0);
        Player mockedPlayer = mock(Player.class);
        powerup.activate(mockedPlayer);
        SemRectangle mockedBounds = mock(SemRectangle.class);
        when(mockedPlayer.getBounds()).thenReturn(mockedBounds);
        powerup.setRemovalCounter(3);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, never()).setColor(any());
        verify(mockedGraphics, never()).draw(any());
    }
}
