package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class VerMovingWall extends AbstractWall {
    
    private int speed;

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
    public VerMovingWall(Image image, int locX, int locY, int speed) {
        super(image, locX, locY);
        
        this.speed = speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        setLocY(getLocY() + speed);
    }

}