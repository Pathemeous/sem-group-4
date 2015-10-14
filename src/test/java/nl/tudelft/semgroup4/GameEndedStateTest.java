package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Player;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
    private MouseOverArea mockedMouseOverArea;

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
        mockedMouseOverArea = Mockito.mock(MouseOverArea.class);

        gameEndedState.setTypeFontPlayer(mockedTypeFont);
        gameEndedState.setTypeFontTitle(mockedTypeFont2);
        gameEndedState.setPlayers(mockedPlayers);
        gameEndedState.setTextFieldPlayer1(mockedTextField1);
        gameEndedState.setTextFieldPlayer2(mockedTextField1);
        gameEndedState.setMouseOverContinueButton(mockedMouseOverArea);
    }

    @Test
    public void testGetId() {
        assertEquals(5, gameEndedState.getID());
    }

    @Test
    public void testRender() throws SlickException {
        gameEndedState.render(mockedContainer, mockedGame, mockedGraphics);

        verify(mockedTypeFont2, times(1)).drawString(0, 0, null, Color.yellow);
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

}
