package nl.tudelft.model.wall;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class HorMovingWall extends MovingWall {

    /**
     * Creates a moving wall, which will move in a horizontal 
     * direction with a defined speed.
     * @param image
     *      - The image of the wall.
     * @param locX
     *      - The starting x location of the wall.
     * @param locY
     *      - The starting y location of the wall.
     * @param speed
     *      - The speed with which the wall moves.
     */
    public HorMovingWall(Image image, float locX, float locY, float speed) {
        super(image, locX, locY, speed);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        setLocX(getLocX() + getSpeed());
    }

}
