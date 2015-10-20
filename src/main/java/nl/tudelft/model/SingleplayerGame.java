package nl.tudelft.model;

import nl.tudelft.controller.Settings;
import nl.tudelft.controller.eventhandlers.PlayerInput;
import nl.tudelft.controller.resources.ResourcesWrapper;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by justin on 07/10/15.
 */
public class SingleplayerGame extends Game {

    private final Player player;
    private final PlayerInput player1Input;

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     *
     * @param mainApp
     *            {@link StateBasedGame} - the mainApp that manages the states.
     * @param player
     *            {@link Player} - the player that take part in this game.
     * @param containerWidth
     *            int - width of the game field.
     * @param containerHeight
     *            int - height of the game field.
     * @param wrapper
     *            {@link ResourcesWrapper} - The resources that Game can inject into LevelFactory.
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public SingleplayerGame(StateBasedGame mainApp, int containerWidth, int containerHeight,
            ResourcesWrapper wrapper, Player player) throws IllegalArgumentException {
        super(mainApp, containerWidth, containerHeight, wrapper);
        this.player = player;
        player1Input = Settings.getPlayer1Input();
    }

    @Override
    public Player[] getPlayers() {
        return new Player[] { player };
    }
    
    @Override
    public void update(int delta) throws SlickException {
        super.update(delta);

        player1Input.poll();
    }
}
