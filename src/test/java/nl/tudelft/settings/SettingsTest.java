package nl.tudelft.settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SettingsTest {

    private PlayerInput mockedInput;
    private InputKey mockedKey;
    private Settings settings;

    /**
     * Mock all dependencies.
     */
    @Before
    public void setUp() {
        Settings.init();
        mockedInput = Mockito.mock(PlayerInput.class);
        mockedKey = Mockito.mock(InputKey.class);
        settings = Settings.getInstance();
    }

    @Test
    public void testInitNoException() {
        Settings.init();
    }

    @Test
    public void testGetSetPlayer1Input() {
        settings.setPlayer1Input(mockedInput);
        assertEquals(mockedInput, settings.getPlayer1Input());
    }

    @Test
    public void testGetSetPlayer2Input() {
        settings.setPlayer2Input(mockedInput);
        assertEquals(mockedInput, settings.getPlayer2Input());
    }

    @Test
    public void testSetPlayer1LeftKey() {
        settings.setPlayer1LeftKey(mockedKey);
        assertEquals(mockedKey, settings.getPlayer1Input().getLeftInput());
    }

    @Test
    public void testSetPlayer1RightKey() {
        settings.setPlayer1RightKey(mockedKey);
        assertEquals(mockedKey, settings.getPlayer1Input().getRightInput());
    }

    @Test
    public void testSetPlayer1ShootKey() {
        settings.setPlayer1ShootKey(mockedKey);
        assertEquals(mockedKey, settings.getPlayer1Input().getShootInput());
    }

    @Test
    public void testSetPlayer2LeftKey() {
        settings.setPlayer2LeftKey(mockedKey);
        assertEquals(mockedKey, settings.getPlayer2Input().getLeftInput());
    }

    @Test
    public void testSetPlayer2RightKey() {
        settings.setPlayer2RightKey(mockedKey);
        assertEquals(mockedKey, settings.getPlayer2Input().getRightInput());
    }

    @Test
    public void testSetPlayer2ShootKey() {
        settings.setPlayer2ShootKey(mockedKey);
        assertEquals(mockedKey, settings.getPlayer2Input().getShootInput());
    }

    @Test
    public void testSave() {
        settings.save();
    }

    @Test
    public void testSetDefaults() {
        settings.setPlayer1Input(mockedInput);
        settings.setDefaults();
        assertNotEquals(mockedInput, settings.getPlayer1Input());
    }

}
