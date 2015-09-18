package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;

import nl.tudelft.model.pickups.Powerup.PowerType;
import org.junit.Test;

public class PowerupTest {

    /**
     * Tests the powerup constructor.
     */
    @Test
    public void testPowerup() {
        for (int random = 1; random <= 10; random++) {
            Powerup powerup = new Powerup(random);
            
            if (random == 10) {
                assertEquals(powerup.getPowerType(), PowerType.LIFE);
            } else if (random > 8) {
                assertEquals(powerup.getPowerType(), PowerType.INVINCIBLE);
            } else if (random > 6) {
                assertEquals(powerup.getPowerType(), PowerType.SHIELD);
            } else if (random > 4) {
                assertEquals(powerup.getPowerType(), PowerType.SPEEDUP);
            } else {
                assertEquals(powerup.getPowerType(), PowerType.POINTS);
            }
        }
    }
}
