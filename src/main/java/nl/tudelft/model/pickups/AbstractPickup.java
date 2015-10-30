package nl.tudelft.model.pickups;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.model.AbstractEnvironmentObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class represents a generic Pickup item in the game.
 */
public abstract class AbstractPickup extends AbstractEnvironmentObject {

    private boolean onGround;
    private boolean active;
    private boolean toRemove;
    private int tickCount;

    /**
     * Creates a pickup with a random content.
     * 
     * @param image
     *            {@link Image} - the image of the object.
     * @param locX
     *            float - the x-coordinate.
     * @param locY
     *            float - the y-coordinate.
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

    /**
     * Renders the pickup while it is falling and while on the ground.
     */
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

    /**
     * Sets the ground-timeout to the specified value.
     * 
     * @param tick
     *            int - the amount of clock cycles until the pickup disappears from the ground.
     */
    protected void setTickCount(int tick) {
        tickCount = tick;
    }

    /**
     * Returns the amount of clock cycles the pickup will remain on the ground until it disappears.
     * 
     * @return int - the amount of cycles.
     */
    protected int getTickCount() {
        return tickCount;
    }

    /**
     * Sets the boolean that represents the state of the Pickup being on the ground or not (still
     * falling).
     * 
     * @param onGround
     *            boolean - True iff the {@link AbstractPickup} is on the ground, false otherwise.
     */
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    /**
     * Returns the boolean that represents the state of the Pickup being on the ground or not
     * (still falling).
     * 
     * @return boolean - True iff the {@link AbstractPickup} is on the ground, false otherwise.
     */
    protected boolean isOnGround() {
        return onGround;
    }

    /**
     * Flags the {@link AbstractPickup} to be removed from the game field (because it has ran out).
     */
    public void setToRemove() {
        toRemove = true;
    }

    /**
     * @return boolean - <code>true</code> iff the object is flagged to be removed from the game
     *         field, <code>false</code> otherwise.
     */
    public boolean willBeRemoved() {
        return toRemove;
    }

    /**
     * Sets the activation state of the {@link AbstractPickup}.
     * 
     * @param active
     *            boolean - <code>true</code> iff the pickup was picked up, <code>false</code>
     *            otherwise.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the activation state of the {@link AbstractPickup}.
     * 
     * @return boolean - <code>true</code> iff the pickup was picked up, <code>false</code>
     *         otherwise.
     */
    public boolean isActive() {
        return active;
    }
}
