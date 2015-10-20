package nl.tudelft.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.CollisionController;
import nl.tudelft.model.Countdown;
import nl.tudelft.model.Game;

import nl.tudelft.view.GameState;
import nl.tudelft.view.PauseScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateControllerTest {
    
    private GameStateController gsController;
    private Game mockedGame;
    
    /**
     * Creates a gamestatecontroller with a mocked game before every test.
     */
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
    public void testTogglePauseMenuWhenOpened() {
        Input mockedInput = Mockito.mock(Input.class);
        
        boolean isPaused = true;
        when(mockedGame.isPaused()).thenReturn(isPaused);
        
        boolean pauseScreenOpen = gsController.togglePauseMenu(true, mockedInput);
        
        verify(mockedGame, times(1)).setPaused(!isPaused);
        verify(mockedInput, times(1)).disableKeyRepeat();
        assertEquals(false, pauseScreenOpen);
    }
    
    @Test
    public void testTogglePauseMenuWhenClosed() {
        Input mockedInput = Mockito.mock(Input.class);
        
        boolean isPaused = false;
        when(mockedGame.isPaused()).thenReturn(isPaused);
        
        boolean pauseScreenOpen = gsController.togglePauseMenu(false, mockedInput);
        
        verify(mockedGame, times(1)).setPaused(!isPaused);
        verify(mockedInput, times(1)).disableKeyRepeat();
        assertEquals(true, pauseScreenOpen);
    }
    
    @Test
    public void testTogglePauseMenuWhenGameIsPausedButPauseMenuIsNotOpened() {
        Input mockedInput = Mockito.mock(Input.class);
        
        boolean isPaused = true;
        when(mockedGame.isPaused()).thenReturn(isPaused);
        
        boolean pauseScreenOpen = gsController.togglePauseMenu(false, mockedInput);
        
        verify(mockedGame, times(0)).setPaused(!isPaused);
        verify(mockedInput, times(0)).disableKeyRepeat();
        assertEquals(false, pauseScreenOpen);
    }
    
    @Test
    public void testUpdateGameWhenGameIsPaused() throws SlickException {
        int delta = 0;
        
        Countdown mockedCountdown = Mockito.mock(Countdown.class);
        when(mockedGame.getCountdown()).thenReturn(mockedCountdown);
        when(mockedGame.isPaused()).thenReturn(true);
        
        gsController.updateGame(delta);
        
        verify(mockedGame, times(0)).update(delta);
        verify(mockedCountdown, times(1)).update();
    }
    
    @Test
    public void testUpdateGameIsNotPaused() throws SlickException {
        Countdown mockedCountdown = Mockito.mock(Countdown.class);
        when(mockedGame.getCountdown()).thenReturn(mockedCountdown);
        when(mockedGame.isPaused()).thenReturn(false);
        
        CollisionController mockedCollisions = Mockito.mock(CollisionController.class);
        gsController.setCollisionController(mockedCollisions);
        
        int delta = 0;
        gsController.updateGame(delta);
        
        verify(mockedGame, times(1)).update(delta);
        verify(mockedCountdown, times(0)).update();
    }
    
    @Test
    public void testShowPauseScreenWhenPauseScreenShouldBeOpen() {
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
    public void testShowPauseScreenWhenPauseScreenShouldNotBeOpen() {
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
