package nl.tudelft.semgroup4;

import nl.tudelft.model.Player;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class App extends BasicGame {
	Image weapon, background, playerImage, wall;	
	Player player;
	Input input = new Input(0);

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
		// TODO Auto-generated constructor stub
	}	

	@Override
	public void init(GameContainer container) throws SlickException {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		wall = new Image("src/main/resources/img/wall2.JPG");
		playerImage =  new Image("src/main/resources/img/player_still.png");
		player = new Player(container.getWidth() / 2, container.getHeight() - playerImage.getHeight(), playerImage);
		background = new Image("src/main/resources/img/level1.jpg");
		
		//weapon = new Image("resources/player_still.bmp");
	}	

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.scale((float) 1.8, (float) 2.3);
		g.drawImage(background, 0,0);
		g.resetTransform();
		for(int i = 0; i <= 4; i++) {
			g.drawImage(wall, 0, i * wall.getHeight() );
			g.drawImage(wall, container.getWidth() - wall.getWidth(), i * wall.getHeight() );
		}
		g.scale(2, 2);
		g.drawImage(player.getImage(), player.getX() /2, (float) (player.getY()/2.1));		
		//arg1.drawImage(weapon, player.getX(), player.getY());
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
		player.tick();
	}
}