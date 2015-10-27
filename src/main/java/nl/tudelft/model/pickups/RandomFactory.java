package nl.tudelft.model.pickups;

public interface RandomFactory {
    
    Pickup createPickup(float locX, float locY);
}
