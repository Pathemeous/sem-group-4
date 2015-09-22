package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;

import nl.tudelft.model.pickups.utility.Utility;

import org.junit.Test;

public class UtilityTest {
    
    /**
     * Tests the utility constructor.
     */
    @Test
    public void testUtility() {
        for (int i = 0; i <= 20; i++) {
            Utility util = new Utility(i);
            
            if (i == 20) {
                assertEquals(util.getType(), UtilityType.LEVELWON);
            } else if (i > 16) {
                assertEquals(util.getType(), UtilityType.SPLIT);
            } else if (i > 11) {
                assertEquals(util.getType(), UtilityType.SLOW);
            } else if (i > 6) {
                assertEquals(util.getType(), UtilityType.FREEZE);
            } else if (i > 0) {
                assertEquals(util.getType(), UtilityType.TIME);
            }
        }
    }
}
