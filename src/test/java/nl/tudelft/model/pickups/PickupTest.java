package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.pickups.powerup.LifePowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class PickupTest {

    /**
     * This method tests the constructor to see if the right pickup is generated based on the
     * number that is put in.
     */
    @Test
    public void testPickup1() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Pickup pickup = new LifePowerup(mockedResources, 0, 0);
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
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Pickup pickup = new LifePowerup(mockedResources, 0, 0);
        
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
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Pickup pickup = new LifePowerup(mockedResources, 0, 0);
        
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
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Pickup pickup = new LifePowerup(mockedResources, 0, 0);
        
        pickup.toRemove();
        
        Modifiable mockedContainer = mock(Modifiable.class);
        pickup.update(mockedContainer, 0);
        verify(mockedContainer, times(1)).toRemove(any());
    }
    
    /**
     * Test if the update method of Pickup works correctly
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdate3() throws SlickException {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Pickup pickup = new LifePowerup(mockedResources, 0, 0);
        
        pickup.setOnGround(true);
        pickup.setTickCount(179);
        
        Modifiable mockedContainer = mock(Modifiable.class);
        pickup.update(mockedContainer, 0);
        verify(mockedContainer, times(1)).toRemove(any());
    }
    
    @Test
    public void testSetActive() {
        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);
        Pickup pickup = new LifePowerup(mockedResources, 0, 0);
        
        assertFalse(pickup.isActive());
        pickup.setActive(true);
        assertTrue(pickup.isActive());
    }
}
