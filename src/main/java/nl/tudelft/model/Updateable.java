package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 * The Updateable interface should be implemented by all objects that require to update
 * on every game tick.
 */
/** package **/ interface Updateable {
    
    public void update(GameContainer container, int delta) throws SlickException;

}
