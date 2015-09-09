package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Projectile extends GameObject {

    private int speed, width;
    private Weapon wp;
    private boolean hitBubble;
    private final int playerWidth;
    private final int playerHeight;
    private final int startHeight;

    /**
     * @param image - The texture used for the weapon/projectile
     * @param x - The x coord
     * @param y - The y coord
     * @param playerWidth - The width of the player
     * @param playerHeight - The height of the player
     * @param speed - The speed
     *
     * Constructor for the class "Projectile".
     * New in this class:
     *              fired - Is the projectile fired
     *              counter - How long the projectile has been at the top of the screen
     *              hit - Has the projectile hit an object (bubble)
     *              top - Has the projectile hit the top
     */
    public Projectile(Image image, int x, int y, int playerWidth, int playerHeight, int speed, Weapon wp) {
        super(image, x, y);
        this.speed = speed;
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;
        this.wp = wp;
        hitBubble = false;
        startHeight = y;
    }

    /**
     * Reset method for the class "Projectile". This method is called when the projectile needs to "disappear".
     */
    public void reset() {
        //Set every variable to the starting variables
        wp.remove(this);
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
        this.locX = (locX+(playerWidth/2))-(image.getWidth()/2);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
//        super.render(container, g);
        final Image img = getImage();

        float drawHeight = getActualHeight();

        g.drawImage(img,
                getLocX(), getLocY(),
                getLocX() + img.getWidth(), getLocY() + drawHeight,
                0, 0,
                img.getWidth(), drawHeight);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.locY -= speed;
    }

    /**
     * The sprite we use is an image of the full length of the projectile.
     * We only draw a certain height, returned by this method.
     * @return The visible height of the projectile.
     */
    private float getActualHeight(){
        return startHeight - getLocY() + playerHeight;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle(getLocX(), getLocY(), getWidth(), getActualHeight());
    }
}