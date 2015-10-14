package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.model.Game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateTest {
    
    private GameState gameState;
    private Game mockedGame;
    private GameStateController gamestateController;
    
    @Before
    public void setUp() {
        mockedGame = Mockito.mock(Game.class);
        gamestateController = Mockito.mock(GameStateController.class);
        gameState = new GameState(mockedGame);
        gameState.setGameStateController(gamestateController);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(mockedGame, gameState.getGame());
    }
    
    @Test
    public void testGetId() {
        assertEquals(States.GameState, gameState.getID());
    }
    
    @Test
    public void testRender() throws SlickException {
        Dashboard mockedDashboard = Mockito.mock(Dashboard.class);
        gameState.setDashboard(mockedDashboard);
        
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        StateBasedGame mockedSlickGame = Mockito.mock(StateBasedGame.class);
        Graphics mockedGraphics = Mockito.mock(Graphics.class);
        
        gameState.render(mockedContainer, mockedSlickGame, mockedGraphics);
        
        verify(mockedDashboard, times(1)).render(mockedContainer, mockedGraphics);
        verify(gameState.getGame(), times(1)).render(mockedContainer, mockedGraphics);
        verify(gamestateController, times(1)).showPauseScreen(gameState.getPauseScreenOpened(), 
                gameState.getPausescreen(), mockedGraphics, mockedContainer, gameState.getInput(), 
                mockedSlickGame, gameState);
    }
    
    @Test
    public void testUpdate1() throws SlickException {
        Dashboard mockedDashboard = Mockito.mock(Dashboard.class);
        gameState.setDashboard(mockedDashboard);
        
        Input mockedInput = Mockito.mock(Input.class);
        gameState.setInput(mockedInput);
        
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        StateBasedGame mockedSlickGame = Mockito.mock(StateBasedGame.class);
        int delta = 0;
        
        gameState.update(mockedContainer, mockedSlickGame, delta);
        
        verify(gamestateController, times(1)).updateGame(delta);
        verify(mockedDashboard, times(1)).update(delta);
    }
    
    @Test
    public void testUpdate2() throws SlickException {
        Dashboard mockedDashboard = Mockito.mock(Dashboard.class);
        gameState.setDashboard(mockedDashboard);
        
        Input mockedInput = Mockito.mock(Input.class);
        when(mockedInput.isKeyPressed(Input.KEY_ESCAPE)).thenReturn(true);
        gameState.setInput(mockedInput);
        
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        StateBasedGame mockedSlickGame = Mockito.mock(StateBasedGame.class);
        int delta = 0;
        
        gameState.update(mockedContainer, mockedSlickGame, delta);
        
        verify(gamestateController, times(1)).togglePauseMenu(gameState.getPauseScreenOpened(), 
                mockedInput);
        verify(gamestateController, times(1)).updateGame(delta);
        verify(mockedDashboard, times(1)).update(delta);
    }
}
