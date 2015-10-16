package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.pickups.weapon.Weapon;

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
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertEquals(null, pickup);
    }
    
    @Test
    public void testCreatePickupThatReturnsWeapon() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(8);
        factory.setPickupTypeNumber(3);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof Weapon);
    }
    
    @Test
    public void testCreatePickupThatReturnsPowerup() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(8);
        factory.setPickupTypeNumber(4);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof Powerup);
    }
    
    @Test
    public void testCreatePickupThatReturnsUtility() {
        RandomPickupFactory factory = new RandomPickupFactory();
        
        factory.setCreateNumber(8);
        factory.setPickupTypeNumber(7);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof Utility);
    }
}
