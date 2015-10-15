package nl.tudelft.semgroup4;

import static org.junit.Assert.*;
import nl.tudelft.semgroup4.eventhandlers.InputKey;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SettingsTest {
    
    private PlayerInput mockedInput;
    private InputKey mockedKey;

    @Before
    public void setUp() {
        Settings.init();
        mockedInput = Mockito.mock(PlayerInput.class);
        mockedKey = Mockito.mock(InputKey.class);
    }

    @Test
    public void testInitNoException() {
        Settings.init();
    }

    @Test
    public void testGetSetPlayer1Input() {
        Settings.setPlayer1Input(mockedInput);
        assertEquals(mockedInput, Settings.getPlayer1Input());
    }
    @Test
    public void testGetSetPlayer2Input() {
        Settings.setPlayer2Input(mockedInput);
        assertEquals(mockedInput, Settings.getPlayer2Input());
    }

    @Test
    public void testSetPlayer1LeftKey() {
        Settings.setPlayer1LeftKey(mockedKey);
        assertEquals(mockedKey, Settings.getPlayer1Input().getLeftInput());
    }

    @Test
    public void testSetPlayer1RightKey() {
        Settings.setPlayer1RightKey(mockedKey);
        assertEquals(mockedKey, Settings.getPlayer1Input().getRightInput());
    }

    @Test
    public void testSetPlayer1ShootKey() {
        Settings.setPlayer1ShootKey(mockedKey);
        assertEquals(mockedKey, Settings.getPlayer1Input().getShootInput());
    }

    @Test
    public void testSetPlayer2LeftKey() {
        Settings.setPlayer2LeftKey(mockedKey);
        assertEquals(mockedKey, Settings.getPlayer2Input().getLeftInput());
    }

    @Test
    public void testSetPlayer2RightKey() {
        Settings.setPlayer2RightKey(mockedKey);
        assertEquals(mockedKey, Settings.getPlayer2Input().getRightInput());
    }

    @Test
    public void testSetPlayer2ShootKey() {
        Settings.setPlayer2ShootKey(mockedKey);
        assertEquals(mockedKey, Settings.getPlayer2Input().getShootInput());
    }

    @Test
    public void testSave() {
        Settings.save();
    }

    @Test
    public void testSetDefaults() {
        Settings.setPlayer1Input(mockedInput);
        Settings.setDefaults();
        assertNotEquals(mockedInput, Settings.getPlayer1Input());
    }

}
