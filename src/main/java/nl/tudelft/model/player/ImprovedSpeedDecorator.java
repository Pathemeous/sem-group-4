package nl.tudelft.model.player;

public class ImprovedSpeedDecorator extends AbstractPlayerDecorator {

    public ImprovedSpeedDecorator(PlayerInterface wrappedObject) {
        super(wrappedObject);
    }

    /**
     * Returns the regular speed times two.
     * 
     * @return int - the total speed of the player.
     */
    @Override
    public int getSpeed() {
        return wrappedObject.getRegularSpeed() * 2;
    }
}
