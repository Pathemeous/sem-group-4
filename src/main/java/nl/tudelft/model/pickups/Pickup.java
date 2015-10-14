package nl.tudelft.model.pickups;

import nl.tudelft.model.AbstractEnvironmentObject;
import nl.tudelft.model.pickups.powerup.RandomPowerupFactory;
import nl.tudelft.model.pickups.utility.RandomUtilityFactory;
import nl.tudelft.model.pickups.weapon.RandomWeaponFactory;
import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Pickup extends AbstractEnvironmentObject {

    private boolean onGround;
    private boolean isActive;
    private boolean toRemove;
    private int tickCount;
    
    /**
     * Creates a pickup with a random content.
     * @param image Image - the image of the object.
     * @param locX float - the x-coordinate.
     * @param locY float - the y-coordinate.
     */
    public Pickup(Image image, float locX, float locY) {
        super(image, locX, locY);
        onGround = false;  
        isActive = false;
        toRemove = false;
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        if (!isActive) {
            graphics.drawImage(getImage(), locX, locY);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        setLocY(getLocY() + 1);
        
        if (toRemove) {
            container.toRemove(this);
        } else if (onGround && !isActive) {
            tickCount++;

            if (tickCount == 180) {
                container.toRemove(this);
            }
        }
    }
    
    protected int getTickCount() {
        return tickCount;
    }
    
    protected void setTickCount(int tick) {
        tickCount = tick;
    }
    
    protected boolean isOnGround() {
        return onGround;
    }
    
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
    public boolean willBeRemoved() {
        return toRemove;
    }
    
    public void toRemove() {
        toRemove = true;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    /**
     * Method that generates and returns a random pickup, based on a random
     * number that is passed as a parameter. 
     * @param random : random int that determines the pickup that
     *      is generated.
     * @param locX : the x location of the pickup to be created.
     * @param locY : the y location of the pickup to be created.
     * @return : an object of type Pickup.
     */
    public static Pickup generateRandomPickup(int random, float locX, float locY) {
        if (random < 4) {
            return new RandomWeaponFactory().createPickup(locX, locY);
        } else if (random < 7) {
            return new RandomPowerupFactory().createPickup(locX, locY);
        } else {
            return new RandomUtilityFactory().createPickup(locX, locY);
        }
    }
}
