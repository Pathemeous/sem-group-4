package nl.tudelft.model.bubble;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.controller.util.Helpers;
import nl.tudelft.model.Game;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 10/21/15.
 */
public class BacchelliBubble extends AbstractBubble {

    private int lives = 8;
    private int healthCounter;
    private final List<BacchelliBullet> bullets = new LinkedList<>();

    /**
     * Constructs a new instance of the {@link AbstractBubble} class.
     * <p>
     * A bubble will be initialized moving right by default. By calling goLeft right after
     * initialization, the bubble will start moving left.
     * </p>
     *
     * @param resources {@link ResourcesWrapper} - The resources that this object may use.
     * @param locX      float - The x-coordinate where the bubble should spawn.
     * @param locY      float - The y-coordinate where the bubble should spawn.
     */
    public BacchelliBubble(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImageBach(), locX, locY, resources);
    }

    /**
     * We hijack this call to prevent the bubble from splitting on every hit.
     * <p>
     * We might want a cleaner solution but this works.
     * </p>
     */
    @Override
    public void setIsHit() {
//        super.setIsHit();
        Game.LOGGER.log(LogSeverity.DEBUG, "BacchelliBubble", "bubble is hit.");
        
        healthCounter = 0;        
        lives--;

        if (lives == 0) {
            for (BacchelliBullet bullet : bullets) {
                bullet.setIsHit();
            }
            // we can now call super. The bubble is now actually 'hit'.
            super.setIsHit();
        }
    }

    @Override
    protected void move() {
//        super.move();

        setLocX(getLocX() + getHorizontalSpeed());

    }

    int shootCounter = 0;
    

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);

        int randInt = Helpers.randInt(0, 10);
        if (shootCounter > 30 && randInt == 8) {
            shootCounter = 0;
            boolean left = shootCounter % 2 == 0;
            float coordX = getLocX() + getWidth() * ((left ? 21.0f : 173.0f) / 200.0f);
            float coordY = getLocY() + getHeight() * ((left ? 70.0f : 74.0f) / 200.0f);

            BacchelliBullet bullet = new BacchelliBullet(getResources(), coordX, coordY);
            bullets.add(bullet);
            container.toAdd(bullet);
        }
        healthRegen();
        
        shootCounter++;
        healthCounter++;

    }

    private void healthRegen() {
        if (healthCounter == 150 && lives < 8) {
            System.out.println("boss gained health, now at: " + lives + "health");
            lives++;
        }        
    }

    @Override
    protected List<AbstractBubble> createNextBubbles() {
        return Collections.emptyList();
    }

    @Override
    protected float initMaxVerticalSpeed() {
        return 0.0f;
    }
}
