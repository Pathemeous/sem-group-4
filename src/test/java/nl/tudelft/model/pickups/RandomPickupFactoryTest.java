package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import nl.tudelft.model.pickups.powerup.AbstractPowerup;
import nl.tudelft.model.pickups.utility.AbstractUtility;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;

import org.junit.Test;

public class RandomPickupFactoryTest {
    
    @Test
    public void testConstructor() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        assertTrue(factory.getCreateNumber() >= 1);
        assertTrue(factory.getCreateNumber() <= 10);
        assertTrue(factory.getPickupTypeNumber() >= 1);
        assertTrue(factory.getPickupTypeNumber() <= 10);
    }
    
    @Test
    public void testCreatePickupThatReturnsNull() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(7);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertEquals(null, pickup);
    }
    
    @Test
    public void testCreatePickupThatReturnsWeapon() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(8);
        factory.setPickupTypeNumber(3);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof AbstractWeapon);
    }
    
    @Test
    public void testCreatePickupThatReturnsPowerup() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(8);
        factory.setPickupTypeNumber(4);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof AbstractPowerup);
    }
    
    @Test
    public void testCreatePickupThatReturnsUtility() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(8);
        factory.setPickupTypeNumber(7);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof AbstractUtility);
    }
}
