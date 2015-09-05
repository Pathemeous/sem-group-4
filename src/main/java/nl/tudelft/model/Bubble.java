package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Bubble extends GameObject {
    private GameContainer gc;
    private int counter;



    public Bubble(Image image, int x, int y, int size, GameContainer gc) {
        super(image,x,y,size,size,1);
        this.gc = gc;
        this.counter=0;
    }

    public void tick() {
        if (this.x_location > 617 || this.x_location < 0) {
            this.speed *= -1;
        }
    }

    public void move() {
        this.y_location=width*speed*Math.abs((int)(Math.sin(counter)));
        counter++;
    }
}
