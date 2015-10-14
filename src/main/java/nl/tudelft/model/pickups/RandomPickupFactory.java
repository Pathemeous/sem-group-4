package nl.tudelft.model.pickups;

public interface RandomPickupFactory {
    
    public Pickup createPickup(float locX, float locY);
}
