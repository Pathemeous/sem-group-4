package nl.tudelft.model.player;

import java.util.HashMap;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.controller.util.SemRectangle;
import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.pickups.powerup.AbstractPowerup;
import nl.tudelft.model.pickups.powerup.InvinciblePowerup;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;
import nl.tudelft.model.pickups.weapon.RegularWeapon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class ConcretePlayer extends AbstractGameObject implements Player {

    private int score;
    private int money;
    private static final int BOUNDS_OFFSET_X = 10;
    private static final int BOUNDS_OFFSET_Y = 15;
    private static final int REGULAR_SPEED = 4;
    private final int initialLocy;
    private final int initialLocx;
    private int speed;
    private int lives;
    private final boolean firstPlayer;
    private final HashMap<String, AbstractPowerup> powerups = new HashMap<>();
    private boolean weaponActivated = false;
    private boolean shopWeapon = false;
    private boolean shopSpeedup = false;
    private Modifiable container;

    private AbstractWeapon weapon;

    private Animation animationCurrent;
    private final Animation animationLeft;
    private final Animation animationRight;

    /**
     * Constructor for the Player class.
     * 
     * @param resources
     *            {@link ResourcesWrapper} - The resources that the player can use. creating a new
     *            instance is fine.
     * @param locX
     *            int - The x-coordinate where the player should spawn.
     * @param locY
     *            int - The y-coordinate where the player should spawn.
     * @param isFirstPlayer
     *            boolean - checks whether the player is number one or two.
     */
    public ConcretePlayer(ResourcesWrapper resources, int locX, int locY, boolean isFirstPlayer) {
        super(resources.getPlayerImageStill(), locX, locY);
        initialLocx = locX;
        initialLocy = locY;
        speed = REGULAR_SPEED;
        lives = 3;
        score = 0;
        money = 0;
        this.firstPlayer = isFirstPlayer;

        this.weapon = new RegularWeapon(new ResourcesWrapper(), 0, 0);
        this.weapon.activate(this);

        this.animationCurrent = null;
        this.animationLeft = resources.getPlayerWalkLeft();
        this.animationRight = resources.getPlayerWalkRight();
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        Animation curAnimation = getAnimationCurrent();
        if (curAnimation == null) {
            graphics.drawImage(getImage(), getLocX(), getLocY());
        } else {
            graphics.drawAnimation(curAnimation, getLocX(), getLocY());
        }
    }

    @Override
    public Shape getBounds() {
        return new SemRectangle(locX + BOUNDS_OFFSET_X, locY + BOUNDS_OFFSET_Y, getImage()
                .getWidth() - (2 * BOUNDS_OFFSET_X), getImage().getHeight() - BOUNDS_OFFSET_Y);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        this.container = container;

        if (getWeapon() != null && getWeapon().isActive() && !weaponActivated) {
            container.toAdd(getWeapon());
            weaponActivated = true;
        }

        if (getLives() == 0) {
            container.toRemove(this);
        }
    }

    /**
     * Moves the {@link Player} left according to its speed and sets the correct animation.
     */
    @Override
    public void moveLeft() {
        setAnimationCurrent(animationLeft);
        setLocX(locX - getSpeed());
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the left");
    }

    /**
     * Moves the {@link Player} right according to its speed and sets the correct animation.
     */
    @Override
    public void moveRight() {
        setAnimationCurrent(animationRight);
        setLocX(locX + getSpeed());
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the right");
    }

    /**
     * Makes the {@link Player} stop moving by stopping the animation.
     */
    @Override
    public void stopMoving() {
        setAnimationCurrent(null);
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player stops moving.");
    }

    /**
     * Fires the {@link Player#getWeapon()}.
     */
    @Override
    public void shoot() {
        getWeapon().fire(container, (int) this.locX, (int) this.locY, this.getWidth(),
                this.getHeight());
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player shoots");
    }

    /**
     * Resets the player state to reflect the clean start of a level.
     * 
     * <p>
     * This means that a player loses all his {@link AbstractPowerup}s and his
     * {@link AbstractWeapon} and makes sure that the weapon fire delay is set to zero.
     * </p>
     */
    public void reset() {
        clearAllPowerups();
        if (shopWeapon) {
            getWeapon().getProjectiles().clear();
        } else {
            setWeapon(new RegularWeapon(new ResourcesWrapper(), 0, 0));
        }
        this.getWeapon().activate(this);
        weaponActivated = false;

        locX = initialLocx;
        locY = initialLocy;
        setAnimationCurrent(null);
    }

    /**
     * Performs all necessary actions that should happens when this {@link Player} dies.
     */
    public void die() {
        removeLife();
        setScore(getScore() - 1000);
    }

    public boolean isAlive() {
        return getLives() > 0;
    }

    /**
     * Removes all {@link AbstractPowerup}s from this {@link Player}.
     */
    public void clearAllPowerups() {
        if (hasPowerup(AbstractPowerup.SPEED)) {
            powerups.remove(AbstractPowerup.SPEED).setToRemove();
        }
        if (hasPowerup(AbstractPowerup.SHIELD)) {
            powerups.remove(AbstractPowerup.SHIELD).setToRemove();
        }
        if (hasPowerup(AbstractPowerup.INVINCIBLE)) {
            powerups.remove(AbstractPowerup.INVINCIBLE).setToRemove();
        }
        if (hasPowerup(AbstractPowerup.SHOPSHIELD)) {
            powerups.remove(AbstractPowerup.SHOPSHIELD).setToRemove();
        }
    }

    /**
     * Returns true if the player has a {@link AbstractPowerup} with the specified key.
     * 
     * @param key
     *            {@link String} - Key which specifies the type of powerup.
     * @return boolean - True iff the player has a {@link AbstractPowerup} with the specified key.
     */
    public boolean hasPowerup(String key) {
        return powerups.get(key) != null;
    }

    /**
     * Removes the {@link AbstractPowerup} specified by the key.
     * 
     * @param key
     *            {@link String} - the key to remove.
     * @return boolean - True iff the key is successfully removed.
     */
    public AbstractPowerup removePowerup(String key) {
        return powerups.remove(key);
    }

    /**
     * Adds the {@link AbstractPowerup} specified by the key.
     * 
     * @param key
     *            {@link String} - the key to add.
     * @param value
     *            {@link AbstractPowerup} - the powerup to put in the hasmap.
     */
    public void setPowerup(String key, AbstractPowerup value) {
        powerups.put(key, value);
    }

    /**
     * Gets the desired {@link AbstractPowerup} based on its key.
     * 
     * @param key
     *            {@link String} - the key associated with the {@link AbstractPowerup}.
     * @return {@link AbstractPowerup} - the desired {@link AbstractPowerup}.
     */
    public AbstractPowerup getPowerup(String key) {
        return powerups.get(key);
    }

    /**
     * Checks whether the player has the {@link InvinciblePowerup} powerup.
     * 
     * @return true if he does, false if not.
     */
    public boolean isInvincible() {
        return powerups.get(AbstractPowerup.INVINCIBLE) != null;
    }

    /**
     * Checks whether the player has the shield powerup.
     * 
     * @return true if the player has a shield, false if not.
     */
    public boolean hasShield() {
        return powerups.get(AbstractPowerup.SHIELD) != null;
    }

    /**
     * Checks whether the player has the shopshield powerup.
     *
     * @return true if the player has a shopshield, false if not.
     */
    public boolean hasShopShield() {
        return powerups.get(AbstractPowerup.SHOPSHIELD) != null;
    }

    /**
     * Sets the speed of the player to the default speed.
     */
    public void setDefaultSpeed() {
        speed = REGULAR_SPEED;
    }

    /**
     * sets the speed of this player.
     * 
     * @param newSpeed
     *            the new speed
     */
    public final void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * returns the speed of the player.
     * 
     * @return the current speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Checks whether the player is the first player.
     * 
     * @return true if he is player 1.
     */
    public boolean isFirstPlayer() {
        return this.firstPlayer;
    }

    /**
     * Sets the weapon of the player.
     * 
     * @param weapon
     *            {@link AbstractWeapon} - the Weapon to use.
     */
    public void setWeapon(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Returns the weapon of the player.
     * 
     * @return {@link AbstractWeapon} - The current weapon
     */
    public AbstractWeapon getWeapon() {
        return this.weapon;
    }

    /**
     * Get the amount of lives that the player has.
     * 
     * @return int - The amount of lives the player has left.
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Sets the lives that this {@link Player} has to the specified value.
     * 
     * @param lives
     *            int - The amount of lives to set.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Sets the score of this {@link Player} to the specified value.
     * 
     * @param score
     *            int - The amount of points to set as the score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the amount of money that this {@link Player} has to the specified value.
     * 
     * @param money
     *            int - The amount of money.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    /**
     * Get the score of the player.
     * 
     * @return int the current score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Removes a player life iff it has at least life remaining.
     */
    public void removeLife() {
        if (this.getLives() > 0) {
            this.lives--;
        }
    }

    /**
     * Get current animation for the player.
     *
     * <p>
     * When null, should draw its imageStill.
     * </p>
     * 
     * @return Animation of current animation of the player.
     */
    public Animation getAnimationCurrent() {
        return animationCurrent;
    }

    /**
     * Sets the current Animation of the player.
     * 
     * @param animationCurrent
     *            The new animation.
     */
    public void setAnimationCurrent(Animation animationCurrent) {
        this.animationCurrent = animationCurrent;
    }

    /**
     * Sets the state of this {@link Player} to determine whether his {@link AbstractWeapon} is a
     * shop weapon.
     * 
     * @param bool
     *            boolean - true iff the {@link Player} currently has a shop weapon.
     */
    @Deprecated
    public void setShopWeapon(boolean bool) {
        this.shopWeapon = bool;
    }

    /**
     * Gets the value of the shopWeapon field.
     * 
     * @return boolean - true iff the {@link Player} currently has a shop weapon.
     */
    @Deprecated
    public boolean isShopWeapon() {
        return this.shopWeapon;
    }

    /**
     * Sets the state of this {@link Player} to determine whether his speedUp is a shop perk.
     * 
     * @param bool
     *            boolean - true iff the {@link Player} currently has a shop speedUp.
     */
    @Deprecated
    public void setShopSpeed(boolean bool) {
        this.shopSpeedup = bool;
    }

    /**
     * Gets the value of the shopSpeed field.
     * 
     * @return boolean - true iff the {@link Player} currently has a shop speedUp.
     */
    @Deprecated
    public boolean isShopSpeed() {
        return shopSpeedup;
    }

    public int getRegularSpeed() {
        return REGULAR_SPEED;
    }

    @Override
    public Animation getAnimationLeft() {
        return this.animationLeft;
    }

    @Override
    public Animation getAnimationRight() {
        return this.animationRight;
    }
}
