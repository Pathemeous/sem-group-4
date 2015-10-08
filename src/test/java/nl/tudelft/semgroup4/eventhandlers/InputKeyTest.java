package nl.tudelft.semgroup4.eventhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import nl.tudelft.model.Game;
import nl.tudelft.model.Wall;
import nl.tudelft.semgroup4.Modifiable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class InputKeyTest {

    private InputKey defaultKey;
    private static final int DEF_KEY = 0;
    private static final int DIFF_KEY = 1;

    @Before
    public void setUp() throws Exception {
        defaultKey = new InputKey(DEF_KEY);
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
        Modifiable container = Mockito.mock(Game.class);
        defaultKey.update(container, 15);
        assertFalse(defaultKey.hasChanged());
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
