package nl.tudelft.semgroup4.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import nl.tudelft.model.AbstractGameObject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Test class for the QuadTree class.
 * 
 * @author Pathemeous
 *
 */
public class QuadTreeTest {

    private Rectangle mckdDefaultBounds;
    private QuadTree testTree;

    /**
     * Sets up the dependencies.
     */
    @Before
    public void setUp() throws Exception {
        mckdDefaultBounds = Mockito.mock(Rectangle.class);
        testTree = new QuadTree(0, mckdDefaultBounds);
    }

    @Test
    public void testQuadTree() {
        assertNotNull(testTree);
        assertEquals(0, testTree.getDepthLevel());
        assertEquals(mckdDefaultBounds, testTree.getBounds());

    }

    @Test
    public void testGetDepthLevel() {
        assertEquals(0, testTree.getDepthLevel());
        QuadTree highlevelTree = new QuadTree(3, mckdDefaultBounds);
        assertEquals(3, highlevelTree.getDepthLevel());

    }

    @Test
    public void testGetBounds() {
        assertEquals(mckdDefaultBounds, testTree.getBounds());
    }

    @Test
    public void testClearLeafTree() {
        AbstractGameObject someBounds1 = Mockito.mock(AbstractGameObject.class);
        AbstractGameObject someBounds2 = Mockito.mock(AbstractGameObject.class);
        testTree.insert(someBounds1);
        testTree.insert(someBounds2);
        assertFalse(testTree.getObjects().isEmpty());
        testTree.clear();
        assertTrue(testTree.getObjects().isEmpty());
    }

    @Test
    public void testInsertOneElement() {
        AbstractGameObject someBounds1 = Mockito.mock(AbstractGameObject.class);
        assertTrue(testTree.getObjects().isEmpty());

        testTree.insert(someBounds1);
        assertTrue(testTree.getObjects().contains(someBounds1));
        assertEquals(1, testTree.getObjects().size());
    }

    @Test
    public void testInsertTwoElements() {
        AbstractGameObject someBounds1 = Mockito.mock(AbstractGameObject.class);
        AbstractGameObject someBounds2 = Mockito.mock(AbstractGameObject.class);
        assertTrue(testTree.getObjects().isEmpty());

        testTree.insert(someBounds1);
        testTree.insert(someBounds2);
        assertTrue(testTree.getObjects().contains(someBounds1));
        assertTrue(testTree.getObjects().contains(someBounds2));
        assertEquals(2, testTree.getObjects().size());
    }

    @Test
    public void testRetrieve() {
        Shape someObject1 = Mockito.mock(Shape.class);
        AbstractGameObject someObject2 = Mockito.mock(AbstractGameObject.class);
        AbstractGameObject someObject3 = Mockito.mock(AbstractGameObject.class);
        testTree.insert(someObject2);
        testTree.insert(someObject3);

        List<AbstractGameObject> result =
                testTree.retrieve(new LinkedList<AbstractGameObject>(), someObject1);
        assertEquals(2, result.size());
    }

}
