package nl.tudelft.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import nl.tudelft.controller.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;

public class StartScreenStateControllerTest {

    private ResourcesWrapper mockedResources;
    private StartScreenStateController defaultInstance;
    private TrueTypeFont mockedFont;

    /**
     * Mock dependencies.
     */
    @Before
    public void setUp() {
        mockedResources = Mockito.mock(ResourcesWrapper.class);
        defaultInstance = new StartScreenStateController(mockedResources);
        mockedFont = Mockito.mock(TrueTypeFont.class);
        defaultInstance.setFont(mockedFont);
    }

    @Test
    public void testStartScreenStateController() {
        assertNotNull(defaultInstance);
        assertEquals(mockedResources, defaultInstance.getResources());
    }

    @Test
    public void testCreatePlayer1Button() {
        Input mockedInput = Mockito.mock(Input.class);
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
        MouseOverArea result = null;
        assertNull(result);
        result = defaultInstance.createPlayer1Button(mockedContainer);
        assertNotNull(result);
    }

    @Test
    public void testCreatePlayer2Button() {
        Input mockedInput = Mockito.mock(Input.class);
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
        MouseOverArea result = null;
        assertNull(result);
        result = defaultInstance.createPlayer2Button(mockedContainer);
        assertNotNull(result);
    }

    @Test
    public void testCreateOptionsButton() {
        Input mockedInput = Mockito.mock(Input.class);
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
        MouseOverArea result = null;
        assertNull(result);
        result = defaultInstance.createOptionsButton(mockedContainer);
        assertNotNull(result);
    }

    @Test
    public void testCreateQuitButton() {
        Input mockedInput = Mockito.mock(Input.class);
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
        MouseOverArea result = null;
        assertNull(result);
        result = defaultInstance.createQuitButton(mockedContainer);
        assertNotNull(result);
    }

    @Test
    public void testCreateHighscoresButton() throws SlickException {
        Image mockedImage = Mockito.mock(Image.class);

        Mockito.when(mockedFont.getWidth(Mockito.any())).thenReturn(5);
        Mockito.when(mockedFont.getHeight()).thenReturn(5);
        Mockito.when(mockedResources.createImage(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(mockedImage);

        Input mockedInput = Mockito.mock(Input.class);
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
        MouseOverArea result = null;
        assertNull(result);
        result = defaultInstance.createHighscoresButton(mockedContainer);
        assertNotNull(result);
    }

    @Test
    public void testGetSetResources() {
        assertEquals(mockedResources, defaultInstance.getResources());
        ResourcesWrapper newResources = Mockito.mock(ResourcesWrapper.class);
        defaultInstance.setResources(newResources);
        assertEquals(newResources, defaultInstance.getResources());
    }

}
