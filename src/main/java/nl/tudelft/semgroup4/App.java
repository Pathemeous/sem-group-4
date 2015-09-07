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

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class App extends BasicGame {
    Image weapon;
    Image background;
    Image playerImage;
    Player player;
    LinkedList<GameObject> objectList;
    Input input = new Input(0);

    final CollisionHandler<GameObject, GameObject> collisionHandler;

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "target/natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());

        App game = new App("Bubble Trouble");
        try {
            AppGameContainer container = new AppGameContainer(game);
            container.setTargetFrameRate(60);
            container.setUpdateOnlyWhenVisible(true);
            container.setDisplayMode(1200, 800, false);
            container.start();

        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public App(String title) {
        super(title);
        collisionHandler = getNewCollisionHandler();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        objectList = new LinkedList<>();
        background = new Image("src/main/resources/img/level1.jpg");
        playerImage =  new Image("src/main/resources/img/player_still.png");

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
        player = new Player(playerImage.copy(), container.getWidth() / 2, container.getHeight() - playerImage.getHeight() - 35, input);

        objectList.add(player);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {

        g.drawImage(background, 0,0, container.getWidth(), container.getHeight(), 0, 0, background.getWidth(), background.getHeight());

        for (GameObject gameObject : objectList) {
            gameObject.render(container, g);
        }

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // collision
        List<GameObject> collidesWithList = CollisionHelper.collideObjectWithList(player, objectList);
        for (GameObject collidesWith : collidesWithList) {
            collisionHandler.onCollision(player, collidesWith);
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

}
