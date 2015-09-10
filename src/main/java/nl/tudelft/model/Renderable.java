package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The Renderable interface should be implemented by all objects that are rendered in the game,
 * specifically, all objects that are rendered in a level.
 */
/** package **/ interface Renderable {

    /**
     * Performs all rendering (drawing) for the implementing class.
     * 
     * <p>
     * This method is part of the {@link nl.tudelft.model.Renderable} interface
     * </p>
     * 
     * @param container
     *            GameContainer - The Slick2D GameContainer that the game runs in.
     * @param graphics
     *            Graphics - The Slick2D Graphics object used for rendering.
     * @throws SlickException
     *             - If the implementation of this method fails to render.
     */
    public void render(GameContainer container, Graphics graphics) throws SlickException;

}
