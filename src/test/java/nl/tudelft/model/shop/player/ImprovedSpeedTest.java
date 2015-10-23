package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import nl.tudelft.model.player.ImprovedSpeedDecorator;
import nl.tudelft.model.player.Player;

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
        Player result = improvedSpeed.applyTo(mockedPlayer);
        assertEquals(ImprovedSpeedDecorator.class, result.getClass());
    }

}
