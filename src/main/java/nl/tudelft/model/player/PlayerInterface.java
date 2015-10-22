package nl.tudelft.model.player;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.Updateable;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.settings.PlayerInputListener;

import org.newdawn.slick.Animation;

public interface PlayerInterface extends Updateable, Renderable, PlayerInputListener {

    public void reset();

    public void die();

    public boolean isAlive();

    public void removeLife();

    public void clearAllPowerups();

    public boolean hasPowerup(String key);

    public Powerup removePowerup(String key);

    public void setPowerup(String key, Powerup value);

    public Powerup getPowerup(String key);

    public boolean isInvincible();

    public boolean hasShield();

    @Deprecated
    public boolean hasShopShield();

    public void applySpeedup();

    public void setDefaultSpeed();

    public void setSpeed(int newSpeed);

    public int getSpeed();

    public boolean isFirstPlayer();

    public void setWeapon(Weapon weapon);

    public Weapon getWeapon();

    public int getLives();

    public void setLives(int lives);

    public void setScore(int score);

    public void setMoney(int money);

    public int getMoney();

    public int getScore();

    public Animation getAnimationCurrent();

    public void setAnimationCurrent(Animation animationCurrent);

    @Deprecated
    public void setShopWeapon(boolean bool);

    @Deprecated
    public boolean isShopWeapon();

    @Deprecated
    public void setShopSpeed(boolean bool);

    @Deprecated
    public boolean isShopSpeed();

    /**
     * Returns the regular speed of this player. This is defined as the normal movement speed,
     * without any upgrades.
     * 
     * @return int - The regular speed of this player;
     */
    public int getRegularSpeed();
}
