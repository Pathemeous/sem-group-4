package nl.tudelft.view;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.GameEndedController;
import nl.tudelft.model.Player;

import nl.tudelft.view.GameEndedState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

public class GameEndedStateTest {

    private GameEndedState gameEndedState;
    private Player[] mockedPlayers;

    private GameContainer mockedContainer;
    private Graphics mockedGraphics;
    private StateBasedGame mockedGame;
    private TrueTypeFont mockedTypeFont;
    private TrueTypeFont mockedTypeFont2;
    private Player mockedPlayer;
    private TextField mockedTextField1;
    private MouseOverArea mockedMouseOverArea1;
    private GameEndedController mockedController;
    private Input mockedInput;
    private MouseOverArea mockedMouseOverArea2;
    private MouseOverArea mockedMouseOverArea3;

    /**
     * Is calles before every test, initializes all need mocks and creates a new
     * GameEndedState.
     */
    @Before
    public void setup() {
        gameEndedState = new GameEndedState();
        mockedPlayers = new Player[2];

        mockedContainer = Mockito.mock(GameContainer.class);
        mockedGraphics = Mockito.mock(Graphics.class);
        mockedGame = Mockito.mock(StateBasedGame.class);
        mockedTypeFont = Mockito.mock(TrueTypeFont.class);
        mockedTypeFont2 = Mockito.mock(TrueTypeFont.class);
        mockedPlayer = Mockito.mock(Player.class);
        mockedPlayers[0] = mockedPlayer;
        mockedPlayers[1] = mockedPlayer;
        mockedTextField1 = Mockito.mock(TextField.class);
        mockedMouseOverArea1 = Mockito.mock(MouseOverArea.class);
        mockedMouseOverArea2 = Mockito.mock(MouseOverArea.class);
        mockedMouseOverArea3 = Mockito.mock(MouseOverArea.class);
        mockedController = Mockito.mock(GameEndedController.class);
        mockedInput = Mockito.mock(Input.class);

        gameEndedState.setTypeFontPlayer(mockedTypeFont);
        gameEndedState.setTypeFontTitle(mockedTypeFont2);
        gameEndedState.setPlayers(mockedPlayers);
        gameEndedState.setTextFieldPlayer1(mockedTextField1);
        gameEndedState.setTextFieldPlayer2(mockedTextField1);
        gameEndedState.setMouseOverContinueButton(mockedMouseOverArea1);
        gameEndedState.setMouseOverTextField1(mockedMouseOverArea2);
        gameEndedState.setMouseOverTextField2(mockedMouseOverArea3);
        gameEndedState.setController(mockedController);
        gameEndedState.setInput(mockedInput);

        when(mockedInput.isMousePressed(Input.MOUSE_LEFT_BUTTON)).thenReturn(
                true);
    }

    @Test
    public void testGetId() {
        assertEquals(5, gameEndedState.getID());
    }

    @Test
    public void testRender() throws SlickException {
        gameEndedState.render(mockedContainer, mockedGame, mockedGraphics);

        verify(mockedTypeFont2, times(1)).drawString(0, 0, "GAME LOST",
                Color.yellow);
        verify(mockedTypeFont, times(1))
                .drawString(0, 0, "SCORE", Color.yellow);
        verify(mockedTypeFont, times(1))
                .drawString(0, 0, "MONEY", Color.yellow);
        verify(mockedTypeFont, times(1)).drawString(0, 0, "NAME", Color.yellow);
        verify(mockedTypeFont, times(1)).drawString(0, 0, "PLAYER 1",
                Color.white);
        verify(mockedTypeFont, times(4)).drawString(0, 0,
                Integer.toString(gameEndedState.getPlayers()[0].getScore()),
                Color.white);
        verify(mockedTypeFont, times(1)).drawString(0, 0, "PLAYER 2",
                Color.white);
        verify(mockedTypeFont, times(1)).drawString(-20, -20, "CONTINUE",
                Color.yellow);
        verify(mockedGraphics, times(2)).drawRect(0, 0, -1, -1);
        verify(mockedTextField1, times(2)).render(mockedContainer,
                mockedGraphics);
    }

    @Test
    public void testUpdate1() throws SlickException {
        when(mockedMouseOverArea1.isMouseOver()).thenReturn(true);
        gameEndedState.update(mockedContainer, mockedGame, 0);

        verify(mockedInput, times(1)).isMousePressed(Input.MOUSE_LEFT_BUTTON);
        verify(mockedMouseOverArea1, times(1)).isMouseOver();
        verify(mockedController, times(1)).saveScore(mockedTextField1,
                mockedTextField1, mockedPlayers);
        verify(mockedGame, times(1)).enterState(0);
    }

    @Test
    public void testUpdate2() throws SlickException {
        when(mockedMouseOverArea2.isMouseOver()).thenReturn(true);
        gameEndedState.update(mockedContainer, mockedGame, 0);

        verify(mockedInput, times(1)).isMousePressed(Input.MOUSE_LEFT_BUTTON);
        verify(mockedMouseOverArea2, times(1)).isMouseOver();
    }

    @Test
    public void testUpdate3() throws SlickException {
        when(mockedMouseOverArea3.isMouseOver()).thenReturn(true);
        gameEndedState.update(mockedContainer, mockedGame, 0);

        verify(mockedInput, times(1)).isMousePressed(Input.MOUSE_LEFT_BUTTON);
        verify(mockedMouseOverArea3, times(1)).isMouseOver();
    }

}
