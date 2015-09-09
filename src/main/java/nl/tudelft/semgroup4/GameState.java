package nl.tudelft.semgroup4;


import nl.tudelft.model.Bubble;
import nl.tudelft.model.BubbleManager;
import nl.tudelft.model.GameObject;
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
	
    LinkedList<GameObject> objectList;
    boolean paused;
    PauseScreen pauseScreen;
    MouseOverArea mouseOver;
    LinkedList<GameObject> toDelete, toAdd, walls, players, bubbles, projectiles, pickups;
    Input input = new Input(0);
    Weapon weapon;
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

        {
            for (int i = 0; i * Resources.vwallImage.getHeight() < container.getHeight(); i++) {
                walls.add(new Wall(Resources.vwallImage, 0, i * Resources.vwallImage.getHeight()));
                walls.add(new Wall(Resources.vwallImage, container.getWidth() - Resources.vwallImage.getWidth()
                		, i * Resources.vwallImage.getHeight()));
            }

            // NOTE: als je rotate dan staan width/height not voor dezeflde dimensies
            for (int i = 0; i * Resources.wallImage.getHeight() < container.getWidth(); i++) {
                walls.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), 0));
                walls.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), 
                		container.getHeight() - Resources.wallImage.getHeight()));
            }
            
            //objectList.add(new Wall(Resources.wallImage, 1000, 400));
        }
        
        new BubbleManager(toDelete, toAdd, pickups).createBubbles(container);
        
        // todo input
        weapon = new Weapon(Resources.weaponImageRegular.copy(), toDelete, toAdd, WeaponType.REGULAR);
        players.add( new Player(
                container.getWidth() / 2,
                container.getHeight() - Resources.playerImageStill.getHeight() - Resources.wallImage.getHeight(),
                input, weapon));
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.drawImage(Resources.backgroundImage, 0,0, container.getWidth(), container.getHeight(), 0, 0, Resources.backgroundImage.getWidth(), Resources.backgroundImage.getHeight());

        for (GameObject gameObject : walls) {
            gameObject.render(container, g);
        }
        for (GameObject gameObject : bubbles) {
            gameObject.render(container, g);
        }
        for (GameObject gameObject : projectiles) {
            gameObject.render(container, g);
        }
        for (GameObject gameObject : players) {
            gameObject.render(container, g);
            Player player = (Player)gameObject;
            if(player.hasShield()) {
            	g.drawImage(Resources.power_shield, player.getLocX(), player.getLocY());
            } else if(player.isInvincible()) {
            	g.drawImage(Resources.power_invincible, player.getLocX(), player.getLocY());
            }
        }
        for (GameObject gameObject : pickups) {
        	Pickup pickup = (Pickup)gameObject;
        	if(!pickup.isInBubble()) {
        		gameObject.render(container, g);
        	}
        }
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
	    	//LinkedList<GameObject> allObjects = new LinkedList<>();
	    	quad.clear();
	    	for (GameObject obj : walls) {
	    	  quad.insert(obj);
	    	}
	//    	for (GameObject obj : bubbles) {
	//      	  quad.insert(obj);
	//      	}
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
	        

	        for (GameObject gameObject : players) {
	            gameObject.update(container, delta);
	        }
	        for (GameObject gameObject : bubbles) {
	            gameObject.update(container, delta);
	        }
	        for (GameObject gameObject : projectiles) {
	            gameObject.update(container, delta);
	        }
	        for (GameObject gameObject : pickups) {
	            gameObject.update(container, delta);
	        }

	        for (GameObject gameObject : toAdd) {
	            if(gameObject instanceof Projectile) {
	            	projectiles.add(gameObject);
	                Projectile proj = (Projectile)gameObject;
	                proj.fire();
	            } else if(gameObject instanceof Bubble) {
	            	bubbles.add(gameObject);
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
