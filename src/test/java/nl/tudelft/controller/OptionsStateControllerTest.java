package nl.tudelft.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.view.LoggerSetScreen;
import nl.tudelft.view.OptionsState;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;


public class OptionsStateControllerTest {

    private OptionsStateController controller;
    private ResourcesWrapper mockedWrapper;
    private OptionsState mockedState;

    /**
     * Create a controller before every test.
     */
    @Before
    public void setUp() {
        mockedWrapper = mock(ResourcesWrapper.class);
        mockedState = mock(OptionsState.class);
        controller = new OptionsStateController(mockedState, mockedWrapper);
    }

    @Test
    public void testConstructor() {
        assertEquals(controller.getResources(), mockedWrapper);
    }

    @Test
    public void testIsMusicOn1() {
        when(mockedWrapper.isMusicOn()).thenReturn(true);
        controller.getMusicImage();
        verify(mockedWrapper, times(1)).getOn();
    }

    @Test
    public void testIsMusicOn2() {
        when(mockedWrapper.isMusicOn()).thenReturn(false);
        controller.getMusicImage();
        verify(mockedWrapper, times(1)).getOff();
    }

    @Test
    public void testShowLoggerScreen1() {
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedContainer = mock(GameContainer.class);
        Input mockedInput = mock(Input.class);
        LoggerSetScreen mockedScreen = mock(LoggerSetScreen.class);

        controller.showLoggerScreen(false, mockedGraphics,
                mockedContainer, mockedInput, mockedScreen);

        verify(mockedScreen, never()).show(mockedGraphics, mockedContainer,
                mockedInput, mockedState);
    }

    @Test
    public void testShowLoggerScreen2() {
        Graphics mockedGraphics = mock(Graphics.class);
        GameContainer mockedContainer = mock(GameContainer.class);
        Input mockedInput = mock(Input.class);
        LoggerSetScreen mockedScreen = mock(LoggerSetScreen.class);

        controller.showLoggerScreen(true, mockedGraphics,
                mockedContainer, mockedInput, mockedScreen);

        verify(mockedScreen, times(1)).show(mockedGraphics, mockedContainer,
                mockedInput, mockedState);
    }

    @Test
    public void testToggleLoggerScreen() {
        controller.toggleLoggerScreen();
        verify(mockedState, times(1)).setLoggerSetEnabled(anyBoolean());
    }

    @Test
    public void testGetID() {
        assertEquals(controller.getID(), 2);
    }

    @Test
    public void testToggleMusic() {
        controller.toggleMusic();
        verify(mockedWrapper, times(1)).setMusicOn(anyBoolean());
    }
}
