package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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

    @Before
    public void setUp() throws Exception {
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
        Mockito.when(mockedResources.createImage(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockedImage);
        
        Input mockedInput = Mockito.mock(Input.class);
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
        MouseOverArea result = null;
        assertNull(result);
        result = defaultInstance.createHighscoresButton(mockedContainer);
        assertNotNull(result);
    }

    @Test
    public void testIsAreaClickedClickAndHoverReturnsTrue() {
        MouseOverArea mockedMouseOver = Mockito.mock(MouseOverArea.class);
        Input mockedInput = Mockito.mock(Input.class);

        Mockito.when(mockedMouseOver.isMouseOver()).thenReturn(true);
        Mockito.when(mockedInput.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);

        assertTrue(defaultInstance.isAreaClicked(mockedMouseOver, mockedInput));
    }

    @Test
    public void testIsAreaClickedClickButNoHoverReturnsFalse() {
        MouseOverArea mockedMouseOver = Mockito.mock(MouseOverArea.class);
        Input mockedInput = Mockito.mock(Input.class);

        Mockito.when(mockedMouseOver.isMouseOver()).thenReturn(false);
        Mockito.when(mockedInput.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)).thenReturn(true);

        assertFalse(defaultInstance.isAreaClicked(mockedMouseOver, mockedInput));
    }

    @Test
    public void testIsAreaClickedHoverButNoClickReturnsTrue() {
        MouseOverArea mockedMouseOver = Mockito.mock(MouseOverArea.class);
        Input mockedInput = Mockito.mock(Input.class);

        Mockito.when(mockedMouseOver.isMouseOver()).thenReturn(true);
        Mockito.when(mockedInput.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)).thenReturn(false);

        assertFalse(defaultInstance.isAreaClicked(mockedMouseOver, mockedInput));
    }

    @Test
    public void testIsAreaClickedNoHoverAndNoClickReturnsFalse() {
        MouseOverArea mockedMouseOver = Mockito.mock(MouseOverArea.class);
        Input mockedInput = Mockito.mock(Input.class);

        Mockito.when(mockedMouseOver.isMouseOver()).thenReturn(false);
        Mockito.when(mockedInput.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)).thenReturn(false);

        assertFalse(defaultInstance.isAreaClicked(mockedMouseOver, mockedInput));
    }

    @Test
    public void testGetSetResources() {
        assertEquals(mockedResources, defaultInstance.getResources());
        ResourcesWrapper newResources = Mockito.mock(ResourcesWrapper.class);
        defaultInstance.setResources(newResources);
        assertEquals(newResources, defaultInstance.getResources());
    }

}
