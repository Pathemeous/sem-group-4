package nl.tudelft.semgroup4;

import java.util.LinkedList;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.util.QuadTree;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {
    LinkedList<Wall> walls;
    LinkedList<Bubble> bubbles;
    LinkedList<Projectile> projectiles;
    LinkedList<Player> players;
    boolean paused;
    PauseScreen pauseScreen;
    MouseOverArea mouseOver;
    LinkedList<Pickup> pickups;
    Input input = new Input(0);
    private Game theGame;
    private Dashboard dashboard;
    private boolean singlePlayer;
    QuadTree quad;

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
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        input = container.getInput();
        mouseOver =
                new MouseOverArea(container, Resources.quitText, container.getHeight() / 2,
                        container.getHeight() / 2, Resources.quitText.getWidth(),
                        Resources.quitText.getHeight());
        pauseScreen = new PauseScreen(mouseOver);
        // Resources.titleScreenMusic.stop();

        // todo input

        LinkedList<Player> playerList = new LinkedList<>();

        // players are initialized with a certain Y coordinate, this should be refactored to be
        // more
        // flexible
        Player firstPlayer =
                new Player(container.getWidth() / 2, container.getHeight()
                        - Resources.playerImageStill.getHeight() - 5
                        * Resources.wallImage.getHeight(), input, true);
        playerList.add(firstPlayer);

        if (!singlePlayer) {
            Player secondPlayer =
                    new Player(container.getWidth() / 2 + 100, container.getHeight()
                            - Resources.playerImageStill.getHeight() - 5
                            * Resources.wallImage.getHeight(), input, false);
            playerList.add(secondPlayer);
        }

        theGame = new Game(mainApp, playerList, container.getWidth(), container.getHeight());
        int dashboardMargin = 10;
        dashboard =
                new Dashboard(theGame, 2 * dashboardMargin, container.getWidth() - 4
                        * dashboardMargin, container.getHeight());
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

        theGame.render(container, graphics);
        dashboard.render(container, graphics);

        if (paused) {
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
        if (Resources.titleScreenMusic.playing()) {
            Resources.titleScreenMusic.stop();
        }
        // checks if the escape key is pressed, if so, the gameState pauses

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.disableKeyRepeat();
            paused = !paused;
        }

        if (!paused) {
            theGame.update(delta);
            dashboard.update(delta);
        }
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
