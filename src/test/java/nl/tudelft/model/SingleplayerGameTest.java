package nl.tudelft.model;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class SingleplayerGameTest {

    private SingleplayerGame game;
    private Player mockedPlayer;

    /**
     * Instantiates all mocks and stubs for the relevant resources.
     */
    @Before
    public void setup() {
        mockedPlayer = mock(Player.class);

        Image mockedImage = mock(Image.class);

        ResourcesWrapper mockedResources = mock(ResourcesWrapper.class);

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

        StateBasedGame stateBasedGame = mock(StateBasedGame.class);

        game = new SingleplayerGame(stateBasedGame, 0, 0, mockedResources, mockedPlayer);
    }

    @Test
    public void testGetPlayers() {
        assertArrayEquals(game.getPlayers(), new Player[]{mockedPlayer});
    }





}
