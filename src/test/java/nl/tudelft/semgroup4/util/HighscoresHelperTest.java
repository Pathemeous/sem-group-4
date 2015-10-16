package nl.tudelft.semgroup4.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HighscoresHelperTest {

    public static final String TEST_FILENAME = "test_file.json";
    private String originalFilename;

    @Before
    public void setup() {
        originalFilename = HighscoresHelper.FILENAME;
        HighscoresHelper.FILENAME = TEST_FILENAME;
    }

    @After
    public void teardown() throws Exception {
        HighscoresHelper.FILENAME = originalFilename;
        new File(TEST_FILENAME).delete();
    }

    @Test
    public void testEmptyNoSave() throws Exception {
        List<HighscoreEntry> loaded = HighscoresHelper.load();

        assertTrue(loaded.isEmpty());
    }

    @Test
    public void testEmpty() throws Exception {
        List<HighscoreEntry> saveMe = new ArrayList<>();

        HighscoresHelper.save(saveMe);

        List<HighscoreEntry> loaded = HighscoresHelper.load();

        assertTrue(loaded.isEmpty());
        assertEquals(saveMe, loaded);
    }

    @Test
    public void testOne() throws Exception {
        List<HighscoreEntry> saveMe = new ArrayList<>();
        saveMe.add(new HighscoreEntry("cool name", 1000L));

        HighscoresHelper.save(saveMe);

        List<HighscoreEntry> loaded = HighscoresHelper.load();

        assertEquals(1, loaded.size());
        assertEquals(saveMe, loaded);
    }

    @Test
    public void testMultiple() throws Exception {
        List<HighscoreEntry> saveMe = new ArrayList<>();
        saveMe.add(new HighscoreEntry("cool name1", 1000L));
        saveMe.add(new HighscoreEntry("cool name2", 1050L));
        saveMe.add(new HighscoreEntry("cool name3", 1100L));
        saveMe.add(new HighscoreEntry("cool name4", 1150L));
        saveMe.add(new HighscoreEntry("cool name5", 1200L));
        saveMe.add(new HighscoreEntry("cool name6", 1250L));

        HighscoresHelper.save(saveMe);

        List<HighscoreEntry> loaded = HighscoresHelper.load();

        assertEquals(6, loaded.size());
        assertEquals(saveMe, loaded);
    }


}