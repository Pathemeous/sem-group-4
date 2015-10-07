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
    private final Input input = new Input(0);

    /**
     * Creates a new {@link InputKey} for the specified keyCode.
     * 
     * <p>
     * See the Slick2D wiki for keyCodes (or get them via {@link Input}).
     * 
     * @param keyCode
     */
    public InputKey(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if (input.isKeyPressed(keyCode)) {
            notifyObservers();
        }
    }
    

}
