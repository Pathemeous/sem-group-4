package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import nl.tudelft.model.Level;

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
    }
}
