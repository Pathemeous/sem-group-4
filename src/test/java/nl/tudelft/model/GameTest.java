package nl.tudelft.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;
import nl.tudelft.view.GameEndedState;
import nl.tudelft.view.ShopState;
import nl.tudelft.view.States;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameTest {

    private StateBasedGame mockedSbg;
    private GameEndedState mockedGameEndedState;
    private ShopState mockedShopState;
    private ResourcesWrapper mockedResources;
    private Player mockedPlayer;

    /**
     * Instantiates all mocks and stubs for the relevant resources.
     */
    @Before
    public void setUp() {
        mockedSbg = mock(StateBasedGame.class);
        mockedGameEndedState = mock(GameEndedState.class);
        mockedShopState = mock(ShopState.class);
        mockedResources = mock(ResourcesWrapper.class);
        Image mockedImage = mock(Image.class);
        when(mockedImage.getHeight()).thenReturn(1);
        when(mockedImage.getWidth()).thenReturn(1);
        when(mockedResources.getVwallImage()).thenReturn(mockedImage);
        when(mockedResources.getWallImage()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage1()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage2()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage3()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage4()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage5()).thenReturn(mockedImage);
        when(mockedResources.getBubbleImage6()).thenReturn(mockedImage);

        mockedPlayer = mock(Player.class);
    }

    /**
     * Test to see if the game constructor works.
     */
    @Test
    public void testGame() {
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        assertArrayEquals(game.getPlayers(), new Player[] { mockedPlayer });
    }

    /**
     * Test to check the current level getter.
     */
    @Test
    public void testGetCurLevel() {
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        assertEquals(game.getCurLevel().getId(), 1);
    }

    /**
     * Test to check the player lives getter.
     */
    @Test
    public void testGetPlayerLives() {
        when(mockedPlayer.getLives()).thenReturn(1);
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        assertEquals(game.getPlayerLives(), 1);
    }

    /**
     * Test to check the reset players.
     */
    @Test
    public void testResetPlayers() {
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        game.resetPlayers();
        verify(mockedPlayer, times(1)).reset();
    }

    /**
     * Test to check the level reset.
     *
     * @throws SlickException
     *             - Resources not found.
     */
    @Test
    public void testLevelReset1() throws SlickException {
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        when(mockedPlayer.getLives()).thenReturn(1);
        assertEquals(game.getCurLevel().getId(), 1);
        game.levelReset();
        assertEquals(game.getCurLevel().getId(), 1);
    }

    /**
     * Test to check the level reset.
     *
     * @throws SlickException
     *             - Resources not found.
     */
    // @Test
    public void testLevelReset2() throws SlickException {
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        when(mockedPlayer.getLives()).thenReturn(0);
        assertEquals(game.getCurLevel().getId(), 1);
        game.levelReset();
        verify(mockedSbg, times(1)).enterState(States.StartScreenState);
    }

    /**
     * Test to check the level reset.
     *
     * @throws SlickException
     *             - Resources not found.
     */
    @Test
    public void testNextLevel1() throws SlickException {
        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        when(mockedSbg.getState(States.ShopState)).thenReturn(mockedShopState);

        assertEquals(game.getCurLevel().getId(), 1);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 2);
    }

    /**
     * Test to check the level reset.
     *
     * @throws SlickException
     *             - Resources not found.
     */
    @Test
    public void testNextLevel2() throws SlickException {
        mockedSbg.addState(mockedGameEndedState);
        when(mockedSbg.getState(States.GameEndedState)).thenReturn(mockedGameEndedState);
        when(mockedSbg.getState(States.ShopState)).thenReturn(mockedShopState);

        AbstractGame game = new TestGame(mockedSbg, 0, 0, mockedResources, mockedPlayer);
        assertEquals(game.getCurLevel().getId(), 1);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 2);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 3);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 4);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 5);

        game.nextLevel();
        verify(mockedSbg, times(1)).enterState(States.GameEndedState);
    }

    /**
     * Test to check the getter for container width.
     */
    @Test
    public void testGetContainerWidth() {
        AbstractGame game = new TestGame(mockedSbg, 1, 0, mockedResources, mockedPlayer);
        assertEquals(game.getContainerWidth(), 1);
    }

    /**
     * Test to check the getter for container height.
     */
    @Test
    public void testGetContainerHeight() {
        AbstractGame game = new TestGame(mockedSbg, 1, 1, mockedResources, mockedPlayer);
        assertEquals(game.getContainerWidth(), 1);
    }

}
