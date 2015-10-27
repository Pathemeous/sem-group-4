package nl.tudelft.settings;

/**
 * A listener interface that describes the actions that a Player can perform based on user input.
 */
public interface PlayerInputListener {

    /**
     * This method is called whenever the PlayerInput notifies about a user input of going left.
     */
    void moveLeft();

    /**
     * This method is called whenever the PlayerInput notifies about a user input of going right.
     */
    void moveRight();

    /**
     * This method is called whenever the PlayerInput notifies about a user input of stopping.
     */
    void stopMoving();

    /**
     * This method is called whenever the PlayerInput notifies about a user input of shooting.
     */
    void shoot();
}
