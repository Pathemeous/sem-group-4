package nl.tudelft.semgroup4;


import java.util.ArrayList;
import java.util.LinkedList;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.Game;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import nl.tudelft.model.Weapon;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.Pickup.WeaponType;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;
import nl.tudelft.semgroup4.util.QuadTree;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.geom.Rectangle;
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
    QuadTree quad;
   

    public GameState(String title) {
        super();
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
        Player firstPlayer = new Player(container.getWidth() / 2, container.getHeight()
                - Resources.playerImageStill.getHeight() - Resources.wallImage.getHeight(), input,
                weapon);

        LinkedList<Player> playerList = new LinkedList<>();
        playerList.add(firstPlayer);
        theGame = new Game(playerList, container.getWidth(), container.getHeight());
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

      theGame.render(container, g);
        
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
		}
    }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
