package nl.tudelft.settings;

import java.util.ArrayList;
import java.util.List;

/**
 * An Observable implementation that can notify the Observer / Listener of specific Player
 * interactions.
 * 
 * <p>
 * The Observers are of type {@link PlayerInputListener}.
 * </p>
 */
public abstract class AbstractPlayerInput {

    private final List<PlayerInputListener> listeners = new ArrayList<PlayerInputListener>();

    /**
     * Polls this PlayerInput, checking for new events.
     */
    public abstract void poll();

    /**
     * Notifies all subscribed listeners of a leftInput by calling their
     * {@link PlayerInputListener#moveLeft()} method.
     */
    protected void notifyLeftInput() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            listeners.get(i).moveLeft();
        }
    }

    /**
     * Notifies all subscribed listeners of a leftInput by calling their
     * {@link PlayerInputListener#moveRight()} method.
     */
    protected void notifyRightInput() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            listeners.get(i).moveRight();
        }
    }

    /**
     * Notifies all subscribed listeners of a leftInput by calling their
     * {@link PlayerInputListener#stopMoving()} method.
     */
    protected void notifyStopInput() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            listeners.get(i).stopMoving();
        }
    }

    /**
     * Notifies all subscribed listeners of a leftInput by calling their
     * {@link PlayerInputListener#shoot()} method.
     */
    protected void notifyShootInput() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            listeners.get(i).shoot();
        }
    }

    /**
     * Adds the specified listener to this subject's subscribed listeners.
     * 
     * <p>
     * The subscribed listeners will be notified of input events.
     * </p>
     * 
     * @param listener
     *            {@link PlayerInputListener} - The listener interested in subscribing to incoming
     *            events.
     */
    public void addListener(PlayerInputListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes the specified listener from this subject's subscribed listeners.
     * 
     * <p>
     * The listener will no longer be notified of input events.
     * </p>
     * 
     * @param listener
     *            {@link PlayerInputListener} - The listener not interested in further events.
     */
    public void removeListener(PlayerInputListener listener) {
        this.listeners.remove(listener);
    }
}
