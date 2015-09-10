package nl.tudelft.semgroup4;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 * 
 * @author TUDelft SID
 *
 */
public class StartScreenState extends BasicGameState {
	private MouseOverArea mouseOverOnePlayer;
	private MouseOverArea mouseOverTwoPlayer;
	private MouseOverArea mouseOverOptions;
	private MouseOverArea mouseOverQuit;
	private Input input;
	private boolean alreadyAdded = false;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {		
		input = container.getInput();		
		//initializes all the areas where the buttons are to see if the mouse is on one of those areas
		mouseOverOnePlayer = new MouseOverArea(container, Resources.titleScreenBackground, 211, 391, 364, 88);
		mouseOverTwoPlayer = new MouseOverArea(container, Resources.titleScreenBackground, 211, 476, 364, 88);
		mouseOverOptions = new MouseOverArea(container, Resources.titleScreenBackground, 211, 573, 364, 88);
		mouseOverQuit = new MouseOverArea(container, Resources.titleScreenBackground, 211, 672, 364, 88);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(Resources.titleScreenBackground, 0,0, container.getWidth(), container.getHeight(), 0, 0, Resources.titleScreenBackground.getWidth(), Resources.titleScreenBackground.getHeight());
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int ticks) throws SlickException {
		if(!alreadyAdded) {
			alreadyAdded = true;
			game.getState(1).init(container, game);	
			game.getState(2).init(container, game);
		}
		//checks if the left mouse button is pressed and where it was pressed to determine 
		//what action to perform
		
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {			
			if (mouseOverOnePlayer.isMouseOver()) {
				game.enterState(1);
			}
			else if (mouseOverTwoPlayer.isMouseOver()) {
				game.enterState(2);
			}
			else if (mouseOverOptions.isMouseOver()) {
				System.out.println("Options menu opened");
			}
			else if (mouseOverQuit.isMouseOver()) {
				container.exit();
			}
		}		
		
	}
	
	

	public boolean isAlreadyAdded() {
		return alreadyAdded;
	}

	public void setAlreadyAdded(boolean alreadyAdded) {
		this.alreadyAdded = alreadyAdded;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
