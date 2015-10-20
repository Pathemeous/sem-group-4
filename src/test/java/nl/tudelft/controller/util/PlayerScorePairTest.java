package nl.tudelft.controller.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerScorePairTest {
    
    @Test
    public void testConstructor() {
        HighscoreEntry pair = new HighscoreEntry("player", 100);
        assertEquals("player", pair.getName());
        assertEquals(100, pair.getScore());
    }
}
