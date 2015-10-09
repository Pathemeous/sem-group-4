package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Player;

import org.junit.Test;



public class ImprovedSpeedTest {

    @Test
    public void testConstructor() {
        ImprovedSpeed improvedSpeed = new ImprovedSpeed(10);
        assertEquals(improvedSpeed.getPrice(), 10);
    }

    @Test
    public void testApplyTo() {
        Player mockedPlayer = mock(Player.class);
        ImprovedSpeed improvedSpeed = new ImprovedSpeed(10);
        improvedSpeed.applyTo(mockedPlayer);
        verify(mockedPlayer, times(1)).applySpeedup();
        verify(mockedPlayer, times(1)).setShopSpeed(true);
    }

}
