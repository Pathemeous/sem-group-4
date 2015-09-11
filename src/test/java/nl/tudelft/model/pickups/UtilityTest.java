package nl.tudelft.model.pickups;

import junit.framework.TestCase;
import nl.tudelft.model.pickups.Utility.UtilityType;

public class UtilityTest extends TestCase {
    
    /**
     * Tests the utility constructor.
     */
    public void testUtility() {
        for (int i = 1; i <= 20; i++) {
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
