package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import nl.tudelft.model.AbstractOpenGLTestCase;
import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.powerup.LifePowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Resources;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class PickupTest extends AbstractOpenGLTestCase {

    /**
     * This method tests the constructor to see if the right pickup is generated based on the
     * number that is put in.
     */
    @Test
    public void testPickup1() {
        Pickup pickup = new LifePowerup(0, 0);
        assertFalse(pickup.isOnGround());
        assertFalse(pickup.isActive());
        assertFalse(pickup.willBeRemoved());
    }

    /**
     * Tests if the update method of Pickup adds one to the Y location.
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdateLocation() throws SlickException {
        Pickup pickup = new LifePowerup(0, 0);
        float locY = pickup.getLocY();
        pickup.update(null, 0);
        assertEquals(locY + 1, pickup.getLocY(), 0.0f);
    }

    /**
     * Test if the update method of Pickup works correctly
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdate1() throws SlickException {
        Pickup pickup = new LifePowerup(0, 0);
        pickup.setOnGround(true);
        assertEquals(pickup.getTickCount(), 0);
        pickup.update(null, 0);
        assertEquals(pickup.getTickCount(), 1);
    }

    /**
     * Test if the update method of Pickup works correctly
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdate2() throws SlickException {
        Pickup pickup = new LifePowerup(0, 0);
        pickup.setOnGround(true);
        pickup.toRemove();
        
        Game mockedContainer = mock(Game.class);
        pickup.update(mockedContainer, 0);
        verify(mockedContainer, times(1)).toRemove(any());
    }
    
    /**
     * Test if the update method of Pickup works correctly
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdate3() throws SlickException {
        Pickup pickup = new LifePowerup(0, 0);
        pickup.setOnGround(true);
        pickup.setTickCount(179);
        
        Game mockedContainer = mock(Game.class);
        pickup.update(mockedContainer, 0);
        verify(mockedContainer, times(1)).toRemove(any());
    }
    
    @Test
    public void testPickupGenerator() {
        Pickup pickup1 = Pickup.generateRandomPickup(0, 0, 0);
        assertTrue(pickup1 instanceof Weapon);
        
        Pickup pickup2 = Pickup.generateRandomPickup(5, 0, 0);
        assertTrue(pickup2 instanceof Powerup);
        
        Pickup pickup3 = Pickup.generateRandomPickup(8, 0, 0);
        assertTrue(pickup3 instanceof Utility);
    }
}
