package nl.tudelft.semgroup4.eventhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Observable;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput.PlayerEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PlayerEventHandlerTest {

    private Player mockedPlayer;
    private PlayerEventHandler defaultHandler;
    private Observable mockedObservable;

    @Before
    public void setUp() throws Exception {
        mockedPlayer = Mockito.mock(Player.class);
        defaultHandler = new PlayerEventHandler(mockedPlayer);
        mockedObservable = Mockito.mock(PlayerInput.class);
    }

    @Test
    public void testPlayerEventHandler() {
        assertEquals(mockedPlayer, defaultHandler.getPlayer());
        Player secondPlayer = Mockito.mock(Player.class);
        assertNotEquals(secondPlayer, defaultHandler.getPlayer());
    }

    @Test
    public void testUpdateLeftEvent() {
        defaultHandler.update(mockedObservable, PlayerEvent.LEFT);
        Mockito.verify(mockedPlayer).moveLeft();
    }

    @Test
    public void testUpdateRightEvent() {
        defaultHandler.update(mockedObservable, PlayerEvent.RIGHT);
        Mockito.verify(mockedPlayer).moveRight();
    }

    @Test
    public void testUpdateShootEvent() {
        defaultHandler.update(mockedObservable, PlayerEvent.SHOOT);
        Mockito.verify(mockedPlayer).fireWeapon();
    }

    @Test
    public void testUpdateStillEvent() {
        defaultHandler.update(mockedObservable, PlayerEvent.STILL);
        Mockito.verify(mockedPlayer).stopMoving();
    }

    @Test
    public void testUpdateNoEvent() {
        defaultHandler.update(mockedObservable, null);
        Mockito.verifyZeroInteractions(mockedPlayer);
    }

}
