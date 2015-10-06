package nl.tudelft.semgroup4.collision;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import nl.tudelft.semgroup4.util.QuadTree;

import org.junit.Before;
import org.junit.Test;

public class CollisionHelperTest {

    private QuadTree mockedQuadTree;

    /**
     * Initializes all dependencies for each test.
     */
    @Before
    public void setUp() {
        mockedQuadTree = mock(QuadTree.class);
    }

    @Test
    public void testCollisionHelper() {
        CollisionHelper collhelper = null;
        collhelper = new CollisionHelper();
        assertFalse(collhelper == null);
    }
}
