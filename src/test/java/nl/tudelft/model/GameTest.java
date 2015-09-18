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
        assertFalse(game == null);
        assertTrue(game.getPlayers().equals(playerList));
    }

    /**
     * Test to see if the game constructor works.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGame2() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        LinkedList<Player> playerList = new LinkedList<>();
        Game game = new Game(mockedSbg, playerList, 0, 0);
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
     * 
     * @throws SlickException
     *             - Resources not found.
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
     * 
     * @throws SlickException
     *             - Resources not found.
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
     * 
     * @throws SlickException
     *             - Resources not found.
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
     * 
     * @throws SlickException
     *             - Resources not found.
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
        assertFalse(handler == null);
    }

    /**
     * Test to check the getter for container width.
     */
    @Test
    public void testGetContainerWidth() {
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

    /**
     * Test to check the toRemove method.
     */
    @Test
    public void testToRemove1() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 1);
        assertTrue(game.getPlayers().contains(mockedPlayer));
        game.toRemove(mockedPlayer);
        assertTrue(game.getPlayerToDelete().contains(mockedPlayer));
    }

    /**
     * Test to check the toRemove method.
     */
    @Test
    public void testToRemove2() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 1);
        Projectile mockedObj = mock(Projectile.class);
        game.toRemove(mockedObj);
        assertTrue(game.getCurLevel().getToRemove().contains(mockedObj));
    }

    /**
     * Test to check the toAdd method.
     */
    @Test
    public void testToAdd1() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 1);
        Projectile mockedObj = mock(Projectile.class);
        game.toAdd(mockedObj);
        assertTrue(game.getCurLevel().getToAdd().contains(mockedObj));
    }

    /**
     * Test to check the toAdd method.
     */
    @Test
    public void testToAdd2() {
        StateBasedGame mockedSbg = mock(StateBasedGame.class);
        Player mockedPlayer = mock(Player.class);
        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
        Game game = new Game(mockedSbg, playerList, 1, 1);
        game.toAdd(mockedPlayer);
        assertFalse(game.getCurLevel().getToAdd().contains(mockedPlayer));
    }
}
