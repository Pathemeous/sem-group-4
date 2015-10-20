package nl.tudelft.view;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.controller.ShopStateController;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.shop.Shop;
import nl.tudelft.model.shop.ShopItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

public class ShopStateTest {

    private ShopState shopState;
    private GameContainer mockedContainer;
    private StateBasedGame mockedStateBasedGame;
    private ResourcesWrapper mockedWrapper;
    private TrueTypeFont mockedTrueTypeFont;
    private Image mockedImage;
    private Graphics mockedGraphics;
    private Player mockedPlayer;
    private Shop mockedShop;
    private Game mockedGame;
    private Player[] mockedPlayers;
    private LinkedList<ShopItem> mockedInventory;
    private ShopItem mockedShopItem;
    private Input mockedInput;
    private MouseOverArea mockedMouseOverArea;
    private ShopStateController mockedController;
    
    /**
     * Initializes the ShopState and its requires mocks, before every test.
     */
    @Before
    public void setup() {
        mockedContainer = Mockito.mock(GameContainer.class);
        mockedStateBasedGame = Mockito.mock(StateBasedGame.class);
        mockedWrapper = Mockito.mock(ResourcesWrapper.class);
        mockedTrueTypeFont = Mockito.mock(TrueTypeFont.class);
        mockedImage = Mockito.mock(Image.class);
        mockedGraphics = Mockito.mock(Graphics.class);
        mockedPlayer = Mockito.mock(Player.class);
        mockedShop = Mockito.mock(Shop.class);
        mockedGame = Mockito.mock(Game.class);
        mockedShopItem = Mockito.mock(ShopItem.class);
        mockedInput = Mockito.mock(Input.class);
        mockedMouseOverArea = Mockito.mock(MouseOverArea.class);
        mockedController = Mockito.mock(ShopStateController.class);
        
        mockedInventory = new LinkedList<ShopItem>();
        for (int i = 0; i < 7; i++) {
            mockedInventory.add(mockedShopItem);
        }
        mockedPlayers = new Player[1];
        mockedPlayers[0] = mockedPlayer;
        
        when(mockedWrapper.getShopBackGround()).thenReturn(mockedImage);
        when(mockedWrapper.getContinueText()).thenReturn(mockedImage);
        when(mockedWrapper.getShopText()).thenReturn(mockedImage);
        when(mockedWrapper.getBuy()).thenReturn(mockedImage);

        when(mockedWrapper.getPickupUtilitySlow()).thenReturn(mockedImage);
        when(mockedWrapper.getPickupUtilityLife()).thenReturn(mockedImage);
        when(mockedWrapper.getPickupPowerSpeedup()).thenReturn(mockedImage);
        when(mockedWrapper.getPickupWeaponDouble()).thenReturn(mockedImage);
        when(mockedWrapper.getPickupPowerShield()).thenReturn(mockedImage);
        when(mockedWrapper.getPickupUtilityTime()).thenReturn(mockedImage);
        when(mockedWrapper.getSpecialWeapon()).thenReturn(mockedImage);

        when(mockedWrapper.getPlayer1On()).thenReturn(mockedImage);
        when(mockedWrapper.getPlayer1Off()).thenReturn(mockedImage);
        when(mockedWrapper.getPlayer2On()).thenReturn(mockedImage);
        when(mockedWrapper.getPlayer2Off()).thenReturn(mockedImage);

        when(mockedTrueTypeFont.getWidth(any())).thenReturn(0);
        when(mockedTrueTypeFont.getHeight(any())).thenReturn(0);
        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(
                mockedTrueTypeFont);
        when(mockedShop.getGame()).thenReturn(mockedGame);
        when(mockedGame.getPlayers()).thenReturn(mockedPlayers);
        when(mockedShop.getInventory()).thenReturn(mockedInventory);
        
        shopState = new ShopState(mockedWrapper);
        shopState.setSelectedPlayer(mockedPlayer);
        shopState.setShop(mockedShop);
        shopState.setInput(mockedInput);
        shopState.setMouseOvers(mockedMouseOverArea);
        shopState.setController(mockedController);
    }

    @Test
    public void testRender() throws SlickException {
        shopState.render(mockedContainer, mockedStateBasedGame, mockedGraphics);
        
        verify(mockedGraphics, times(12)).drawImage(any(), anyInt(), anyInt());
        verify(mockedTrueTypeFont, times(8)).drawString(anyInt(), anyInt(), any(), any());
    }
 
    @Test
    public void testUpdate() throws SlickException {
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);
        when(mockedMouseOverArea.isMouseOver()).thenReturn(true);
        shopState.update(mockedContainer, mockedStateBasedGame, 0);
        
        verify(mockedInput, times(1)).isMousePressed(Input.MOUSE_LEFT_BUTTON);
        verify(mockedMouseOverArea, times(11)).isMouseOver();
        verify(mockedController, times(1)).enterState(any());
        verify(mockedController, times(7)).selectItem(anyInt());
        verify(mockedController, times(2)).selectPlayer(anyInt());
    }
}
