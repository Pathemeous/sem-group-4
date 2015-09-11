package nl.tudelft.model;

import java.util.LinkedList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import junit.framework.Test;
import junit.framework.TestSuite;
import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.OpenGLTestCase;
import nl.tudelft.semgroup4.Resources;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import static org.mockito.Mockito.*;

public class GameTest extends OpenGLTestCase {
	
	public static Test suite() {
		return new TestSuite(GameTest.class);
	}

	public void testGame() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		assertFalse(game.equals(null));
		assertTrue(game.getPlayers().equals(pList));
	}
	
	public void testGetCurLevel() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		assertEquals(game.getCurLevel().getId(), 1);
	}
	
	public void testGetPlayerLives() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		when(mockedPlayer.getLives()).thenReturn(1);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		assertEquals(game.getPlayerLives(), 1);
	}
	
	public void testResetPlayers() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		game.resetPlayers();
		verify(mockedPlayer, times(1)).reset();
	}
	
	public void testLevelReset1() throws SlickException {
		Resources.init();
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		when(mockedPlayer.getLives()).thenReturn(1);
		assertEquals(game.getCurLevel().getId(), 1);
		game.levelReset();
		assertEquals(game.getCurLevel().getId(), 1);
	}
	
	public void testLevelReset2() throws SlickException {
		Resources.init();
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		when(mockedPlayer.getLives()).thenReturn(0);
		assertEquals(game.getCurLevel().getId(), 1);
		game.levelReset();
		verify(mSBG, times(1)).enterState(0);
	}

	public void testNextLevel1() throws SlickException {
		Resources.init();
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		assertEquals(game.getCurLevel().getId(), 1);
		game.nextLevel();
		assertEquals(game.getCurLevel().getId(), 2);
	}
	
	public void testNextLevel2() throws SlickException {
		Resources.init();
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		assertEquals(game.getCurLevel().getId(), 1);
		game.nextLevel();
		assertEquals(game.getCurLevel().getId(), 2);
		game.nextLevel();
		assertEquals(game.getCurLevel().getId(), 3);
		game.nextLevel();
		assertEquals(game.getCurLevel().getId(), 4);
		game.nextLevel();
		verify(mSBG, times(1)).enterState(0);
	}
	
	public void testGetNewCollisionHandler() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 0, 0);
		CollisionHandler<GameObject, GameObject> handler = null;
		assertEquals(handler, null);
		handler = game.getNewCollisionHandler();
		assertFalse(handler.equals(null));
	}
	
	public void testGetContainerWidth() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 1, 0);
		assertEquals(game.getContainerWidth(), 1);
	}
	
	public void testGetContainerHeight() {
		StateBasedGame mSBG = mock(StateBasedGame.class);
		Player mockedPlayer = mock(Player.class);
		LinkedList<Player> pList = new LinkedList<Player>();
		pList.add(mockedPlayer);
		Game game = new Game(mSBG, pList, 1, 1);
		assertEquals(game.getContainerWidth(), 1);
	}
}
