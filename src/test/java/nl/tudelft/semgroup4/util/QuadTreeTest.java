/**
 * 
 */
package nl.tudelft.semgroup4.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Pathemeous
 *
 */
public class QuadTreeTest {

    private Rectangle mockedDefaultBounds;

    /**
     * Sets up the dependencies.
     */
    @Before
    public void setUp() throws Exception {
        mockedDefaultBounds = Mockito.mock(Rectangle.class);
    }

    @Test
    public void testQuadTree() {
        QuadTree testTree = new QuadTree(0, mockedDefaultBounds);
        assertNotNull(testTree);

    }

    @Test
    public void testClear() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsert() {
        fail("Not yet implemented");
    }

    @Test
    public void testRetrieve() {
        fail("Not yet implemented");
    }

}
