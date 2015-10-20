package nl.tudelft.settings;

/**
 * A listener interface that describes the actions that a Player can perform based on user input.
 */
public interface PlayerInputListener {

    /**
     * This method is called whenever the PlayerInput notifies about a user input of going left.
     */
    public void moveLeft();

    /**
     * This method is called whenever the PlayerInput notifies about a user input of going right.
     */
    public void moveRight();

    /**
     * This method is called whenever the PlayerInput notifies about a user input of stopping.
     */
    public void stopMoving();

    /**
     * This method is called whenever the PlayerInput notifies about a user input of shooting.
     */
    public void shoot();

}
