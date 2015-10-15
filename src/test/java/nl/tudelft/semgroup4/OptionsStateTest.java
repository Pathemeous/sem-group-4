package nl.tudelft.semgroup4;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
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

    /**
     * Create an OptionsState and all needed mocks.
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

        when(mockedWrapper.createImage(anyInt(), anyInt())).thenReturn(mockedImage);
        when(mockedContainer.getInput()).thenReturn(mockedInput);
        when(mockedWrapper.getOn()).thenReturn(mockedImage);
        when(mockedWrapper.getBackText()).thenReturn(mockedImage);
        when(mockedWrapper.getLoggerText()).thenReturn(mockedImage);
        when(mockedWrapper.getKeyText()).thenReturn(mockedImage);
        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(mockedFont);
        when(mockedWrapper.createImage(anyInt(), anyInt())).thenReturn(mockedImage);

        when(mockedContainer.getHeight()).thenReturn(100);
        when(mockedContainer.getWidth()).thenReturn(100);

        state = new OptionsState();
        state.setController(mockedController);
        state.setInput(mockedInput);
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
}
