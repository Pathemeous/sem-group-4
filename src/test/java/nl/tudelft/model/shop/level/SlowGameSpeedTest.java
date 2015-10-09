package nl.tudelft.model.shop.level;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Level;
import nl.tudelft.model.Player;

import org.junit.Test;


public class SlowGameSpeedTest {

    @Test
    public void testConstructor() {
        Level mockedLevel = mock(Level.class);
        SlowGameSpeed slowGameSpeed = new SlowGameSpeed(10, mockedLevel);
        assertEquals(slowGameSpeed.getPrice(), 10);
        slowGameSpeed.setPrice(20);
        assertEquals(slowGameSpeed.getPrice(), 20);
    }

    @Test
    public void testApplyTo() {
        Level mockedLevel = mock(Level.class);
        SlowGameSpeed slowGameSpeed = new SlowGameSpeed(10, mockedLevel);
        Player mockedPlayer = mock(Player.class);
        slowGameSpeed.applyTo(mockedPlayer);
        verify(mockedLevel, times(1)).getBubbles();
    }


}
