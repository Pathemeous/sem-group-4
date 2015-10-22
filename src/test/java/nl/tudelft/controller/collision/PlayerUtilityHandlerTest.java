package nl.tudelft.controller.collision;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.player.Player;

import org.junit.Test;

public class PlayerUtilityHandlerTest {

    @Test
    public void testCollision() {
        DefaultPlayerInteractionMap map = new DefaultPlayerInteractionMap();
        Game mockedGame = mock(Game.class);
        Utility mockedUtility = mock(Utility.class);
        Player mockedPlayer = mock(Player.class);

        map.collide(mockedGame, mockedUtility, mockedPlayer);

        verify(mockedUtility, times(1)).activate(any());
    }
}
