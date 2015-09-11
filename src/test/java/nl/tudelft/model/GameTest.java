package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameTest extends OpenGLTestCase {

    /**
     * Test to see if the game constructor works.
     */
    @Test
    public void testGame() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertFalse(game.equals(null));
        assertTrue(game.getPlayers().equals(playerList));
    }

    /**
     * Test to check the current level getter.
     */
    @Test
    public void testGetCurLevel() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertEquals(game.getCurLevel().getId(), 1);
    }

    /**
     * Test to check the player lives getter.
     */
    @Test
    public void testGetPlayerLives() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.getLives()).thenReturn(1);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertEquals(game.getPlayerLives(), 1);
    }

    /**
     * Test to check the reset players.
     */
    @Test
    public void testResetPlayers() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        game.resetPlayers();
        verify(mockedPlayer, times(1)).reset();
    }

    /**
     * Test to check the level reset.
     * @throws SlickException - Resources not found.
     */
    @Test
    public void testLevelReset1() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        when(mockedPlayer.getLives()).thenReturn(1);
        assertEquals(game.getCurLevel().getId(), 1);
        game.levelReset();
        assertEquals(game.getCurLevel().getId(), 1);
    }

    /**
     * Test to check the level reset.
     * @throws SlickException - Resources not found.
     */
    @Test
    public void testLevelReset2() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        when(mockedPlayer.getLives()).thenReturn(0);
        assertEquals(game.getCurLevel().getId(), 1);
        game.levelReset();
        verify(mockedSbg, times(1)).enterState(0);
    }

    /**
     * Test to check the level reset.
     * @throws SlickException - Resources not found.
     */
    @Test
    public void testNextLevel1() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertEquals(game.getCurLevel().getId(), 1);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 2);
    }

    /**
     * Test to check the level reset.
     * @throws SlickException - Resources not found.
     */
    @Test
    public void testNextLevel2() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertEquals(game.getCurLevel().getId(), 1);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 2);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 3);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 4);
        game.nextLevel();
        verify(mockedSbg, times(1)).enterState(0);
    }

    /**
     * Test to check the new Collisionhandler getter.
     */
    @Test
    public void testGetNewCollisionHandler() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        CollisionHandler<GameObject, GameObject> handler = null;
        assertEquals(handler, null);
        handler = game.getNewCollisionHandler();
        assertFalse(handler.equals(null));
    }

    /**
     * Test to check the getter for container width.
     */
    @Test
    public void testGetContainerWidthw() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 0);
        assertEquals(game.getContainerWidth(), 1);
    }

    /**
     * Test to check the getter for container height.
     */
    @Test
    public void testGetContainerHeight() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 1);
        assertEquals(game.getContainerWidth(), 1);
    }
}
