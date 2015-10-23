package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.SlickException;

public class BacchelliBubbleTest {
    private BacchelliBubble bubble;
    private ResourcesWrapper wrapper;
    private Modifiable mockedContainer;

    /**
     * setup before every test, sets up the baccheliBubble and wrapper.
     */
    @Before
    public void setup() {
        mockedContainer = Mockito.mock(Modifiable.class);
        wrapper = new ResourcesWrapper();
        bubble = new BacchelliBubble(wrapper, 0, 0);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, (int) bubble.getLocX());
        assertEquals(0, (int) bubble.getLocY());
        assertEquals(wrapper.getBubbleImageBach(), bubble.getImage());
        assertEquals(8, bubble.getLives());
    }

    @Test
    public void testSetIsHit1() {
        bubble.setIsHit();

        assertEquals(7, bubble.getLives());
        assertEquals(0, bubble.getHealthCounter());
    }

    @Test
    public void testSetIsHit2() {
        bubble.getBullets().add(new BacchelliBullet(wrapper, 0, 0));
        bubble.setLives(1);
        bubble.setIsHit();

        assertTrue(bubble.getBullets().get(0).isHit());
    }

    @Test
    public void testMove() {
        int test = (int) (bubble.getLocX() + bubble.getHorizontalSpeed());
        bubble.move();

        assertEquals(test, (int) bubble.getLocX());
    }

    @Test
    public void testUpdate1() throws SlickException {
        bubble.update(mockedContainer, 0);

        assertEquals(1, bubble.getHealthCounter());
        assertEquals(1, bubble.getShootCounter());

    }

    @Test
    public void testUpdate2() throws SlickException {
        bubble.setHealthCounter(150);
        bubble.setLives(5);
        bubble.update(mockedContainer, 0);

        assertEquals(6, bubble.getLives());
    }

    @Test
    public void testCreateNextBubbles() {
        assertEquals(Collections.EMPTY_LIST, bubble.createNextBubbles());
    }

    @Test
    public void testInitMaxVerticalSpeed() {
        assertEquals(0, (int) bubble.initMaxVerticalSpeed());
    }
}
