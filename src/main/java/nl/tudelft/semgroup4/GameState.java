package nl.tudelft.semgroup4;

import java.util.LinkedList;

import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {
    
    private PauseScreen pauseScreen;
    private MouseOverArea mouseOver;
    private Input input = new Input(0);
    private Game currentGame;
    private Dashboard dashboard;
    private final boolean singlePlayer;
    private boolean pauseScreenOpened = false;

    public GameState(String title, boolean singlePlayer) {
        super();
        this.singlePlayer = singlePlayer;
    }

    /**
     * Starts the state.
     * 
     * @param container
     *            GameContainer - The container in which the game exists.
     * @param mainApp
     *            StateBasedGame - the main state manager
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        final ResourcesWrapper res = new ResourcesWrapper();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        input = container.getInput();
        mouseOver =
                new MouseOverArea(container, res.getQuitText(), container.getHeight() / 2,
                        container.getHeight() / 2, res.getQuitText().getWidth(), res
                                .getQuitText().getHeight());
        pauseScreen = new PauseScreen(new ResourcesWrapper(), mouseOver);
        // Resources.titleScreenMusic.stop();

        // todo input

        LinkedList<Player> playerList = new LinkedList<>();

        // players are initialized with a certain Y coordinate, this should be refactored to be
        // more
        // flexible
        Player firstPlayer =
                new Player(new ResourcesWrapper(), container.getWidth() / 2,
                        container.getHeight() - res.getPlayerImageStill().getHeight() - 5
                                * res.getWallImage().getHeight(), input, true);
        playerList.add(firstPlayer);

        if (!singlePlayer) {
            Player secondPlayer =
                    new Player(new ResourcesWrapper(), container.getWidth() / 2 + 100,
                            container.getHeight() - res.getPlayerImageStill().getHeight() - 5
                                    * res.getWallImage().getHeight(), input, false);
            playerList.add(secondPlayer);
        }

        currentGame =
                new Game(mainApp, playerList, container.getWidth(), container.getHeight(),
                        new ResourcesWrapper());
        for (Player player : playerList) {
            currentGame.toAdd(player.getWeapon());
        }

        int dashboardMargin = 20;
        dashboard = new Dashboard(new ResourcesWrapper(), currentGame,
                        dashboardMargin,
                        container.getWidth() - dashboardMargin,
                        container.getHeight());
    }

    /**
     * Renders the state with every tick.
     * 
     * @param container
     *            GameContainer - The container in which the game exists.
     * @param game
     *            StateBasedGame - the main state manager
     * @param graphics
     *            Graphics - the Slick graphics.
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {

        currentGame.render(container, graphics);
        dashboard.render(container, graphics);

        if (pauseScreenOpened) {
            ResourcesWrapper res = new ResourcesWrapper();
            if (res.getWeaponFire().playing()) {
                res.stopFireSound();                
            }
            pauseScreen.show(graphics, container, input, game, this);
        }

    }

    /**
     * Updates the state with every tick.
     * 
     * @param container
     *            GameContainer - The container in which the game exists.
     * @param mainApp
     *            StateBasedGame - the main state manager
     * @param delta
     *            int - the amount of time that has passed since this method was last called.
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public void update(GameContainer container, StateBasedGame mainApp, int delta)
            throws SlickException {
        // checks if the escape key is pressed, if so, the gameState pauses
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Game", "Player "
                    + (currentGame.isPaused() ? "resumed" : "paused") + " the game");
            input.disableKeyRepeat();
            currentGame.setPaused(!currentGame.isPaused());
            pauseScreenOpened = !pauseScreenOpened;
        }

        if (!currentGame.isPaused()) {
            currentGame.update(delta);
            dashboard.update(delta);
        }
    }
    
    protected Game getGame() {
        return currentGame;
    }

    @Override
    public int getID() {
        if (singlePlayer) {
            return 1;
        } else {
            return 2;
        }

    }
}
