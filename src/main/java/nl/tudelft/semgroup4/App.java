package nl.tudelft.semgroup4;

import java.util.LinkedList;

import nl.tudelft.model.Collision;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class App extends BasicGame {
	Image weapon, background, playerImage, wallImage;
	Wall wall;
	Player player;
	Input input = new Input(0);
	LinkedList<GameObject> objectList;

	public static void main(String[] args) {
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
	}	

	@Override
	public void init(GameContainer container) throws SlickException {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		objectList = new LinkedList<>();
		wallImage = new Image("src/main/resources/img/wall2.JPG");
		playerImage =  new Image("src/main/resources/img/player_still.png");
		background = new Image("src/main/resources/img/level1.jpg");

		for(int i = 0; i <= 5; i++) {
			objectList.add(new Wall(wallImage, 0, i * wallImage.getHeight(), wallImage.getWidth(), wallImage.getHeight(), 0)) ;
			objectList.add(new Wall(wallImage, container.getWidth() -  55, i * wallImage.getHeight(),
					wallImage.getWidth(), wallImage.getHeight(), 0));
			objectList.add(new Wall(wallImage, 0, container.getHeight() - wallImage.getWidth(),
					wallImage.getHeight() * 2 * i, wallImage.getWidth(), 0));
		}

		player = new Player(playerImage,container.getWidth() / 2, container.getHeight() - playerImage.getHeight() - 35,
				playerImage.getWidth(), playerImage.getHeight(), 0);	
		objectList.add(player);
	}	

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
//		for(int i = 0; i < objectList.size(); i++) {
//			g.drawImage(objectList.get(i).image, objectList.get(i).getX_location(), objectList.get(i).getY_location());
//		}
		g.scale((float) 1.8, (float) 2.3);
		g.drawImage(background, 0,0);

		for(int i = 0; i <= 4; i++) {
			g.resetTransform();
			g.drawImage(wallImage, 0, i * wallImage.getHeight() );
			g.drawImage(wallImage, container.getWidth() - wallImage.getWidth(), i * wallImage.getHeight() );			
		}
		wallImage.setRotation(90);
		for(int i = 0; i <= 8; i++) {	
			g.drawImage(wallImage, i * wallImage.getHeight(), container.getHeight()- 108);
		}
		wallImage.setRotation(0);
		g.resetTransform(); 
		g.scale(2, 2);
		g.drawImage(player.getImage(), player.getX() /2, (float) (player.getY()/2.1));			
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {		
		if(input.isKeyDown(Input.KEY_LEFT)) {
			player.setImage(new Image("src/main/resources/img/player_left.png"));
			player.setX(-4);
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			player.setImage(new Image("src/main/resources/img/player_right.png"));
			player.setX(4);
		}
		if(input.isKeyPressed(Input.KEY_SPACE)) {			
			System.out.println("PEW PEW");
		}
		if(!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))){
			player.setImage(new Image("src/main/resources/img/player_still.png"));
		}

		Collision.Colission(player, objectList);
			
		
		player.tick();
	}
}