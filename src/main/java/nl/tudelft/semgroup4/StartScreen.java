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
	MouseOverArea mouseOverStart;
	ComponentListener listener;
	Image titleScreen;
	Input input;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {	
		input = container.getInput();
		mouseOverStart = new MouseOverArea(container, titleScreen, 211, 391, 230, 80);
		titleScreen = new Image("src/main/resources/img/titleScreen.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(titleScreen, 0,0, container.getWidth(), container.getHeight(), 0, 0, titleScreen.getWidth(), titleScreen.getHeight());
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int ticks) throws SlickException {		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(mouseOverStart.isMouseOver()) {
				game.enterState(1);
			}
		}			
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
