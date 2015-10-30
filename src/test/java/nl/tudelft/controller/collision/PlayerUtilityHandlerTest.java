package nl.tudelft.controller.collision;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.pickups.utility.AbstractUtility;
import nl.tudelft.model.player.Player;

import org.junit.Test;

public class PlayerUtilityHandlerTest {

    @Test
    public void testCollision() {
        DefaultPlayerInteractionMap map = new DefaultPlayerInteractionMap();
        AbstractGame mockedGame = mock(AbstractGame.class);
        AbstractUtility mockedUtility = mock(AbstractUtility.class);
        Player mockedPlayer = mock(Player.class);

        map.collide(mockedGame, mockedUtility, mockedPlayer);

        verify(mockedUtility, times(1)).activate(any());
    }
}
