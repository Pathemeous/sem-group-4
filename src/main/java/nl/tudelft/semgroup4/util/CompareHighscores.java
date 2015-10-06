package nl.tudelft.semgroup4.util;

import java.util.Comparator;

public class CompareHighscores implements Comparator<PlayerScorePair> {

    @Override
    public int compare(PlayerScorePair pair1, PlayerScorePair pair2) {
        if (pair1.getScore() > pair2.getScore()) {
            return -1;
        } else if (pair1.getScore() < pair2.getScore()) {
            return 1;
        }
        return 0;
    }
}
