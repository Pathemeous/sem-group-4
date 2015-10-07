package nl.tudelft.model;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by justin on 07/10/15.
 */
public class MultiplayerGame extends Game {

    private final Player firstPlayer;
    private final Player secondPlayer;

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     *
     * @param mainApp         {@link StateBasedGame} - the mainApp that manages the states.
     * @param containerWidth  int - width of the game field.
     * @param containerHeight int - height of the game field.
     * @param wrapper         {@link ResourcesWrapper} - The resources that Game
     *                                                can inject into LevelFactory.
     * @param firstPlayer     {@link Player} - the first player
     * @param secondPlayer    {@link Player} - the second player
     * @throws IllegalArgumentException - If <code>levels</code> or <code>players</code> is empty.
     */
    public MultiplayerGame(
            StateBasedGame mainApp,
            int containerWidth, int containerHeight,
            ResourcesWrapper wrapper,
            Player firstPlayer, Player secondPlayer) throws IllegalArgumentException {
        super(mainApp, containerWidth, containerHeight, wrapper);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override
    public Player[] getPlayers() {
        return new Player[]{
            firstPlayer,
            secondPlayer
        };
    }
}
