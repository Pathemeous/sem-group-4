package nl.tudelft.controller.collision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.util.QuadTree;
import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.GameObject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.newdawn.slick.geom.Shape;

public class CollisionHelperTest {

    private QuadTree mockedQuadTree;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        assertNotNull(collhelper);
    }

    @Test
    public void testGetCollisionNullQuad() {
        thrown.expect(IllegalArgumentException.class);

        AbstractGameObject mockedObject = mock(AbstractGameObject.class);
        CollisionHelper.getCollisionsFor(mockedObject, null);
    }

    @Test
    public void testGetCollisionSameObject() {
        GameObject mockedObject = mock(AbstractGameObject.class);
        List<GameObject> returnObjects = new ArrayList<>();
        returnObjects.add(mockedObject);

        when(mockedQuadTree.retrieve(anyListOf(GameObject.class), any())).thenReturn(
                returnObjects);

        List<GameObject> resultList =
                CollisionHelper.getCollisionsFor(mockedObject, mockedQuadTree);

        assertEquals(0, resultList.size());
    }

    @Test
    public void testGetCollisionDifferentObjects() {
        AbstractGameObject mockedObject1 = mock(AbstractGameObject.class);
        Shape mockedShape1 = mock(Shape.class);
        when(mockedObject1.getBounds()).thenReturn(mockedShape1);

        AbstractGameObject mockedObject2 = mock(AbstractGameObject.class);
        Shape mockedShape2 = mock(Shape.class);
        when(mockedObject2.getBounds()).thenReturn(mockedShape2);

        List<GameObject> returnObjects = new ArrayList<>();
        returnObjects.add(mockedObject2);

        when(mockedShape1.intersects(mockedShape2)).thenReturn(true);

        when(mockedQuadTree.retrieve(anyListOf(GameObject.class), any())).thenReturn(
                returnObjects);

        List<GameObject> resultList =
                CollisionHelper.getCollisionsFor(mockedObject1, mockedQuadTree);

        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(mockedObject2));
    }
}
