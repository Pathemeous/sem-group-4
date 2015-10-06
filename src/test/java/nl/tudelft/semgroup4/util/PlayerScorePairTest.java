package nl.tudelft.semgroup4.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerScorePairTest {
    
    @Test
    public void testConstructor() {
        PlayerScorePair pair = new PlayerScorePair("player", 100);
        assertEquals("player", pair.getName());
        assertEquals(100, pair.getScore());
    }
}
