package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.ConcretePlayer;
import nl.tudelft.model.player.Player;

import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class InvinciblePowerupTest {
    
    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupPowerInvincible()).thenReturn(mockedImg);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        
        assertEquals(mockedResources.getPickupPowerInvincible(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }
    
    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        
        Player player = new ConcretePlayer(mockedResources, 0, 0, true);
        
        assertFalse(player.hasPowerup(AbstractPowerup.INVINCIBLE));
        assertFalse(powerup.isActive());
        
        powerup.activate(player);
        
        assertEquals(powerup, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertTrue(powerup.isActive());
    }
    
    @Test
    public void testActivate2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        
        Player player = new ConcretePlayer(mockedResources, 0, 0, true);
        ShieldPowerup mockedShield = mock(ShieldPowerup.class);
        
        assertFalse(player.hasPowerup(AbstractPowerup.INVINCIBLE));
        assertFalse(powerup.isActive());
        
        player.setPowerup(AbstractPowerup.SHIELD, mockedShield);
        assertTrue(player.hasPowerup(AbstractPowerup.SHIELD));
        
        powerup.activate(player);
        
        assertFalse(player.hasPowerup(AbstractPowerup.SHIELD));
        assertTrue(powerup.isActive());
        assertEquals(powerup, player.getPowerup(AbstractPowerup.INVINCIBLE));
    }
    
    @Test
    public void testActivate3() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        
        Player player = new ConcretePlayer(mockedResources, 0, 0, true);
        
        assertFalse(powerup.isActive());
        assertFalse(player.hasPowerup(AbstractPowerup.INVINCIBLE));
        
        powerup.activate(player);
        
        assertEquals(powerup, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertTrue(powerup.isActive());
        
        InvinciblePowerup powerup2 = new InvinciblePowerup(mockedResources, 0, 0);
        
        assertTrue(player.hasPowerup(AbstractPowerup.INVINCIBLE));
        
        powerup2.activate(player);
        
        assertEquals(powerup2, player.getPowerup(AbstractPowerup.INVINCIBLE));
        assertTrue(powerup2.isActive());
        assertTrue(powerup.willBeRemoved());
    }

    @Test
    public void testActivate4() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Hit3ShieldPowerup powerup = new Hit3ShieldPowerup(mockedResources, 0, 0);

        Player player = new ConcretePlayer(mockedResources, 0, 0, true);

        assertFalse(powerup.isActive());
        assertFalse(player.hasPowerup(AbstractPowerup.SHOPSHIELD));

        powerup.activate(player);

        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHOPSHIELD));
        assertTrue(powerup.isActive());

        InvinciblePowerup powerup2 = new InvinciblePowerup(mockedResources, 0, 0);

        assertTrue(player.hasPowerup(AbstractPowerup.SHOPSHIELD));

        powerup2.activate(player);

        assertEquals(powerup, player.getPowerup(AbstractPowerup.SHOPSHIELD));
        assertTrue(powerup2.willBeRemoved());
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        
        assertEquals(0, powerup.getCounter());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        
        powerup.update(mockedContainer, 0);
        
        assertEquals(0, powerup.getCounter());
        
        powerup.setActive(true);
        powerup.update(mockedContainer, 0);
        assertEquals(1, powerup.getCounter());
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        
        Player player = new ConcretePlayer(mockedResources, 0, 0, true);
        
        powerup.activate(player);
        powerup.setCounter(599);
        
        assertTrue(player.isInvincible());
        
        Modifiable mockedContainer = mock(Modifiable.class);
        
        powerup.update(mockedContainer, 0);
        
        assertEquals(600, powerup.getCounter());
        assertTrue(powerup.willBeRemoved());
        assertFalse(player.isInvincible());
        assertFalse(player.hasPowerup(AbstractPowerup.INVINCIBLE));
    }

    @Test
    public void testRender1() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, times(1)).drawImage(any(), anyFloat(), anyFloat());
    }

    @Test
    public void testRender2() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Player mockedPlayer = mock(Player.class);
        powerup.activate(mockedPlayer);
        powerup.setCounter(542);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, times(1)).drawImage(any(), anyFloat(), anyFloat());
    }

    @Test
    public void testRender3() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Player mockedPlayer = mock(Player.class);
        powerup.activate(mockedPlayer);
        powerup.setCounter(539);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, times(1)).drawImage(any(), anyFloat(), anyFloat());
    }

    @Test
    public void testRender4() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        InvinciblePowerup powerup = new InvinciblePowerup(mockedResources, 0, 0);
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Player mockedPlayer = mock(Player.class);
        powerup.activate(mockedPlayer);
        powerup.setCounter(541);
        powerup.render(mockedGameContainer, mockedGraphics);
        verify(mockedGraphics, never()).drawImage(any(), anyFloat(), anyFloat());
    }


}
