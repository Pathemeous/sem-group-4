package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends GameObject {

    private int speed, width;
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
        this.locX = (locX+(width/2))-(image.getWidth()/2);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.locY -= speed;
    }
}