package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import nl.tudelft.model.player.ExtraLifeDecorator;
import nl.tudelft.model.player.Player;

import org.junit.Test;



public class ExtraLifeTest {

    @Test
    public void testConstructor() {
        ExtraLife extraLife = new ExtraLife(10);
        assertEquals(extraLife.getPrice(), 10);
    }

    @Test
    public void testApplyTo() {
        Player mockedPlayer = mock(Player.class);
        ExtraLife extraLife = new ExtraLife(10);
        Player decoratedresult = extraLife.applyTo(mockedPlayer);
        assertEquals(ExtraLifeDecorator.class, decoratedresult.getClass());
    }


}
