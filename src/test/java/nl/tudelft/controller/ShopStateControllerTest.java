package nl.tudelft.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.model.Game;
import nl.tudelft.model.player.Player;
import nl.tudelft.model.shop.Shop;
import nl.tudelft.model.shop.ShopItem;
import nl.tudelft.view.ShopState;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ShopStateControllerTest {

    private ShopState mockedShopState;
    private ShopStateController shopStateController;
    private ShopItem mockedShopItem;
    private Player mockedPlayer;
    private Shop mockedShop;
    private LinkedList<ShopItem> mockedInventory;
    private Game mockedGame;
    private Player[] mockedPlayers;

    /**
     * Setup before every test, initializes the shopStateController and its
     * required mocks.
     */
    @Before
    public void setup() {
        mockedShopState = Mockito.mock(ShopState.class);
        mockedShopItem = Mockito.mock(ShopItem.class);
        mockedPlayer = Mockito.mock(Player.class);
        mockedShop = Mockito.mock(Shop.class);
        mockedGame = Mockito.mock(Game.class);

        mockedPlayers = new Player[1];
        mockedPlayers[0] = mockedPlayer;
        mockedInventory = new LinkedList<ShopItem>();
        mockedInventory.add(mockedShopItem);

        when(mockedShopState.getSelectedItem()).thenReturn(mockedShopItem);
        when(mockedShopState.getSelectedPlayer()).thenReturn(mockedPlayer);
        when(mockedShop.getInventory()).thenReturn(mockedInventory);
        when(mockedShopState.getShop()).thenReturn(mockedShop);
        when(mockedShop.getGame()).thenReturn(mockedGame);
        when(mockedGame.getPlayers()).thenReturn(mockedPlayers);

        shopStateController = new ShopStateController(mockedShopState);
    }

    @Test
    public void testContructor() {
        assertEquals(mockedShopState, shopStateController.getShopState());
    }

    @Test
    public void testApplyUpgrade() {
        shopStateController.applyUpgrade();

        verify(mockedShopState, times(4)).getSelectedPlayer();
        verify(mockedShopItem, times(1)).applyTo(mockedPlayer);
        verify(mockedPlayer, times(2)).getMoney();
    }

    @Test
    public void testSelectPlayer() {
        shopStateController.selectPlayer(0);

        verify(mockedShopState, times(1)).setSelectedPlayer(any());
    }

    @Test
    public void testSelectItem() {
        shopStateController.selectItem(0);

        verify(mockedShopState, times(1)).setSelectedItem(any());
    }

}
