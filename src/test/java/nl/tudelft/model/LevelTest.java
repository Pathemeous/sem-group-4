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

import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LevelTest {

    private LinkedList<AbstractWall> walls = new LinkedList<AbstractWall>();
    private LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
    private LinkedList<Pickup> pickups = new LinkedList<Pickup>();
    private LinkedList<AbstractBubble> bubbles = new LinkedList<AbstractBubble>();
    private AbstractBubble bubble;
    private AbstractWall wall;
    private Projectile projectile;
    private Pickup pickup;
    private Image mockedImage;
    private Level level;

    /**
     * Create mocks for all elements in linked lists.
     */
    @Before
    public void initialize() {
        wall = mock(AbstractWall.class);
        walls.add(wall);
        projectile = mock(Projectile.class);
        projectiles.add(projectile);
        pickup = mock(Pickup.class);
        pickups.add(pickup);
        bubble = mock(AbstractBubble.class);
        bubbles.add(bubble);
        mockedImage = Mockito.mock(Image.class);
        level = new Level(mockedImage, walls, projectiles, pickups, bubbles, 3, 1);
    }
    /**
     * Test to test the constructor for level.
     * This test also tests the getters.
     */
    @Test
    public void testLevel() {
        assertEquals(level.getBubbles(), bubbles);
        assertEquals(level.getId(), 1);
        assertEquals(level.getTime(), 3);
        assertEquals(level.getMaxTime(), 3);
        assertEquals(level.getWalls(), walls);
        assertEquals(level.getProjectiles(), projectiles);
        assertEquals(level.getPickups(), pickups);
    }

    /**
     * Test to check the isCompleted method for level.
     */
    @Test
    public void testIsCompleted() {
        assertFalse(level.isCompleted());
    }

    /**
     * Test to check the timeExpired method for level.
     */
    @Test
    public void testTimeExpired1() {
        assertFalse(level.timerExpired());
    }

    /**
     * Test to check the timeExpired method for level.
     */
    @Test
    public void testTimeExpired2() {
        level.setTime(0);
        assertTrue(level.timerExpired());
    }

    /**
     * Test to check the time setter for level.
     */
    @Test
    public void testSetTime() {
        assertTrue(level.getTime() == 3);
        level.setTime(2);
        assertTrue(level.getTime() == 2);
    }

    /**
     * Test to check the update method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testUpdate1() throws SlickException {
        Modifiable modifiable = mock(Modifiable.class);
        level.update(modifiable, 1);
        verify(bubble, times(1)).update(any(), anyInt());
        verify(wall, times(1)).update(any(), anyInt());
        verify(projectile, times(1)).update(any(), anyInt());
        verify(pickup, times(1)).update(any(), anyInt());
    }

    /**
     * Test to check the update method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testUpdate2() throws SlickException {
        Modifiable modifiable = mock(Modifiable.class);
        AbstractWall mockedWall = mock(AbstractWall.class);
        level.toAdd(mockedWall);
        AbstractBubble mockedBubble = mock(AbstractBubble.class);
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
        Modifiable modifiable = mock(Modifiable.class);
        AbstractWall mockedWall = mock(AbstractWall.class);
        level.toAdd(mockedWall);
        AbstractBubble mockedBubble = mock(AbstractBubble.class);
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
    
    @Test
    public void testUpdateShopSlowActive() throws SlickException {
        Modifiable modifiable = mock(Modifiable.class);
        level.setShopSlow(true);
        for (AbstractBubble bubble : bubbles) {
            assertFalse(bubble.isSlow());
        }
        level.update(modifiable, 1);
        for (AbstractBubble bubble : bubbles) {
            verify(bubble).setSlow(true);
        }
    }

    /**
     * Test to check the splitAllBubbles method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testSplitAllBubbles1() throws SlickException {
        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        bubbles.add(mockedBubble);
        level.splitAllBubbles(bubbles, true);
        verify(mockedBubble, times(1)).getNext();
    }

    /**
     * Test to check the splitAllBubbles method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testSplitAllBubbles2() throws SlickException {
        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        bubbles.add(mockedBubble);
        level.splitAllBubbles(bubbles, false);
        verify(mockedBubble, times(1)).getNext();
    }

    /**
     * Test to check the splitAllBubbles method for level.
     * @throws SlickException : An exception created by Slick.
     */
    @Test
    public void testSplitAllBubbles3() throws SlickException {
        AbstractBubble mockedBubble = mock(AbstractBubble.class);
        bubbles.add(mockedBubble);
        when(mockedBubble.getImage()).thenReturn(null);
        level.splitAllBubbles(bubbles, false);
        verify(mockedBubble, times(1)).getNext();
    }
    
    @Test
    public void testGetSetMaxTime() {
        assertEquals(3, level.getMaxTime());
        level.setMaxTime(34);
        assertEquals(34, level.getMaxTime());
    }
    
    @Test
    public void render() throws SlickException {
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Graphics mockedGraphics  = Mockito.mock(Graphics.class);
        Mockito.when(mockedImage.getWidth()).thenReturn(1);
        Mockito.when(mockedImage.getHeight()).thenReturn(1);
        level.render(mockedContainer, mockedGraphics);
        
        for(AbstractGameObject gameObject : bubbles) {
            Mockito.verify(gameObject).render(mockedContainer, mockedGraphics);
        }
        for(AbstractGameObject gameObject : walls) {
            Mockito.verify(gameObject).render(mockedContainer, mockedGraphics);
        }
        for(AbstractGameObject gameObject : bubbles) {
            Mockito.verify(gameObject).render(mockedContainer, mockedGraphics);
        }
        for(AbstractGameObject gameObject : pickups) {
            Mockito.verify(gameObject).render(mockedContainer, mockedGraphics);
        }
    }
}
