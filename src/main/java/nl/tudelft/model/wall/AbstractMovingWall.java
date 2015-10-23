package nl.tudelft.model.wall;

import nl.tudelft.controller.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class AbstractMovingWall extends AbstractWall {
    
    private float speed;

    /**
     * Creates a moving wall, which will move in a vertical or
     * horizontal direction with a defined speed.
     * @param image
     *      - The image of the wall.
     * @param locX
     *      - The starting x location of the wall.
     * @param locY
     *      - The starting y location of the wall.
     * @param speed
     *      - The speed with which the wall moves.
     */
    public AbstractMovingWall(Image image, float locX, float locY, float speed) {
        super(image, locX, locY);
        
        this.speed = speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public float getSpeed() {
        return speed;
    }

    @Override
    public  abstract <T extends Modifiable> void update(T container, int delta) 
            throws SlickException;

}
