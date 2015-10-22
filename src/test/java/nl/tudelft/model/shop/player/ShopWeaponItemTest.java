package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.player.Player;

import org.junit.Test;



public class ShopWeaponItemTest {

    @Test
    public void testConstructor() {
        ShopWeaponItem shopWeaponItem = new ShopWeaponItem(10);
        assertEquals(shopWeaponItem.getPrice(), 10);
    }

    @Test
    public void testApplyTo() {
        Player mockedPlayer = mock(Player.class);
        ShopWeaponItem shopWeaponItem = new ShopWeaponItem(10);
        shopWeaponItem.applyTo(mockedPlayer);
        verify(mockedPlayer, times(2)).setWeapon(any());
        verify(mockedPlayer, times(1)).setShopWeapon(true);
    }


}
