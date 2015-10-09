package nl.tudelft.model.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;

import org.junit.Test;


public class ShopTest {

    @Test
    public void testConstructor() {
        Game mockedGame = mock(Game.class);
        Level mockedLevel = mock(Level.class);
        when(mockedGame.getCurLevel()).thenReturn(mockedLevel);
        Shop shop = new Shop(mockedGame);
        assertEquals(shop.getGame(), mockedGame);
        assertFalse(shop.getInventory() == null);
    }
}
