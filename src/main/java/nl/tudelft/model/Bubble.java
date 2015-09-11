package nl.tudelft.model;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.util.Helpers;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.LinkedList;

/**
 * Bubble is a subclass of GameObject and represents a single bubble in the game.
 * 
 * @author Casper
 *
 */
public class Bubble extends GameObject {

    private float gravity = 0.1f;
    private float verticalSpeed = 0.0f;
    private float horizontalSpeed = 2.0f;
    private boolean isHit = false;
    private float maxVerticalSpeed;
    private int size;
    private boolean slowBalls = false;
    private boolean freeze = false;
    private int tickCount = 0;

    /**
     * Constructor for Bubble. This is a shorthanded version initialising the bubble moving to the
     * right. See {@link #Bubble(float, float, int, boolean)} for the complete constructor.
     * 
     * @param locX
     *            float - The x-coordinate where the bubble should spawn.
     * @param locY
     *            float - The y-coordinate where the bubble should spawn.
     * @param size
     *            int - The size of the bubble, in the range [1, 6] (inclusive).
     * @throws IllegalArgumentException
     *             - if the <code>size</code> is out of the defined range.
     */
    public Bubble(float locX, float locY, int size) throws IllegalArgumentException {
        this(locX, locY, size, true);

    }

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
     * @param locX
     *            float - The x-coordinate where the bubble should spawn.
     * @param locY
     *            float - The y-coordinate where the bubble should spawn.
     * @param size
     *            int - The size of the bubble, in the range [1, 6] (inclusive).
     * @param goRight
     *            boolean - true if the bubble should initialise moving to the right.
     * @throws IllegalArgumentException
     *             - if the <code>size</code> is out of the defined range.
     */
    public Bubble(float locX, float locY, int size, boolean goRight) throws IllegalArgumentException {
        super(getBubbleImage(size), locX, locY);
        this.size = size;

        if (!goRight) {
            horizontalSpeed = -horizontalSpeed;
        }

        switch (size) {
            case 6:
                maxVerticalSpeed = 10.0f;
                break;
            case 5:
                maxVerticalSpeed = 9.0f;
                break;
            case 4:
                maxVerticalSpeed = 8.0f;
                break;
            case 3:
                maxVerticalSpeed = 7.0f;
                break;
            case 2:
                maxVerticalSpeed = 6.0f;
                break;
            case 1:
                maxVerticalSpeed = 5.0f;
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    private static Image getBubbleImage(int size) throws IllegalArgumentException {
        switch (size) {
            case 1:
                return Resources.bubbleImage1;
            case 2:
                return Resources.bubbleImage2;
            case 3:
                return Resources.bubbleImage3;
            case 4:
                return Resources.bubbleImage4;
            case 5:
                return Resources.bubbleImage5;
            case 6:
                return Resources.bubbleImage6;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * This method is called every tick, to update the ball.
     */
    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if ((!slowBalls || tickCount % 2 == 0) && !freeze) {
            move();
        }

        if (isHit) {
            split(container);
        }

        tickCount++;
    }

    public <T extends Modifiable> LinkedList<Bubble> split(T container) {
        LinkedList<Bubble> newBubbles = new LinkedList<>();
        Resources.bubblePop.play();
        container.toRemove(this);

        int random = Helpers.randInt(1, 10);
        if (random > 1 && size > 1) {
            Pickup pickup = new Pickup(null, getLocX(), getLocY());
            container.toAdd(pickup);
        }

        if (getSize() > 1) {
            Bubble bubbleLeft = new Bubble(getLocX(), getLocY(), getSize() - 1, false);
            Bubble bubbleRight = new Bubble(getLocX() + (bubbleLeft.getBounds().getWidth() / 2),
                    getLocY(), getSize() - 1, true);
            newBubbles.add(bubbleLeft);
            newBubbles.add(bubbleRight);

            bubbleLeft.setVerticalSpeed(bubbleLeft.getMaxVerticalSpeed() / 1.5f);
            bubbleRight.setVerticalSpeed(bubbleLeft.getMaxVerticalSpeed() / 1.5f);

            container.toAdd(bubbleLeft);
            container.toAdd(bubbleRight);
        }
        return newBubbles;
    }

    /**
     * Method which is called to make the ball move. It moves in a direction based on its
     * horizontalspeed and verticalspeed. The verticalspeed is afterwards decreased with the
     * gravity.
     */
    private void move() {
        float xLoc = getLocX();
        float yLoc = getLocY();

        float newX = 0;
        newX = xLoc + horizontalSpeed;

        float newY = 0;
        newY = yLoc - verticalSpeed;

        setLocX(newX);
        setLocY(newY);
        verticalSpeed -= gravity;
    }

    public void slowBubbleDown(boolean slowDown) {
        slowBalls = slowDown;
    }

    public void freeze(boolean freeze) {
        this.freeze = freeze;
    }

    public void setIsHit() {
        this.isHit = true;
    }

    /**
     * 
     * @return the vertical speed of the bubble
     */
    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * 
     * @return the horizontal speed of the bubble
     */
    public float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /**
     * 
     * @return the maximum vertical speed the bubble can reach
     */
    public float getMaxVerticalSpeed() {
        return maxVerticalSpeed;
    }

    /**
     * Sets the vertical speed of the bubble
     * 
     * @param newSpeed
     *            : the new speed
     */
    public void setVerticalSpeed(float newSpeed) {
        verticalSpeed = newSpeed;
    }

    /**
     * Sets the horizontal speed of the bubble
     * 
     * @param newSpeed
     *            : the new speed
     */
    public void setHorizontalSpeed(float newSpeed) {
        horizontalSpeed = newSpeed;
    }

    /**
     * @return the maximum speed the bubble can have at any time in any direction
     */
    public float getMaxSpeed() {
        if (horizontalSpeed > maxVerticalSpeed) {
            return horizontalSpeed;
        }
        return maxVerticalSpeed;
    }

    public int getSize() {
        return size;
    }

    /**
     * Overrides the getbounds method of GameObject, Bubble's superclass, so the shape of the bubble
     * is an instance of Circle instead of Rectangle
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
