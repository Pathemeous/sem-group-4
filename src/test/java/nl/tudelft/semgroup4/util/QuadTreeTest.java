/**
 * 
 */
package nl.tudelft.semgroup4.util;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.AbstractOpenGLTestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * @author Pathemeous
 *
 */
public class QuadTreeTest {

    private Rectangle mockedDefaultBounds;
    private QuadTree testTree;

    /**
     * Sets up the dependencies.
     */
    @Before
    public void setUp() throws Exception {
        mockedDefaultBounds = Mockito.mock(Rectangle.class);
        testTree = new QuadTree(0, mockedDefaultBounds);
    }

    @Test
    public void testQuadTree() {
        assertNotNull(testTree);
        assertEquals(0, testTree.getDepthLevel());
        assertEquals(mockedDefaultBounds, testTree.getBounds());

    }

    @Test
    public void testGetDepthLevel() {
        assertEquals(0, testTree.getDepthLevel());
        QuadTree highlevelTree = new QuadTree(3, mockedDefaultBounds);
        assertEquals(3, highlevelTree.getDepthLevel());

    }

    @Test
    public void testGetBounds() {
        assertEquals(mockedDefaultBounds, testTree.getBounds());
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
        
        List<AbstractGameObject> result = testTree.retrieve(new LinkedList<AbstractGameObject>(), someObject1);
        assertEquals(2, result.size());
    }

}
