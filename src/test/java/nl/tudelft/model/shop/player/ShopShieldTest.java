package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.player.Player;

import org.junit.Test;



public class ShopShieldTest {

    @Test
    public void testConstructor() {
        Game mockedGame = mock(Game.class);
        ShopShield shopShield = new ShopShield(10, mockedGame);
        assertEquals(shopShield.getPrice(), 10);
    }

    @Test
    public void testApplyTo() {
        Player mockedPlayer = mock(Player.class);
        Level mockedLevel = mock(Level.class);
        Game mockedGame = mock(Game.class);
        when(mockedGame.getCurLevel()).thenReturn(mockedLevel);
        ShopShield shopShield = new ShopShield(10, mockedGame);
        shopShield.applyTo(mockedPlayer);
        verify(mockedLevel, times(1)).toAdd(any());
    }


}
