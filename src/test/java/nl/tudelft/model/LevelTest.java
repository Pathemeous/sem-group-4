package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.semgroup4.Modifiable;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class LevelTest extends AbstractOpenGLTestCase {

    public LinkedList<Wall> walls = new LinkedList<Wall>();
    public LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
    public LinkedList<Pickup> pickups = new LinkedList<Pickup>();
    public LinkedList<Bubble> bubbles = new LinkedList<Bubble>();
    public Bubble bubble;
    public Wall wall;
    public Projectile projectile;
    public Pickup pickup;

    /**
     * Create mocks for all elements in linked lists.
     */
    @Before
    public void initialize() {
        wall = mock(Wall.class);
        walls.add(wall);
        projectile = mock(Projectile.class);
        projectiles.add(projectile);
        pickup = mock(Pickup.class);
        pickups.add(pickup);
        bubble = mock(Bubble.class);
        bubbles.add(bubble);
    }
    /**
     * Test to test the constructor for level.
     * This test also tests the getters.
     */
    @Test
    public void testLevel() {
        Level level = new Level(walls, projectiles, pickups, bubbles, 1, 1);
        assertEquals(level.getBubbles(), bubbles);
        assertEquals(level.getId(), 1);
        assertEquals(level.getTime(), 1);
        assertEquals(level.getMaxTime(), 1);
        assertEquals(level.getWalls(), walls);
        assertEquals(level.getProjectiles(), projectiles);
        assertEquals(level.getPickups(), pickups);
    }

    /**
     * Test to check the isCompleted method for level.
     */
    @Test
    public void testIsCompleted() {
        Level level = new Level(walls, projectiles, pickups, bubbles, 1, 1);
        assertFalse(level.isCompleted());
    }

    /**
     * Test to check the timeExpired method for level.
     */
    @Test
    public void testTimeExpired1() {
        Level level = new Level(walls, projectiles, pickups, bubbles, 1, 1);
        assertFalse(level.timerExpired());
    }

    /**
     * Test to check the timeExpired method for level.
     */
    @Test
    public void testTimeExpired2() {
        Level level = new Level(walls, projectiles, pickups, bubbles, 0, 1);
        assertTrue(level.timerExpired());
    }

    /**
     * Test to check the time setter for level.
     */
    @Test
    public void testSetTime() {
        Level level = new Level(walls, projectiles, pickups, bubbles, 1, 1);
        assertTrue(level.getTime() == 1);
        level.setTime(2);
        assertTrue(level.getTime() == 2);
    }

    /**
     * Test to check the update method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testUpdate1() throws SlickException {
        Level level = new Level(walls, projectiles, pickups, bubbles, 2, 1);
        Modifiable modifiable = mock(Modifiable.class);
        level.update(modifiable, 1);
        verify(bubble, times(1)).update(any(), anyInt());
        verify(wall, times(1)).update(any(), anyInt());
        verify(projectile, times(1)).update(any(), anyInt());
        verify(pickup, times(1)).update(any(), anyInt());
        assertTrue(level.getTime() == 1);
    }

    /**
     * Test to check the update method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testUpdate2() throws SlickException {
        Level level = new Level(walls, projectiles, pickups, bubbles, 2, 1);
        Modifiable modifiable = mock(Modifiable.class);
        Wall mockedWall = mock(Wall.class);
        level.toAdd(mockedWall);
        Bubble mockedBubble = mock(Bubble.class);
        level.toAdd(mockedBubble);
        Pickup mockedPickup = mock(Pickup.class);
        level.toAdd(mockedPickup);
        Projectile mockedProjectile = mock(Projectile.class);
        level.toAdd(mockedProjectile);
        level.update(modifiable, 1);
        assertTrue(walls.contains(mockedWall));
        assertTrue(bubbles.contains(mockedBubble));
        assertTrue(pickups.contains(mockedPickup));
        assertTrue(projectiles.contains(mockedProjectile));
    }

    /**
     * Test to check the update method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testUpdate3() throws SlickException {
        Level level = new Level(walls, projectiles, pickups, bubbles, 3, 1);
        Modifiable modifiable = mock(Modifiable.class);
        Wall mockedWall = mock(Wall.class);
        level.toAdd(mockedWall);
        Bubble mockedBubble = mock(Bubble.class);
        level.toAdd(mockedBubble);
        Pickup mockedPickup = mock(Pickup.class);
        level.toAdd(mockedPickup);
        Projectile mockedProjectile = mock(Projectile.class);
        level.toAdd(mockedProjectile);
        level.update(modifiable, 1);
        assertTrue(walls.contains(mockedWall));
        assertTrue(bubbles.contains(mockedBubble));
        assertTrue(pickups.contains(mockedPickup));
        assertTrue(projectiles.contains(mockedProjectile));
        level.toRemove(mockedWall);
        level.toRemove(mockedProjectile);
        level.toRemove(mockedBubble);
        level.toRemove(mockedPickup);
        level.update(modifiable, 1);
        assertFalse(walls.contains(mockedWall));
        assertFalse(bubbles.contains(mockedBubble));
        assertFalse(pickups.contains(mockedPickup));
        assertFalse(projectiles.contains(mockedProjectile));
    }

    /**
     * Test to check the splitAllBubbles method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testSplitAllBubbles1() throws SlickException {
        Level level = new Level(walls, projectiles, pickups, bubbles, 3, 1);
        Bubble mockedBubble = mock(Bubble.class);
        bubbles.add(mockedBubble);
        level.splitAllBubbles(bubbles, true);
        verify(mockedBubble, times(1)).getBubbleFactory();
    }

    /**
     * Test to check the splitAllBubbles method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testSplitAllBubbles2() throws SlickException {
        Level level = new Level(walls, projectiles, pickups, bubbles, 3, 1);
        Bubble mockedBubble = mock(Bubble.class);
        bubbles.add(mockedBubble);
        level.splitAllBubbles(bubbles, false);
        verify(mockedBubble, times(1)).getBubbleFactory();
    }

    /**
     * Test to check the splitAllBubbles method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testSplitAllBubbles3() throws SlickException {
        Level level = new Level(walls, projectiles, pickups, bubbles, 3, 1);
        Bubble mockedBubble = mock(Bubble.class);
        bubbles.add(mockedBubble);
        when(mockedBubble.getImage()).thenReturn(null);
        level.splitAllBubbles(bubbles, false);
        verify(mockedBubble, times(1)).getBubbleFactory();
    }
}
