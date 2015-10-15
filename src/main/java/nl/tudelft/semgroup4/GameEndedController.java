package nl.tudelft.semgroup4;

import java.io.IOException;
import java.util.List;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.util.HighscoreEntry;
import nl.tudelft.semgroup4.util.HighscoresHelper;

import org.newdawn.slick.gui.TextField;


public class GameEndedController {
    public List<HighscoreEntry> highscores;
    private HighscoresHelper highscoresHelper;

    public GameEndedController() {
        this.highscoresHelper = new HighscoresHelper();
    }

    /**
     * saves the score of the player(s) which have finished a game, the score is
     * saved with the corresponding filled out name.
     * 
     * @param textFieldPlayer1
     *            the textfield of player 1 from which the name is used
     * @param textFieldPlayer2
     *            the textfield of player 1 from which the name is used
     * @param players
     *            the players which have played the game and have entered their
     *            names
     */
    public void saveScore(TextField textFieldPlayer1,
            TextField textFieldPlayer2, Player[] players) {
        try {
            List<HighscoreEntry> highscores = highscoresHelper.load();

            highscores.add(new HighscoreEntry(textFieldPlayer1.getText(),
                    players[0].getScore()));

            if (players.length == 2) {
                highscores.add(new HighscoreEntry(textFieldPlayer2.getText(),
                        players[1].getScore()));
            }

            highscoresHelper.save(highscores);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the hghscore helper.
     * 
     * @param highscoresHelper
     *            the highscoresHelper to set
     */
    protected void setHighscoresHelper(HighscoresHelper highscoresHelper) {
        this.highscoresHelper = highscoresHelper;
    }

}
