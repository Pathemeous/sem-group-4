package nl.tudelft.model.shop.player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import nl.tudelft.model.player.Player;
import nl.tudelft.model.player.ShopWeaponDecorator;

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
        Player result = shopWeaponItem.applyTo(mockedPlayer);
        assertEquals(ShopWeaponDecorator.class, result.getClass());
    }


}
