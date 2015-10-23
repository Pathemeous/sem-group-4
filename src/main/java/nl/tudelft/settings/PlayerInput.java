package nl.tudelft.settings;

import java.util.Observable;
import java.util.Observer;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.model.player.Player;

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
public class PlayerInput extends AbstractPlayerInput implements Observer {

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
        if (argument == null) {
            return;
        }

        if (observable.equals(leftInput) && (Boolean) argument) {
            notifyLeftInput();
        }

        if (observable.equals(rightInput) && (Boolean) argument) {
            notifyRightInput();
        }

        if ((observable.equals(leftInput) || observable.equals(rightInput))
                && !(Boolean) argument) {
            notifyStopInput();
        }

        if (observable.equals(shootInput) && (Boolean) argument) {
            notifyShootInput();
        }
    }

    /**
     * polls its {@link InputKey}s for input.
     */
    @Override
    public void poll() {
        leftInput.poll();
        rightInput.poll();
        shootInput.poll();
    }

    /**
     * Sets a new key as the input for moving left.
     * 
     * <p>
     * Assures that this object removes itself from the old input and subscribes itself to the new
     * input.
     * </p>
     * 
     * @param leftInput
     *            {@link InputKey} - The leftInput to set.
     */
    public void setLeftInput(InputKey leftInput) {
        this.leftInput.deleteObserver(this);
        this.leftInput = leftInput;
        this.leftInput.addObserver(this);
    }

    /**
     * Sets a new key as the input for moving right.
     * 
     * <p>
     * Assures that this object removes itself from the old input and subscribes itself to the new
     * input.
     * </p>
     * 
     * @param rightInput
     *            {@link InputKey} - The rightInput to set.
     */
    public void setRightInput(InputKey rightInput) {
        this.rightInput.deleteObserver(this);
        this.rightInput = rightInput;
        this.rightInput.addObserver(this);
    }

    /**
     * Sets a new key as the input for shooting.
     * 
     * <p>
     * Assures that this object removes itself from the old input and subscribes itself to the new
     * input.
     * </p>
     * 
     * @param shootInput
     *            {@link InputKey} - The shootInput to set.
     */
    public void setShootInput(InputKey shootInput) {
        this.shootInput.deleteObserver(this);
        this.shootInput = shootInput;
        this.shootInput.addObserver(this);
    }

    /**
     * Accesses the {@link InputKey} for the {@link PlayerEvent#LEFT} event.
     * 
     * @return {@link InputKey} - The leftInput of this instance.
     */
    public InputKey getLeftInput() {
        return leftInput;
    }

    /**
     * Accesses the {@link InputKey} for the {@link PlayerEvent#RIGHT} event.
     * 
     * @return {@link InputKey} - The rightInput of this instance.
     */
    public InputKey getRightInput() {
        return rightInput;
    }

    /**
     * Accesses the {@link InputKey} for the {@link PlayerEvent#SHOOT} event.
     * 
     * @return {@link InputKey} - The shootInput of this instance.
     */
    public InputKey getShootInput() {
        return shootInput;
    }

}
