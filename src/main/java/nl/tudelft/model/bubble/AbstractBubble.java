package nl.tudelft.model.bubble;

import java.util.LinkedList;
import java.util.List;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.Resources;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractEnvironmentObject;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.RandomPickupFactory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Bubble is an abstract subclass of AbstractEnvironmentObject and represents a single bubble in
 * the game.
 */

public abstract class AbstractBubble extends AbstractEnvironmentObject {

    private static final float GRAVITY = 0.1f;
    private float verticalSpeed;
    private float horizontalSpeed;
    private boolean isHit = false;
    private float maxVerticalSpeed;
    private boolean slow = false;
    private boolean frozen = false;
    private int tickCount = 0;
    private final ResourcesWrapper resources;
    private List<AbstractBubble> nextBubbles;

    /**
     * Constructs a new instance of the {@link AbstractBubble} class.
     * <p>
     * A bubble will be initialized moving right by default. By calling goLeft right after
     * initialization, the bubble will start moving left.
     * </p>
     * 
     * @param bubbleImg
     *            {@link Image} - image for this bubble.
     * @param locX
     *            float - The x-coordinate where the bubble should spawn.
     * @param locY
     *            float - The y-coordinate where the bubble should spawn.
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this object may use.
     */
    public AbstractBubble(Image bubbleImg, float locX, float locY, ResourcesWrapper resources) {
        super(bubbleImg, locX, locY);

        nextBubbles = createNextBubbles();
        maxVerticalSpeed = initMaxVerticalSpeed();
        this.resources = resources;
        verticalSpeed = 0.0f;
        horizontalSpeed = 2.0f;
    }

    /**
     * Creates a list with bubbles that will appear when this bubble splits.
     * 
     * @return {@link List} of {@link AbstractBubble}s - A list containing the bubbles.
     */
    protected abstract List<AbstractBubble> createNextBubbles();

    /**
     * Used to initialize the maximum vertical speed of a bubble.
     * 
     * @return float - A positive float value that determines maximum speed the bubble can have.
     */
    protected abstract float initMaxVerticalSpeed();

    /**
     * Set list of bubbles that will appear when this bubble splits.
     * 
     * @param next
     *            : List with bubbles.
     */
    protected void setNext(List<AbstractBubble> next) {
        this.nextBubbles = next;
    }

    /**
     * Returns the list with bubbles that will appear when this bubble splits.
     * 
     * @return {@link List} of {@link AbstractBubble}s - A list containing the bubbles.
     */
    public List<AbstractBubble> getNext() {
        return nextBubbles;
    }

    /**
     * Makes the bubble go left.
     */
    protected void goLeft() {
        horizontalSpeed = -Math.abs(horizontalSpeed);
    }

    /**
     * This method is called every tick, to update the ball.
     */
    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if ((!slow || tickCount % 2 == 0) && !frozen) {
            move();
        }

        if (isHit) {
            split(container);
        }

        tickCount++;
    }

    /**
     * Splits the bubble into two bubbles of one size smaller. The bubbles spawn at the sides of
     * this bubble and will move away from each other.
     * 
     * @param <T>
     *            implements Modifiable
     * @param container
     *            T implements Modifiable - The container in which this bubble lives.
     * @return {@link LinkedList} of {@link AbstractBubble}s - A list containing the bubbles
     *         generated by this method.
     */
    public <T extends Modifiable> LinkedList<AbstractBubble> split(T container) {
        LinkedList<AbstractBubble> newBubbles = new LinkedList<>();
        resources.playBubbleSplit();
        container.toRemove(this);
        
        if (!nextBubbles.isEmpty()) {
            Pickup pickup = new RandomPickupFactory().createPickup(getLocX(), getLocY());
            if (pickup != null) {
                container.toAdd(pickup);
            }
        }

        // We're going to split
        for (int i = 0; i < nextBubbles.size(); i++) {
            AbstractBubble bubble = nextBubbles.get(i);

            if (i % 2 == 0) {
                // Bubble goes right
                bubble.setLocX(getLocX() + bubble.getBounds().getWidth() / 2);
            } else {
                // Bubble goes left
                bubble.setLocX(getLocX());
                bubble.goLeft();
            }

            bubble.setLocY(getLocY());
            bubble.setVerticalSpeed(bubble.getMaxVerticalSpeed() / 1.5f);
            bubble.setFrozen(frozen);

            container.toAdd(bubble);
            newBubbles.add(bubble);
        }

        return newBubbles;
    }

    /**
     * Method which is called to make the ball move.
     * 
     * <p>
     * It moves in a direction based on its horizontalspeed and verticalspeed. The verticalspeed is
     * afterwards decreased with the gravity.
     * </p>
     */
    protected void move() {
        float locX = getLocX();
        float locY = getLocY();

        float newX = 0;
        newX = locX + horizontalSpeed;

        float newY = 0;
        newY = locY - verticalSpeed;

        setLocX(newX);
        setLocY(newY);
        verticalSpeed -= GRAVITY;
    }

    public void setSlow(boolean slowDown) {
        slow = slowDown;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setFrozen(boolean freeze) {
        this.frozen = freeze;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setIsHit() {
        this.isHit = true;
    }

    /**
     * Accesses the verticalSpeed value.
     * 
     * @return the vertical speed of the bubble
     */
    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * Accesses the horizontalSpeed value.
     * 
     * @return the horizontal speed of the bubble
     */
    public float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /**
     * Accesses the maxVerticalSpeed value.
     * 
     * @return the maximum vertical speed the bubble can reach
     */
    public float getMaxVerticalSpeed() {
        return maxVerticalSpeed;
    }

    public void setMaxVerticalSpeed(float speed) {
        maxVerticalSpeed = speed;
    }

    /**
     * Sets the vertical speed of the bubble.
     * 
     * @param newSpeed
     *            : the new speed
     */
    public void setVerticalSpeed(float newSpeed) {
        verticalSpeed = newSpeed;
    }

    /**
     * Sets the horizontal speed of the bubble.
     * 
     * @param newSpeed
     *            : the new speed
     */
    public void setHorizontalSpeed(float newSpeed) {
        horizontalSpeed = newSpeed;
    }

    /**
     * Accesses the maxSpeed value.
     * 
     * @return the maximum speed the bubble can have at any time in any direction
     */
    public float getMaxSpeed() {

        if (Math.abs(horizontalSpeed) > Math.abs(maxVerticalSpeed)) {
            return Math.abs(horizontalSpeed);
        }
        return Math.abs(maxVerticalSpeed);
    }

    /**
     * Accesses the resourceWrapper.
     *
     * @return the resource wrapper that is used to init this class.
     */
    public ResourcesWrapper getResources() {
        return resources;
    }

    /**
     * Returns a {@link Circle} object instead of a {@link Rectangle} object. This allows for more
     * dynamic collisions, because bubbles have circular shapes.
     */
    @Override
    public Shape getBounds() {
        int width = getImage().getWidth();
        int height = getImage().getHeight();

        float centerPointX = getLocX() + 0.5f * width;
        float centerPointY = getLocY() + 0.5f * height;
        float radius = (float) 0.5 * width;

        return new Circle(centerPointX, centerPointY, radius);
    }

    /**
     * Returns if the bubble is hit.
     * @return the isHit
     */
    public boolean isHit() {
        return isHit;
    }

}
