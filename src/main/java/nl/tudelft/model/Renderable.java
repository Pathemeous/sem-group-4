package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The Renderable interface should be implemented by all objects that are rendered in the game,
 * specifically, all objects that are rendered in a level.
 */
/** package **/ interface Renderable {
    
    public void render(GameContainer container, Graphics grapics) throws SlickException;

}
