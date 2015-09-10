package nl.tudelft.semgroup4;


import java.util.ArrayList;
import java.util.LinkedList;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.BubbleManager;
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
    LinkedList<GameObject> toDelete, toAdd;
    LinkedList<Wall> walls;
    LinkedList<Bubble> bubbles;
    LinkedList<Projectile> projectiles;
    LinkedList<Player> players;
    boolean paused;
    PauseScreen pauseScreen;
    MouseOverArea mouseOver;
    LinkedList<GameObject> pickups;
    Input input = new Input(0);
    Weapon weapon;
    private Game theGame;
    QuadTree quad;

    final CollisionHandler<GameObject, GameObject> collisionHandler;
   

    public GameState(String title) {
        super();
        collisionHandler = getNewCollisionHandler();
    }
    
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
        
        input = container.getInput();        
        mouseOver = new MouseOverArea(container, Resources.quitText, container.getHeight()/2,
        		container.getHeight() / 2, Resources.quitText.getWidth(), Resources.quitText.getHeight());
        pauseScreen = new PauseScreen(mouseOver);
            
        quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));
        walls = new LinkedList<>();
        projectiles = new LinkedList<>();
        players = new LinkedList<>();
        bubbles = new LinkedList<>();
        pickups = new LinkedList<>();
        toDelete = new LinkedList<>();
        toAdd = new LinkedList<>();

            for (int i = 0; i * Resources.vwallImage.getHeight() < container.getHeight(); i++) {
                walls.add(new Wall(Resources.vwallImage, 0, i * Resources.vwallImage.getHeight()));
                walls.add(new Wall(Resources.vwallImage, container.getWidth() - Resources.vwallImage.getWidth()
                		, i * Resources.vwallImage.getHeight()));
            }

            // NOTE: als je rotate dan staan width/height not voor dezeflde dimensies
            for (int i = 0; i * Resources.wallImage.getWidth() < container.getWidth(); i++) {
                walls.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), 0));
                walls.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), 
                		container.getHeight() - Resources.wallImage.getHeight()));
            }
            
            // Create Bubbles for level
            bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 100, 
                    container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 6));

            bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 200, 
                    container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 5));
            
            bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 300, 
                    container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 4));
            
            bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 400, 
                container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 3));
            
            bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 500, 
                    container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 2));
              
            bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 600, 
                    container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 1));
                    
                    // todo input
                    weapon = new Weapon(Resources.weaponImageRegular.copy(), WeaponType.REGULAR);
                    Player firstPlayer = new Player(
                        container.getWidth() / 2,
                        container.getHeight() - Resources.playerImageStill.getHeight() - Resources.wallImage.getHeight(),
                        input, weapon);
                    players.add(firstPlayer);
                    
                    double time = 120;
                    
                    Level firstLevel = new Level(walls, projectiles, players, bubbles, toDelete, toAdd, time, 1);
                    Level secondLevel = new Level(walls, projectiles, players, bubbles, toDelete, toAdd, time, 2);
                    Level thirdLevel = new Level(walls, projectiles, players, bubbles, toDelete, toAdd, time, 3);
                    
                    LinkedList<Level> levelList = new LinkedList<>();
                    levelList.add(firstLevel);
                    levelList.add(secondLevel);
                    levelList.add(thirdLevel);
                    LinkedList<Player> playerList = new LinkedList<>();
                    playerList.add(firstPlayer);
                    theGame = new Game(levelList, playerList);
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
			// collision 
	    	quad.clear();
	    	for (GameObject obj : walls) {
	    	  quad.insert(obj);
	    	}
	    	for (GameObject obj : projectiles) {
	      	  quad.insert(obj);
	      	}
	    	for (GameObject obj : players) {
	      	  quad.insert(obj);
	      	}
	    	
	    	// collision
	        for (GameObject collidesWithA : bubbles) {
	            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, quad)) {
	                collisionHandler.onCollision(collidesWithA, collidesWithB);	
	        	}
	            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, players, quad)) {
	                collisionHandler.onCollision(collidesWithA, collidesWithB);	
	        	}
	            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, projectiles, quad)) {
	                collisionHandler.onCollision(collidesWithA, collidesWithB);	
	        	}
	        }
	        
	        for (GameObject collidesWithA : projectiles) {
	            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, null)) {
	                collisionHandler.onCollision(collidesWithA, collidesWithB);	
	        	}
	        }
	        
	        for (GameObject collidesWithA : players) {
	            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, quad)) {
	                collisionHandler.onCollision(collidesWithA, collidesWithB);	
	        	}
	        }
	        for (GameObject collidesWithA : players) {
	            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, pickups, null)) {
	                collisionHandler.onCollision(collidesWithA, collidesWithB);	
	        	}
	        }
	        
	        for (GameObject collidesWithA : pickups) {
	        	for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, walls, null)) {
	        		collisionHandler.onCollision(collidesWithA, collidesWithB);
	        	}
	        }
	        
	        // Updates
	        theGame.update(delta);

	        // Addition and Deletion
	        for (GameObject gameObject : toAdd) {
	            if(gameObject instanceof Projectile) {
	            	projectiles.add((Projectile)gameObject);
	                Projectile proj = (Projectile)gameObject;
	                proj.fire();
	            } else if(gameObject instanceof Bubble) {
	            	bubbles.add((Bubble)gameObject);
	            }
	        }
	        for (GameObject gameObject : toDelete) {
	        	if (gameObject instanceof Bubble) {
	        		bubbles.remove(gameObject);
	        	} else if (gameObject instanceof Projectile) {
	        		projectiles.remove(gameObject);
	        	} else if (gameObject instanceof Pickup) {
	        		pickups.remove(gameObject);
	        	}
	        }
	        toAdd.clear();
	        toDelete.clear();	        
		}
    }
        
    /**
     * game will use CollisionHandler returned in this method.
     * @return the CollisionHandler that will be used.
     */
    protected CollisionHandler getNewCollisionHandler() {
        return new DefaultCollisionHandler();
    }



	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
