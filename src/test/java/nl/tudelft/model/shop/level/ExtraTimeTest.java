package nl.tudelft.model.shop.level;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Level;
import nl.tudelft.model.player.Player;

import org.junit.Test;


public class ExtraTimeTest {

    @Test
    public void testConstructor() {
        Level mockedLevel = mock(Level.class);
        ExtraTime extraTime = new ExtraTime(10, mockedLevel);
        assertEquals(extraTime.getPrice(), 10);
        extraTime.setPrice(20);
        assertEquals(extraTime.getPrice(), 20);
    }

    @Test
    public void testApplyTo() {
        Level mockedLevel = mock(Level.class);
        ExtraTime extraTime = new ExtraTime(10, mockedLevel);
        Player mockedPlayer = mock(Player.class);
        extraTime.applyTo(mockedPlayer);
        verify(mockedLevel, times(1)).setMaxTime(anyInt());
        verify(mockedLevel, times(1)).setTime(anyInt());
    }


}
