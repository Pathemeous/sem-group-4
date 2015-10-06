package nl.tudelft.semgroup4.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CompareHighscoresTest {
    
    @Test
    public void testSort() {
        ArrayList<PlayerScorePair> pairs = new ArrayList<>();
        
        PlayerScorePair pair1 = new PlayerScorePair("Player1", 100);
        PlayerScorePair pair2 = new PlayerScorePair("Player2", 300);
        PlayerScorePair pair3 = new PlayerScorePair("Player3", 200);
        PlayerScorePair pair4 = new PlayerScorePair("Player4", 300);
        
        pairs.add(pair1);
        pairs.add(pair2);
        pairs.add(pair3);
        pairs.add(pair4);
        
        pairs.sort(new CompareHighscores());
        
        assertEquals(pair2, pairs.get(0));
        assertEquals(pair4, pairs.get(1));
        assertEquals(pair3, pairs.get(2));
        assertEquals(pair1, pairs.get(3));
    }
}
