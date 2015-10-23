package nl.tudelft.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.util.CompareHighscores;
import nl.tudelft.controller.util.HighscoreEntry;
import nl.tudelft.controller.util.HighscoresHelper;
import nl.tudelft.view.States;


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
    public List<HighscoreEntry> getScores() {
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
    public String getPlayerName(int counter, List<HighscoreEntry> scores) {
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
    public String getPlayerScore(int counter, List<HighscoreEntry> scores) {
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
    public int getID() {
        return States.HighscoresState;
    }
}
