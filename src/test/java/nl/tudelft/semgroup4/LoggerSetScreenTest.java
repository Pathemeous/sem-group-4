package nl.tudelft.semgroup4;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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


public class LoggerSetScreenTest {

    private GameContainer mockedGameContainer;
    private ResourcesWrapper mockedWrapper;
    private Input mockedInput;
    private Graphics mockedGraphics;
    private OptionsState mockedState;
    private LoggerSetScreen screen;
    private TrueTypeFont mockedFont;

    /**
     * Create a screen which will be used for testing.
     * @throws SlickException
     *                      font can't be found
     */
    @Before
    public void setUp() throws SlickException {
        mockedGameContainer = mock(GameContainer.class);
        mockedInput = mock(Input.class);
        mockedWrapper = mock(ResourcesWrapper.class);
        mockedGraphics = mock(Graphics.class);
        mockedState = mock(OptionsState.class);
        Image mockedImage = mock(Image.class);
        mockedFont = mock(TrueTypeFont.class);
        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(mockedFont);
        when(mockedWrapper.createImage(anyInt(), anyInt())).thenReturn(mockedImage);
        when(mockedGameContainer.getInput()).thenReturn(mockedInput);
        screen = new LoggerSetScreen(mockedGameContainer, mockedWrapper);
    }

    @Test
    public void testConstructor() throws SlickException {
        verify(mockedWrapper, times(5)).createImage(anyInt(), anyInt());
        verify(mockedWrapper, times(1)).createFont(any(), anyBoolean());
    }

    @Test
    public void testShow() {
        screen.show(mockedGraphics, mockedGameContainer, mockedInput, mockedState);
        verify(mockedGraphics, times(1)).setAntiAlias(true);
        verify(mockedGraphics, times(1)).setColor(any());
        verify(mockedGraphics, times(1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testRenderText() {
        screen.show(mockedGraphics, mockedGameContainer, mockedInput, mockedState);
        verify(mockedFont, times(5)).drawString(anyInt(), anyInt(), anyString(), any());
    }

    @Test
    public void testInput() {
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);
        MouseOverArea mockedArea = mock(MouseOverArea.class);
        when(mockedArea.isMouseOver()).thenReturn(true);
        screen.setAreasForTesting(mockedArea);
        screen.show(mockedGraphics, mockedGameContainer, mockedInput, mockedState);
        verify(mockedInput, times(1)).isMousePressed(0);
        verify(mockedState, times(1)).toggleLoggerSet();
    }
}
