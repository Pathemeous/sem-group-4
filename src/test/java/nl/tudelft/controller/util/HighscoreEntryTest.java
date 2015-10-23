package nl.tudelft.controller.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by justin on 16/10/15.
 */
public class HighscoreEntryTest {

    @Test
    public void testGetScore1() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name", 1000L);
        assertEquals(1000L, entry.getScore());
    }

    @Test
    public void testGetScore2() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name", -1000L);
        assertEquals(-1000L, entry.getScore());
    }

    @Test
    public void testGetScore3() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name", 0L);
        assertEquals(0L, entry.getScore());
    }

    @Test
    public void testGetScore4() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name", 1L);
        assertEquals(1L, entry.getScore());
    }

    @Test
    public void testGetScore5() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name", -1L);
        assertEquals(-1L, entry.getScore());
    }

    @Test
    public void testGetName1() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("", -1L);
        assertEquals("", entry.getName());
    }

    @Test
    public void testGetName2() throws Exception {
        HighscoreEntry entry = new HighscoreEntry(null, -1L);
        assertEquals(null, entry.getName());
    }

    @Test
    public void testGetName3() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("1", -1L);
        assertEquals("1", entry.getName());
    }

    @Test
    public void testGetName4() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name", -1L);
        assertEquals("cool name", entry.getName());
    }

    @Test
    public void testGetName5() throws Exception {
        HighscoreEntry entry = new HighscoreEntry("cool name, that is also pretty long", -1L);
        assertEquals("cool name, that is also pretty long", entry.getName());
    }

}