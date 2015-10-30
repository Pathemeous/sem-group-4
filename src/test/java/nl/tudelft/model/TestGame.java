package nl.tudelft.model;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by justin on 07/10/15.
 */
public class TestGame extends AbstractGame {

    private Player player;

    public TestGame(StateBasedGame mainApp, int containerWidth, int containerHeight,
            ResourcesWrapper wrapper, Player player) throws IllegalArgumentException {
        super(mainApp, containerWidth, containerHeight, wrapper);
        this.player = player;
    }

    @Override
    public Player[] getPlayers() {
        return new Player[] { player };
    }

    /**
     * Empty implementation as this is not needed for testing.
     */
    @Override
    public void decoratePlayer(Player player, Player decorator) {}
}
