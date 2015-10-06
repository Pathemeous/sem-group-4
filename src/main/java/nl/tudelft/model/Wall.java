package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a solid wall that exists in the game field.
 */
public class Wall extends AbstractEnvironmentObject {

    public Wall(Image image, int locX, int locY) {
        super(image, locX, locY);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        // Walls do not do anything in their update as of yet.
    }

}
