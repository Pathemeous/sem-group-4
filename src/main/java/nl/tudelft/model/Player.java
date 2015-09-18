package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Powerup;
import nl.tudelft.model.pickups.Powerup.PowerType;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.util.SemRectangle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Player extends GameObject {

    // TODO: Remove magic numbers and at them to a general file for setup/config.
    private int score = 0;
    private static final int BOUNDINGBOX_OFFSET_X = 10;
    private static final int BOUNDINGBOX_OFFSET_Y = 15;
    private static final int REGULAR_SPEED = 4;
    private static final int SPEEDUP = 2;
    private int speed = REGULAR_SPEED;
    private int lives = 3;
    private int fireCounter = 0;
    private int removingShieldCounter = 0;
    private int invincibilityCounter = 0;
    private int speedupCounter = 0;
    private final boolean firstPlayer;
    private boolean speedup = false;
    private final Input input;

    private Weapon weapon;

    private final LinkedList<Powerup> powerups;
    private Animation animationCurrent;
    private final Animation animationLeft;
    private final Animation animationRight;

    /**
     * Constructor for the Player class.
     * 
     * @param locX
     *            int - The x-coordinate where the player should spawn.
     * @param locY
     *            int - The y-coordinate where the player should spawn.
     * @param input
     *            Input - The input to enable the user to move.
     * @param isFirstPlayer
     *            boolean - checks whether the player is number one or two.
     */
    public Player(int locX, int locY, Input input, boolean isFirstPlayer) {
        super(Resources.playerImageStill.copy(), locX, locY);
        powerups = new LinkedList<>();
        speed = 4;
        this.firstPlayer = isFirstPlayer;

        this.input = input;
        this.weapon = new Weapon(Resources.weaponImageRegular.copy(), Pickup.WeaponType.REGULAR);
        this.weapon.setPlayer(this);

        this.animationCurrent = null;
        this.animationLeft = Resources.playerWalkLeft;
        this.animationRight = Resources.playerWalkRight;
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        Animation curAnimation = getAnimationCurrent();
        if (curAnimation == null) {
            graphics.drawImage(getImage(), getLocX(), getLocY());
        } else {
            graphics.drawAnimation(curAnimation, getLocX(), getLocY());
        }
        if (hasShield()) {
            if (removingShieldCounter % 2 == 0) {
                // g.drawImage(Resources.power_shield, getLocX(), getLocY());
                graphics.setColor(Color.yellow);
                graphics.draw(getBounds());
            }
        } else if (isInvincible()) {
            if ((invincibilityCounter > 540 && invincibilityCounter % 2 == 0)
                    || invincibilityCounter < 540) {
                graphics.drawImage(Resources.powerInvincible, getLocX(), getLocY());
            }
        }
        graphics.setColor(Color.green);
    }

    @Override
    public Shape getBounds() {
        return new SemRectangle(locX + BOUNDINGBOX_OFFSET_X, locY + BOUNDINGBOX_OFFSET_Y,
                getImage().getWidth() - (2 * BOUNDINGBOX_OFFSET_X), getImage().getHeight()
                        - BOUNDINGBOX_OFFSET_Y);
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if (getLives() == 0) {
            container.toRemove(this);
        }

        if ((input.isKeyDown(Input.KEY_LEFT) && firstPlayer)
                || (input.isKeyDown(Input.KEY_A) && !firstPlayer)) {
            setAnimationCurrent(animationLeft);
            setLocX(locX - speed);
        }
        if ((input.isKeyDown(Input.KEY_RIGHT) && firstPlayer)
                || (input.isKeyDown(Input.KEY_D) && !firstPlayer)) {
            setAnimationCurrent(animationRight);
            setLocX(locX + speed);
        }
        if ((input.isKeyDown(Input.KEY_SPACE) && firstPlayer)
                || (input.isKeyDown(Input.KEY_W) && !firstPlayer)) {
            if (fireCounter == 0) {
                fireCounter++;
                weapon.fire(container, (int) this.locX, (int) this.locY, this.getWidth(),
                        this.getHeight());
            }
        }
        if ((!(input.isKeyDown(Input.KEY_LEFT)
                || input.isKeyDown(Input.KEY_RIGHT)) && firstPlayer)
                || (!(input.isKeyDown(Input.KEY_A)
                || input.isKeyDown(Input.KEY_D)) && !firstPlayer)) {
            setAnimationCurrent(null);
        }

        fireCounter = (fireCounter <= 10 && fireCounter != 0) ? fireCounter + 1 : 0;

        invincibilityCounter = (invincibilityCounter <= 600 && invincibilityCounter != 0)
                ? invincibilityCounter + 1
                : (invincibilityCounter > 600) ? 0 : invincibilityCounter;

        if (invincibilityCounter == 600) {
            removeInvincibility();
        }

        speedupCounter = (speedupCounter <= 600 && speedupCounter != 0) ? speedupCounter + 1
                : (speedupCounter > 600) ? 0 : speedupCounter;

        if (speedupCounter == 600) {
            removeSpeedup();
        }

        removingShieldCounter = (removingShieldCounter <= 120 && removingShieldCounter != 0)
                ? removingShieldCounter + 1
                : (removingShieldCounter > 600) ? 0 : removingShieldCounter;

        if (removingShieldCounter == 120) {
            removeShield();
            removingShieldCounter = 0;
        }
    }

    /**
     * Resets the player state to reflect the clean start of a level.
     * 
     * <p>
     * This means that a player loses all his powerups, his weapons and makes sure that the weapon
     * firedelay is set to zero.
     * </p>
     */
    public void reset() {
        clearAllPowerups();
        fireCounter = 0;
        setWeapon(new Weapon(Resources.weaponImageRegular.copy(), Pickup.WeaponType.REGULAR));
        this.weapon.setPlayer(this);
    }

    /**
     *  Removes the speedup from the player.
     */
    public void removeSpeedup() {
        speed = REGULAR_SPEED;
        speedupCounter = 0;
        speedup = false;
    }

    /**
     * Removes all powerups from.
     */
    public void clearAllPowerups() {
        removeSpeedup();
        removeInvincibility();
        removeShield();
    }

    /**
     * This methods returns the current list of powerups.
     * @return the current powerups
     */
    public LinkedList<Powerup> getPowerups() {
        return powerups;
    }

    /**
     * Give the player the powerup.
     * 
     * @param up
     *            Powerup - the powerup to give to the player.
     * @throws IllegalArgumentException
     *             - If the powerup type is incorrect.
     */
    public void addPowerup(Powerup up) throws IllegalArgumentException {
        switch (up.getPowerType()) {
            case SHIELD:
                if (!isInvincible() && !hasShield()) {
                    powerups.add(up);
                }
                break;
            case INVINCIBLE:
                if (hasShield()) {
                    removeShield();
                }
                if (isInvincible()) {
                    removeInvincibility();
                }
                invincibilityCounter = 1;
                powerups.add(up);
                break;
            case SPEEDUP:
                speedupCounter = 1;
                if (!speedup) {
                    speed = SPEEDUP * speed;
                    speedup = true;
                }
                break;
            case POINTS:
                score += 100;
                break;
            case LIFE:
                if (lives < 9) {
                    lives++;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Checks whether the player has the Invincible powerup.
     * 
     * @return true if he does, false if not.
     */
    public boolean isInvincible() {
        for (Powerup up : powerups) {
            if (up.getPowerType() == PowerType.INVINCIBLE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove sthe Invincibile powerup from the player.
     */
    private void removeInvincibility() {
        powerups.remove(getInvincibility());
        invincibilityCounter = 0;
    }

    /**
     * Returns the Invincibility powerup object from the player.
     * 
     * @return Powerup - the Invincibility object.
     */
    private Powerup getInvincibility() {
        for (Powerup up : powerups) {
            if (up.getPowerType() == PowerType.INVINCIBLE) {
                return up;
            }
        }
        return null;
    }

    /**
     * Checks whether the player has the shield powerup.
     * 
     * @return true if the player has a shield, false if not.
     */
    public boolean hasShield() {
        for (Powerup up : powerups) {
            if (up.getPowerType() == PowerType.SHIELD) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the Shield powerup object from the player.
     * 
     * @return Powerup - the Shield powerup.
     */
    private Powerup getShield() {
        for (Powerup up : powerups) {
            if (up.getPowerType() == PowerType.SHIELD) {
                return up;
            }
        }
        return null;
    }

    /**
     * Checks whether the player has the Speedup powerup.
     * 
     * @return boolean - true if the player has the speedup, false if not.
     */
    public boolean hasSpeedup() {
        for (Powerup up : powerups) {
            if (up.getPowerType() == PowerType.SPEEDUP) {
                return true;
            }
        }
        return false;
    }
    /**
     * sets the speed of this player.
     * @param newSpeed the new speed
     */
    public final void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }
    /**
     * returns the speed of the player.
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
     * returns the powerups of the player.
     * @return powerups
     */
    public final LinkedList<Powerup> getPowerUps() {
        return powerups;
    }

    /**
     * Disables the shield.
     */
    public void setShieldInactive() {
        removingShieldCounter = 1;
    }

    /**
     * returns whether the shield is being removed.
     *
     * @return boolean - True if the shield is being removed, false if not.
     */
    public boolean removingShield() {
        return removingShieldCounter != 0;
    }

    /**
     * Removes the shield from the player's powerups.
     */
    private void removeShield() {
        powerups.remove(getShield());
    }

    /**
     * Sets the weapon of the player.
     * @param weapon
     *            Weapon - the Weapon to use.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    /**
     * Returns the weapon of the player.
     * @return the current weapon
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Get the amount of lives that the player has.
     * 
     * @return int the amount of lives the player has left.
     */
    public int getLives() {
        return this.lives;
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
        if (this.lives > 0) {
            this.lives--;
        }
    }

    /**
     * Adds points to the player score.
     * 
     * @param points
     *            int - The amount of points that should be added to the player score.
     */
    public void addScore(int points) {
        this.score += points;
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
     * retruns the firecoutner of the player.
     * @return firecounter
     */
    public final int getFireCounter() {
        return this.fireCounter;
    }
    
    /**
     * sets the firecounter of this player.
     * @param newCounter the new counter
     */
    public final void setFireCounter(int newCounter) {
        this.fireCounter = newCounter;
    }
}
