package nl.tudelft.settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Observable;
import java.util.Observer;

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
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        Observable wrongObservable = Mockito.mock(Observable.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(wrongObservable, null);
        defaultInstance.update(wrongObservable, false);
        defaultInstance.update(wrongObservable, true);
        Mockito.verifyZeroInteractions(mockedListener);
    }

    @Test
    public void testUpdateNullArgumentNoEvent() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedLeftKey, null);
        Mockito.verifyZeroInteractions(mockedListener);
    }

    @Test
    public void testUpdateLeftEvent() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedLeftKey, true);

        Mockito.verify(mockedListener).moveLeft();
        Mockito.verifyNoMoreInteractions(mockedListener);
    }

    @Test
    public void testUpdateRightEvent() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedRightKey, true);

        Mockito.verify(mockedListener).moveRight();
        Mockito.verifyNoMoreInteractions(mockedListener);
    }

    @Test
    public void testUpdateShootEvent() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedShootKey, true);

        Mockito.verify(mockedListener).shoot();
        Mockito.verifyNoMoreInteractions(mockedListener);
    }

    @Test
    public void testUpdateStillEventOnLeftKeyUp() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedLeftKey, false);

        Mockito.verify(mockedListener).stopMoving();
        Mockito.verifyNoMoreInteractions(mockedListener);
    }

    @Test
    public void testUpdateStillEventOnRightKeyUp() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedRightKey, false);

        Mockito.verify(mockedListener).stopMoving();
        Mockito.verifyNoMoreInteractions(mockedListener);
    }

    @Test
    public void testUpdateNoEventOnShootKeyUp() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);

        defaultInstance.update(mockedShootKey, false);
        Mockito.verifyZeroInteractions(mockedListener);
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
    
    @Test
    public void testRemoveListener() {
        PlayerInputListener mockedListener = Mockito.mock(PlayerInputListener.class);
        defaultInstance.addListener(mockedListener);
        
        defaultInstance.notifyLeftInput();
        Mockito.verify(mockedListener).moveLeft();
        
        defaultInstance.removeListener(mockedListener);

        defaultInstance.notifyLeftInput();
        Mockito.verifyNoMoreInteractions(mockedListener);
    }

}
