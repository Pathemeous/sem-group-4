package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;


public class LoggerSetScreenControllerTest {

    private LoggerSetScreenController controller;
    private ResourcesWrapper mockedWrapper;

    /**
     * Create a controller before every test.
     */
    @Before
    public void setUp() {
        controller = new LoggerSetScreenController();
        mockedWrapper = mock(ResourcesWrapper.class);
    }

    @Test
    public void testDecideColor1() {
        Game.LOGGER.setSeverity(LogSeverity.CRITICAL);
        assertEquals(Color.green, controller.decideColor(LogSeverity.CRITICAL));
    }

    @Test
    public void testDecideColor2() {
        Game.LOGGER.setSeverity(LogSeverity.CRITICAL);
        assertEquals(Color.yellow, controller.decideColor(LogSeverity.ERROR));
    }

    @Test
    public void testCreateMouseOverAreas1() throws SlickException {
        TrueTypeFont typeFont = mock(TrueTypeFont.class);
        String[] text = {};
        GameContainer mockedGameContainer = mock(GameContainer.class);
        MouseOverArea[] areas = controller.createMouseOverAreas(
                10, 10, mockedGameContainer, text, typeFont,
                mockedWrapper);
        assertEquals(areas.length, 0);
    }

    @Test
    public void testCreateMouseOverAreas2() throws SlickException {
        TrueTypeFont typeFont = mock(TrueTypeFont.class);
        String[] text = {"TEST"};
        GameContainer mockedGameContainer = mock(GameContainer.class);
        Input mockedInput = mock(Input.class);
        when(mockedGameContainer.getInput()).thenReturn(mockedInput);
        Image mockedImage = mock(Image.class);
        when(mockedWrapper.createImage(anyInt(),anyInt())).thenReturn(mockedImage);
        MouseOverArea[] areas = controller.createMouseOverAreas(
                10, 10, mockedGameContainer, text, typeFont,
                mockedWrapper);
        assertEquals(areas.length, 1);
        assertEquals(areas[0].getX(), 10);
        assertEquals(areas[0].getY(), 10);
    }
}
