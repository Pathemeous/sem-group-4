package nl.tudelft.model.pickups.weapon;

import java.util.ArrayList;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.pickups.AbstractPickup;
import nl.tudelft.model.player.Player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class AbstractWeapon extends AbstractPickup {

    private Player player;
    private final ArrayList<Projectile> projectiles;
    private final boolean sticky;
    private final int maxCount;
    private int fireCounter = 0;
    private final Image img;
    private final ResourcesWrapper resources;

    /**
     * Creates a new instance of a Weapon.
     * 
     * @param pickupImage
     *            - the image the pickup will have.
     * @param projImage
     *            - the image the projectile that this weapon shoots will have.
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this weapon will use.
     * @param locX
     *            - the x location of this weapon, when it's not activated.
     * @param locY
     *            - the y location of this weapon, when it's not activated.
     * @param sticky
     *            - boolean indicating if a projectile of this weapon sticks to the wall for a
     *            short period of time.
     * @param maxCount
     *            - the maximum amount of projectiles this weapon can shoot.
     */
    public AbstractWeapon(Image pickupImage, Image projImage, ResourcesWrapper resources,
            float locX, float locY, boolean sticky, int maxCount) {
        super(pickupImage, locX, locY);
        this.resources = resources;
        this.sticky = sticky;
        this.maxCount = maxCount;
        img = projImage;
        projectiles = new ArrayList<Projectile>();
    }

    /**
     * This method activates the current weapon and removes the old weapon of the player.
     * 
     * @param player
     *            {@link Player} - sets the player this weapon now belongs to.
     */
    public void activate(Player player) {
        setActive(true);
        this.player = player;
        AbstractWeapon oldWeapon = player.getWeapon();

        if (oldWeapon != null && oldWeapon != this) {
            oldWeapon.setToRemove();
        }

        player.setWeapon(this);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);
        fireCounter = (fireCounter <= 10 && fireCounter != 0) ? fireCounter + 1 : 0;
    }

    /**
     * Fires the weapon, creating a projectile.
     * 
     * @param <T>
     *            implements Modifiable
     * @param container
     *            Implements Modifiable - the container to call modifications.
     * @param locX
     *            int - The x-coordinate.
     * @param locY
     *            int - The y-coordinate.
     * @param width
     *            int
     * @param height
     *            int
     */
    public <T extends Modifiable> void fire(T container, int locX, int locY, int width,
            int height) {
        if (fireCounter == 0 && projectiles.size() < maxCount && isActive()) {
            fireCounter++;
            Projectile proj =
                    new Projectile(resources, img, locX, locY, width, height, 6, this);
            proj.fire();
            container.toAdd(proj);
            projectiles.add(proj);
        }
    }

    /**
     * Remove the projectile.
     * 
     * @param container
     *            implements Modifiable.
     * @param proj
     *            Projectile - the Projectile to remove.
     */
    public <T extends Modifiable> void remove(T container, Projectile proj) {
        projectiles.remove(proj);
        container.toRemove(proj);
    }

    public int getMaxCount() {
        return maxCount;
    }

    public boolean isSticky() {
        return sticky;
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

    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    protected int getFireCounter() {
        return fireCounter;
    }

    protected void setFireCounter(int count) {
        fireCounter = count;
    }
}
