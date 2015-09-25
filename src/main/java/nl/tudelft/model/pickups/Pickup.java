package nl.tudelft.model.pickups;

import nl.tudelft.model.AbstractEnvironmentObject;
import nl.tudelft.model.pickups.powerup.InvinciblePowerup;
import nl.tudelft.model.pickups.powerup.LifePowerup;
import nl.tudelft.model.pickups.powerup.MoneyPowerup;
import nl.tudelft.model.pickups.powerup.PointsPowerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import nl.tudelft.model.pickups.powerup.SpeedPowerup;
import nl.tudelft.model.pickups.utility.FreezeUtility;
import nl.tudelft.model.pickups.utility.LevelWonUtility;
import nl.tudelft.model.pickups.utility.SlowUtility;
import nl.tudelft.model.pickups.utility.SplitUtility;
import nl.tudelft.model.pickups.utility.TimeUtility;
import nl.tudelft.model.pickups.weapon.DoubleWeapon;
import nl.tudelft.model.pickups.weapon.FlowerWeapon;
import nl.tudelft.model.pickups.weapon.RegularWeapon;
import nl.tudelft.model.pickups.weapon.StickyWeapon;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import nl.tudelft.semgroup4.util.Helpers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Pickup extends AbstractEnvironmentObject {

    private boolean onGround;
    private boolean isActive;
    private boolean toRemove;
    private int tickCount;
    
    /**
     * Creates a pickup with a random content.
     * @param image Image - the image of the object.
     * @param locX float - the x-coordinate.
     * @param locY float - the y-coordinate.
     */
    public Pickup(Image image, float locX, float locY) {
        super(image, locX, locY);
        onGround = false;  
        isActive = false;
        toRemove = false;
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        if (!isActive) {
            graphics.drawImage(getImage(), locX, locY);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        setLocY(getLocY() + 1);
        
        if (toRemove) {
            container.toRemove(this);
        } else if (onGround && !isActive) {
            tickCount++;

            if (tickCount == 180) {
                container.toRemove(this);
            }
        }
    }
    
    protected int getTickCount() {
        return tickCount;
    }
    
    protected void setTickCount(int tick) {
        tickCount = tick;
    }
    
    protected boolean isOnGround() {
        return onGround;
    }
    
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
    public boolean willBeRemoved() {
        return toRemove;
    }
    
    public void toRemove() {
        toRemove = true;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    /**
     * Method that generates and returns a random pickup, based on a random
     * number that is passed as a parameter. 
     * @param random : random int that determines the pickup that
     *      is generated.
     * @param locX : the x location of the pickup to be created.
     * @param locY : the y location of the pickup to be created.
     * @return : an object of type Pickup.
     */
    public static Pickup generateRandomPickup(int random, float locX, float locY) {
        if (random < 4) {
            int randomWeaponNr = Helpers.randInt(1, 4);
            // new weapon
            switch (randomWeaponNr) {
                // regular weapon
                case 1:
                    return new RegularWeapon(new ResourcesWrapper(), locX, locY);
                // Double shoot
                case 2:
                    return new DoubleWeapon(new ResourcesWrapper(), locX, locY);
                // Sticky weapon
                case 3:
                    return new StickyWeapon(new ResourcesWrapper(), locX, locY);
                // Flower weapon
                case 4:
                    return new FlowerWeapon(new ResourcesWrapper(), locX, locY);
                default:
                    return new RegularWeapon(new ResourcesWrapper(), locX, locY);
            }
        } else if (random < 7) {
            // new powerup
            int randomPowerupNr = Helpers.randInt(6, 11);

            if (randomPowerupNr == 11) {
                return new LifePowerup(new ResourcesWrapper(), locX, locY);
            } else if (randomPowerupNr > 9) {
                return new MoneyPowerup(new ResourcesWrapper(), locX, locY);
            } else if (randomPowerupNr > 8) {
                return new InvinciblePowerup(new ResourcesWrapper(), locX, locY);
            } else if (randomPowerupNr > 6) {
                return new ShieldPowerup(new ResourcesWrapper(), locX, locY);
            } else if (randomPowerupNr > 4) {
                return new SpeedPowerup(new ResourcesWrapper(), locX, locY);
            } else {
                return new PointsPowerup(new ResourcesWrapper(), locX, locY);
            }
        } else {
            // new utility
            int randomUtilNr = Helpers.randInt(1, 20);
            
            if (randomUtilNr == 20) {
                return new LevelWonUtility(new ResourcesWrapper(), locX, locY);
            } else if (randomUtilNr > 16) {
                return new SplitUtility(new ResourcesWrapper(), locX, locY);
            } else if (randomUtilNr > 11) {
                return new SlowUtility(new ResourcesWrapper(), locX, locY);
            } else if (randomUtilNr > 6) {
                return new FreezeUtility(new ResourcesWrapper(), locX, locY);
            } else {
                return new TimeUtility(new ResourcesWrapper(), locX, locY);
            }
        }
    }
}
