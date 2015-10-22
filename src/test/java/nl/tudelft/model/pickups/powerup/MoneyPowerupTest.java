package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.junit.Test;
import org.newdawn.slick.Image;

public class MoneyPowerupTest {

    @Test
    public void testConstructor() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupPowerMoney()).thenReturn(mockedImg);
        MoneyPowerup powerup = new MoneyPowerup(mockedResources, 0, 0);

        assertEquals(mockedResources.getPickupPowerMoney(), powerup.getImage());
        assertEquals(0.0f, powerup.getLocX(), 0.0f);
        assertEquals(0.0f, powerup.getLocY(), 0.0f);
    }

    @Test
    public void testActivate1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        MoneyPowerup powerup = new MoneyPowerup(mockedResources, 0, 0);

        Player player = new Player(mockedResources, 0, 0, true);

        assertEquals(0, player.getMoney());
        assertFalse(powerup.isActive());

        powerup.activate(player);

        assertEquals(50, player.getMoney());
        assertTrue(powerup.isActive());
        assertTrue(powerup.willBeRemoved());
    }

    @Test
    public void testActivate2() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        MoneyPowerup powerup = new MoneyPowerup(mockedResources, 0, 0);

        Player player = new Player(mockedResources, 0, 0, true);

        assertEquals(0, player.getMoney());
        assertFalse(powerup.isActive());

        powerup.activate(player);
        powerup.activate(player);

        assertEquals(50, player.getMoney());
        assertTrue(powerup.isActive());
        assertTrue(powerup.willBeRemoved());
    }
}
