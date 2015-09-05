package nl.tudelft.model.pickups;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import org.newdawn.slick.Image;

public class Projectile extends GameObject {

    private boolean fired, top, hit;
    private int counter;
    public Projectile(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
        fired = false;
        counter = 0;
        hit = false;
        top = false;
    }

    public void tick(Player p) {
        if(this.fired) {
            if (this.hit || (this.top && counter > 90)) {
                reset(p);
            } else if (this.top && counter <= 90) {
                counter++;
            } else {
                if (this.y_location - 6 <= 0) {
                    this.setY_location(0);
                    this.top = true;
                } else {
                    this.y_location -= 6;
                }
            }
        }
        else {
            reset(p);
        }
    }

    public void reset(Player p) {
        this.x_location = (p.getX_location() + p.getWidth() / 2) + 8;
        this.y_location = p.getY_location();
        this.fired = false;
        this.top = false;
        this.counter = 0;
        this.hit = false;
    }
    public void fire(Player p) {
        if(this.fired==true) return;
        this.fired = true;
        this.x_location = (p.getX_location()+p.getWidth() / 2) + 8;
        this.y_location = p.getY_location();
    }
    public boolean getFired() {
        return this.fired;
    }
}
