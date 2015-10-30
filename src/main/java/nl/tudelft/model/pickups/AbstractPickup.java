package nl.tudelft.model.pickups;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.model.AbstractEnvironmentObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class AbstractPickup extends AbstractEnvironmentObject {

    private boolean onGround;
    private boolean active;
    private boolean toRemove;
    private int tickCount;
    
    /**
     * Creates a pickup with a random content.
     * @param image Image - the image of the object.
     * @param locX float - the x-coordinate.
     * @param locY float - the y-coordinate.
     */
    public AbstractPickup(Image image, float locX, float locY) {
        super(image, locX, locY);
        onGround = false;  
        active = false;
        toRemove = false;
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        if (!active) {
            graphics.drawImage(getImage(), locX, locY);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        setLocY(getLocY() + 1);
        
        if (toRemove) {
            container.toRemove(this);
        } else if (onGround && !active) {
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
    
    public void isToRemove() {
        toRemove = true;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
