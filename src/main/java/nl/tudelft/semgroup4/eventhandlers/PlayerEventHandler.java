package nl.tudelft.semgroup4.eventhandlers;

import java.util.Observable;
import java.util.Observer;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput.PlayerEvent;
import nl.tudelft.semgroup4.logger.LogSeverity;

public class PlayerEventHandler implements Observer {

    private final Player player;

    /**
     * Creates a new {@link PlayerEventHandler} that will perform make its {@link Player} perform
     * all necessary actions whenever a relevant {@link PlayerEvent} is observed.
     * 
     * @param player
     *            {@link Player} - The Player that this EventHandler belongs to.
     */
    public PlayerEventHandler(Player player) {
        this.player = player;
    }

    @Override
    public void update(Observable observable, Object event) {
        if (event == PlayerEvent.LEFT) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the left");
            player.moveLeft();
        }
        if (event == PlayerEvent.RIGHT) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player moves to the right");
            player.moveRight();
        }
        if (event == PlayerEvent.SHOOT) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player shoots");
            player.fireWeapon();
        }
        if (event == PlayerEvent.STILL) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Player", "Player stops moving.");
            player.stopMoving();
        }
    }

}
