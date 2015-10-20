package nl.tudelft.controller.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

    private Rectangle defaultBounds;
    private QuadTree testTree;

    /**
     * Sets up the dependencies.
     */
    @Before
    public void setUp() throws Exception {
        defaultBounds = new Rectangle(0, 0, 10, 10);
        testTree = new QuadTree(0, defaultBounds);
    }

    @Test
    public void testQuadTree() {
        assertNotNull(testTree);
        assertEquals(0, testTree.getDepthLevel());
        assertEquals(defaultBounds, testTree.getBounds());

    }

    @Test
    public void testGetDepthLevel() {
        assertEquals(0, testTree.getDepthLevel());
        QuadTree highlevelTree = new QuadTree(3, defaultBounds);
        assertEquals(3, highlevelTree.getDepthLevel());
    }

    @Test
    public void testGetBounds() {
        assertEquals(defaultBounds, testTree.getBounds());
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
    public void testClearLeafTree2() {
        Shape mockedShape = Mockito.mock(Shape.class);
        when(mockedShape.getX()).thenReturn(0.0f);
        when(mockedShape.getY()).thenReturn(0.0f);
        
        for (int i = 0; i < 11; i++) {
            AbstractGameObject someBounds1 = Mockito.mock(AbstractGameObject.class);
            when(someBounds1.getBounds()).thenReturn(mockedShape);
            testTree.insert(someBounds1);
        }
        
        AbstractGameObject someBounds1 = Mockito.mock(AbstractGameObject.class);
        when(someBounds1.getBounds()).thenReturn(mockedShape);
        
        List<AbstractGameObject> res = new ArrayList<>();
        testTree.retrieve(res, mockedShape);
        
        assertFalse(res.isEmpty());
        testTree.clear();
        
        res.clear();
        testTree.retrieve(res, mockedShape);
        assertTrue(res.isEmpty());
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
    public void testInsertMultipleElements() {
        for (int i = 0; i < 24; i++) {
            AbstractGameObject someBounds1 = Mockito.mock(AbstractGameObject.class);
            Shape mockedShape = Mockito.mock(Shape.class);
            
            if (i < 6) {
                when(mockedShape.getX()).thenReturn(1.0f);
                when(mockedShape.getY()).thenReturn(1.0f);
            } else if (i < 12) {
                when(mockedShape.getX()).thenReturn(9.0f);
                when(mockedShape.getY()).thenReturn(1.0f);
            } else if (i < 16) {
                when(mockedShape.getX()).thenReturn(1.0f);
                when(mockedShape.getY()).thenReturn(9.0f);
            } else {
                when(mockedShape.getX()).thenReturn(9.0f);
                when(mockedShape.getY()).thenReturn(9.0f);
            }
            when(someBounds1.getBounds()).thenReturn(mockedShape);
            testTree.insert(someBounds1);
        }
        
        Shape mockedShape = Mockito.mock(Shape.class);
        when(mockedShape.getX()).thenReturn(4.0f);
        when(mockedShape.getY()).thenReturn(1.0f);
        when(mockedShape.getWidth()).thenReturn(5.0f);
        
        List<AbstractGameObject> res = new ArrayList<>();
        testTree.retrieve(res, mockedShape);
        assertEquals(12, res.size());
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
