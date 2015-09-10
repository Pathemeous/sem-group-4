package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.pickups.Powerup;
import nl.tudelft.model.pickups.Powerup.PowerType;
import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

    // TODO: Remove magic numbers and at them to a general file for setup/config.
    private int score = 0;
    private int speed;
    private int lives = 3;
    private int counter = 0;
    private int invincibilityCounter = 0;
    private int speedupCounter = 0;
    private boolean hasSpeedup = false;
    private final Input input;

    private Weapon weapon;
    
    private LinkedList<Powerup> powerups;
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
     * @param weapon
     *            Weapon - the default Weapon object to start off with.
     */
    public Player(int locX, int locY, Input input,
            Weapon weapon) {
        super(Resources.playerImageStill.copy(), locX, locY);
		powerups = new LinkedList<>();
		speed = 4;

        this.input = input;
        this.weapon = weapon;

        this.animationCurrent = null;
        this.animationLeft = Resources.playerWalkLeft;
        this.animationRight = Resources.playerWalkRight;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Animation curAnimation = getAnimationCurrent();
        if (curAnimation == null) {
            g.drawImage(getImage(), getLocX(), getLocY());
        } else {
            g.drawAnimation(curAnimation, getLocX(), getLocY());
        }
        if (hasShield()) {
            g.drawImage(Resources.power_shield, getLocX(), getLocY());
        } else if (isInvincible()) {
            g.drawImage(Resources.power_invincible, getLocX(), getLocY());
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        if (input.isKeyDown(Input.KEY_LEFT)) {
            setAnimationCurrent(animationLeft);
            setLocX((int) (getBounds().getX() - 4));
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            setAnimationCurrent(animationRight);
            setLocX((int) (getBounds().getX() + 4));
        }
        if (input.isKeyDown(Input.KEY_SPACE) && counter == 0) {
            counter++;
            weapon.fire(container, (int)this.locX, (int)this.locY, this.getWidth(), this.getHeight());
        }
        if (!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))) {
            setAnimationCurrent(null);
        }
        
        this.weapon.update(container, delta);
        
        counter = (counter <= 10 && counter != 0) ? counter+1 : 0;
        
        
        invincibilityCounter = (invincibilityCounter <= 600 && invincibilityCounter != 0) 
        		? invincibilityCounter+1 : (invincibilityCounter > 600) ? 0 : invincibilityCounter;
        
        if(invincibilityCounter == 600) {
        	removeInvicibility();
        	invincibilityCounter = 0;
        }
        
        speedupCounter = (speedupCounter <= 600 && speedupCounter != 0) 
        		? speedupCounter+1 : (speedupCounter > 600) ? 0 : speedupCounter;
        
        if(speedupCounter == 600) {
        	speed = (int)(0.5*speed);
        	speedupCounter = 0;
        	hasSpeedup = false;
        }
    }
    
    public void addPowerup(Powerup up) {
    	switch(up.getPowerType()) {
    	case SHIELD:
    		if(!isInvincible() && !hasShield()) {
    			powerups.add(up);
    		}
    		break;
    	case INVINCIBLE:
    		if(hasShield()) {
    			removeShield();
    		}
    		invincibilityCounter = 1;
    		if(isInvincible()) {
    			removeInvicibility();
    		}
    		powerups.add(up);
    		break;
    	case SPEEDUP:
    		speedupCounter = 1;
    		speed = 2*speed;
    		hasSpeedup = true;
    		break;
    	case POINTS:
    		score += 100;
    		break;
    	}
    }
    
    public boolean isInvincible() {
    	for(Powerup up : powerups) {
    		if(up.getPowerType() == PowerType.INVINCIBLE) 
    			return true;
    	}
    	return false;
    }
    
    private void removeInvicibility() {
    	powerups.remove(getInvincibility());
    }
    
    private Powerup getInvincibility() {
    	for(Powerup up : powerups) {
    		if(up.getPowerType() == PowerType.INVINCIBLE) 
    			return up;
    	}
    	return null;
    }
    
    public boolean hasShield() {
    	for(Powerup up : powerups) {
    		if(up.getPowerType() == PowerType.SHIELD) 
    			return true;
    	}
    	return false;
    }
    
    private Powerup getShield() {
    	for(Powerup up : powerups) {
    		if(up.getPowerType() == PowerType.SHIELD) 
    			return up;
    	}
    	return null;
    }
    
    public boolean hasSpeedup() {
    	for(Powerup up : powerups) {
    		if(up.getPowerType() == PowerType.SPEEDUP) 
    			return true;
    	}
    	return false;
    }
    
    public void removeShield() {
    	powerups.remove(getShield());
    }
    
    public void setWeapon(Weapon weapon) {
    	this.weapon = weapon;
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
     * When null, should draw its imageStill.
     * @return Animation of current animation of the player.
     */
    public Animation getAnimationCurrent() {
        return animationCurrent;
    }

    /**
     * Sets the current Animation of the player.
     * @param animationCurrent The new animation.
     */
    public void setAnimationCurrent(Animation animationCurrent) {
        this.animationCurrent = animationCurrent;
    }
}
