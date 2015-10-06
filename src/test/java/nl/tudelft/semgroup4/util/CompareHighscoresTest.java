package nl.tudelft.semgroup4.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CompareHighscoresTest {
    
    @Test
    public void testSort() {
        ArrayList<HighscoreEntry> pairs = new ArrayList<>();
        
        HighscoreEntry pair1 = new HighscoreEntry("Player1", 100);
        HighscoreEntry pair2 = new HighscoreEntry("Player2", 300);
        HighscoreEntry pair3 = new HighscoreEntry("Player3", 200);
        HighscoreEntry pair4 = new HighscoreEntry("Player4", 300);
        
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
