package nl.tudelft.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestSuite;

import nl.tudelft.semgroup4.OpenGLTestCase;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.collision.CollisionHandler;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameTest extends OpenGLTestCase {
	
    public static Test suite() {
        return new TestSuite(GameTest.class);
    }

    /**
     * Test to see if the game constructor works.
     */
    public void testGame() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertFalse(game.equals(null));
        assertTrue(game.getPlayers().equals(playerList));
    }

    /**
     * Test to check the current level getter.
     */
    public void testGetCurLevel() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertEquals(game.getCurLevel().getId(), 1);
    }

    /**
     * Test to check the player lives getter.
     */
    public void testGetPlayerLives() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        when(mockedPlayer.getLives()).thenReturn(1);
        LinkedList<Player> playerList = new LinkedList<Player>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        assertEquals(game.getPlayerLives(), 1);
    }

    /**
     * Test to check the reset players.
     */
    public void testResetPlayers() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 0, 0);
        game.resetPlayers();
        verify(mockedPlayer, times(1)).reset();
    }

    /**
     * Test to check the level reset.
     * @throws SlickException - Resources not found.
     */
    public void testLevelReset1() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
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
    public void testLevelReset2() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
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
    public void testNextLevel1() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
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
    public void testNextLevel2() throws SlickException {
        Resources.init();
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
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
    public void testGetNewCollisionHandler() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
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
    public void testGetContainerWidth() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 0);
        assertEquals(game.getContainerWidth(), 1);
    }

    /**
     * Test to check the getter for container height.
     */
    public void testGetContainerHeight() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<Player>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 1);
        assertEquals(game.getContainerWidth(), 1);
    }
}
