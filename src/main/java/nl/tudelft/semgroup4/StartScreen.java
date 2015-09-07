package nl.tudelft.semgroup4;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreen extends BasicGameState {
	MouseOverArea mouseOverOnePlayer;
	MouseOverArea mouseOverTwoPlayer;
	MouseOverArea mouseOverOptions;
	MouseOverArea mouseOverQuit;
	ComponentListener listener;
	Image titleScreen;
	Input input;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {	
		input = container.getInput();
		mouseOverOnePlayer = new MouseOverArea(container, titleScreen, 211, 391, 364, 88);
		mouseOverTwoPlayer = new MouseOverArea(container, titleScreen, 211, 476, 364, 88);
		mouseOverOptions = new MouseOverArea(container, titleScreen, 211, 573, 364, 88);		
		mouseOverQuit = new MouseOverArea(container, titleScreen, 211, 672, 364, 88);
		titleScreen = new Image("src/main/resources/img/titleScreen2.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(titleScreen, 0,0, container.getWidth(), container.getHeight(), 0, 0, titleScreen.getWidth(), titleScreen.getHeight());
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int ticks) throws SlickException {		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			if(mouseOverOnePlayer.isMouseOver()) {
				game.enterState(1);
			}
			else if(mouseOverTwoPlayer.isMouseOver()) {
				System.out.println("Two player game started");
			}
			else if(mouseOverOptions.isMouseOver()) {
				System.out.println("Options menu opened");
			}
			else if(mouseOverQuit.isMouseOver()) {
				container.exit();
			}
		}
		
		
		//used for finding button placement 
		//dimensions for buttons:
		//1) 211, 380  width = 575 -211 = 364, height = 88
		//2) 211, 476 ...
		System.out.println("x = " + input.getMouseX());
		System.out.println("y = " + input.getMouseY());
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
