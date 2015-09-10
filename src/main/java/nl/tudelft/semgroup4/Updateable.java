package nl.tudelft.semgroup4;

import org.newdawn.slick.SlickException;

/**
 * The Updateable interface should be implemented by all objects that require to update
 * on every game tick.
 */
public interface Updateable {

    /**
     * Performs all on-tick updates for the implementing class.
     * 
     * <p>
     * This method is part of the {@link Updateable} interface.
     * </p>
     * 
     * @param container
     *            GameContainer - The Slick2D GameContainer that the game runs in.
     * @param delta
     *            int - The amount of time in milliseconds that has passed since the previous
     *            update.
     * @throws SlickException
     *             - If the implementation of this method fails to update.
     */
    public <T extends Modifiable> void update(T container, int delta) throws SlickException;

}
