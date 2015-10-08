package nl.tudelft.semgroup4.eventhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Observer;

import nl.tudelft.model.Game;
import nl.tudelft.model.Wall;
import nl.tudelft.semgroup4.Modifiable;

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
    public void testUpdateWithNoInputGivesNoNotification() throws SlickException {
        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultKey.addObserver(mockedObserver);

        Modifiable container = Mockito.mock(Game.class);
        defaultKey.update(container, 15);

        Mockito.verify(mockedObserver, Mockito.never()).update(defaultKey, null);
    }

    @Test
    public void testUpdateWithMockedInputGivesNotification() throws SlickException {
        Input mockedInput = Mockito.mock(Input.class);
        Mockito.when(mockedInput.isKeyPressed(DEF_KEY)).thenReturn(true);
        InputKey.setInput(mockedInput);

        Observer mockedObserver = Mockito.mock(PlayerInput.class);
        defaultKey.addObserver(mockedObserver);

        Modifiable container = Mockito.mock(Game.class);
        defaultKey.update(container, 15);

        Mockito.verify(mockedObserver).update(defaultKey, null);
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
        Object someWall = new Wall(Mockito.mock(Image.class), 4, 5);
        assertFalse(defaultKey.equals(someWall));
    }

    @Test
    public void testGetKeyCode() {
        assertEquals(0, defaultKey.getKeyCode());
        InputKey keyWithDiff = new InputKey(15);
        assertEquals(15, keyWithDiff.getKeyCode());
    }

}