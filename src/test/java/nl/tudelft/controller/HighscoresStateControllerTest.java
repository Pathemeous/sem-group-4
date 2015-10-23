package nl.tudelft.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import nl.tudelft.controller.util.HighscoreEntry;

import org.junit.Before;
import org.junit.Test;

public class HighscoresStateControllerTest {

    private HighscoresStateController controller;


    /**
     * Create a controller.
     */
    @Before
    public void setUp() {
        controller = new HighscoresStateController();
    }

    /**
     * The exception isn't tested as it should never get thrown.
     */
    @Test
    public void testGetScores1() {
        assertNotNull(controller.getScores());
    }

    @Test
    public void testGetPlayerName1() {
        List mockedList = mock(List.class);
        when(mockedList.size()).thenReturn(1);

        assertEquals(controller.getPlayerName(1, mockedList), "-");
        verify(mockedList, never()).get(anyInt());
    }

    @Test
    public void testGetPlayerName2() {
        List mockedList = mock(List.class);
        HighscoreEntry mockedEntry = mock(HighscoreEntry.class);
        when(mockedList.size()).thenReturn(1);
        when(mockedList.get(anyInt())).thenReturn(mockedEntry);
        when(mockedEntry.getName()).thenReturn("Bob");

        assertNotEquals(controller.getPlayerName(0, mockedList), "-");
        assertEquals(controller.getPlayerName(0, mockedList), "Bob");
        verify(mockedList, times(2)).get(anyInt());
    }

    @Test
    public void testGetPlayerScore1() {
        List mockedList = mock(List.class);
        when(mockedList.size()).thenReturn(1);

        assertEquals(controller.getPlayerScore(1, mockedList), "-");
        verify(mockedList, never()).get(anyInt());
    }

    @Test
    public void testGetPlayerScore2() {
        List mockedList = mock(List.class);
        HighscoreEntry mockedEntry = mock(HighscoreEntry.class);
        when(mockedList.size()).thenReturn(1);
        when(mockedList.get(anyInt())).thenReturn(mockedEntry);
        when(mockedEntry.getScore()).thenReturn(10L);

        assertNotEquals(controller.getPlayerScore(0, mockedList), "-");
        verify(mockedList, times(1)).get(anyInt());
    }

    @Test
    public void testGetID() {
        assertEquals(controller.getID(), 3);
    }
}
