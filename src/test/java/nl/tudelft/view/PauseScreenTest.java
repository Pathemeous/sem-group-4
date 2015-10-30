package nl.tudelft.view;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractGame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

public class PauseScreenTest {

    private PauseScreen pausescreen;
    private ResourcesWrapper mockedWrapper;
    private MouseOverArea mockedMouseOver;
    private Graphics mockedGraphics;
    private GameContainer mockedContainer;
    private Input mockedInput;
    private AbstractGame mockedGame;
    private GameState mockedGameState;
    private Image mockedImage;
    private StateBasedGame mockedStateBasedGame;

    /**
     * Setup before every test, initializes the pause screen and its required
     * mocks.
     */
    @Before
    public void setup() {
        mockedWrapper = Mockito.mock(ResourcesWrapper.class);
        mockedMouseOver = Mockito.mock(MouseOverArea.class);
        mockedGraphics = Mockito.mock(Graphics.class);
        mockedContainer = Mockito.mock(GameContainer.class);
        mockedInput = Mockito.mock(Input.class);
        mockedStateBasedGame = Mockito.mock(StateBasedGame.class);
        mockedGame = Mockito.mock(AbstractGame.class);
        mockedGameState = Mockito.mock(GameState.class);
        mockedImage = Mockito.mock(Image.class);

        when(mockedWrapper.getPauseText()).thenReturn(mockedImage);
        when(mockedWrapper.getQuitText()).thenReturn(mockedImage);
        when(mockedGameState.getGame()).thenReturn(mockedGame);
        pausescreen = new PauseScreen(mockedWrapper, mockedMouseOver);
    }

    @Test
    public void testShow() {
        pausescreen.show(mockedGraphics, mockedContainer, mockedInput,
                mockedStateBasedGame, mockedGameState);

        verify(mockedGraphics, times(1)).setColor(any());
        verify(mockedGraphics, times(1)).setAntiAlias(anyBoolean());
        verify(mockedGraphics, times(1)).fillRect(anyInt(), anyInt(), anyInt(),
                anyInt());
        verify(mockedGraphics, times(2)).drawImage(any(), anyInt(), anyInt());
    }

    @Test
    public void testShowUpdate() {
        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(
                true);
        when(mockedMouseOver.isMouseOver()).thenReturn(true);
        pausescreen.show(mockedGraphics, mockedContainer, mockedInput,
                mockedStateBasedGame, mockedGameState);

        verify(mockedInput, times(1)).isMousePressed(Input.MOUSE_LEFT_BUTTON);
        verify(mockedGameState, times(1)).getGame();
        verify(mockedStateBasedGame, times(1)).enterState(anyInt());
    }
}
