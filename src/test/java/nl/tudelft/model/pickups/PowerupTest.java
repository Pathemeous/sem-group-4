package nl.tudelft.model.pickups;

import junit.framework.TestCase;
import nl.tudelft.model.pickups.Powerup.PowerType;

public class PowerupTest extends TestCase {
    
    
    /**
     * Tests the powerup constructor.
     */
    public void testPowerup() {
        for (int random = 1; random <= 10; random++) {
            Powerup up = new Powerup(random);
            
            if (random == 10) {
                assertEquals(up.getPowerType(), PowerType.LIFE);
            } else if (random > 8) {
                assertEquals(up.getPowerType(), PowerType.INVINCIBLE);
            } else if (random > 6) {
                assertEquals(up.getPowerType(), PowerType.SHIELD);
            } else if (random > 4) {
                assertEquals(up.getPowerType(), PowerType.SPEEDUP);
            } else {
                assertEquals(up.getPowerType(), PowerType.POINTS);
            }
        }
    }
}
