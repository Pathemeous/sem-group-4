package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import nl.tudelft.model.OpenGLTestCase;
import nl.tudelft.model.Weapon;
import nl.tudelft.semgroup4.Resources;
import org.junit.Test;
import org.newdawn.slick.SlickException;

public class PickupTest extends OpenGLTestCase {

    /**
     * This method tests the constructor to see if the right pickup is generated based on the
     * number that is put in.
     */
    @Test
    public void testPickup() {
        for (int i = 1; i < 11; i++) {
            Pickup pickup = new Pickup(null, 0, 0, i);
            PickupContent content = pickup.getContent();
            if (i < 4) {
                assertTrue(content instanceof Weapon);
            } else if (i < 7) {
                assertTrue(content instanceof Powerup);
            } else {
                assertTrue(content instanceof Utility);
            }
        }
    }

    /**
     * Test the images.
     * @throws SlickException : slick exception.
     */
    @Test
    public void testImages() throws SlickException {
        Resources.init();

        for (int i = 0; i < 1000; i++) {
            Pickup pickup = new Pickup(null, 0, 0, 4);
            Powerup content = (Powerup)pickup.getContent();
            switch (content.getPowerType()) {
                case INVINCIBLE:
                    assertEquals(pickup.getImage(), Resources.pickup_power_invincible);
                    break;
                case LIFE:
                    assertEquals(pickup.getImage(), Resources.pickup_utility_life);
                    break;
                case POINTS:
                    assertEquals(pickup.getImage(), Resources.pickup_power_points);
                    break;
                case SHIELD:
                    assertEquals(pickup.getImage(), Resources.pickup_power_shield);
                    break;
                case SPEEDUP:
                    assertEquals(pickup.getImage(), Resources.pickup_power_speedup);
                    break;
                default:
                    break;
            }

            Pickup pickup2 = new Pickup(null, 0, 0, 7);
            Utility content2 = (Utility)pickup2.getContent();

            switch (content2.getType()) {
                case FREEZE:
                    assertEquals(pickup2.getImage(), Resources.pickup_utility_freeze);
                    break;
                case LEVELWON:
                    assertEquals(pickup2.getImage(), Resources.pickup_utility_levelwon);
                    break;
                case SLOW:
                    assertEquals(pickup2.getImage(), Resources.pickup_utility_slow);
                    break;
                case SPLIT:
                    assertEquals(pickup2.getImage(), Resources.pickup_utility_split);
                    break;
                case TIME:
                    assertEquals(pickup2.getImage(), Resources.pickup_utility_time);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Tests if the update method of Pickup adds one to the Y location.
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdateLocation() throws SlickException {
        Pickup pickup = new Pickup(null, 0, 0, 1);
        pickup.setOnGround(true);
        float locY = pickup.getLocY();
        pickup.update(null, 0);
        assertEquals(pickup.getLocY(), locY + 1, 0.0f);
    }
}