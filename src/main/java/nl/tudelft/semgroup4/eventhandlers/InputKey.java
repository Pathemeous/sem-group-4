package nl.tudelft.semgroup4.eventhandlers;

import java.util.Observable;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Updateable;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Represents the input to one key. Checks for this input with every update.
 * 
 * @author Pathemeous
 *
 */
public class InputKey extends Observable implements Updateable {

    private final int keyCode;
    private static final Input INPUT = new Input(0);

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

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if (INPUT.isKeyPressed(keyCode)) {
            notifyObservers();
        }
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
        return INPUT.hashCode() + keyCode;
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
    protected final int getKeyCode() {
        return keyCode;
    }

}
