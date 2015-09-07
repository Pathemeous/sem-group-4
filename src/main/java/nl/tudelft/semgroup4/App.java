package nl.tudelft.semgroup4;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;
import nl.tudelft.semgroup4.collision.CollisionHandler;
import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.DefaultCollisionHandler;

import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class App extends BasicGameState {
    Image weapon;
    Image background;     
    LinkedList<GameObject> objectList;
    Input input = new Input(0);

    final CollisionHandler<GameObject, GameObject> collisionHandler;
   

    public App(String title) {
        super();
        collisionHandler = getNewCollisionHandler();
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        objectList = new LinkedList<>();
        background = new Image("src/main/resources/img/level1.jpg");
        Image playerImage =  new Image("src/main/resources/img/player_still.png");
        playerImage = playerImage.getScaledCopy(2);
        Image playerImageRight =  new Image("src/main/resources/img/player_right.png");
        playerImageRight = playerImageRight.getScaledCopy(2);
        Image playerImageLeft =  new Image("src/main/resources/img/player_left.png");
        playerImageLeft = playerImageLeft.getScaledCopy(2);

        Image wallImageVertical = new Image("src/main/resources/img/wall2.JPG");
        Image wallImageHorizontal = wallImageVertical.copy();
//        wallImageHorizontal.setCenterOfRotation(wallImageHorizontal.getWidth() / 2, wallImageHorizontal.getHeight() / 2);
        wallImageHorizontal.setCenterOfRotation(0, 0);
        wallImageHorizontal.setRotation(-90);

        {
            for (int i = 0; i * wallImageVertical.getHeight() < container.getHeight(); i++) {
                objectList.add(new Wall(wallImageVertical, 0, i * wallImageVertical.getHeight()));
                objectList.add(new Wall(wallImageVertical, container.getWidth() - wallImageVertical.getWidth(), i * wallImageVertical.getHeight()));
            }

            // NOTE: als je rotate dan staan width/height not voor dezeflde dimensies
            for (int i = 0; i * wallImageHorizontal.getHeight() < container.getWidth(); i++) {
                objectList.add(new Wall(wallImageHorizontal, i * wallImageHorizontal.getHeight(), wallImageHorizontal.getWidth()));
                objectList.add(new Wall(wallImageHorizontal, i * wallImageHorizontal.getHeight(), container.getHeight()));
            }
        }

        // todo input
        objectList.add( new Player(
        		playerImage.copy(),
        		playerImageLeft.copy(),
        		playerImageRight.copy(),        		
        		container.getWidth() / 2, container.getHeight() - playerImage.getHeight() - wallImageHorizontal.getWidth(), input));
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.drawImage(background, 0,0, container.getWidth(), container.getHeight(), 0, 0, background.getWidth(), background.getHeight());

        for (GameObject gameObject : objectList) {
            gameObject.render(container, g);
        }

    }
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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
		return 0;
	}

	


}
