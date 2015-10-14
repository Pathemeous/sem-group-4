package nl.tudelft.model.pickups.powerup;

import static org.junit.Assert.assertTrue;
import nl.tudelft.model.pickups.Pickup;

import org.junit.Test;

public class RandomPowerupFactoryTest {
    
    @Test
    public void testConstructor() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        assertTrue(factory.getRandomNumber() >= 1);
        assertTrue(factory.getRandomNumber() <= 12);
    }
    
    @Test
    public void testCreateLife() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        factory.setRandomNumber(12);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof LifePowerup);
    }
    
    @Test
    public void testCreateMoney() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        factory.setRandomNumber(11);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof MoneyPowerup);
    }
    
    @Test
    public void testCreateInvincible() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        factory.setRandomNumber(10);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof InvinciblePowerup);
    }
    
    @Test
    public void testCreateShield() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        factory.setRandomNumber(8);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof ShieldPowerup);
    }
    
    @Test
    public void testCreateSpeed() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        factory.setRandomNumber(6);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof SpeedPowerup);
    }
    
    @Test
    public void testCreatePoints() {
        RandomPowerupFactory factory = new RandomPowerupFactory();
        
        factory.setRandomNumber(4);
        
        Pickup pickup = factory.createPickup(0, 0);
        
        assertTrue(pickup instanceof PointsPowerup);
    }
}
