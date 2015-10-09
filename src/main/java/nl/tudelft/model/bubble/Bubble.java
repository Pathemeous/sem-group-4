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
    private BubbleFactory bubbleFactory = null;

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
     * @param bubbleFactory
     *            This is a bubblefactory for bubble that are created when 
     *            this bubble splits.           
     */
    public Bubble(Image bubbleImg, float locX, float locY, boolean goRight,
            ResourcesWrapper resources, BubbleFactory bubbleFactory) {
        super(bubbleImg, locX, locY);

        this.resources = resources;
        this.bubbleFactory = bubbleFactory;
        verticalSpeed = 0.0f;
        horizontalSpeed = 2.0f;

        if (!goRight) {
            horizontalSpeed = -horizontalSpeed;
        }
    }

    public BubbleFactory getBubbleFactory() {
        return this.bubbleFactory;
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

        final BubbleFactory bubbleFactory = getBubbleFactory();
        if (bubbleFactory != null) {
            // we're going to split!
            if (random > 7) {
                // feeling lucky?
                Pickup pickup =
                        Pickup.generateRandomPickup(Helpers.randInt(1, 10), getLocX(), getLocY());
                container.toAdd(pickup);
            }

            // create bubbles
            Bubble bubbleLeft  =  bubbleFactory.createBubble();
            Bubble bubbleRight =  bubbleFactory.createBubble();

            // left goes left, right goes right
            bubbleLeft.setHorizontalSpeed(-Math.abs(bubbleLeft.getHorizontalSpeed()));
            bubbleRight.setHorizontalSpeed(Math.abs(bubbleRight.getHorizontalSpeed()));

            // both bubbles get same y coord
            bubbleLeft.setLocY(getLocY());
            bubbleRight.setLocY(getLocY());

            // bubbles get different x coords
            bubbleLeft.setLocX(getLocX());
            bubbleRight.setLocX(getLocX() + bubbleRight.getBounds().getWidth() / 2);

            // also same vert speed
            bubbleLeft.setVerticalSpeed(bubbleLeft.getMaxVerticalSpeed() / 1.5f);
            bubbleRight.setVerticalSpeed(bubbleLeft.getMaxVerticalSpeed() / 1.5f);

            // propagate frozen state (why is frozen bool here, not in level / game?)
            bubbleLeft.setFrozen(frozen);
            bubbleRight.setFrozen(frozen);

            // actually add to game
            container.toAdd(bubbleLeft);
            container.toAdd(bubbleRight);

            // track created bubbles and add to result list
            newBubbles.add(bubbleLeft);
            newBubbles.add(bubbleRight);

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
