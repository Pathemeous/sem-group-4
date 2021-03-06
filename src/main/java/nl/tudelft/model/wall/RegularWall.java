package nl.tudelft.model.wall;

import nl.tudelft.controller.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class RegularWall extends AbstractWall {
    
    public RegularWall(Image image, float locX, float locY) {
        super(image, locX, locY);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        // Walls do not do anything in their update as of yet.
    }
}
