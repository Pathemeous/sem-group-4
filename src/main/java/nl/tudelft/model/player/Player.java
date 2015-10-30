package nl.tudelft.model.player;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.Updateable;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.pickups.powerup.AbstractPowerup;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;
import nl.tudelft.settings.PlayerInputListener;

import org.newdawn.slick.Animation;

public interface Player extends Updateable, Renderable, PlayerInputListener, GameObject {

    void reset();

    void die();

    boolean isAlive();

    void removeLife();

    void clearAllPowerups();

    boolean hasPowerup(String key);

    AbstractPowerup removePowerup(String key);

    void setPowerup(String key, AbstractPowerup value);

    AbstractPowerup getPowerup(String key);

    boolean isInvincible();

    boolean hasShield();

    boolean hasShopShield();

    void setDefaultSpeed();

    void setSpeed(int newSpeed);

    int getSpeed();

    boolean isFirstPlayer();

    void setWeapon(AbstractWeapon weapon);

    AbstractWeapon getWeapon();

    int getLives();

    void setLives(int lives);

    void setScore(int score);

    void setMoney(int money);

    int getMoney();

    int getScore();

    Animation getAnimationCurrent();

    void setAnimationCurrent(Animation animationCurrent);

    void setShopWeapon(boolean bool);

    boolean isShopWeapon();

    void setShopSpeed(boolean bool);

    boolean isShopSpeed();

    /**
     * Returns the regular speed of this player. This is defined as the normal movement speed,
     * without any upgrades.
     * 
     * @return int - The regular speed of this player;
     */
    int getRegularSpeed();

    Animation getAnimationLeft();

    Animation getAnimationRight();
}
