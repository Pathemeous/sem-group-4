package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Dashboard;
import nl.tudelft.semgroup4.DashboardController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.geom.Rectangle;

public class DashBoardControllerTest {

    private DashboardController dashboardController;
    private Game mockedGame;
    private Dashboard mockedDashboard;
    private Rectangle mockedRectangle;
    private Object mockedLevel;
    private Player[] mockedPlayers;
    private Player mockedPlayer;

    /**
     * Setup before each test, initialies the dashboard controller and all used
     * mocks.
     */
    @Before
    public void setup() {
        mockedGame = Mockito.mock(Game.class);
        mockedDashboard = Mockito.mock(Dashboard.class);
        mockedRectangle = Mockito.mock(Rectangle.class);
        mockedLevel = Mockito.mock(Level.class);
        mockedPlayer = Mockito.mock(Player.class);
        mockedPlayers = new Player[1];
        mockedPlayers[0] = mockedPlayer;

        when(mockedGame.getCurLevel()).thenReturn((Level) mockedLevel);
        when(mockedGame.getPlayers()).thenReturn(mockedPlayers);
        dashboardController = new DashboardController(mockedDashboard);
        dashboardController.setGame(mockedGame);
    }

    @Test
    public void testConstructor() {
        assertEquals(mockedGame, dashboardController.getGame());
        assertEquals(mockedDashboard, dashboardController.getDashboard());
    }

    @Test
    public void testSetTimeBar() {
        dashboardController.setTimeBar(mockedRectangle, 0, 0, 0);

        verify(mockedRectangle, times(1)).setWidth(anyInt());
    }

    @Test
    public void testSetPlayerInfo1() {
        when(mockedPlayer.isFirstPlayer()).thenReturn(true);
        dashboardController.setPlayerInfo();

        verify(mockedDashboard, times(1)).setLivesLeft(anyInt());
        verify(mockedDashboard, times(1)).setMoneyLeft(anyInt());
        verify(mockedDashboard, times(1)).setScoreLeft(anyInt());
    }

    @Test
    public void testSetPlayerInfo2() {
        dashboardController.setPlayerInfo();

        verify(mockedDashboard, times(1)).setLivesRight(anyInt());
        verify(mockedDashboard, times(1)).setMoneyRight(anyInt());
        verify(mockedDashboard, times(1)).setScoreRight(anyInt());
    }
    
    @Test
    public void testSetLevel() {
        dashboardController.setLevel();
        
        verify(mockedDashboard, times(1)).setLevelId(anyInt());
    }
}
