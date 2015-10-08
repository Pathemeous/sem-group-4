package nl.tudelft.semgroup4.eventhandlers;

import java.util.Observable;
import java.util.Observer;

import nl.tudelft.model.Player;

/**
 * Represents an object to handle input events for a {@link Player}.
 * 
 * <p>
 * This object is {@link Observable} able and can be used to receive notifications of the Input Events of a
 * {@link Player}.
 * </p>
 * 
 * @author Pathemeous
 *
 */
public class PlayerEventHandler extends Observable implements Observer {

    /**
     * Represents the actions that a {@link Player} can perform.
     * 
     * @author Pathemeous
     *
     */
    public enum PlayerEvent {
        LEFT, RIGHT, SHOOT
    }

    private InputKey leftInput;
    private InputKey rightInput;
    private InputKey shootInput;

    /**
     * Creates a new {@link PlayerEventHandler} with the according {@link InputKey}s.
     * 
     * @param leftKey
     *            {@link InputKey} - The key to use for moving left.
     * @param rightKey
     *            {@link InputKey} - The key to use for moving right.
     * @param shootKey
     *            {@link InputKey} - The key to use for shooting.
     */
    public PlayerEventHandler(InputKey leftKey, InputKey rightKey, InputKey shootKey) {
        this.leftInput = leftKey;
        this.rightInput = rightKey;
        this.shootInput = shootKey;
        this.leftInput.addObserver(this);
        this.rightInput.addObserver(this);
        this.shootInput.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object argument) {
        if (observable.equals(leftInput)) {
            notifyObservers(PlayerEvent.LEFT);
        }

        if (observable.equals(rightInput)) {
            notifyObservers(PlayerEvent.RIGHT);
        }

        if (observable.equals(shootInput)) {
            notifyObservers(PlayerEvent.SHOOT);
        }
    }
}
