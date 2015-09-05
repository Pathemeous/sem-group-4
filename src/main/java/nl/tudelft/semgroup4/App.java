package nl.tudelft.semgroup4;

import nl.tudelft.model.Player;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class App extends BasicGame {
	Image weapon;
	Player player;
	Input input = new Input(0);

	public static void main(String[] args) {
		App game = new App("Bubble Trouble");
		try {
			AppGameContainer container = new AppGameContainer(game);
			container.setTargetFrameRate(60);			
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
		player = new Player(container.getWidth() / 2, 443, new Image("src/main/resources/img/player_still.bmp"));
		//weapon = new Image("resources/player_still.bmp");
	}	

	@Override
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		arg1.drawImage(player.getImage(), player.getX(), player.getY());
		//arg1.drawImage(weapon, player.getX(), player.getY());


	}


	@Override
	public void update(GameContainer container, int arg1) throws SlickException {		
		if(input.isKeyDown(Input.KEY_LEFT)) {
			player.setImage(new Image("src/main/resources/img/player_left.bmp"));
			player.setX(-4);
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			player.setImage(new Image("src/main/resources/img/player_right.bmp"));
			player.setX(4);
		}
		if(input.isKeyPressed(Input.KEY_SPACE)) {			
			System.out.println("PEW PEW");
		}
		if(!(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT))){
			player.setImage(new Image("src/main/resources/img/player_still.bmp"));
		}
		player.tick();
		

	}
}