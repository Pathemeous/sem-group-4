package nl.tudelft.semgroup4;

import static org.junit.Assert.*;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StartScreenStateControllerTest {
    
    private StartScreenStateController defaultInstance;
    private ResourcesWrapper mockedResources;

    @Before
    public void setUp() throws Exception {
        mockedResources = Mockito.mock(ResourcesWrapper.class);
        defaultInstance = new StartScreenStateController(mockedResources);
    }

    @Test
    public void testStartScreenStateController() {
        assertNotNull(defaultInstance);
        assertEquals(mockedResources, defaultInstance.getResources());
    }

    @Test
    public void testDrawHighScoresButton() {
        
    }
    
    @Test
    public void testCreateSingleplayerGame() {
        
    }
    
    @Test
    public void testCreateMultiplayerGame() {
        
    }

}
