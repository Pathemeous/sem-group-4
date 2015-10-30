package nl.tudelft.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Font;

import nl.tudelft.controller.StartScreenStateController;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractGame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreenStateTest {

    private StartScreenState state;
    private StartScreenStateController mockedController;
    private ResourcesWrapper mockedResources;
    private Input mockedInput;
    private GameContainer mockedContainer;
    private StateBasedGame mockedSBG;
    private TrueTypeFont mockedFont;
    private MouseOverArea mockedMouseOverArea;

    /**
     * Sets up all dependencies.
     * 
     * @throws SlickException
     *             If the setup goes wrong.
     */
    @Before
    public void setUp() throws SlickException {
        Image mockedImage = Mockito.mock(Image.class);
        Mockito.when(mockedImage.getWidth()).thenReturn(5);
        Mockito.when(mockedImage.getHeight()).thenReturn(5);
        mockedResources = Mockito.mock(ResourcesWrapper.class);
        Mockito.when(mockedResources.getTitleScreenBackground()).thenReturn(mockedImage);

        state = new StartScreenState(mockedResources);

        mockedController = Mockito.mock(StartScreenStateController.class);
        state.setController(mockedController);

        mockedMouseOverArea = Mockito.mock(MouseOverArea.class);
        Mockito.when(mockedController.createPlayer1Button(Mockito.any())).thenReturn(
                mockedMouseOverArea);
        Mockito.when(mockedController.createPlayer2Button(Mockito.any())).thenReturn(
                mockedMouseOverArea);
        Mockito.when(mockedController.createOptionsButton(Mockito.any())).thenReturn(
                mockedMouseOverArea);
        Mockito.when(mockedController.createHighscoresButton(Mockito.any())).thenReturn(
                mockedMouseOverArea);
        Mockito.when(mockedController.createQuitButton(Mockito.any())).thenReturn(
                mockedMouseOverArea);

        mockedFont = Mockito.mock(TrueTypeFont.class);
        Mockito.when(mockedResources.createFont(Mockito.any(), Mockito.anyBoolean()))
                .thenReturn(mockedFont);
        state.setResources(mockedResources);
        mockedInput = Mockito.mock(Input.class);
        state.setInputForTesting(mockedInput);

        mockedContainer = Mockito.mock(GameContainer.class);
        Mockito.when(mockedContainer.getInput()).thenReturn(mockedInput);
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

        Mockito.when(mockedInput.isMousePressed(Mockito.anyInt())).thenReturn(false);

        state.update(mockedContainer, mockedSBG, 0);

        Mockito.verify(mockedResources).playTitleScreen();
    }

    @Test
    public void testUpdateMusicPlayingRemainsOn() throws SlickException {
        Music mockedMusic = Mockito.mock(Music.class);
        Mockito.when(mockedResources.getTitleScreenMusic()).thenReturn(mockedMusic);
        Mockito.when(mockedMusic.playing()).thenReturn(true);

        Mockito.when(mockedInput.isMousePressed(Mockito.anyInt())).thenReturn(false);

        state.update(mockedContainer, mockedSBG, 0);

        Mockito.verify(mockedResources, Mockito.never()).stopTitleScreen();
    }

    @Test
    public void testUpdateButtonCallsAreMade() throws SlickException {
        Music mockedMusic = Mockito.mock(Music.class);
        Mockito.when(mockedResources.getTitleScreenMusic()).thenReturn(mockedMusic);
        Mockito.when(mockedMusic.playing()).thenReturn(true);

        Mockito.when(mockedInput.isMousePressed(Mockito.anyInt())).thenReturn(true);

        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(false);

        state.update(mockedContainer, mockedSBG, 0);

        Mockito.verify(mockedResources, Mockito.never()).stopTitleScreen();
    }

    @Test
    public void testUpdateHighscoresButtonNoHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(false);
        state.updateHighscoresButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.HighscoresState);
    }

    @Test
    public void testUpdateOptionsButtonNoHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(false);
        state.updateOptionsButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.OptionsState);
    }

    @Test
    public void testUpdatePlayer1ButtonNoHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(false);
        state.updatePlayer1Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.GameState);
    }

    @Test
    public void testUpdatePlayer2ButtonNoHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(false);
        state.updatePlayer2Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG, Mockito.never()).enterState(States.GameState);
    }

    @Test
    public void testUpdateExitButtonNoHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(false);
        state.updateExitButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedContainer, Mockito.never()).exit();
    }

    @Test
    public void testUpdateHighscoresButtonHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.HighscoresState)).thenReturn(mockedNewState);

        state.updateHighscoresButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.HighscoresState);
    }

    @Test
    public void testUpdatePlayer1ButtonHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.GameState)).thenReturn(mockedNewState);

        state.updatePlayer1Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.GameState);
    }

    @Test
    public void testUpdatePlayer2ButtonHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.GameState)).thenReturn(mockedNewState);

        state.updatePlayer2Button(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.GameState);
    }

    @Test
    public void testUpdateOptionsButtonHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(true);
        GameState mockedNewState = Mockito.mock(GameState.class);
        Mockito.when(mockedSBG.getState(States.OptionsState)).thenReturn(mockedNewState);
        state.setInput(mockedInput);

        state.updateOptionsButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedSBG).enterState(States.OptionsState);
    }

    @Test
    public void testUpdateExitButtonHover() throws SlickException {
        state.init(mockedContainer, mockedSBG);
        Mockito.when(mockedMouseOverArea.isMouseOver()).thenReturn(true);
        state.updateExitButton(mockedContainer, mockedSBG);
        Mockito.verify(mockedContainer).exit();
    }

    @Test
    public void testEnterGameState() throws SlickException {
        BasicGameState mockedState = Mockito.mock(BasicGameState.class);
        Mockito.when(mockedSBG.getState(States.GameState)).thenReturn(mockedState);
        AbstractGame mockedGame = Mockito.mock(AbstractGame.class);
        state.enterGameState(mockedContainer, mockedSBG, mockedGame);
        Mockito.verify(mockedSBG).enterState(Mockito.anyInt());
    }
}
