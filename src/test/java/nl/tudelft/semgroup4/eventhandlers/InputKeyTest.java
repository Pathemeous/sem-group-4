package nl.tudelft.semgroup4.eventhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Observer;

import nl.tudelft.model.AbstractWall;
import nl.tudelft.model.RegularWall;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class InputKeyTest {

    private InputKey defaultKey;
    private static final int DEF_KEY = 0;
    private static final int DIFF_KEY = 1;
    private static Input defaultInput;

    /**
     * Saves the static input field so that it can be reset afterwards after potential
     * modification.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        defaultInput = InputKey.getInput();
    }

    /**
     * Sets up the default instances for each test.
     */
    @Before
    public void setUp() {
        defaultKey = new InputKey(DEF_KEY);
    }

    /**
     * Sets the static input field back to its default so that it ensures each tests starts clean.
     */
    @After
    public void tearDown() {
        InputKey.setInput(defaultInput);
    }

    @Test
    public void testHashCodeNotEqualWithDifferentObjects() {
        int firstHashCode = defaultKey.hashCode();
        InputKey secondObject = new InputKey(DIFF_KEY);
        assertNotEquals(firstHashCode, secondObject.hashCode());
    }

    @Test
    public void testHashCodeEqualOnSecondCall() {
        int firstHashCode = defaultKey.hashCode();
        assertEquals(firstHashCode, defaultKey.hashCode());
    }

    @Test
    public void testInputKey() {
        assertNotNull(defaultKey);
        assertEquals(0, defaultKey.getKeyCode());
    }

    @Test
    public void testPollWithNoInputGivesNoNotification() throws SlickException {
        Input mockedInput = Mockito.mock(Input.class);
        Mockito.when(mockedInput.isKeyDown(DEF_KEY)).thenReturn(false);
        InputKey.setInput(mockedInput);

        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultKey.addObserver(mockedObserver);

        defaultKey.poll();

        Mockito.verify(mockedObserver, Mockito.never()).update(Mockito.any(), Mockito.any());
    }

    @Test
    public void testPollOnKeyDownGivesNotification() throws SlickException {
        Input mockedInput = Mockito.mock(Input.class);
        Mockito.when(mockedInput.isKeyDown(DEF_KEY)).thenReturn(true);
        InputKey.setInput(mockedInput);

        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultKey.addObserver(mockedObserver);

        defaultKey.poll();

        Boolean expectedDownState = true;
        Mockito.verify(mockedObserver).update(defaultKey, expectedDownState);
    }

    @Test
    public void testPollOnKeyUpGivesNotification() throws SlickException {
        Input mockedInput = Mockito.mock(Input.class);
        Mockito.when(mockedInput.isKeyDown(DEF_KEY)).thenReturn(false);
        InputKey.setInput(mockedInput);
        Boolean downState = true;
        defaultKey.setDown(downState);

        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultKey.addObserver(mockedObserver);

        defaultKey.poll();

        Boolean expectedDownState = false;
        Mockito.verify(mockedObserver).update(defaultKey, expectedDownState);
    }

    @Test
    public void testEqualsWithSelf() {
        assertTrue(defaultKey.equals(defaultKey));
    }

    @Test
    public void testEqualsWithSameKeyCode() {
        InputKey keyWithDef = new InputKey(DEF_KEY);
        assertTrue(defaultKey.equals(keyWithDef));
    }

    @Test
    public void testEqualsNotWithDifferentKeyCode() {
        InputKey keyWithDiff = new InputKey(DIFF_KEY);
        assertFalse(defaultKey.equals(keyWithDiff));
    }

    @Test
    public void testEqualsNotWithDifferentObject() {
        Object someWall = new RegularWall(Mockito.mock(Image.class), 4, 5);
        assertFalse(defaultKey.equals(someWall));
    }

    @Test
    public void testGetKeyCode() {
        assertEquals(0, defaultKey.getKeyCode());
        InputKey keyWithDiff = new InputKey(15);
        assertEquals(15, keyWithDiff.getKeyCode());
    }

}
