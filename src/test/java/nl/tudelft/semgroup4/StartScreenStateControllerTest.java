package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.MouseOverArea;

public class StartScreenStateControllerTest {

    private ResourcesWrapper mockedResources;
    private StartScreenStateController defaultInstance;

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
