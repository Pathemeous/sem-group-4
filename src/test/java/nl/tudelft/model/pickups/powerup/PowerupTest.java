package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Player;

import org.junit.Test;

public class PowerupTest {

    /**
     * Tests the powerup constructor.
     */
    @Test
    public void testActivate() {
        Player mockedPlayer = mock(Player.class);
        
        Powerup powerup1 = new PointsPowerup(0, 0);
        powerup1.activate(mockedPlayer);
        assertTrue(powerup1.isActive());
        verify(mockedPlayer, times(1)).getScore();
        
        Powerup powerup2 = new ShieldPowerup(0, 0);
        powerup2.activate(mockedPlayer);
        assertTrue(powerup2.isActive());
        
        Powerup powerup3 = new InvinciblePowerup(0, 0);
        powerup3.activate(mockedPlayer);
        assertTrue(powerup3.isActive());
        
        Powerup powerup4 = new SpeedPowerup(0, 0);
        powerup4.activate(mockedPlayer);
        assertTrue(powerup4.isActive());
        
        Powerup powerup5 = new LifePowerup(0, 0);
        powerup5.activate(mockedPlayer);
        assertTrue(powerup5.isActive());
        verify(mockedPlayer, times(1)).getLives();
    }
}
