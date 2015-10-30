package nl.tudelft.model.pickups;

public interface RandomFactory {
    
    AbstractPickup createPickup(float locX, float locY);
}
