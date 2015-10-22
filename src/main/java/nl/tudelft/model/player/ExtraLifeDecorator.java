package nl.tudelft.model.player;

/**
 * Adds one life to the player.
 */
public class ExtraLifeDecorator extends AbstractPlayerDecorator {

    public ExtraLifeDecorator(PlayerInterface wrappedObject) {
        super(wrappedObject);
    }

    /**
     * Adds 1 life to the {@link PlayerInterface#getLives()} method, so that it will always return
     * one more life.
     */
    @Override
    public int getLives() {
        return wrappedObject.getLives() + 1;
    }

}
