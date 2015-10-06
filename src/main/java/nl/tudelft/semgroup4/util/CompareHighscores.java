package nl.tudelft.semgroup4.util;

import java.util.Comparator;

public class CompareHighscores implements Comparator<HighscoreEntry> {

    @Override
    public int compare(HighscoreEntry pair1, HighscoreEntry pair2) {
        if (pair1.getScore() > pair2.getScore()) {
            return -1;
        } else if (pair1.getScore() < pair2.getScore()) {
            return 1;
        }
        return 0;
    }
}
