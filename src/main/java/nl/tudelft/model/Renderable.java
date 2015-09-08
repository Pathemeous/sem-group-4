package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/** package **/ interface Renderable {
    
    public void render(GameContainer container, Graphics g) throws SlickException;

}
