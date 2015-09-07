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
		//initializes all the areas where the buttons are to see if the mouse is on one of those areas
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
		//checks if the left mouse button is pressed and where it was pressed to determine 
		//what action to perform
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
		
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
