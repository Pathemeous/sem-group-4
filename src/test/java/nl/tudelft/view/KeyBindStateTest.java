package nl.tudelft.view;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.KeyBindStateController;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.settings.Settings;
import nl.tudelft.view.KeyBindState;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

public class KeyBindStateTest {

    private GameContainer mockedContainer;
    private StateBasedGame mockedGame;
    private KeyBindStateController mockedController;
    private Input mockedInput;
    private KeyBindState state;
    private ResourcesWrapper mockedWrapper;
    private Image mockedImage;
    private TrueTypeFont mockedFont;
    private Graphics mockedGraphics;
    private Settings mockedSettings;

    /**
     * Create an OptionsState and all needed mocks.
     * @throws SlickException
     *                      this will never happen
     */
    @Before
    public void setUp() throws SlickException {
        mockedContainer = mock(GameContainer.class);
        mockedGame = mock(StateBasedGame.class);
        mockedInput = mock(Input.class);
        mockedController = mock(KeyBindStateController.class);
        mockedWrapper = mock(ResourcesWrapper.class);
        mockedImage = mock(Image.class);
        mockedFont = mock(TrueTypeFont.class);
        mockedGraphics = mock(Graphics.class);
        mockedSettings = mock(Settings.class);

        when(mockedContainer.getInput()).thenReturn(mockedInput);

        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(mockedFont);
        when(mockedWrapper.createImage(anyInt(), anyInt())).thenReturn(mockedImage);

        when(mockedContainer.getHeight()).thenReturn(100);
        when(mockedContainer.getWidth()).thenReturn(100);

        when(mockedImage.getHeight()).thenReturn(100);
        when(mockedImage.getWidth()).thenReturn(100);

        state = new KeyBindState();
        state.setController(mockedController);
        state.setWrapper(mockedWrapper);
        state.setInputForTesting(mockedInput);
        state.setSettings(mockedSettings);
    }

    @Test
    public void testInit() throws SlickException {
        state.init(mockedContainer, mockedGame);

        verify(mockedContainer, times(3)).getWidth();
        verify(mockedWrapper, times(2)).createFont(any(), anyBoolean());
        verify(mockedController, times(3)).createButtonArea(anyFloat(), anyFloat(), any(),
                anyString(), any(), any());
        verify(mockedController, times(1)).createMouseOverAreas(anyFloat(), anyFloat(), any(),
                any(String[].class), any(), any());
    }

    @Test
    public void testRender() throws SlickException {
        state.render(mockedContainer, mockedGame, mockedGraphics);

        verify(mockedInput, times(1)).disableKeyRepeat();
        verify(mockedController, times(6)).getKey(anyInt());
    }

    @Test
    public void testGetID() {
        state.getID();

        verify(mockedController, times(1)).getID();
    }

    @Test
    public void testUpdate1() throws SlickException {
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        MouseOverArea mockedArea2 = mock(MouseOverArea.class);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(false);
        when(mockedArea.isMouseOver()).thenReturn(false);
        when(mockedArea2.isMouseOver()).thenReturn(false);

        state.setInputForTesting(mockedInput);
        state.setAreasForTesting(mockedArea);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedGame, never()).enterState(anyInt());
        verify(mockedController, never()).setKey(anyInt(), any());
        verify(mockedSettings, never()).setDefaults();
        verify(mockedSettings, never()).save();
    }

    @Test
    public void testUpdate2() throws SlickException {
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        MouseOverArea mockedArea2 = mock(MouseOverArea.class);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);
        when(mockedArea.isMouseOver()).thenReturn(false);
        when(mockedArea2.isMouseOver()).thenReturn(false);

        state.setInputForTesting(mockedInput);
        state.setAreasForTesting(mockedArea);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedGame, never()).enterState(anyInt());
        verify(mockedController, never()).setKey(anyInt(), any());
        verify(mockedSettings, never()).setDefaults();
        verify(mockedSettings, never()).save();
    }

    @Test
    public void testUpdate3() throws SlickException {
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        MouseOverArea mockedArea2 = mock(MouseOverArea.class);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(false);
        when(mockedArea.isMouseOver()).thenReturn(true);
        when(mockedArea2.isMouseOver()).thenReturn(false);

        state.setInputForTesting(mockedInput);
        state.setAreasForTesting(mockedArea);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedGame, never()).enterState(anyInt());
        verify(mockedController, never()).setKey(anyInt(), any());
        verify(mockedSettings, never()).setDefaults();
        verify(mockedSettings, never()).save();
    }

    @Test
    public void testUpdate4() throws SlickException {
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        MouseOverArea mockedArea2 = mock(MouseOverArea.class);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);
        when(mockedArea.isMouseOver()).thenReturn(true);
        when(mockedArea2.isMouseOver()).thenReturn(false);

        state.setInputForTesting(mockedInput);
        state.setAreasForTesting(mockedArea);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedGame, times(1)).enterState(anyInt());
        verify(mockedController, times(6)).setKey(anyInt(), any());
        verify(mockedSettings, times(1)).setDefaults();
        verify(mockedSettings, times(1)).save();
    }
}
