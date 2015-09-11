package nl.tudelft.model.pickups;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nl.tudelft.model.pickups.Powerup.PowerType;

public class PowerupTest extends TestCase {
    
    public PowerupTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        return new TestSuite(PowerupTest.class);
    }
    
    /**
     * Tests the powerup constructor.
     */
    public void testPowerup() {
        for (int i = 1; i <= 5; i++) {
            Powerup up = new Powerup(i);
            
            switch (i) {
                case 1: 
                    assertEquals(up.getPowerType(), PowerType.SHIELD); break;
                case 2:
                    assertEquals(up.getPowerType(), PowerType.INVINCIBLE); break;
                case 3:
                    assertEquals(up.getPowerType(), PowerType.SPEEDUP); break;
                case 4:
                    assertEquals(up.getPowerType(), PowerType.POINTS); break;
                case 5:
                    assertEquals(up.getPowerType(), PowerType.LIFE); break;
                default:
                    break;
            }
        }
    }
}
