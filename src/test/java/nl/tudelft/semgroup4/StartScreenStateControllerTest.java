package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StartScreenStateControllerTest {

    private StartScreenStateController defaultInstance;
    private ResourcesWrapper mockedResources;

    /**
     * Sets up all mocks.
     */
    @Before
    public void setUp() {
        mockedResources = Mockito.mock(ResourcesWrapper.class);
        defaultInstance = new StartScreenStateController(mockedResources);
    }

    @Test
    public void testStartScreenStateController() {
        assertNotNull(defaultInstance);
        assertEquals(mockedResources, defaultInstance.getResources());
    }

}
