package nl.tudelft.semgroup4.collision;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;

import nl.tudelft.semgroup4.util.QuadTree;
import nl.tudelft.semgroup4.util.SemRectangle;
import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;


public class CollisionHelperTest {

    @Test
    public void testCollideObjectWithList1() {
        Player player = mock(Player.class);
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        list.add(player);
        ArrayList<GameObject> res =
                (ArrayList<GameObject>)CollisionHelper.collideObjectWithList(player, list, null);
        assertFalse(res.contains(player));
    }

    @Test
    public void testCollideObjectWithList2() {
        SemRectangle rect = new SemRectangle(1, 1, 1, 1);
        Player player = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player.getBounds()).thenReturn(rect);
        when(player2.getBounds()).thenReturn(rect);
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        list.add(player2);
        ArrayList<GameObject> res =
                (ArrayList<GameObject>)CollisionHelper.collideObjectWithList(player, list, null);
        assertTrue(res.contains(player2));
    }

    @Test
    public void testCollideObjectWithList3() {
        SemRectangle rect = new SemRectangle(1, 1, 1, 1);
        Player player = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player.getBounds()).thenReturn(rect);
        when(player2.getBounds()).thenReturn(rect);
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        list.add(player2);
        QuadTree quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));
        quad.insert(player2);
        ArrayList<GameObject> res =
                (ArrayList<GameObject>)CollisionHelper.collideObjectWithList(player, list, quad);
        assertTrue(res.contains(player2));
    }

    @Test
    public void testCollideObjectWithList4() {
        SemRectangle rect = new SemRectangle(1, 1, 1, 1);
        Player player = mock(Player.class);
        when(player.getBounds()).thenReturn(rect);
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        list.add(player);
        QuadTree quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));
        quad.insert(player);
        ArrayList<GameObject> res =
                (ArrayList<GameObject>)CollisionHelper.collideObjectWithList(player, list, quad);
        assertFalse(res.contains(player));
    }

    @Test
    public void testCollideObjectWithList5() {
        SemRectangle rect = new SemRectangle(1, 1, 1, 1);
        SemRectangle rect2 = new SemRectangle(2, 2, 2, 2);
        Player player = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player.getBounds()).thenReturn(rect);
        when(player2.getBounds()).thenReturn(rect2);
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        list.add(player2);
        ArrayList<GameObject> res =
                (ArrayList<GameObject>)CollisionHelper.collideObjectWithList(player, list, null);
        assertFalse(res.contains(player2));
    }

    @Test
    public void testCollideObjectWithList6() {
        SemRectangle rect = new SemRectangle(1, 1, 1, 1);
        SemRectangle rect2 = new SemRectangle(2, 2, 2, 2);
        Player player = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player.getBounds()).thenReturn(rect);
        when(player2.getBounds()).thenReturn(rect2);
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        list.add(player2);
        QuadTree quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));
        quad.insert(player2);
        ArrayList<GameObject> res =
                (ArrayList<GameObject>)CollisionHelper.collideObjectWithList(player, list, quad);
        assertFalse(res.contains(player2));
    }

    @Test
    public void testCollisionHelper() {
        CollisionHelper collhelper = null;
        collhelper = new CollisionHelper();
        assertFalse(collhelper == null);
    }
}
