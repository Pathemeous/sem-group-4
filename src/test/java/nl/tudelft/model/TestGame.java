package nl.tudelft.model;

import nl.tudelft.semgroup4.resources.ResourceWrapper;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by justin on 07/10/15.
 */
public class TestGame extends Game {

    private final Player player;

    public TestGame(StateBasedGame mainApp,
                    int containerWidth, int containerHeight,
                    ResourceWrapper wrapper,
                    Player player) throws IllegalArgumentException {
        super(mainApp, containerWidth, containerHeight, wrapper);
        this.player = player;
    }

    @Override
    public Player[] getPlayers() {
        return new Player[]{player};
    }

}
