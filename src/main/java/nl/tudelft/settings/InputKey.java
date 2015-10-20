package nl.tudelft.settings;

import java.util.Observable;

import org.newdawn.slick.Input;

/**
 * Represents the input to one key. Checks for this input with every update.
 * 
 * @author Pathemeous
 *
 */
public class InputKey extends Observable {

    private final int keyCode;
    private static Input input = new Input(0);
    private Boolean down = false;

    /**
     * Creates a new {@link InputKey} for the specified keyCode.
     * 
     * <p>
     * See the Slick2D wiki for keyCodes (or get them via {@link Input}).
     * </p>
     * 
     * @param keyCode
     *            int - The value representing the desired key.
     */
    public InputKey(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Checks for input and notifies the observers when the key is down (every poll) or when the
     * key is released (once only).
     */
    public void poll() {
        if (input.isKeyDown(keyCode)) {
            down = true;
            setChanged();
        } else if (down) {
            down = false;
            setChanged();
        }
        notifyObservers(down);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InputKey) {
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return input.hashCode() + keyCode;
    }

    /**
     * Accesses the keyCode field of this object.
     * 
     * <p>
     * The keyCode is an integer representing a key on the keyboard, according to the Slick2D
     * library.
     * </p>
     * 
     * @return int - the keyCode of the key that this object checks for.
     */
    public final int getKeyCode() {
        return keyCode;
    }

    /**
     * Accesses the static {@link Input} field of this class.
     * 
     * <p>
     * This field is static and should not be changed aside from testing purposes.
     * </p>
     * 
     * @return the input
     */
    protected static final Input getInput() {
        return input;
    }

    /**
     * Sets the static {@link Input} field of this object.
     * 
     * <p>
     * This field is static and should not be changed aside from testing purposes.
     * </p>
     * 
     * @param input
     *            {@link Input} - The input to set.
     */
    protected static final void setInput(Input input) {
        InputKey.input = input;
    }

    /**
     * Sets the boolean field representing the state of the key.
     * 
     * @param Boolean
     *            down - The key is considered to be pressed when true, and false otherwise
     */
    protected final void setDown(Boolean down) {
        this.down = down;
    }

}
