package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;

public class CountdownTest {

    private Game game;
    private CountdownController countdown;

    @Before
    public void setUp() {
        game = mock(Game.class);
        countdown = new CountdownController(game, new ResourcesWrapper());
    }

    @Test
    public void testConstructor() {
        verify(game, times(1)).setPaused(true);
        assertEquals(180, countdown.getCounter());
    }

    @Test
    public void testReset() {
        countdown.setCounter(0);
        assertEquals(0, countdown.getCounter());

        countdown.reset();

        verify(game, atLeast(1)).setPaused(true);
        assertEquals(180, countdown.getCounter());
    }
    
    @Test
    public void testUpdate() {
        assertEquals(180, countdown.getCounter());
        countdown.update();
        assertEquals(179, countdown.getCounter());
        
        countdown.setCounter(0);
        countdown.update();
        assertEquals(-1, countdown.getCounter());
        verify(game, times(1)).setPaused(false);
        
        countdown.update();
        assertEquals(-1, countdown.getCounter());
    }
}
