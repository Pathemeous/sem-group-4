package nl.tudelft.semgroup4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreenStateTest {

    private StartScreenState state;
    private StartScreenStateController mockedController;
    private ResourcesWrapper mockedResources;
    private Input mockedInput;
    private GameContainer mockedContainer;
    private StateBasedGame mockedSBG;

    /**
     * Sets up all dependencies.
     * 
     */
    @Before
    public void setUp() {
        state = new StartScreenState();
        mockedController = Mockito.mock(StartScreenStateController.class);
        state.setController(mockedController);
        mockedResources = Mockito.mock(ResourcesWrapper.class);
        state.setResources(mockedResources);
        mockedInput = Mockito.mock(Input.class);
        state.setInput(mockedInput);

        mockedContainer = Mockito.mock(GameContainer.class);
        mockedSBG = Mockito.mock(StateBasedGame.class);
    }

    @Test
    public void testGetID() {
        assertEquals(States.StartScreenState, state.getID());
    }

    @Test
    public void testStartScreenState() {
        assertNotNull(state.getController());
    }

    @Test
    public void testInit() throws SlickException {
        state.init(mockedContainer, mockedSBG);

        Mockito.verify(mockedController).createPlayer1Button(mockedContainer);
        Mockito.verify(mockedController).createPlayer2Button(mockedContainer);
        Mockito.verify(mockedController).createOptionsButton(mockedContainer);
        Mockito.verify(mockedController).createQuitButton(mockedContainer);
        Mockito.verify(mockedController).createHighscoresButton(mockedContainer);
    }

    @Test
    public void testRender() throws SlickException {
        Mockito.when(mockedContainer.getWidth()).thenReturn(5);
        Mockito.when(mockedContainer.getHeight()).thenReturn(5);

        Image mockedImage = Mockito.mock(Image.class);
        Mockito.when(mockedImage.getWidth()).thenReturn(5);
        Mockito.when(mockedImage.getHeight()).thenReturn(5);
        Mockito.when(mockedResources.getTitleScreenBackground()).thenReturn(mockedImage);

        Graphics mockedGraphics = Mockito.mock(Graphics.class);

        state.render(mockedContainer, mockedSBG, mockedGraphics);

        Mockito.verify(mockedController).drawHighScoresButton(mockedGraphics);
        Mockito.verify(mockedGraphics).drawImage(mockedResources.getTitleScreenBackground(),
                0, 0, mockedContainer.getWidth(), mockedContainer.getHeight(), 0, 0,
                mockedResources.getTitleScreenBackground().getWidth(),
                mockedResources.getTitleScreenBackground().getHeight());
    }

    @Test
    public void testUpdateMusicNotPlayingIsTurnedOn() throws SlickException {
        Music mockedMusic = Mockito.mock(Music.class);
        Mockito.when(mockedResources.getTitleScreenMusic()).thenReturn(mockedMusic);
        Mockito.when(mockedMusic.playing()).thenReturn(false);

        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);

        state.update(mockedContainer, mockedSBG, 0);

        Mockito.verify(mockedResources).playTitleScreen();
    }

    @Test
    public void testUpdateMusicPlayingRemainsOn() throws SlickException {
        Music mockedMusic = Mockito.mock(Music.class);
        Mockito.when(mockedResources.getTitleScreenMusic()).thenReturn(mockedMusic);
        Mockito.when(mockedMusic.playing()).thenReturn(true);

        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);

        state.update(mockedContainer, mockedSBG, 0);

        Mockito.verify(mockedResources, Mockito.never()).stopTitleScreen();
    }

    @Test
    public void testUpdateHighscoresButtonNoClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);
        state.updateHighscoresButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.HighscoresState);
    }

    @Test
    public void testUpdateOptionsButtonNoClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);
        state.updateOptionsButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.OptionsState);
    }

    @Test
    public void testUpdatePlayer1ButtonNoClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);
        state.updatePlayer1Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.GameState);
    }

    @Test
    public void testUpdatePlayer2ButtonNoClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);
        state.updatePlayer2Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.GameState);
    }

    @Test
    public void testUpdateExitButtonNoClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                false);
        state.updateExitButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedContainer, Mockito.never()).exit();
    }

    @Test
    public void testUpdateHighscoresButtonClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.HighscoresState)).thenReturn(mockedNewState);

        state.updateHighscoresButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.HighscoresState);
    }

    @Test
    public void testUpdatePlayer1ButtonClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.GameState)).thenReturn(mockedNewState);

        state.updatePlayer1Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.GameState);
    }

    @Test
    public void testUpdatePlayer2ButtonClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.GameState)).thenReturn(mockedNewState);

        state.updatePlayer2Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.GameState);
    }

    //
    // @Test
    // public void testUpdateOptionsButtonClick() throws SlickException {
    // Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
    // true);
    // GameState mockedNewState = Mockito.mock(GameState.class);
    // Mockito.when(mockedSBG.getState(States.OptionsState)).thenReturn(mockedNewState);
    //
    // state.updateOptionsButton(mockedContainer, mockedSBG);
    // Mockito.verify(mockedSBG).enterState(States.OptionsState);
    // }

    @Test
    public void testUpdateExitButtonClick() throws SlickException {
        Mockito.when(mockedController.isAreaClicked(Mockito.any(), Mockito.any())).thenReturn(
                true);
        state.updateExitButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedContainer).exit();
    }

}
