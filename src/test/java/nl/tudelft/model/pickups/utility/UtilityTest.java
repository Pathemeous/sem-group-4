package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import nl.tudelft.model.Level;
import nl.tudelft.model.bubble.Bubble;

import org.junit.Test;

public class UtilityTest {
    
    /**
     * Tests the utility constructor.
     */
    @Test
    public void testActivate() {
        Level mockedLevel = mock(Level.class);
        
        Utility util1 = new FreezeUtility(0, 0);
        assertFalse(util1.isActive());
        util1.activate(mockedLevel);
        assertTrue(util1.isActive());
        
        Utility util2 = new SlowUtility(0, 0);
        util2.activate(mockedLevel);
        assertTrue(util2.isActive());
        
        Utility util3 = new SplitUtility(0, 0);
        util3.activate(mockedLevel);
        assertTrue(util3.isActive());
        
        Utility util4 = new TimeUtility(0, 0);
        util4.activate(mockedLevel);
        assertTrue(util4.isActive());
        verify(mockedLevel, times(1)).getTime();
        
        Utility util5 = new LevelWonUtility(0, 0);
        util5.activate(mockedLevel);
        assertTrue(util5.isActive());
        verify(mockedLevel, times(1)).splitAllBubbles(new LinkedList<Bubble>(), true);
    }
}
