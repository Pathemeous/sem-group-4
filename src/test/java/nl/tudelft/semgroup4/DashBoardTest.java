package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class DashBoardTest {
    
    private Dashboard dashboard;
    private Game mockedGame;
    private ResourcesWrapper mockedWrapper;
    private TrueTypeFont mockedTtf;
    private Graphics mockedGraphics;
    private Container mockedContainer;

    @Before 
    public void setup() {        
        mockedWrapper = Mockito.mock(ResourcesWrapper.class);
        mockedGame = Mockito.mock(Game.class);
        mockedTtf = Mockito.mock(TrueTypeFont.class);
        mockedGraphics = Mockito.mock(Graphics.class);
        mockedContainer = Mockito.mock(Container.class);
        
        dashboard = new Dashboard(mockedWrapper, mockedGame, 0, 0, 0);
        
        dashboard.setTtf(mockedTtf);
    }
    
    @Test
    public void testRender() throws SlickException {
        dashboard.render(mockedContainer, mockedGraphics);
        
        verify(mockedTtf, times(1)).drawString(0, 0, null, Color.black);
    }
}
