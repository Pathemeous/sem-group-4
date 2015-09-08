package nl.tudelft.semgroup4;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;
import nl.tudelft.model.Wall.WallType;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

public class GameState extends BasicGameState {
    LinkedList<GameObject> objectList;
    Input input = new Input(0);

    final CollisionHandler<GameObject, GameObject> collisionHandler;
   

    public GameState(String title) {
        super();
        collisionHandler = getNewCollisionHandler();
    }
    
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        objectList = new LinkedList<>();

        {
            for (int i = 0; i * Resources.vwallImage.getHeight() < container.getHeight(); i++) {
                objectList.add(new Wall(Resources.vwallImage, 0, i * Resources.vwallImage.getHeight(), WallType.VERTICAL_WALL));
                objectList.add(new Wall(Resources.vwallImage, container.getWidth() - Resources.vwallImage.getWidth(), i * Resources.vwallImage.getHeight(), WallType.VERTICAL_WALL));
            }

            // NOTE: als je rotate dan staan width/height not voor dezeflde dimensies
            for (int i = 0; i * Resources.wallImage.getHeight() < container.getWidth(); i++) {
                objectList.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), 0, WallType.HORIZONTAL_WALL));
                objectList.add(new Wall(Resources.wallImage, i * Resources.wallImage.getWidth(), container.getHeight() - Resources.wallImage.getHeight(), WallType.HORIZONTAL_WALL));
            }
        }
        
        Image bubbleImage = new Image("src/main/resources/img/rball6.bmp");
        objectList.add(new Bubble(bubbleImage.copy(), Resources.vwallImage.getWidth() + 300, container.getHeight() - Resources.wallImage.getWidth() - bubbleImage.getWidth() ));

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

    }
    public void update(GameContainer container, StateBasedGame mainApp, int delta) throws SlickException {
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
