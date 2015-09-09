package nl.tudelft.model;

import nl.tudelft.semgroup4.Resources;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

    // TODO: Remove magic numbers and at them to a general file for setup/config.
    private int score = 0;
    private int lives = 3;
    private int counter = 0;
    private final Input input;

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
     * @param weapon
     *            Weapon - the default Weapon object to start off with.
     */
    public Player(int locX, int locY, Input input,
            Weapon weapon) {
        super(Resources.playerImageStill.copy(), locX, locY);
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
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
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
            weapon.fire((int)this.locX, (int)this.locY, this.getWidth(), this.getHeight());
        }
        if (!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))) {
            setAnimationCurrent(null);
        }
        counter = (counter <= 30 && counter != 0) ? counter+1 : (counter > 30) ? 0 : counter;
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
