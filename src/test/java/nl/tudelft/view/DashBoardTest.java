package nl.tudelft.view;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.DashboardController;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractGame;

import nl.tudelft.view.Dashboard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

public class DashBoardTest {

    private Dashboard dashboard;
    private AbstractGame mockedGame;
    private ResourcesWrapper mockedWrapper;
    private TrueTypeFont mockedTtf;
    private Graphics mockedGraphics;
    private Container mockedContainer;
    private Image mockedImage;
    private Rectangle mockedTimerBar;
    private DashboardController mockedController;

    /**
     * Run before every test, sets up the mocks for every class.
     */
    @Before
    public void setup() {
        mockedWrapper = Mockito.mock(ResourcesWrapper.class);
        mockedGame = Mockito.mock(AbstractGame.class);
        mockedTtf = Mockito.mock(TrueTypeFont.class);
        mockedGraphics = Mockito.mock(Graphics.class);
        mockedContainer = Mockito.mock(Container.class);
        mockedImage = Mockito.mock(Image.class);
        mockedTimerBar = Mockito.mock(Rectangle.class);
        mockedController = Mockito.mock(DashboardController.class);

        when(mockedWrapper.createFont(any(), anyBoolean())).thenReturn(
                mockedTtf);
        when(mockedWrapper.getDashboardPlayerContainerLeft()).thenReturn(
                mockedImage);
        when(mockedWrapper.getDashboardPlayerContainerRight()).thenReturn(
                mockedImage);
        when(mockedWrapper.getDashboardLivesContainer())
                .thenReturn(mockedImage);
        when(mockedWrapper.getDashboardlivesFull()).thenReturn(mockedImage);
        when(mockedWrapper.getDashboardlivesEmpty()).thenReturn(mockedImage);
        when(mockedWrapper.getLevelContainer()).thenReturn(mockedImage);

        dashboard = new Dashboard(mockedWrapper, mockedGame, 0, 0, 0);
        dashboard.setTimeBar(mockedTimerBar);
        dashboard.setTimeBarBackground(mockedTimerBar);
        dashboard.setController(mockedController);
    }

    @Test
    public void testRender() throws SlickException {
        dashboard.render(mockedContainer, mockedGraphics);

        verify(mockedTtf, times(5))
                .drawString(anyInt(), anyInt(), any(), any());
        verify(mockedGraphics, times(5)).drawImage(any(), anyInt(), anyInt());
        verify(mockedGraphics, times(3)).setColor(any());
        verify(mockedGraphics, times(2)).fill(any());
        verify(mockedTimerBar, times(2)).setY(anyInt());
    }

    @Test
    public void testUpdate() throws SlickException {
        dashboard.update(0);

        verify(mockedController, times(1)).setTimeBar(any(), anyInt(),
                anyInt(), anyInt());
        verify(mockedController, times(1)).setLevel();
        verify(mockedController, times(1)).setPlayerInfo();
    }
}
