package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class AbstractEnvironmentObject extends GameObject {

    public AbstractEnvironmentObject(Image image, float locX, float locY) {
        super(image, locX, locY);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        // TODO Auto-generated method stub

    }

}
