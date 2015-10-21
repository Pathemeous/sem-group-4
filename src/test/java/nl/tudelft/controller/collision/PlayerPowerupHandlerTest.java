package nl.tudelft.controller.collision;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.pickups.powerup.Powerup;
import org.junit.Test;

public class PlayerPowerupHandlerTest {

    @Test
    public void testCollision() {
        DefaultPlayerInteractionMap map = new DefaultPlayerInteractionMap();
        Game mockedGame = mock(Game.class);
        Powerup mockedPowerup = mock(Powerup.class);
        Player mockedPlayer = mock(Player.class);

        map.collide(mockedGame, mockedPowerup, mockedPlayer);

        verify(mockedPowerup, times(1)).activate(any());
    }
}
