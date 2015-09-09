package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends GameObject {

    private int speed, width;
    private Weapon wp;
    private boolean hitBubble;
    private int tickCount;
    private boolean hitWall;

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
        hitBubble = false;
        hitWall = false;
        tickCount = 0;
    }

    /**
     * Reset method for the class "Projectile". This method is called when the projectile needs to "disappear".
     */
    public void reset() {
    	hitWall = true;
        //Set every variable to the starting variables
    	if(!wp.isSticky() || hitBubble) {
    		wp.remove(this);
    	} else if(tickCount == 0) {
    		tickCount++;
    	} else if(tickCount == 60) {
    		wp.remove(this);
    		tickCount = 0;
    	}
    }
    
    public void setHitBubble (boolean hit) {
    	hitBubble = hit;
    }
    
    public boolean getHitBubble () {
    	return hitBubble;
    }

    /**
     * Fire method for the class "Projectile". This method is called when the projectile is fired.
     */
    public void fire() {
        this.locX = (locX+(width/2))-(image.getWidth()/2);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	if(!hitWall) {
    		this.locY -= speed;
    	}
        
        if(tickCount < 60 && tickCount != 0) {
        	tickCount++;
        }
    }
}