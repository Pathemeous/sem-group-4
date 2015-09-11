package nl.tudelft.model.pickups;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Weapon;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.util.Helpers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pickup extends GameObject {

    private boolean onGround;
    private PickupContent pickup;
    private int tickCount;

    public enum WeaponType {
        REGULAR, DOUBLE, STICKY, FLOWER
    }

    /**
     * Creates a pickup with a random content.
     * @param image Image - the image of the object.
     * @param locX float - the x-coordinate.
     * @param locY float - the y-coordinate.
     */
    public Pickup(Image image, float locX, float locY, int random) {
        super(image, locX, locY);
        onGround = false;

        //int random = Helpers.randInt(1, 10);
        if (random < 4) {
            int randomWeaponNr = Helpers.randInt(1, 4);
            // new weapon
            switch (randomWeaponNr) {
            // regular weapon
                case 1:
                    pickup = new Weapon(Resources.weaponImageRegular, WeaponType.REGULAR);
                    setImage(Resources.pickup_weapon_regular);
                    break;
                // Double shoot
                case 2:
                    pickup = new Weapon(Resources.weaponImageRegular, WeaponType.DOUBLE);
                    setImage(Resources.pickup_weapon_double);
                    break;
                // Sticky weapon
                case 3:
                    pickup = new Weapon(Resources.weaponImageSticky, WeaponType.STICKY);
                    setImage(Resources.pickup_weapon_sticky);
                    break;
                // Flower weapon
                case 4:
                    pickup = new Weapon(Resources.weaponImageFlower, WeaponType.FLOWER);
                    setImage(Resources.pickup_weapon_flowers);
                    break;
                default:
                    break;
            }

        } else if (random > 3 && random < 7) {
            // new powerup
            pickup = new Powerup(Helpers.randInt(1, 10));
            Powerup up = (Powerup) pickup;
            switch (up.getPowerType()) {
                case INVINCIBLE:
                    setImage(Resources.pickup_power_invincible);
                    break;
                case POINTS:
                    setImage(Resources.pickup_power_points);
                    break;
                case SHIELD:
                    setImage(Resources.pickup_power_shield);
                    break;
                case SPEEDUP:
                    setImage(Resources.pickup_power_speedup);
                    break;
                case LIFE:
                    setImage(Resources.pickup_utility_life);
                    break;
                default:
                    break;
            }
        } else {
            // new utility
            int randomUtil = Helpers.randInt(1, 20);
            pickup = new Utility(randomUtil);
            Utility util = (Utility) pickup;

            switch (util.getType()) {
                case FREEZE:
                    setImage(Resources.pickup_utility_freeze);
                    break;
                case LEVELWON:
                    setImage(Resources.pickup_utility_levelwon);
                    break;
                case SLOW:
                    setImage(Resources.pickup_utility_slow);
                    break;
                case SPLIT:
                    setImage(Resources.pickup_utility_split);
                    break;
                case TIME:
                    setImage(Resources.pickup_utility_time);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        setLocY(getLocY() + 1);
        if (onGround) {
            tickCount++;

            if (tickCount == 180) {
                container.toRemove(this);
            }
        }
    }

    public PickupContent getContent() {
        return pickup;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
    

}
