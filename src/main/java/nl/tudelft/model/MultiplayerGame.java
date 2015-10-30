package nl.tudelft.model;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;
import nl.tudelft.settings.PlayerInput;
import nl.tudelft.settings.Settings;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class represents a multiplayer game with two players.
 */
public class MultiplayerGame extends AbstractGame {

    private Player firstPlayer;
    private Player secondPlayer;
    private final PlayerInput player1Input;
    private final PlayerInput player2Input;
    private final Settings settings = Settings.getInstance();

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     *
     * @param mainApp
     *            {@link StateBasedGame} - the mainApp that manages the states.
     * @param containerWidth
     *            int - width of the game field.
     * @param containerHeight
     *            int - height of the game field.
     * @param wrapper
     *            {@link ResourcesWrapper} - The resources that Game can inject into LevelFactory.
     * @param firstPlayer
     *            {@link Player} - the first player
     * @param secondPlayer
     *            {@link Player} - the second player
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public MultiplayerGame(StateBasedGame mainApp, int containerWidth, int containerHeight,
            ResourcesWrapper wrapper, Player firstPlayer, Player secondPlayer)
            throws IllegalArgumentException {
        super(mainApp, containerWidth, containerHeight, wrapper);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        player1Input = settings.getPlayer1Input();
        player2Input = settings.getPlayer2Input();
    }

    @Override
    public Player[] getPlayers() {
        return new Player[] { firstPlayer, secondPlayer };
    }

    @Override
    public void update(int delta) throws SlickException {
        player1Input.poll();
        player2Input.poll();

        super.update(delta);
    }

    /**
     * Decorates the specified player.
     * 
     * <p>
     * Performs checks to verify that only the player equal to the player in this game is
     * decorated.
     * </p>
     */
    @Override
    public void decoratePlayer(Player player, Player decorator) {
        if (player.equals(firstPlayer)) {
            settings.getPlayer1Input().removeListener(player);
            settings.getPlayer1Input().addListener(decorator);
            firstPlayer = decorator;
        } else if (player.equals(secondPlayer)) {
            settings.getPlayer2Input().removeListener(player);
            settings.getPlayer2Input().addListener(decorator);
            secondPlayer = decorator;
        }

    }
}
