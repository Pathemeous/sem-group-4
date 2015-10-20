package nl.tudelft.controller.eventhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Observable;
import java.util.Observer;

import nl.tudelft.settings.InputKey;
import nl.tudelft.settings.PlayerInput;
import nl.tudelft.settings.PlayerInput.PlayerEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.SlickException;

public class PlayerInputTest {

    private InputKey mockedLeftKey;
    private InputKey mockedRightKey;
    private InputKey mockedShootKey;
    private PlayerInput defaultInstance;

    /**
     * Sets up all dependencies and default instances.
     */
    @Before
    public void setUp() {
        mockedLeftKey = Mockito.mock(InputKey.class);
        mockedRightKey = Mockito.mock(InputKey.class);
        mockedShootKey = Mockito.mock(InputKey.class);
        defaultInstance = new PlayerInput(mockedLeftKey, mockedRightKey, mockedShootKey);
    }

    @Test
    public void testPlayerInput() {
        assertNotNull(defaultInstance);
        assertEquals(mockedLeftKey, defaultInstance.getLeftInput());
        assertEquals(mockedRightKey, defaultInstance.getRightInput());
        assertEquals(mockedShootKey, defaultInstance.getShootInput());
    }

    @Test
    public void testUpdateWrongObservableNoEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        Observable wrongObservable = Mockito.mock(InputKey.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(wrongObservable, null);
        defaultInstance.update(wrongObservable, false);
        defaultInstance.update(wrongObservable, true);
        Mockito.verify(mockedObserver, Mockito.never()).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void testUpdateNullArgument() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedLeftKey, null);
        Mockito.verify(mockedObserver, Mockito.never()).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void testUpdateLeftEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedLeftKey, true);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.LEFT);
    }

    @Test
    public void testUpdateRightEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedRightKey, true);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.RIGHT);
    }

    @Test
    public void testUpdateShootEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedShootKey, true);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.SHOOT);
    }

    @Test
    public void testUpdateStillEventOnLeftKeyUp() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedLeftKey, false);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.STILL);
    }

    @Test
    public void testUpdateStillEventOnRightKeyUp() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedRightKey, false);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.STILL);
    }

    @Test
    public void testUpdateNoEventOnShootKeyUp() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedShootKey, false);
        Mockito.verify(mockedObserver, Mockito.never()).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void testPoll() throws SlickException {
        defaultInstance.poll();
        Mockito.verify(mockedLeftKey).poll();
        Mockito.verify(mockedRightKey).poll();
        Mockito.verify(mockedShootKey).poll();
    }

    @Test
    public void testSetLeftInput() {
        InputKey newInput = Mockito.mock(InputKey.class);
        defaultInstance.setLeftInput(newInput);
        assertEquals(newInput, defaultInstance.getLeftInput());
        Mockito.verify(mockedLeftKey).deleteObserver(defaultInstance);
        Mockito.verify(newInput).addObserver(defaultInstance);

    }

    @Test
    public void testSetRightInput() {
        InputKey newInput = Mockito.mock(InputKey.class);
        defaultInstance.setRightInput(newInput);
        assertEquals(newInput, defaultInstance.getRightInput());
        Mockito.verify(mockedRightKey).deleteObserver(defaultInstance);
        Mockito.verify(newInput).addObserver(defaultInstance);
    }

    @Test
    public void testSetShootInput() {
        InputKey newInput = Mockito.mock(InputKey.class);
        defaultInstance.setShootInput(newInput);
        assertEquals(newInput, defaultInstance.getShootInput());
        Mockito.verify(mockedShootKey).deleteObserver(defaultInstance);
        Mockito.verify(newInput).addObserver(defaultInstance);
    }

    @Test
    public void testGetLeftInput() {
        assertEquals(mockedLeftKey, defaultInstance.getLeftInput());

    }

    @Test
    public void testGetRightInput() {
        assertEquals(mockedRightKey, defaultInstance.getRightInput());
    }

    @Test
    public void testGetShootInput() {
        assertEquals(mockedShootKey, defaultInstance.getShootInput());
    }

}
