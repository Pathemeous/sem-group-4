package nl.tudelft.model;

import java.util.HashMap;

import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.weapon.RegularWeapon;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.util.SemRectangle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Player extends AbstractGameObject {

    // TODO: Remove magic numbers and at them to a general file for setup/config.
    private int score;
    private static final int BOUNDINGBOX_OFFSET_X = 10;
    private static final int BOUNDINGBOX_OFFSET_Y = 15;
    private static final int REGULAR_SPEED = 4;
    private static final int SPEEDUP = 2;
    private int speed;
    private int lives;
    private int fireCounter = 0;
    private final boolean firstPlayer;
    //private boolean speedup = false;
    private final Input input;
    private HashMap<String, Powerup> powerups = new HashMap<>();

    private Weapon weapon;

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
        speed = REGULAR_SPEED;
        lives = 3;
        score = 0;
        this.firstPlayer = isFirstPlayer;

        this.input = input;
        this.weapon = new RegularWeapon(0, 0);
        this.weapon.activate(this);

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
            Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the left");
            
            setAnimationCurrent(animationLeft);
            setLocX(locX - speed);
        }
        if ((input.isKeyDown(Input.KEY_RIGHT) && firstPlayer)
                || (input.isKeyDown(Input.KEY_D) && !firstPlayer)) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the right");
            
            setAnimationCurrent(animationRight);
            setLocX(locX + speed);
        }
        if ((input.isKeyDown(Input.KEY_SPACE) && firstPlayer)
                || (input.isKeyDown(Input.KEY_W) && !firstPlayer)) {
            if (fireCounter == 0) {
                Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player shoots");
                
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
        setWeapon(new RegularWeapon(0,0));
        this.weapon.activate(this);
    }

    /**
     * Removes all powerups from.
     */
    public void clearAllPowerups() {
        if (hasPowerup(Powerup.SPEED)) {
            powerups.remove(Powerup.SPEED).toRemove();
        }
        if (hasPowerup(Powerup.SHIELD)) {
            powerups.remove(Powerup.SHIELD).toRemove();
        }
        if (hasPowerup(Powerup.INVINCIBLE)) {
            powerups.remove(Powerup.INVINCIBLE).toRemove();
        }
    }

    public boolean hasPowerup(String key) {
        System.out.println("Key: "+key);
        System.out.println(powerups.get(key));
        return powerups.get(key) != null;
    }
    
    public Powerup removePowerup(String key) {
        return powerups.remove(key);
    }
    
    public void setPowerup(String key, Powerup value) {
        powerups.put(key, value);
    }
    
    public Powerup getPowerup(String key) {
        return powerups.get(key);
    }

    /**
     * Checks whether the player has the Invincible powerup.
     * 
     * @return true if he does, false if not.
     */
    public boolean isInvincible() {
        return powerups.get(Powerup.INVINCIBLE) != null;
    }

    /**
     * Checks whether the player has the shield powerup.
     * 
     * @return true if the player has a shield, false if not.
     */
    public boolean hasShield() {
        return powerups.get(Powerup.SHIELD) != null;
    }
    
    public void applySpeedup() {
        speed = REGULAR_SPEED * SPEEDUP;
    }
    
    public void setDefaultSpeed() {
        speed = REGULAR_SPEED;
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
    
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void setScore(int score) {
        this.score = score;
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
