package nl.tudelft.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.semgroup4.GameEndedState;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameTest extends AbstractOpenGLTestCase {
    
    private StateBasedGame mockedSbg;
    private GameEndedState mockedGameEndedState;
    private ResourcesWrapper mockedResources;
    private LinkedList<Player> playerList;
    private Player mockedPlayer;
    
    /**
     * Instantiates all mocks and stubs for the relevant resources.
     */
    @Before
    public void setUp() {
        mockedSbg = mock(StateBasedGame.class); 
        mockedGameEndedState = mock(GameEndedState.class);
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
        playerList = new LinkedList<>();
        playerList.add(mockedPlayer);
    }

    /**
     * Test to see if the game constructor works.
     */
    @Test
    public void testGame() {
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
        assertFalse(game == null);
        assertTrue(game.getPlayers().equals(playerList));
    }

    /**
     * Test to see if the game constructor works.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGame2() {
        LinkedList<Player> emptyPlayerList = new LinkedList<>();
        Game game = new Game(mockedSbg, emptyPlayerList, 0, 0, mockedResources);
    }

    /**
     * Test to check the current level getter.
     */
    @Test
    public void testGetCurLevel() {
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
        assertEquals(game.getCurLevel().getId(), 1);
    }

    /**
     * Test to check the player lives getter.
     */
    @Test
    public void testGetPlayerLives() {
        when(mockedPlayer.getLives()).thenReturn(1);
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
        assertEquals(game.getPlayerLives(), 1);
    }

    /**
     * Test to check the reset players.
     */
    @Test
    public void testResetPlayers() {
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
        game.resetPlayers();
        verify(mockedPlayer, times(1)).reset();
    }

    /**
     * Test to check the level reset.
     * 
     * @throws SlickException
     *             - Resources not found.
     */
    // @Test
    // TODO Create injecatble Audio dependence and mock it for this test.
    public void testLevelReset1() throws SlickException {
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
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
    // TODO Create injecatble Audio dependence and mock it for this test.
    public void testLevelReset2() throws SlickException {
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
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
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
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
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
        mockedSbg.addState(mockedGameEndedState);
        System.out.println(mockedSbg.getState(6));
        assertEquals(game.getCurLevel().getId(), 1);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 2);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 3);
        game.nextLevel();
        assertEquals(game.getCurLevel().getId(), 4);
        when(mockedSbg.getState(6)).thenReturn(mockedGameEndedState);
        game.nextLevel();
        verify(mockedSbg, times(1)).enterState(6);
    }

    /**
     * Test to check the new Collisionhandler getter.
     */
    @Test
    public void testGetNewCollisionHandler() {
        Game game = new Game(mockedSbg, playerList, 0, 0, mockedResources);
        CollisionHandler<AbstractGameObject, AbstractGameObject> handler = null;
        assertEquals(handler, null);
        handler = game.getNewCollisionHandler();
        assertFalse(handler == null);
    }

    /**
     * Test to check the getter for container width.
     */
    @Test
    public void testGetContainerWidth() {
        Game game = new Game(mockedSbg, playerList, 1, 0, mockedResources);
        assertEquals(game.getContainerWidth(), 1);
    }

    /**
     * Test to check the getter for container height.
     */
    @Test
    public void testGetContainerHeight() {
        Game game = new Game(mockedSbg, playerList, 1, 1, mockedResources);
        assertEquals(game.getContainerWidth(), 1);
    }

    /**
     * Test to check the toRemove method.
     */
    @Test
    public void testToRemove1() {
        Game game = new Game(mockedSbg, playerList, 1, 1, mockedResources);
        assertTrue(game.getPlayers().contains(mockedPlayer));
        game.toRemove(mockedPlayer);
        assertTrue(game.getPlayerToDelete().contains(mockedPlayer));
    }

    /**
     * Test to check the toRemove method.
     */
    @Test
    public void testToRemove2() {
        Game game = new Game(mockedSbg, playerList, 1, 1, mockedResources);
        Projectile mockedObj = mock(Projectile.class);
        game.toRemove(mockedObj);
        assertTrue(game.getCurLevel().getToRemove().contains(mockedObj));
    }

    /**
     * Test to check the toAdd method.
     */
    @Test
    public void testToAdd1() {
        Game game = new Game(mockedSbg, playerList, 1, 1, mockedResources);
        Projectile mockedObj = mock(Projectile.class);
        game.toAdd(mockedObj);
        assertTrue(game.getCurLevel().getToAdd().contains(mockedObj));
    }

    /**
     * Test to check the toAdd method.
     */
    @Test
    public void testToAdd2() {
        Game game = new Game(mockedSbg, playerList, 1, 1, mockedResources);
        game.toAdd(mockedPlayer);
        assertFalse(game.getCurLevel().getToAdd().contains(mockedPlayer));
    }
}
