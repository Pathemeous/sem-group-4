package nl.tudelft.semgroup4;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;


public class GameState extends BasicGameState {
    LinkedList<GameObject> objectList;
    Input input;
    boolean paused;
    PauseScreen pauseScreen;
    MouseOverArea mouseOver;
    final CollisionHandler<GameObject, GameObject> collisionHandler;
   

    public GameState(String title) {
        super();
        collisionHandler = getNewCollisionHandler();
    }
    
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        objectList = new LinkedList<>();
        input = container.getInput();        
        mouseOver = new MouseOverArea(container, Resources.quitText, container.getHeight()/2,
        		container.getHeight() / 2, Resources.quitText.getWidth(), Resources.quitText.getHeight());
        pauseScreen = new PauseScreen(mouseOver);
        
        {
            for (int i = 0; i * Resources.vwallImage.getHeight() < container.getHeight(); i++) {
                objectList.add(new Wall(Resources.vwallImage, 0, i * Resources.vwallImage.getHeight()));
                objectList.add(new Wall(Resources.vwallImage, container.getWidth() - Resources.vwallImage.getWidth(), i * Resources.vwallImage.getHeight()));
            }

            // NOTE: als je rotate dan staan width/height not voor dezeflde dimensies
            for (int i = 0; i * Resources.wallImage.getHeight() < container.getWidth(); i++) {
                objectList.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), 0));
                objectList.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), container.getHeight() - Resources.wallImage.getHeight()));
            }
        }

        // todo input
        objectList.add( new Player(
                Resources.playerImageStill.copy(),
                Resources.playerImageLeft.copy(),
                Resources.playerImageRight.copy(),
        		container.getWidth() / 2, container.getHeight() - Resources.playerImageStill.getHeight() - Resources.wallImage.getHeight(), input));
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.drawImage(Resources.backgroundImage, 0,0, container.getWidth(), container.getHeight(), 0, 0, Resources.backgroundImage.getWidth(), Resources.backgroundImage.getHeight());

        for (GameObject gameObject : objectList) {
            gameObject.render(container, g);
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
        for (GameObject collidesWithA : objectList) {
            for (GameObject collidesWithB : CollisionHelper.collideObjectWithList(collidesWithA, objectList)) {
                collisionHandler.onCollision(collidesWithA, collidesWithB);	
        	}
        }

        for (GameObject gameObject : objectList) {
            gameObject.update(container, delta);
        	}       
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
