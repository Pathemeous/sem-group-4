package nl.tudelft.model.pickups.weapon;

import static org.junit.Assert.assertTrue;

import nl.tudelft.model.pickups.Pickup;

import org.junit.Test;

public class RandomWeaponFactoryTest {
    
    @Test
    public void testConstructor() {
        RandomWeaponFactory factory = new RandomWeaponFactory();
        
        assertTrue(factory.getRandomNumber() >= 1);
        assertTrue(factory.getRandomNumber() <= 4);
    }
    
    @Test
    public void testCreateRegular() {
        RandomWeaponFactory factory = new RandomWeaponFactory();
        
        factory.setRandomNumber(1);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof RegularWeapon);
    }
    
    @Test
    public void testCreateDouble() {
        RandomWeaponFactory factory = new RandomWeaponFactory();
        
        factory.setRandomNumber(2);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof DoubleWeapon);
    }
    
    @Test
    public void testCreateSticky() {
        RandomWeaponFactory factory = new RandomWeaponFactory();
        
        factory.setRandomNumber(3);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof StickyWeapon);
    }
    
    @Test
    public void testCreateFlower() {
        RandomWeaponFactory factory = new RandomWeaponFactory();
        
        factory.setRandomNumber(4);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof FlowerWeapon);
    }
    
    @Test
    public void testHighRandomNumber() {
        RandomWeaponFactory factory = new RandomWeaponFactory();
        
        factory.setRandomNumber(100);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof RegularWeapon);
    }
}
