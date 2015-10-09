package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SettingsTest {

    @Before
    public void setup() {
        Settings.init();
    }

    /**
     * Delete the created file.
     */
    @After
    public void breakDownTestFile() {
        File file = new File("keyBinds.json");
        try {
            if (file.delete()) {
                System.out.println("File deleted.");
            } else {
                System.out.println("Deletion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initTest() {
        assertEquals(Settings.getSettings().length(), 6);
    }

    @Test
    public void getPlayer1InputTest() {
        assertNotEquals(Settings.getPlayer1Input(), null);
    }

    @Test
    public void getPlayer2InputTest() {
        assertNotEquals(Settings.getPlayer2Input(), null);
    }


}
