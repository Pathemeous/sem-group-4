package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import nl.tudelft.model.player.DoubleWeaponDecorator;
import nl.tudelft.model.player.Player;

import org.junit.Test;

public class DoubleWeaponItemTest {

    @Test
    public void testConstructor() {
        DoubleWeaponItem doubleWeapon = new DoubleWeaponItem(10);
        assertEquals(doubleWeapon.getPrice(), 10);
    }

    @Test
    public void testApplyTo() {
        Player mockedPlayer = mock(Player.class);
        DoubleWeaponItem doubleWeapon = new DoubleWeaponItem(10);
        Player result = doubleWeapon.applyTo(mockedPlayer);
        assertEquals(DoubleWeaponDecorator.class, result.getClass());
    }

}
