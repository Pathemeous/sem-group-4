package nl.tudelft.model.pickups;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import org.newdawn.slick.Image;

public class Projectile extends GameObject {

    private boolean fired, top, hit;
    private int counter;

    /**
     * @param image - The texture used for the weapon/projectile
     * @param x - The x coord
     * @param y - The y coord
     * @param width - The width of the texture used
     * @param height - The height of the texture used
     * @param speed - The speed
     *
     * Constructor for the class "Projectile".
     * New in this class:
     *              fired - Is the projectile fired
     *              counter - How long the projectile has been at the top of the screen
     *              hit - Has the projectile hit an object (bubble)
     *              top - Has the projectile hit the top
     */
    public Projectile(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
        fired = false;
        counter = 0;
        hit = false;
        top = false;
    }

    /**
     * @param p - The player who has fired the projectile
     *
     * Tick method for the class "Projectile". This method is called every update.
     * As we run at 60fps the counter needs to reach 90 (1.5 seconds) before
     * the projectile will disappear.
     */
    public void tick(Player p) {
        //If the projectile has been fired
        if(this.fired) {
            //If the projectile is either hit or it's at the top and the counter has reached 1.5 seconds
            if (this.hit || (this.top && counter > 90)) {
                reset(p);
            }
            //If the projectile is at the top but hasn't reached 1.5 seconds yet, increment the counter
            else if (this.top && counter <= 90) {
                counter++;
            }
            //If the projectile has been fired but has hasn't hit the top nor has it been hit
            else {
                //If the projectile would hit or exceed the top this tick
                if (this.y_location - speed <= 0) {
                    //Set the location to the top of the screen and set the boolean top to true
                    this.setY_location(0);
                    this.top = true;
                }
                //Else move the projectile up "speed" pixels
                else {
                    this.y_location -= speed;
                }
            }
        }
        else {
            reset(p);
        }
    }

    /**
     * @param p - The player who has fired the projectile
     *
     * Reset method for the class "Projectile". This method is called when the projectile needs to "disappear".
     */
    public void reset(Player p) {
        //Set every variable to the starting variables
        this.x_location = (p.getX_location() + p.getWidth() / 2) + 8;
        this.y_location = p.getY_location();
        this.fired = false;
        this.top = false;
        this.counter = 0;
        this.hit = false;
    }

    /**
     * @param p - The player who fires the projectile
     *
     * Fire method for the class "Projectile". This method is called when the projectile is fired.
     */
    public void fire(Player p) {
        if(this.fired==true) return;
        this.fired = true;
        this.x_location = (p.getX_location()+p.getWidth() / 2) + 8;
        this.y_location = p.getY_location();
    }
}
