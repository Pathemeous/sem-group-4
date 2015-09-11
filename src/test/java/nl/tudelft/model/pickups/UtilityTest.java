package nl.tudelft.model.pickups;

import nl.tudelft.model.pickups.Powerup.PowerType;
import nl.tudelft.model.pickups.Utility.UtilityType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UtilityTest extends TestCase {
    
    public UtilityTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        return new TestSuite(UtilityTest.class);
    }
    
    /**
     * Tests the utility constructor.
     */
    public void testUtility() {
        for (int i = 1; i <= 5; i++) {
            Utility util = new Utility(i);
            
            switch (i) {
                case 1: 
                    assertEquals(util.getType(), UtilityType.SPLIT); break;
                case 2:
                    assertEquals(util.getType(), UtilityType.SLOW); break;
                case 3:
                    assertEquals(util.getType(), UtilityType.FREEZE); break;
                case 4:
                    assertEquals(util.getType(), UtilityType.LEVELWON); break;
                case 5:
                    assertEquals(util.getType(), UtilityType.TIME); break;
                default:
                    break;
            }
        }
    }
}
