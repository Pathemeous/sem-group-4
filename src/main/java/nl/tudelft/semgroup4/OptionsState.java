package nl.tudelft.semgroup4;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionsState extends BasicGameState {
  

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
       

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
           g.drawImage(Resources.soundText, container.getWidth() / 3, container.getHeight() /2); 
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks) {
         
    }

   

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 3;
    }

}
