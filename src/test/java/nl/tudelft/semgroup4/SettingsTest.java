package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import nl.tudelft.semgroup4.eventhandlers.InputKey;
import nl.tudelft.semgroup4.eventhandlers.PlayerInput;
import org.junit.Before;
import org.junit.Test;


public class SettingsTest {

    /**
     * Initialise the settings.
     */
    @Before
    public void setup() {
        Settings.init();
    }

    @Test
    public void initTest() {
        assertNotEquals(Settings.getSettings(), null);
    }

    @Test
    public void getPlayer1InputTest() {
        PlayerInput check = Settings.getPlayer1Input();
        PlayerInput against = new PlayerInput(
                new InputKey(Settings.getPlayer1LeftValue()),
                new InputKey(Settings.getPlayer1RightValue()),
                new InputKey(Settings.getPlayer1ShootValue())
        );
        /**
         * If this is not equals it means the
         * init did what it should do.
         * If it's equals (which is what you would expect)
         * something would be broken
         */
        assertNotEquals(check, against);
    }

    @Test
    public void getPlayer2InputTest() {
        PlayerInput check = Settings.getPlayer2Input();
        PlayerInput against = new PlayerInput(
                new InputKey(Settings.getPlayer2LeftValue()),
                new InputKey(Settings.getPlayer2RightValue()),
                new InputKey(Settings.getPlayer2ShootValue())
        );
        /**
         * If this is not equals it means the
         * init did what it should do.
         * If it's equals (which is what you would expect)
         * something would be broken
         */
        assertNotEquals(check, against);
    }

    @Test
    public void player1LeftInputTest() {
        assertTrue(Settings.getPlayer1LeftValue() > 0);
        Settings.setPlayer1Left(0);
        assertEquals(Settings.getPlayer1LeftValue(), 0);
    }

    @Test
    public void player1RightInputTest() {
        assertTrue(Settings.getPlayer1RightValue() > 0);
        Settings.setPlayer1Right(0);
        assertEquals(Settings.getPlayer1RightValue(), 0);
    }

    @Test
    public void player1ShootInputTest() {
        assertTrue(Settings.getPlayer1ShootValue() > 0);
        Settings.setPlayer1Shoot(0);
        assertEquals(Settings.getPlayer1ShootValue(), 0);
    }

    @Test
    public void player2LeftInputTest() {
        assertTrue(Settings.getPlayer2LeftValue() > 0);
        Settings.setPlayer2Left(0);
        assertEquals(Settings.getPlayer2LeftValue(), 0);
    }

    @Test
    public void player2RightInputTest() {
        assertTrue(Settings.getPlayer2RightValue() > 0);
        Settings.setPlayer2Right(0);
        assertEquals(Settings.getPlayer2RightValue(), 0);
    }

    @Test
    public void player2ShootInputTest() {
        assertTrue(Settings.getPlayer2ShootValue() > 0);
        Settings.setPlayer2Shoot(0);
        assertEquals(Settings.getPlayer2ShootValue(), 0);
    }
}
