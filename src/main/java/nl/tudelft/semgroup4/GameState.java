package nl.tudelft.semgroup4;


import java.util.LinkedList;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.BubbleManager;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import nl.tudelft.model.Weapon;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;
import nl.tudelft.semgroup4.util.QuadTree;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {
    LinkedList<GameObject> toDelete, toAdd, walls, players, bubbles, projectiles;
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
        quad = new QuadTree(0, new Rectangle(0, 0, 1200, 800));
        walls = new LinkedList<>();
        projectiles = new LinkedList<>();
        players = new LinkedList<>();
        bubbles = new LinkedList<>();
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
        
        new BubbleManager(toDelete, toAdd).createBubbles(container);
        
        // todo input
        weapon = new Weapon(Resources.weaponImage.copy(), toDelete, toAdd);
        players.add( new Player(
                Resources.playerImageStill.copy(),
                Resources.playerImageLeft.copy(),
                Resources.playerImageRight.copy(),
        		container.getWidth() / 2, container.getHeight() - Resources.playerImageStill.getHeight() - Resources.wallImage.getHeight(), input, weapon));
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.drawImage(Resources.backgroundImage, 0,0, container.getWidth(), container.getHeight(), 0, 0, Resources.backgroundImage.getWidth(), Resources.backgroundImage.getHeight());

        for (GameObject gameObject : walls) {
            gameObject.render(container, g);
        }
        for (GameObject gameObject : bubbles) {
            gameObject.render(container, g);
        }
        for (GameObject gameObject : players) {
            gameObject.render(container, g);
        }
        for (GameObject gameObject : projectiles) {
            gameObject.render(container, g);
        }

    }
    public void update(GameContainer container, StateBasedGame mainApp, int delta) throws SlickException {
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
        

        for (GameObject gameObject : players) {
            gameObject.update(container, delta);
        }
        for (GameObject gameObject : bubbles) {
            gameObject.update(container, delta);
        }
        for (GameObject gameObject : projectiles) {
            gameObject.update(container, delta);
        }

        for (GameObject gameObject : toAdd) {
            if(gameObject instanceof Projectile) {
            	projectiles.add(gameObject);
                Projectile proj = (Projectile)gameObject;
                proj.fire();
                weapon.getAL().add(proj);
            } else if(gameObject instanceof Bubble) {
            	bubbles.add(gameObject);
            }
        }
        for (GameObject gameObject : toDelete) {
        	if (gameObject instanceof Bubble) {
        		bubbles.remove(gameObject);
        	} else if (gameObject instanceof Projectile) {
        		projectiles.remove(gameObject);
        	}
        }
        toAdd.clear();
        toDelete.clear();
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
