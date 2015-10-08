package nl.tudelft.semgroup4.eventhandlers;

import java.util.Observable;
import java.util.Observer;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Updateable;

import org.newdawn.slick.SlickException;

/**
 * Represents an object to handle input events for a {@link Player}.
 * 
 * <p>
 * This object is {@link Observable} and can be used to receive notifications of the Input Events
 * of a {@link Player}.
 * </p>
 * 
 * <p>
 * The input must be polled with the game update cycle. The object storing this object should
 * therefore take responsibility in calling the {@link PlayerInput#update(Modifiable, int)} on this
 * object.
 * </p>
 * 
 * @author Pathemeous
 *
 */
public class PlayerInput extends Observable implements Observer, Updateable {

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
     * Creates a new {@link PlayerInput} with the according {@link InputKey}s.
     * 
     * @param leftKey
     *            {@link InputKey} - The key to use for moving left.
     * @param rightKey
     *            {@link InputKey} - The key to use for moving right.
     * @param shootKey
     *            {@link InputKey} - The key to use for shooting.
     */
    public PlayerInput(InputKey leftKey, InputKey rightKey, InputKey shootKey) {
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
            setChanged();
            notifyObservers(PlayerEvent.LEFT);
        }

        if (observable.equals(rightInput)) {
            setChanged();
            notifyObservers(PlayerEvent.RIGHT);
        }

        if (observable.equals(shootInput)) {
            setChanged();
            notifyObservers(PlayerEvent.SHOOT);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        leftInput.update(container, delta);
        rightInput.update(container, delta);
        shootInput.update(container, delta);
    }

    /**
     * Sets a new key as the input for moving left.
     * 
     * @param leftInput
     *            {@link InputKey} - The leftInput to set.
     */
    public final void setLeftInput(InputKey leftInput) {
        this.leftInput = leftInput;
    }

    /**
     * Sets a new key as the input for moving right.
     * 
     * @param rightInput
     *            {@link InputKey} - The rightInput to set.
     */
    public final void setRightInput(InputKey rightInput) {
        this.rightInput = rightInput;
    }

    /**
     * Sets a new key as the input for shooting.
     * 
     * @param shootInput
     *            {@link InputKey} - The shootInput to set.
     */
    public final void setShootInput(InputKey shootInput) {
        this.shootInput = shootInput;
    }

}
