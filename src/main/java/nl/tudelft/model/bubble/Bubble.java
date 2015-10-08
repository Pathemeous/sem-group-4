package nl.tudelft.model.bubble;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nl.tudelft.model.AbstractEnvironmentObject;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Helpers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 * Bubble is an abstract subclass of AbstractEnvironmentObject and represents a single bubble in
 * the game.
 */

public abstract class Bubble extends AbstractEnvironmentObject {

    private static final float GRAVITY = 0.1f;
    private float verticalSpeed;
    private float horizontalSpeed;
    private boolean isHit = false;
    private float maxVerticalSpeed;
    private boolean slow = false;
    private boolean frozen = false;
    private int tickCount = 0;
    private final ResourcesWrapper resources;
    protected BubbleFactory bubbleFactory = null;
    private final BubbleFactoryFactory bubbleFactoryFactory;
    private final List<Bubble> next;
    private final boolean goesRightInitially;

    /**
     * The complete constructor for Bubble.
     * <p>
     * A bubble can be initialised moving either right or left.
     * </p>
     * <p>
     * The bubble has a size, which will determine its image and its maximum vertical speed (and
     * thus height)
     * </p>
     * 
     * @param bubbleImg
     *            Image - image for this bubble.
     * @param locX
     *            float - The x-coordinate where the bubble should spawn.
     * @param locY
     *            float - The y-coordinate where the bubble should spawn.
     * @param goRight
     *            boolean - true if the bubble should initialise moving to the right.
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this class may use.
     * @param bubbleFactoryFactory
     *            {@link BubbleFactoryFactory} - The BubbleFactoryFactory that this class may use.
     */
    public Bubble(Image bubbleImg, float locX, float locY, boolean goRight,
            ResourcesWrapper resources, BubbleFactoryFactory bubbleFactoryFactory) {
        super(bubbleImg, locX, locY);

        this.resources = resources;
        this.bubbleFactoryFactory = bubbleFactoryFactory;
        verticalSpeed = 0.0f;
        horizontalSpeed = 2.0f;

        goesRightInitially = goRight;
        if (!goRight) {
            horizontalSpeed = -horizontalSpeed;
        }

        next = new ArrayList<>();
    }

    protected final BubbleFactory getBubbleFactory() {
        return this.bubbleFactory;
    }

    protected final BubbleFactoryFactory getBubbleFactoryFactory() {
        return this.bubbleFactoryFactory;
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
            split(container, Helpers.randInt(1, 10));
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
     * @param random
     *            An integer between 0 and 10 that is used to determine whether this split will
     *            drop a pickup.
     * @return LinkedList containing the bubbles generated by this method.
     */
    public <T extends Modifiable> LinkedList<Bubble> split(T container, int random) {
        LinkedList<Bubble> newBubbles = new LinkedList<>();
        resources.playBubbleSplit();
        container.toRemove(this);

        if (random > 7 && !next.isEmpty()) {
            Pickup pickup =
                    Pickup.generateRandomPickup(Helpers.randInt(1, 10), getLocX(), getLocY());
            container.toAdd(pickup);
        }

        for (Bubble bubble : next) {
            if (bubble.goesRight()) {
                bubble.setLocX(getLocX() + bubble.getBounds().getWidth() / 2);
            } else {
                bubble.setLocX(getLocX());
            }

            bubble.setLocY(getLocY());
            bubble.setVerticalSpeed(bubble.getMaxVerticalSpeed() / 1.5f);

            if (frozen) {
                bubble.setFrozen(true);
            }

            container.toAdd(bubble);
            newBubbles.add(bubble);
        }

        return newBubbles;
    }

    /**
     * Method which is called to make the ball move. It moves in a direction based on its
     * horizontalspeed and verticalspeed. The verticalspeed is afterwards decreased with the
     * gravity.
     */
    private void move() {
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

    public List<Bubble> getNext() {
        return next;
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
        if (horizontalSpeed > maxVerticalSpeed) {
            return horizontalSpeed;
        }
        return maxVerticalSpeed;
    }

    public boolean goesRight() {
        return goesRightInitially;
    }

    /**
     * Overrides the getbounds method of GameObject, Bubble's superclass, so the shape of the
     * bubble is an instance of Circle instead of Rectangle.
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

}
