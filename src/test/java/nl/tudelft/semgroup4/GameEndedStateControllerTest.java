package nl.tudelft.semgroup4;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.util.HighscoresHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.gui.TextField;

public class GameEndedStateControllerTest {

    private GameEndedController controller;
    private Player[] mockedPlayers;
    private Player mockedPlayer;
    private TextField mockedTextField;
    private HighscoresHelper mockedHighscoreHelper;
    
    /**
     * Setup run before every test, initializes mocks.
     */
    @Before
    public void setup() {
        controller = new GameEndedController();
        mockedPlayer = Mockito.mock(Player.class);
        mockedTextField = Mockito.mock(TextField.class);
        mockedHighscoreHelper = Mockito.mock(HighscoresHelper.class);

        mockedPlayers = new Player[2];
        mockedPlayers[0] = mockedPlayer;
        mockedPlayers[1] = mockedPlayer;

        controller.setHighscoresHelper(mockedHighscoreHelper);
    }

    @Test
    public void testSaveScores() throws IOException {
        controller.saveScore(mockedTextField, mockedTextField, mockedPlayers);

        verify(mockedHighscoreHelper, times(1)).load();
        verify(mockedHighscoreHelper, times(1)).save(
                mockedHighscoreHelper.load());
    }
}
