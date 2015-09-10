package nl.tudelft.semgroup4;


import nl.tudelft.model.Bubble;
import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import nl.tudelft.model.Weapon;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Pickup.WeaponType;
import nl.tudelft.semgroup4.util.QuadTree;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;


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
    Weapon weapon;
    private Game theGame;
    private Dashboard dashboard;
    private boolean singlePlayer;
    QuadTree quad;
   

    public GameState(String title, boolean singlePlayer) {
        super();
        this.singlePlayer = singlePlayer;
    }
    
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        input = container.getInput();
        mouseOver = new MouseOverArea(container, Resources.quitText, container.getHeight() / 2,
                container.getHeight() / 2, Resources.quitText.getWidth(),
                Resources.quitText.getHeight());
        pauseScreen = new PauseScreen(mouseOver);

        // todo input
        weapon = new Weapon(Resources.weaponImageRegular.copy(), WeaponType.REGULAR);


LinkedList<Player> playerList = new LinkedList<>();

        // players are initialized with a certain Y coordinate, this should be refactored to be more flexible
        Player firstPlayer = new Player(
                container.getWidth() / 2,
                container.getHeight() - Resources.playerImageStill.getHeight() - 5 * Resources.wallImage.getHeight(),
                input,
                weapon,
                true);
        playerList.add(firstPlayer);

        if(!singlePlayer) {
            Player secondPlayer = new Player(
                    container.getWidth() / 2 + 100,
                    container.getHeight() - Resources.playerImageStill.getHeight() - 5 *  Resources.wallImage.getHeight(),
                    input,
                    weapon,
                    false);
            playerList.add(secondPlayer);
        }

        theGame = new Game(mainApp, playerList, container.getWidth(), container.getHeight());
        int dashboardMargin = 10;
        dashboard = new Dashboard(theGame,
                2 * dashboardMargin,
                container.getWidth() - 2 * dashboardMargin,
                container.getHeight());
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        theGame.render(container, g);
        dashboard.render(container, g);
        
        if(paused) {          
          pauseScreen.show(g, container, input, game, this);
        }

    }

    public void update(GameContainer container, StateBasedGame mainApp, int delta) throws SlickException {
        //checks if the escape key is pressed, if so, the gameState pauses 
    	if (input.isKeyPressed(Input.KEY_ESCAPE)) { 
			input.disableKeyRepeat();
			paused = !paused;
		}
    	    	
		if(!paused) {
	        theGame.update(delta);
            dashboard.update(delta);
		}
    }

	@Override
	public int getID() {
		if(singlePlayer) {
			return 1;
		} else {
			return 2;
		}
		
	}
}
