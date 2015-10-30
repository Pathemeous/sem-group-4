package nl.tudelft.model;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;
import nl.tudelft.settings.PlayerInput;
import nl.tudelft.settings.Settings;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class represents a singleplayer game.
 */
public class SingleplayerGame extends AbstractGame {

    private Player player;
    private final PlayerInput player1Input;
    private final Settings settings = Settings.getInstance();

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
        player1Input = settings.getPlayer1Input();
    }

    @Override
    public Player[] getPlayers() {
        return new Player[] { player };
    }

    /**
     * Updates the player inputs necessary for all players of this game and calls the
     * {@link AbstractGame#update(int)}.
     */
    @Override
    public void update(int delta) throws SlickException {
        player1Input.poll();

        super.update(delta);
    }

    /**
     * Decorates the player in the game.
     * 
     * <p>
     * Does not perform any checks to verify that the specified player is the same as the player in
     * this game.
     * </p>
     */
    @Override
    public void decoratePlayer(Player player, Player decorator) {
        settings.getPlayer1Input().removeListener(player);
        settings.getPlayer1Input().addListener(decorator);
        this.player = decorator;
    }
}
