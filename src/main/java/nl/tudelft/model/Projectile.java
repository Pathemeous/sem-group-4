package nl.tudelft.model;

import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.util.Audio;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Projectile extends AbstractEnvironmentObject {

    private final int speed;
    private int width;
    private final Weapon weapon;
    private boolean hitBubble;
    private int tickCount;
    private boolean hitWall;
    private final int playerWidth;
    private final int playerHeight;
    private final int startHeight;

    /**
     * @param image
     *            - The texture used for the weapon/projectile
     * @param locX
     *            - The x coord
     * @param locY
     *            - The y coord
     * @param playerWidth
     *            - The width of the player
     * @param playerHeight
     *            - The height of the player
     * @param speed
     *            - The speed
     * @param wp
     *            Weapon - The weapon that this projectile was shot by.
     *
     *            Constructor for the class "Projectile". New in this class: fired - Is the
     *            projectile fired counter - How long the projectile has been at the top of the
     *            screen hit - Has the projectile hit an object (bubble) top - Has the projectile
     *            hit the top
     */
    public Projectile(Image image, int locX, int locY, int playerWidth, int playerHeight,
            int speed, Weapon wp) {
        super(image, locX, locY);
        this.speed = speed;
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;
        this.weapon = wp;
        hitBubble = false;
        hitWall = false;
        tickCount = 0;
        startHeight = locY;

        if (wp.getNumberOfProjectiles() == 0 && !Resources.weaponFire.playing()) {            
            Audio.playFireSound();
        }
    }

    /**
     * Reset method for the class "Projectile". This method is called when the projectile needs to
     * "disappear".
     * 
     * @param <T>
     *            implements Modifiable.
     * @param container
     *            T - The container that this object may request changes to.
     */
    public <T extends Modifiable> void reset(T container) {
        Audio.stopFireSound();
        // Set every variable to the starting variables
        if (!weapon.isSticky() || hitBubble) {
            weapon.remove(container, this);
        } else if (tickCount == 0) {
            tickCount++;
        } else if (tickCount == 90) {
            weapon.remove(container, this);
            tickCount = 0;
        }

    }

    public void setHitWall() {
        hitWall = true;
    }

    public void setHitBubble() {
        hitBubble = true;
    }

    public boolean isHitBubble() {
        return hitBubble;
    }

    /**
     * Fire method for the class "Projectile". This method is called when the projectile is fired.
     */
    public void fire() {
        this.locX = (locX + (playerWidth / 2)) - (image.getWidth() / 2);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        // super.render(container, g);
        final Image img = getImage();

        float drawHeight = getActualHeight();

        graphics.drawImage(img, getLocX(), getLocY(), getLocX() + img.getWidth(), getLocY()
                + drawHeight, 0, 0, img.getWidth(), drawHeight);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if (!hitWall) {
            this.locY -= speed;
        } else {
            if (tickCount < 90 && tickCount != 0) {
                tickCount++;
            } else {
                reset(container);
            }
        }

        if (hitBubble) {
            reset(container);
        }

    }

    /**
     * The sprite may be longer than the actual bounds that should be visible. This method returns
     * the visible height of the image, according to startHeight and playerHeight.
     * 
     * @return The visible height of the projectile.
     */
    private float getActualHeight() {
        float height = getHeight();
        float maxHeight = startHeight - getLocY() + playerHeight;
        return Math.min(height, maxHeight);
    }

    @Override
    public Shape getBounds() {
        return new Rectangle(getLocX(), getLocY(), getWidth(), getActualHeight());
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public int getTickCount() {
        return tickCount;
    }

    public void setTickCount(int count) {
        tickCount = count;
    }
}
