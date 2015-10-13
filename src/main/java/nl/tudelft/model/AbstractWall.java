package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a solid wall that exists in the game field.
 */
public abstract class AbstractWall extends AbstractEnvironmentObject {

    public AbstractWall(Image image, int locX, int locY) {
        super(image, locX, locY);
    }

    @Override
    public  abstract <T extends Modifiable> void update(T container, int delta) throws SlickException;

}
