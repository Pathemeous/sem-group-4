package nl.tudelft.controller;

import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.CollisionController;
import nl.tudelft.model.AbstractGame;

import nl.tudelft.view.GameState;
import nl.tudelft.view.PauseScreen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Controls the GameState.
 * @author Casper
 *
 */
public class GameStateController {
    
    private final AbstractGame currentGame;
    private CollisionController collisions;
    
    /**
     * Creates a game state controller, which controls the logic for the 
     * GameState.
     * @param game : the current game this GameState is based on.
     */
    public GameStateController(AbstractGame game) {
        this.currentGame = game;
        collisions = new CollisionController(currentGame);
    }
    
    /**
     * Checks if the game is paused with the pause screen opened (which would toggle 
     * the pausescreen to close) or if the game is running (which would toggle the 
     * pausescreen to open.
     * @param pauseScreenOpened : boolean that indicates if the pause screen is open.
     * @param input : slick input. 
     * @return : boolean that indicates if the pause screen is open.
     */
    public boolean togglePauseMenu(boolean pauseScreenOpened, Input input) {
        // If the game is paused and the pause screen is openend, or if the
        // game isn't paused, this code is executed. This prevents the user from
        // being able to unpause the game while the countdown is running (because then
        // the game is paused without the pause screen being open)
        if (currentGame.isPaused() && pauseScreenOpened
                || !(currentGame.isPaused() || pauseScreenOpened)) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Game", "Player "
                    + (currentGame.isPaused() ? "resumed" : "paused") + " the game");
            input.disableKeyRepeat();
            currentGame.setPaused(!currentGame.isPaused());
            pauseScreenOpened = !pauseScreenOpened;
        }
        
        return pauseScreenOpened;
    }
    
    /**
     * Updates the current game. If the game is not paused, the game itself is
     * updated. If the game is paused, it might mean that the countdown is 
     * still running, so the countdown is updated. 
     * @param delta : does nothing at all.
     * @throws SlickException : slick exception.
     */
    public void updateGame(int delta) throws SlickException {
        if (currentGame.isPaused()) {
            currentGame.getCountdown().update();
        } else {
            currentGame.update(delta);
            collisions.updateCollisions();
        }
    }
    
    /**
     * Method that checks if the pausescreen is openend. If so, it shows it.
     * @param pauseScreenOpened : boolean to indicate if the pause screen
     *      is opened.
     * @param pauseScreen : view for the pausescreen.
     * @param graphics : Slick2D graphics.
     * @param container : Slick2D GameContainer.
     * @param input : Slick2D input.
     * @param game : Slick2D StateBasedGame.
     * @param gamestate : view for the Game. 
     */
    public void showPauseScreen(boolean pauseScreenOpened, PauseScreen pauseScreen,
            Graphics graphics, GameContainer container, Input input, 
            StateBasedGame game, GameState gamestate) {
        
        if (pauseScreenOpened) {
            ResourcesWrapper res = new ResourcesWrapper();
            if (res.getWeaponFire() != null && res.getWeaponFire().playing()) {
                res.stopFireSound();
            }
            pauseScreen.show(graphics, container, input, game, gamestate);
        }
    }

    public AbstractGame getGame() {
        return currentGame;
    }

    public void setCollisionController(CollisionController controller) {
        this.collisions = controller;
    }
}
