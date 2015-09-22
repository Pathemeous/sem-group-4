package nl.tudelft.model.pickups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.AbstractOpenGLTestCase;
import nl.tudelft.model.Game;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.Resources;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class PickupTest extends AbstractOpenGLTestCase {

    /**
     * This method tests the constructor to see if the right pickup is generated based on the
     * number that is put in.
     */
    @Test
    public void testPickup1() {
        for (int i = 0; i < 11; i++) {
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
                    assertEquals(pickup.getImage(), Resources.pickupPowerInvincible);
                    break;
                case LIFE:
                    assertEquals(pickup.getImage(), Resources.pickupUtilityLife);
                    break;
                case POINTS:
                    assertEquals(pickup.getImage(), Resources.pickupPowerPoints);
                    break;
                case SHIELD:
                    assertEquals(pickup.getImage(), Resources.pickupPowerShield);
                    break;
                case SPEEDUP:
                    assertEquals(pickup.getImage(), Resources.pickupPowerSpeedup);
                    break;
                default:
                    break;
            }

            Pickup pickup2 = new Pickup(null, 0, 0, 7);
            Utility content2 = (Utility)pickup2.getContent();

            switch (content2.getType()) {
                case FREEZE:
                    assertEquals(pickup2.getImage(), Resources.pickupUtilityFreeze);
                    break;
                case LEVELWON:
                    assertEquals(pickup2.getImage(), Resources.pickupUtilityLevelwon);
                    break;
                case SLOW:
                    assertEquals(pickup2.getImage(), Resources.pickupUtilitySlow);
                    break;
                case SPLIT:
                    assertEquals(pickup2.getImage(), Resources.pickupUtilitySplit);
                    break;
                case TIME:
                    assertEquals(pickup2.getImage(), Resources.pickupUtilityTime);
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

    /**
     * Test if the update method of Pickup works correctly
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdate1() throws SlickException {
        Pickup pickup = new Pickup(null, 0, 0, 1);
        pickup.setOnGround(true);
        assertEquals(pickup.getTickCount(), 0);
        pickup.update(null, 0);
        assertEquals(pickup.getTickCount(), 1);
    }

    /**
     * Test if the update method of Pickup works correctly
     * @throws SlickException : exception from Slick.
     */
    @Test
    public void testUpdate2() throws SlickException {
        Pickup pickup = new Pickup(null, 0, 0, 1);
        pickup.setOnGround(true);
        assertEquals(pickup.getTickCount(), 0);
        pickup.setTickCount(179);
        Game mockedContainer = mock(Game.class);
        pickup.update(mockedContainer, 0);
        assertEquals(pickup.getTickCount(), 180);
        verify(mockedContainer, times(1)).toRemove(any());
    }
}
