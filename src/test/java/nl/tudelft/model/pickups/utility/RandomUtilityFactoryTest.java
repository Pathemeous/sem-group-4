package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertTrue;

import nl.tudelft.model.pickups.AbstractPickup;

import org.junit.Test;

public class RandomUtilityFactoryTest {
    
    @Test
    public void testConstructor() {
        RandomUtilityFactory factory = new RandomUtilityFactory();
        
        assertTrue(factory.getRandomNumber() >= 1);
        assertTrue(factory.getRandomNumber() <= 20);
    }
    
    @Test
    public void testCreateLevelwon() {
        RandomUtilityFactory factory = new RandomUtilityFactory();
        
        factory.setRandomNumber(20);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof LevelWonUtility);
    }
    
    @Test
    public void testCreateSplit() {
        RandomUtilityFactory factory = new RandomUtilityFactory();
        
        factory.setRandomNumber(17);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof SplitUtility);
    }
    
    @Test
    public void testCreateSlow() {
        RandomUtilityFactory factory = new RandomUtilityFactory();
        
        factory.setRandomNumber(16);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof SlowUtility);
    }
    
    @Test
    public void testCreateFreeze() {
        RandomUtilityFactory factory = new RandomUtilityFactory();
        
        factory.setRandomNumber(11);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof FreezeUtility);
    }
    
    @Test
    public void testCreateTime() {
        RandomUtilityFactory factory = new RandomUtilityFactory();
        
        factory.setRandomNumber(6);
        
        AbstractPickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof TimeUtility);
    }
}
