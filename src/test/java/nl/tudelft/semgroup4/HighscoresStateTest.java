package nl.tudelft.semgroup4;

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


public class HighscoresStateTest {

    private HighscoresState state;
    private GameContainer mockedContainer;
    private StateBasedGame mockedGame;
    private HighscoresStateController mockedController;
    private Input mockedInput;
    private ResourcesWrapper mockedWrapper;
    private Image mockedImage;
    private TrueTypeFont mockedFont;
    private Graphics mockedGraphics;
    private MouseOverArea mockedArea;

    /**
     * Create a state and mock everything which is needed.
     * @throws SlickException
     *                          this will never happen
     */
    @Before
    public void setUp() throws SlickException {
        mockedContainer = mock(GameContainer.class);
        mockedGame = mock(StateBasedGame.class);
        mockedInput = mock(Input.class);
        mockedController = mock(HighscoresStateController.class);
        mockedWrapper = mock(ResourcesWrapper.class);
        mockedImage = mock(Image.class);
        mockedFont = mock(TrueTypeFont.class);
        mockedGraphics = mock(Graphics.class);
        mockedArea = mock(MouseOverArea.class);

        when(mockedContainer.getInput()).thenReturn(mockedInput);

        when(mockedWrapper.getBackText()).thenReturn(mockedImage);

        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(mockedFont);

        when(mockedContainer.getHeight()).thenReturn(100);
        when(mockedContainer.getWidth()).thenReturn(100);

        when(mockedImage.getHeight()).thenReturn(100);
        when(mockedImage.getWidth()).thenReturn(100);
        state = new HighscoresState();
        state.setController(mockedController);
        state.setWrapper(mockedWrapper);
        state.setInputForTesting(mockedInput);
    }

    @Test
    public void testInit() throws SlickException {
        state.init(mockedContainer, mockedGame);

        verify(mockedContainer, times(3)).getInput();
        verify(mockedImage, times(1)).getHeight();
        verify(mockedImage, times(1)).getWidth();
        verify(mockedContainer, times(1)).getWidth();
        verify(mockedContainer, times(1)).getHeight();
        verify(mockedWrapper, times(1)).createFont(any(), anyBoolean());
    }

    @Test
    public void testRender() throws SlickException {
        state.init(mockedContainer, mockedGame);
        state.render(mockedContainer, mockedGame, mockedGraphics);

        verify(mockedGraphics, times(1)).drawImage(any(), anyFloat(), anyFloat());
        verify(mockedFont, times(31)).drawString(anyFloat(), anyFloat(),
                anyString(), any());
        verify(mockedContainer, times(12)).getWidth();
    }

    @Test
    public void testUpdate1() throws SlickException {
        state.setBackButton(mockedArea);

        when(mockedArea.isMouseOver()).thenReturn(false);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                .thenReturn(true);
        state.update(mockedContainer, mockedGame, 1);
        verify(mockedGame, never()).enterState(anyInt());
    }

    @Test
    public void testUpdate2() throws SlickException {
        state.setBackButton(mockedArea);

        when(mockedArea.isMouseOver()).thenReturn(true);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                .thenReturn(false);
        state.update(mockedContainer, mockedGame, 1);
        verify(mockedGame, never()).enterState(anyInt());
    }

    @Test
    public void testUpdate3() throws SlickException {
        state.setBackButton(mockedArea);

        when(mockedArea.isMouseOver()).thenReturn(false);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                .thenReturn(false);
        state.update(mockedContainer, mockedGame, 1);
        verify(mockedGame, never()).enterState(anyInt());
    }

    @Test
    public void testUpdate4() throws SlickException {
        state.setBackButton(mockedArea);

        when(mockedArea.isMouseOver()).thenReturn(true);
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                .thenReturn(true);
        state.update(mockedContainer, mockedGame, 1);
        verify(mockedGame, times(1)).enterState(anyInt());
    }

    @Test
    public void testGetID() {
        state.getID();

        verify(mockedController, times(1)).getID();
    }

}
