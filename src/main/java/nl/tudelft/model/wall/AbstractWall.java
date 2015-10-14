package nl.tudelft.model.wall;

import nl.tudelft.model.AbstractEnvironmentObject;
import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a solid wall that exists in the game field.
 */
public abstract class AbstractWall extends AbstractEnvironmentObject {

    /**
     * Creates an abstract wall.
     * @param image : wall image.
     * @param locX : x location of the wall.
     * @param locY : y location of the wall.
     */
    public AbstractWall(Image image, float locX, float locY) {
        super(image, locX, locY);
    }

    @Override
    public  abstract <T extends Modifiable> void update(T container, int delta) 
            throws SlickException;
}
