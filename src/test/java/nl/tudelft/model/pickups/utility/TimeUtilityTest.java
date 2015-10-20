package nl.tudelft.model.pickups.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Level;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Image;

public class TimeUtilityTest {

    private ResourcesWrapper mockedResources;
    private TimeUtility utility;

    /**
     * Sets up all mocks.
     */
    @Before
    public void setUp() {
        mockedResources = mock(ResourcesWrapper.class);
        Image mockedImg = mock(Image.class);
        when(mockedResources.getPickupUtilityTime()).thenReturn(mockedImg);
        utility = new TimeUtility(mockedResources, 0, 0);
    }

    @Test
    public void testConstructor() {
        assertEquals(mockedResources.getPickupUtilityTime(), utility.getImage());
        assertEquals(0.0f, utility.getLocX(), 0.0f);
        assertEquals(0.0f, utility.getLocY(), 0.0f);
    }

    @Test
    public void testActivateMaxTimeRemaining() {
        final int maxTime = 50000;
        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getTime()).thenReturn(maxTime);
        Mockito.when(level.getMaxTime()).thenReturn(maxTime);

        assertFalse(utility.isActive());

        utility.activate(level);

        assertTrue(utility.isActive());
        Mockito.verify(level).setTime(maxTime);
    }

    @Test
    public void testActivateLessThanMaxTimeRemaining() {
        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getTime()).thenReturn(0);
        Mockito.when(level.getMaxTime()).thenReturn(50000);
        
        assertFalse(utility.isActive());
        
        utility.activate(level);

        Mockito.verify(level).setTime(20000);
    }
}
