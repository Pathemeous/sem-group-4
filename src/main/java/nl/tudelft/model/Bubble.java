package nl.tudelft.model;

import nl.tudelft.viewControllers.GameController;
import org.newdawn.slick.GameContainer;

public class Bubble extends GameController {
    private int x;
    private int y;
    private int size;
    private int dir;
    private GameContainer gc;

    public Bubble(int x, int y, int size, GameContainer gc) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.gc = gc;
        this.dir = 1;
    }

    public void tick() {
        if (x > 617||x<0) {
            dir *= -1;
        }
    }


}
