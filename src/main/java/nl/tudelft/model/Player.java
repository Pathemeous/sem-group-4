package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

    // TODO: Remove magic numbers and at them to a general file for setup/config.
    private int score = 0;
    private int lives = 3;
    private int counter = 0;
    private final Input input;

    private Weapon weapon;

    private final Image imageLeft;
    private final Image imageRight;
    private final Image imageStill;

    /**
     * Constructor for the Player class.
     * 
     * @param image
     *            Image - The image for standing still.
     * @param imageLeft
     *            Image - The image for moving left.
     * @param imageRight
     *            Image - The image for moving right.
     * @param locX
     *            int - The x-coordinate where the player should spawn.
     * @param locY
     *            int - The y-coordinate where the player should spawn.
     * @param input
     *            Input - The input to enable the user to move.
     * @param weapon
     *            Weapon - the default Weapon object to start off with.
     */
    public Player(Image image, Image imageLeft, Image imageRight, int locX, int locY, Input input,
            Weapon weapon) {
        super(image, locX, locY);
        this.input = input;
        this.weapon = weapon;

        this.imageStill = image;
        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (input.isKeyDown(Input.KEY_LEFT)) {
            setImage(imageLeft);
            setLocX((int) (getBounds().getX() - 4));
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            setImage(imageRight);
            setLocX((int) (getBounds().getX() + 4));
        }
        if (input.isKeyDown(Input.KEY_SPACE) && counter == 0) {
            counter++;
            weapon.fire((int)this.locX, (int)this.locY, this.getWidth());
        }
        if (!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))) {
            setImage(imageStill);
        }
        counter = (counter <= 10 && counter != 0) ? counter+1 : (counter > 10) ? 0 : counter;
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
}
