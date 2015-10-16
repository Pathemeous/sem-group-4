package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import nl.tudelft.semgroup4.util.HighscoreEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class HighscoreEntryEqualsTest {

    private final Object left;
    private final Object right;
    private final boolean expected;

    /**
     * Constructor for this parameterized test.
     *
     * @param left the left entry
     * @param right the right entry
     * @param expected the expected value, true for equal, false otherwise
     */
    public HighscoreEntryEqualsTest(Object left, Object right, boolean expected) {
        this.left = left;
        this.right = right;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertEquals(expected, left.equals(right));
    }

    /**
     * Data for the test.
     *
     * <p>
     *     What we want it to check is whether the bubbles
     *     actually intersect as a circle. There are some parameters that,
     *     when used for rectangles, intersect but with circles wont intersect.
     * </p>
     *
     * @return A collection of parameters, used to instantiate this class.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {
                new HighscoreEntry("name1", 0L),
                new HighscoreEntry("name1", 0L),
                true
            },
            {
                new HighscoreEntry("name1", 0L),
                new HighscoreEntry("name2", 0L),
                false
            },
            {
                new HighscoreEntry("name2", 0L),
                new HighscoreEntry("name2", 0L),
                true
            },
            {
                new HighscoreEntry("name1", 0L),
                new HighscoreEntry("name1", 1L),
                false
            },
            {
                new HighscoreEntry("name1", 0L),
                new HighscoreEntry("name1", -0L),
                true
            },
            {
                new HighscoreEntry("name1", 0L),
                new HighscoreEntry("name1", -1L),
                false
            },
            {
                new HighscoreEntry("name1", 1L),
                null,
                false
            },
            {
                new HighscoreEntry("name1", 1L),
                new Object(),
                false
            },
        });
    }
}
