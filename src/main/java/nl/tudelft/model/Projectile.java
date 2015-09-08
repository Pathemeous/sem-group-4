package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends GameObject {

    private boolean top, hit, fired;
    private int counter, speed, width;
    private Weapon wp;

    /**
     * @param image - The texture used for the weapon/projectile
     * @param x - The x coord
     * @param y - The y coord
     * @param width - The width of the texture used
     * @param speed - The speed
     *
     * Constructor for the class "Projectile".
     * New in this class:
     *              fired - Is the projectile fired
     *              counter - How long the projectile has been at the top of the screen
     *              hit - Has the projectile hit an object (bubble)
     *              top - Has the projectile hit the top
     */
    public Projectile(Image image, int x, int y, int width, int speed, Weapon wp) {
        super(image, x, y);
        counter = 0;
        hit = false;
        top = false;
        fired = false;
        this.speed = speed;
        this.width = width;
        this.wp = wp;
    }

    /**
     * Reset method for the class "Projectile". This method is called when the projectile needs to "disappear".
     */
    public void reset() {
        //Set every variable to the starting variables
        wp.remove(this);
    }

    /**
     * Fire method for the class "Projectile". This method is called when the projectile is fired.
     */
    public void fire() {
        this.locX = (locX+(width / 2)) + 8;
        this.fired = true;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(fired) {
            //If the projectile is either hit or it's at the top and the counter has reached 1.5 seconds
            if (this.hit || (this.top && counter > 90)) {
                reset();
            }
            //If the projectile is at the top but hasn't reached 1.5 seconds yet, increment the counter
            else if (this.top && counter <= 90) {
                counter++;
            }
            //If the projectile has been fired but has hasn't hit the top nor has it been hit
            else {
                //If the projectile would hit or exceed the top this tick
                if (this.locY - speed <= 0) {
                    //Set the location to the top of the screen and set the boolean top to true
                    this.setLocY(0);
                    this.top = true;
                }
                //Else move the projectile up "speed" pixels
                else {
                    this.locY -= speed;
                }
            }
        }
    }
}