package nl.tudelft.model;

import java.util.ArrayList;

import nl.tudelft.model.pickups.Pickup.WeaponType;
import nl.tudelft.model.pickups.PickupContent;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.Updateable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Weapon extends PickupContent implements Updateable {

    private Player player;
    private ArrayList<Projectile> projectiles;
    private Image img;
    private WeaponType type;
    private boolean sticky;
    private int maxCount;

    /**
     * Creates a new Weapon of the given type and with the given image.
     * 
     * @param image
     *            Image - the image to use.
     * @param type
     *            WeaponType
     * @throws IllegalArgumentException
     *             - if the type is wrong.
     */
    public Weapon(Image image, WeaponType type) throws IllegalArgumentException {
        this.type = type;
        sticky = false;

        switch (type) {
            case REGULAR:
                maxCount = 1;
                break;
            case DOUBLE:
                maxCount = 2;
                break;
            case STICKY:
                maxCount = 1;
                sticky = true;
                break;
            case FLOWER:
                maxCount = 10;
                break;
            default:
                throw new IllegalArgumentException();
        }

        img = image;
        projectiles = new ArrayList<Projectile>();
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        // TODO Auto-generated method stub

    }

    public WeaponType getType() {
        return type;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public boolean isSticky() {
        return sticky;
    }

    /**
     * Fires the weapon, creating a projectile.
     * 
     * @param <T> implements Modifiable
     * @param container Implements Modifiable - the container to call modifications.
     * @param locX int - The x-coordinate.
     * @param locY int - The y-coordinate.
     * @param width int
     * @param height int
     */
    public <T extends Modifiable> void
            fire(T container, int locX, int locY, int width, int height) {
        if (projectiles.size() < maxCount) {
            Projectile proj = new Projectile(img, locX, locY, width, height, 6, this);
            proj.fire();
            container.toAdd(proj);
            projectiles.add(proj);
        }
    }

    /**
     * Remove the projectile.
     * 
     * @param container implements Modifiable.
     * @param proj Projectile - the Projectile to remove.
     */
    public <T extends Modifiable> void remove(T container, Projectile proj) {
        projectiles.remove(proj);
        container.toRemove(proj);
    }

    public int getNumberOfProjectiles() {
        return projectiles.size();
    }

    /**
     * Sets the player that this weapon belongs to.
     * 
     * @param player
     *            Player - the player that owns this weapon.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
