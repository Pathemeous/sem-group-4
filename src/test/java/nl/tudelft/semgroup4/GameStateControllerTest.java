package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Countdown;
import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateControllerTest {
    
    private GameStateController gsController;
    private Game mockedGame;
    
    @Before
    public void setUp() {
        mockedGame = Mockito.mock(Game.class);
        gsController = new GameStateController(mockedGame);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(mockedGame, gsController.getGame());
    }
    
    @Test
    public void testTogglePauseMenu1() {
        Input mockedInput = Mockito.mock(Input.class);
        
        boolean isPaused = true;
        when(mockedGame.isPaused()).thenReturn(isPaused);
        
        boolean pauseScreenOpen = gsController.togglePauseMenu(true, mockedInput);
        
        verify(mockedGame, times(1)).setPaused(!isPaused);
        verify(mockedInput, times(1)).disableKeyRepeat();
        assertEquals(false, pauseScreenOpen);
    }
    
    @Test
    public void testTogglePauseMenu2() {
        Input mockedInput = Mockito.mock(Input.class);
        
        boolean isPaused = false;
        when(mockedGame.isPaused()).thenReturn(isPaused);
        
        boolean pauseScreenOpen = gsController.togglePauseMenu(false, mockedInput);
        
        verify(mockedGame, times(1)).setPaused(!isPaused);
        verify(mockedInput, times(1)).disableKeyRepeat();
        assertEquals(true, pauseScreenOpen);
    }
    
    @Test
    public void testTogglePauseMenu3() {
        Input mockedInput = Mockito.mock(Input.class);
        
        boolean isPaused = true;
        when(mockedGame.isPaused()).thenReturn(isPaused);
        
        boolean pauseScreenOpen = gsController.togglePauseMenu(false, mockedInput);
        
        verify(mockedGame, times(0)).setPaused(!isPaused);
        verify(mockedInput, times(0)).disableKeyRepeat();
        assertEquals(false, pauseScreenOpen);
    }
    
    @Test
    public void testUpdateGame1() throws SlickException {
        int delta = 0;
        
        Countdown mockedCountdown = Mockito.mock(Countdown.class);
        when(mockedGame.getCountdown()).thenReturn(mockedCountdown);
        when(mockedGame.isPaused()).thenReturn(true);
        
        gsController.updateGame(delta);
        
        verify(mockedGame, times(0)).update(delta);
        verify(mockedCountdown, times(1)).update();
    }
    
    @Test
    public void testUpdateGame2() throws SlickException {
        int delta = 0;
        
        Countdown mockedCountdown = Mockito.mock(Countdown.class);
        when(mockedGame.getCountdown()).thenReturn(mockedCountdown);
        when(mockedGame.isPaused()).thenReturn(false);
        
        gsController.updateGame(delta);
        
        verify(mockedGame, times(1)).update(delta);
        verify(mockedCountdown, times(0)).update();
    }
    
    @Test
    public void testShowPauseScreen1() {
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        StateBasedGame mockedSlickGame = Mockito.mock(StateBasedGame.class);
        Graphics mockedGraphics = Mockito.mock(Graphics.class);
        PauseScreen mockedPausescreen = Mockito.mock(PauseScreen.class);
        GameState mockedGameState = Mockito.mock(GameState.class);
        Input mockedInput = Mockito.mock(Input.class);
        
        gsController.showPauseScreen(true, mockedPausescreen, mockedGraphics, mockedContainer, 
                mockedInput, mockedSlickGame, mockedGameState);
        
        verify(mockedPausescreen, times(1)).show(mockedGraphics, mockedContainer, mockedInput, 
                mockedSlickGame, mockedGameState);
    }
    
    @Test
    public void testShowPauseScreen2() {
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        StateBasedGame mockedSlickGame = Mockito.mock(StateBasedGame.class);
        Graphics mockedGraphics = Mockito.mock(Graphics.class);
        PauseScreen mockedPausescreen = Mockito.mock(PauseScreen.class);
        GameState mockedGameState = Mockito.mock(GameState.class);
        Input mockedInput = Mockito.mock(Input.class);
        
        gsController.showPauseScreen(false, mockedPausescreen, mockedGraphics, mockedContainer, 
                mockedInput, mockedSlickGame, mockedGameState);
        
        verify(mockedPausescreen, times(0)).show(mockedGraphics, mockedContainer, mockedInput, 
                mockedSlickGame, mockedGameState);
    }
}
