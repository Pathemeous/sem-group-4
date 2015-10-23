package nl.tudelft.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.settings.InputKey;
import nl.tudelft.settings.PlayerInput;
import nl.tudelft.settings.Settings;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;


public class KeyBindStateControllerTest {

    private KeyBindStateController controller;
    private ResourcesWrapper mockedWrapper;
    private Settings mockedSettings;
    private PlayerInput mockedInput;
    private InputKey mockedKey;

    /**
     * Create a controller before every test.
     */
    @Before
    public void setUp() {
        mockedWrapper = mock(ResourcesWrapper.class);
        mockedSettings = mock(Settings.class);

        controller = new KeyBindStateController();
        controller.setSettings(mockedSettings);

        mockedInput = mock(PlayerInput.class);
        mockedKey = mock(InputKey.class);
    }

    @Test
    public void testGetID() {
        assertEquals(controller.getID(), 6);
    }

    @Test
    public void testCreateMouseOverAreas1() throws SlickException {
        TrueTypeFont typeFont = mock(TrueTypeFont.class);
        String[] text = {};
        GameContainer mockedGameContainer = mock(GameContainer.class);
        MouseOverArea[] areas = controller.createMouseOverAreas(
                10, 10, mockedGameContainer, text, typeFont,
                mockedWrapper);
        assertEquals(areas.length, 0);
    }

    @Test
    public void testCreateMouseOverAreas2() throws SlickException {
        TrueTypeFont typeFont = mock(TrueTypeFont.class);
        String[] text = {"TEST"};
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Input mockedInput = mock(Input.class);
        when(mockedGameContainer.getInput()).thenReturn(mockedInput);
        Image mockedImage = mock(Image.class);
        when(mockedWrapper.createImage(anyInt(),anyInt())).thenReturn(mockedImage);
        MouseOverArea[] areas = controller.createMouseOverAreas(
                10, 10, mockedGameContainer, text, typeFont,
                mockedWrapper);
        assertEquals(areas.length, 1);
        assertEquals(areas[0].getX(), 10);
        assertEquals(areas[0].getY(), 10);
    }

    @Test
    public void testCreateButtonArea() throws SlickException {
        TrueTypeFont typeFont = mock(TrueTypeFont.class);
        String text = "text";
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Input mockedInput = mock(Input.class);
        when(mockedGameContainer.getInput()).thenReturn(mockedInput);
        Image mockedImage = mock(Image.class);
        when(mockedWrapper.createImage(anyInt(),anyInt())).thenReturn(mockedImage);
        MouseOverArea area = controller.createButtonArea(10, 10, mockedGameContainer,
                text, typeFont, mockedWrapper);
        assertEquals(area.getX(), 10);
        assertEquals(area.getY(), 10);
    }

    @Test
    public void testSetKey0() {
        controller.setKey(0, mockedKey);

        verify(mockedSettings, times(1)).setPlayer1LeftKey(mockedKey);
    }

    @Test
    public void testSetKey1() {
        controller.setKey(1, mockedKey);

        verify(mockedSettings, times(1)).setPlayer1RightKey(mockedKey);
    }

    @Test
    public void testSetKey2() {
        controller.setKey(2, mockedKey);

        verify(mockedSettings, times(1)).setPlayer1ShootKey(mockedKey);
    }

    @Test
    public void testSetKey3() {
        controller.setKey(3, mockedKey);

        verify(mockedSettings, times(1)).setPlayer2LeftKey(mockedKey);
    }

    @Test
    public void testSetKey4() {
        controller.setKey(4, mockedKey);

        verify(mockedSettings, times(1)).setPlayer2RightKey(mockedKey);
    }

    @Test
    public void testSetKey5() {
        controller.setKey(5, mockedKey);

        verify(mockedSettings, times(1)).setPlayer2ShootKey(mockedKey);
    }

    @Test
    public void testSetKeyDefault() {
        controller.setKey(10, mockedKey);

        verify(mockedSettings, never()).setPlayer1LeftKey(mockedKey);
        verify(mockedSettings, never()).setPlayer1RightKey(mockedKey);
        verify(mockedSettings, never()).setPlayer1ShootKey(mockedKey);
        verify(mockedSettings, never()).setPlayer2LeftKey(mockedKey);
        verify(mockedSettings, never()).setPlayer2RightKey(mockedKey);
        verify(mockedSettings, never()).setPlayer2ShootKey(mockedKey);
    }

    @Test
    public void testGetKey0() {
        when(mockedSettings.getPlayer1Input()).thenReturn(mockedInput);
        when(mockedInput.getLeftInput()).thenReturn(mockedKey);
        when(mockedKey.getKeyCode()).thenReturn(0);

        controller.getKey(0);

        verify(mockedSettings, times(1)).getPlayer1Input();
        verify(mockedInput, times(1)).getLeftInput();
        verify(mockedKey, times(1)).getKeyCode();
    }

    @Test
    public void testGetKey1() {
        when(mockedSettings.getPlayer1Input()).thenReturn(mockedInput);
        when(mockedInput.getRightInput()).thenReturn(mockedKey);
        when(mockedKey.getKeyCode()).thenReturn(0);

        controller.getKey(1);

        verify(mockedSettings, times(1)).getPlayer1Input();
        verify(mockedInput, times(1)).getRightInput();
        verify(mockedKey, times(1)).getKeyCode();
    }

    @Test
    public void testGetKey2() {
        when(mockedSettings.getPlayer1Input()).thenReturn(mockedInput);
        when(mockedInput.getShootInput()).thenReturn(mockedKey);
        when(mockedKey.getKeyCode()).thenReturn(0);

        controller.getKey(2);

        verify(mockedSettings, times(1)).getPlayer1Input();
        verify(mockedInput, times(1)).getShootInput();
        verify(mockedKey, times(1)).getKeyCode();
    }

    @Test
    public void testGetKey3() {
        when(mockedSettings.getPlayer2Input()).thenReturn(mockedInput);
        when(mockedInput.getLeftInput()).thenReturn(mockedKey);
        when(mockedKey.getKeyCode()).thenReturn(0);

        controller.getKey(3);

        verify(mockedSettings, times(1)).getPlayer2Input();
        verify(mockedInput, times(1)).getLeftInput();
        verify(mockedKey, times(1)).getKeyCode();
    }

    @Test
    public void testGetKey4() {
        when(mockedSettings.getPlayer2Input()).thenReturn(mockedInput);
        when(mockedInput.getRightInput()).thenReturn(mockedKey);
        when(mockedKey.getKeyCode()).thenReturn(0);

        controller.getKey(4);

        verify(mockedSettings, times(1)).getPlayer2Input();
        verify(mockedInput, times(1)).getRightInput();
        verify(mockedKey, times(1)).getKeyCode();
    }

    @Test
    public void testGetKey5() {
        when(mockedSettings.getPlayer2Input()).thenReturn(mockedInput);
        when(mockedInput.getShootInput()).thenReturn(mockedKey);
        when(mockedKey.getKeyCode()).thenReturn(0);

        controller.getKey(5);

        verify(mockedSettings, times(1)).getPlayer2Input();
        verify(mockedInput, times(1)).getShootInput();
        verify(mockedKey, times(1)).getKeyCode();
    }

    @Test
    public void testGetKeyDefault() {
        assertEquals(controller.getKey(10), "ERROR");
    }
}
