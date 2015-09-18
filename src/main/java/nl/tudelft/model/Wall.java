package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends GameObject {

    public Wall(Image image, int locX, int locY) {
        super(image, locX, locY);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        // Walls do not do anything in their update as of yet.
    }

}
