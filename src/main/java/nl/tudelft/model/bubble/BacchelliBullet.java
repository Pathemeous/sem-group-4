package nl.tudelft.model.bubble;

import nl.tudelft.controller.resources.ResourcesWrapper;
import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by justin on 10/21/15.
 */
public class BacchelliBullet extends AbstractBubble {

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
    public BacchelliBullet(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getBubbleImage1(), locX, locY, resources);
        setHorizontalSpeed(0.0f);
    }

    @Override
    protected List<AbstractBubble> createNextBubbles() {
        return Collections.emptyList();
    }

    @Override
    protected float initMaxVerticalSpeed() {
        return 10.0f;
    }
}
