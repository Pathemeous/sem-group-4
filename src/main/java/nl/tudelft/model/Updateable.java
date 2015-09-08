package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/** package **/ interface Updateable {
    
    public void update(GameContainer container, int delta) throws SlickException;

}
