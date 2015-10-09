package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Player;

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
        doubleWeapon.applyTo(mockedPlayer);
        verify(mockedPlayer, times(2)).setWeapon(any());
        verify(mockedPlayer, times(1)).setShopWeapon(true);
    }


}
