package nl.tudelft.semgroup4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.semgroup4.util.CompareHighscores;
import nl.tudelft.semgroup4.util.HighscoreEntry;
import nl.tudelft.semgroup4.util.HighscoresHelper;


public class HighscoresStateController {
    
    private HighscoresHelper highscoresHelper;

    public HighscoresStateController() {
        this.highscoresHelper = new HighscoresHelper();
    }
    /**
     * Get all scores which are saved to file.
     * @return
     *              a list of all scores
     */
    protected List<HighscoreEntry> getScores() {
        List<HighscoreEntry> scores = new ArrayList<>();
        try {
            scores.addAll(highscoresHelper.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        scores.sort(new CompareHighscores());
        return scores;
    }

    /**
     * Get the name of the player belonging to a certain score.
     * @param counter
     *                  the counter at which the for loop currently is
     * @param scores
     *                  the list of all highscores
     * @return
     *                  the name of the player
     */
    protected String getPlayerName(int counter, List<HighscoreEntry> scores) {
        if (scores.size() > counter) {
            return scores.get(counter).getName();
        } else {
            return "-";
        }
    }

    /**
     * Get the score of the player belonging to a certain score.
     * @param counter
     *                  the counter at which the for loop currently is
     * @param scores
     *                  the list of all highscores
     * @return
     *                  the score of the player
     */
    protected String getPlayerScore(int counter, List<HighscoreEntry> scores) {
        if (scores.size() > counter) {
            return Long.toString(scores.get(counter).getScore());
        } else {
            return "-";
        }
    }

    /**
     * Get the ID for the state.
     * @return
     *              the ID for the state
     */
    protected int getID() {
        return States.HighscoresState;
    }
}
