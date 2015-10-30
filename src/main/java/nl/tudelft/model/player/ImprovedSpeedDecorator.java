package nl.tudelft.model.player;

import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.model.AbstractGame;

public class ImprovedSpeedDecorator extends AbstractPlayerDecorator {

    public ImprovedSpeedDecorator(Player wrappedObject) {
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

    @Override
    public void moveLeft() {
        wrappedObject.setAnimationCurrent(wrappedObject.getAnimationLeft());
        setLocX(getLocX() - getSpeed());
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the left");
    }

    /**
     * Moves the {@link Player} right according to its speed and sets the correct animation.
     */
    @Override
    public void moveRight() {
        wrappedObject.setAnimationCurrent(wrappedObject.getAnimationRight());
        setLocX(getLocX() + getSpeed());
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the right");
    }
}
