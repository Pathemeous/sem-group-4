package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
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


public class OptionsStateTest {

    private GameContainer mockedContainer;
    private StateBasedGame mockedGame;
    private OptionsStateController mockedController;
    private Input mockedInput;
    private OptionsState state;
    private ResourcesWrapper mockedWrapper;
    private Image mockedImage;
    private TrueTypeFont mockedFont;
    private Graphics mockedGraphics;

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
        mockedController = mock(OptionsStateController.class);
        mockedWrapper = mock(ResourcesWrapper.class);
        mockedImage = mock(Image.class);
        mockedFont = mock(TrueTypeFont.class);
        mockedGraphics = mock(Graphics.class);

        when(mockedContainer.getInput()).thenReturn(mockedInput);

        when(mockedWrapper.getOn()).thenReturn(mockedImage);
        when(mockedWrapper.getBackText()).thenReturn(mockedImage);
        when(mockedWrapper.getLoggerText()).thenReturn(mockedImage);
        when(mockedWrapper.getKeyText()).thenReturn(mockedImage);
        when(mockedWrapper.getOptionsText()).thenReturn(mockedImage);

        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(mockedFont);
        when(mockedWrapper.createImage(anyInt(), anyInt())).thenReturn(mockedImage);

        when(mockedContainer.getHeight()).thenReturn(100);
        when(mockedContainer.getWidth()).thenReturn(100);

        when(mockedImage.getHeight()).thenReturn(100);
        when(mockedImage.getWidth()).thenReturn(100);

        state = new OptionsState();
        state.setController(mockedController);
        state.setWrapper(mockedWrapper);
    }

    @Test
    public void testInit() throws SlickException {
        state.init(mockedContainer, mockedGame);

        verify(mockedContainer, times(1)).getWidth();
        verify(mockedWrapper, times(1)).getOn();
        verify(mockedWrapper, times(1)).getBackText();
        verify(mockedWrapper, times(1)).getLoggerText();
        verify(mockedWrapper, times(1)).getKeyText();
    }

    @Test
    public void testRender1() {
        state.render(mockedContainer, mockedGame, mockedGraphics);

        verify(mockedGraphics, times(6)).drawImage(any(), anyFloat(), anyFloat());
        verify(mockedController, times(1)).showLoggerScreen(false, mockedGraphics,
                mockedContainer, mockedInput, state.getLoggerSetScreen());
    }

    @Test
    public void testRender2() {
        state.setLoggerSetEnabled(true);
        state.render(mockedContainer, mockedGame, mockedGraphics);

        verify(mockedGraphics, times(6)).drawImage(any(), anyFloat(), anyFloat());
        verify(mockedController, times(1)).showLoggerScreen(true, mockedGraphics,
                mockedContainer, mockedInput, state.getLoggerSetScreen());
    }

    @Test
    public void testUpdate1() {
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(false);
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        when(mockedArea.isMouseOver()).thenReturn(true);

        state.setAreas(mockedArea);
        state.setInputForTesting(mockedInput);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedController, times(0)).toggleMusic();
        verify(mockedController, times(0)).toggleLoggerScreen();
        verify(mockedGame, times(0)).enterState(anyInt());
    }

    @Test
    public void testUpdate2() {
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        when(mockedArea.isMouseOver()).thenReturn(false);

        state.setAreas(mockedArea);
        state.setInputForTesting(mockedInput);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedController, times(0)).toggleMusic();
        verify(mockedController, times(0)).toggleLoggerScreen();
        verify(mockedGame, times(0)).enterState(anyInt());
    }

    @Test
    public void testUpdate3() {
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        when(mockedArea.isMouseOver()).thenReturn(true);

        state.setAreas(mockedArea);
        state.setInputForTesting(mockedInput);
        state.update(mockedContainer, mockedGame, 1);

        verify(mockedController, times(1)).toggleMusic();
        verify(mockedController, times(1)).toggleLoggerScreen();
        verify(mockedGame, times(2)).enterState(anyInt());
    }

    @Test
    public void testGetID() {
        state.getID();
        verify(mockedController, times(1)).getID();
    }

    @Test
    public void testToggleLoggerSet() {
        state.toggleLoggerSet();
        verify(mockedController, times(1)).toggleLoggerScreen();
    }

    @Test
    public void testIsLoggerSetEnabled() {
        assertEquals(state.isLoggerSetEnabled(), false);
    }

    @Test
    public void testSetLoggerSetEnabled() {
        assertEquals(state.isLoggerSetEnabled(), false);
        state.setLoggerSetEnabled(true);
        assertEquals(state.isLoggerSetEnabled(), true);
    }

}
