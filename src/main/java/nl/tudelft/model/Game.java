package nl.tudelft.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import nl.tudelft.controller.Renderable;
import nl.tudelft.controller.logger.DefaultLogger;
import nl.tudelft.controller.logger.Logger;
import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.view.GameEndedState;
import nl.tudelft.view.ShopState;
import nl.tudelft.view.States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The Game class represents a game session. A game can be single player or multiplayer, and
 * contains a list of levels and players.
 */
public abstract class Game implements Renderable {

    public static final Logger LOGGER;

    static {
        try {
            LOGGER = new DefaultLogger();
        } catch (IOException e) {
            throw new IllegalStateException("This shouldn't happen", e);
        }
    }

    private final int containerWidth;
    private final int containerHeight;
    private final Iterator<Level> levelIt;
    private Level curLevel;
    private final LevelFactory levelFact;
    private final StateBasedGame mainApp;
    private final ResourcesWrapper resources;
    private boolean paused = false;
    private Countdown countdown;

    /**
     * Creates a Game with its levels and players. Note that the levels and players must both
     * contain at least one object.
     * 
     * @param mainApp
     *            {@link StateBasedGame} - the mainApp that manages the states.
     * @param containerWidth
     *            int - width of the game field.
     * @param containerHeight
     *            int - height of the game field.
     * @param wrapper
     *            {@link ResourcesWrapper} - The resources that Game can inject into LevelFactory.
     * @throws IllegalArgumentException
     *             - If <code>levels</code> or <code>players</code> is empty.
     */
    public Game(StateBasedGame mainApp, int containerWidth,
                int containerHeight, ResourcesWrapper wrapper) {
        // LOGGER.log(VERBOSE, "Game", "constructor called");
        this.mainApp = mainApp;
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.levelFact = new LevelFactory(this, wrapper);
        LinkedList<Level> levels = levelFact.getAllLevels();

        this.resources = wrapper;
        this.levelIt = levels.iterator();

        this.curLevel = this.levelIt.next();
        countdown = new Countdown(this, wrapper);
    }


    /**
     * Performs updates and logic on the Game object. This makes sure the game continues to
     * function.
     * 
     * <p>
     * Delegates all updating to the currentLevel. Checks all collisions.
     * </p>
     * 
     * @param delta
     *            int - the amount of milliseconds since this method was last called.
     * @throws SlickException
     *             - If the game engines crashes.
     */
    public void update(int delta) throws SlickException {
        // updates
        playerUpdate(delta);
        getCurLevel().update(getCurLevel(), delta);

        levelCompleted();

        levelTimeExpired();
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        getCurLevel().render(container, graphics);

        for (Player player : getPlayers()) {
            if (player.isAlive()) {
                player.render(container, graphics);
            }
        }

        countdown.render(container, graphics);
    }

    /**
     * Tests whether the current level is completed and if so, logs this event and proceeds to the
     * next level.
     */
    private void levelCompleted() {
        if (getCurLevel().isCompleted()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game",
                    "Level has been completed. Go to next level!");
            nextLevel();
        } 
    }

    /**
     * Tests whether the current level's timer has expired. If so, logs this event, makes the
     * player lose a life, and resets the current level.
     */
    private void levelTimeExpired() {
        if (getCurLevel().timerExpired()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Time has expired");

            resources.playTimeUp();
            for (Player player : getPlayers()) {
                player.removeLife();
            }
            levelReset();
        }
    }

    /**
     * Performs updates for the player, checks the collisions and manages the player list.
     * 
     * @param delta
     *            the time between updates.
     * @throws SlickException
     *             exception from Slick if something goes wrong.
     */
    private void playerUpdate(int delta) throws SlickException {
        for (Player player : getPlayers()) {
            if (player.isAlive()) {
                player.update(getCurLevel(), delta);
            }
        }
    }

    /**
     * Sets the current level.
     * 
     * @param level
     *            Level object to set as the current level.
     */
    private void setCurLevel(Level level) {
        this.curLevel = level;
        countdown.reset();
    }

    /**
     * Returns the current level.
     * 
     * @return Level - the current level.
     */
    public Level getCurLevel() {
        return this.curLevel;
    }

    /**
     * Returns the amount of lives that the players have left.
     * 
     * <p>
     * When the players run out of lives, the game is over.
     * </p>
     * 
     * @return int - the total amount of lives left until the game is over.
     */
    protected int getPlayerLives() {
        int result = 0;
        for (Player player : getPlayers()) {
            result += player.getLives();
        }
        return result;
    }

    /**
     * Calls {@link Player#reset()} on all players in the game.
     */
    protected void resetPlayers() {
        for (Player player : getPlayers()) {
            player.reset();
        }
    }

    /**
     * Resets the current level if the players have lives left, ends the game if they do not.
     */
    public void levelReset() {
        resources.stopFireSound();
        if (getPlayerLives() > 0) {
            resetPlayers();
            setCurLevel(levelFact.getLevel(getCurLevel().getId()));
        } else {
            gameOver();
        }
    }

    /**
     * Tries to set the next level as the current level. If there is no next level, the game is
     * completed and {@link #gameCompleted()} is called.
     */
    protected void nextLevel() {
        if (levelIt.hasNext()) {
            resetPlayers();
            int score = (getCurLevel().getTime() / getCurLevel().getMaxTime()) * 500;
            for (Player player : getPlayers()) {
                player.setScore(player.getScore() + score);
            }
            setCurLevel(levelIt.next());
            
            ((ShopState) mainApp.getState(States.ShopState)).setup(this);
            mainApp.enterState(States.ShopState); 
        } else {
            gameCompleted();
        }
    }

    /**
     * The game has been completed.
     */
    private void gameCompleted() {
        Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Player has won the game!");
        ((GameEndedState) mainApp.getState(States.GameEndedState)).setup(getPlayers(), true);
        mainApp.enterState(States.GameEndedState);
    }

    /**
     * The game has been lost. Returns to the main screen.
     * 
     * <p>
     * This happens when the players run out of lives.
     * </p>
     */
    private void gameOver() {
        Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Game over for the player");
        ((GameEndedState) mainApp.getState(States.GameEndedState)).setup(getPlayers(), false);
        mainApp.enterState(States.GameEndedState);
    }

    public int getContainerWidth() {
        return this.containerWidth;
    }

    public int getContainerHeight() {
        return this.containerHeight;
    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    public Countdown getCountdown() {
        return countdown;
    }

    /**
     * Gets the players.
     *
     * @return Player[] - player(s) of the game.
     */
    public abstract Player[] getPlayers();
}
