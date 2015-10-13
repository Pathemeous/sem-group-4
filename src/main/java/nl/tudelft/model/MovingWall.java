package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MovingWall extends AbstractWall {
    
    private int speed;

    public MovingWall(Image image, int locX, int locY) {
        super(image, locX, locY);
        
        speed = 2;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        
    }

}
