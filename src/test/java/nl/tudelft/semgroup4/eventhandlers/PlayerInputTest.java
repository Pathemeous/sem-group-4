package nl.tudelft.semgroup4.eventhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Observable;
import java.util.Observer;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput.PlayerEvent;

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
    public void testUpdateObservableObjectNoEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        Observable wrongObservable = Mockito.mock(InputKey.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(wrongObservable, null);
        Mockito.verify(mockedObserver, Mockito.never()).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void testUpdateObservableObjectLeftEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedLeftKey, null);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.LEFT);
    }

    @Test
    public void testUpdateObservableObjectRightEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedRightKey, null);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.RIGHT);
    }

    @Test
    public void testUpdateObservableObjectShootEvent() {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultInstance.addObserver(mockedObserver);

        defaultInstance.update(mockedShootKey, null);
        Mockito.verify(mockedObserver).update(defaultInstance, PlayerEvent.SHOOT);
    }

    @Test
    public void testUpdateTInt() throws SlickException {
        Modifiable container = Mockito.mock(Game.class);
        defaultInstance.update(container, 15);
        Mockito.verify(mockedLeftKey).update(container, 15);
        Mockito.verify(mockedRightKey).update(container, 15);
        Mockito.verify(mockedShootKey).update(container, 15);
    }

    @Test
    public void testSetLeftInput() {
        InputKey newInput = Mockito.mock(InputKey.class);
        defaultInstance.setLeftInput(newInput);
        assertEquals(newInput, defaultInstance.getLeftInput());
    }

    @Test
    public void testSetRightInput() {
        InputKey newInput = Mockito.mock(InputKey.class);
        defaultInstance.setRightInput(newInput);
        assertEquals(newInput, defaultInstance.getRightInput());
    }

    @Test
    public void testSetShootInput() {
        InputKey newInput = Mockito.mock(InputKey.class);
        defaultInstance.setShootInput(newInput);
        assertEquals(newInput, defaultInstance.getShootInput());
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
