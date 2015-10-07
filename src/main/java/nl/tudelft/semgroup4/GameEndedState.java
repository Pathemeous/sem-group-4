package nl.tudelft.semgroup4;

import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.util.LinkedList;

import nl.tudelft.model.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameEndedState extends BasicGameState {
    private final int margin = 20;
    
    private Font fontTitle;
    private Font fontPlayer;
    private TrueTypeFont typeFontTitle;
    private TrueTypeFont typeFontPlayer;
    
    
    private String gameOverText;
    private String gameWonText;
    private String gameContinueText;
    
    private boolean won;
    private Player[] players;
    private Input input;
    
    private TextField textFieldPlayer1;
    private TextField textFieldPlayer2;
    
    private MouseOverArea mouseOverTextField1;
    private MouseOverArea mouseOverTextField2;
    private MouseOverArea mouseOverContinueButton;   

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        input = container.getInput();
        
        fontTitle = new Font("Calibri", Font.BOLD, 60);
        typeFontTitle = new TrueTypeFont(fontTitle, true);  
        
        fontPlayer = new Font("Calibri", Font.BOLD, 40);
        typeFontPlayer = new TrueTypeFont(fontPlayer, true); 
                
        gameOverText  = "GAME OVER";
        gameWonText = "GAME COMPLETED";   
        gameContinueText = "CONTINUE";
        
        textFieldPlayer1 = new TextField(container,
                typeFontPlayer,
                container.getWidth() / 5 * 4,
                container.getHeight() / 3,
                200,
                50);
        
        textFieldPlayer2 = new TextField(container,
                typeFontPlayer,
                container.getWidth() / 5 * 4,
                container.getHeight() / 2,
                200,
                50);
        
        mouseOverTextField1 = new MouseOverArea(container,
                null,
                container.getWidth() / 5 * 4,
                container.getHeight() / 3,
                textFieldPlayer1.getWidth(),
                textFieldPlayer1.getHeight());
        
        mouseOverTextField2 = new MouseOverArea(container,
                null,
                container.getWidth() / 5 * 4,
                container.getHeight() / 2,
                textFieldPlayer2.getWidth(),
                textFieldPlayer2.getHeight());
        
        mouseOverContinueButton = new MouseOverArea(container,
                null,
                container.getWidth() - margin - typeFontPlayer.getWidth(gameContinueText),
                container.getHeight() - margin - typeFontPlayer.getHeight(),
                typeFontPlayer.getWidth(gameContinueText),
                typeFontPlayer.getHeight());
        
    }

    public void setup(Player[] players2, boolean won) {
        this.won = won;
        this.players = players2;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {
        if (won) {
            typeFontTitle.drawString(
                    container.getWidth() / 2 - typeFontTitle.getWidth(gameOverText) / 2,
                    container.getHeight() / 8, gameWonText, Color.yellow);
        } else {
            typeFontTitle.drawString(
                    container.getWidth() / 2 - typeFontTitle.getWidth(gameOverText) / 2,
                    container.getHeight() / 8, gameOverText, Color.yellow);
        }
        typeFontPlayer.drawString(container.getWidth() / 5 * 2, container.getHeight() / 4,
                "SCORE", Color.yellow);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3, container.getHeight() / 4,
                "MONEY", Color.yellow);
        typeFontPlayer.drawString(container.getWidth() / 5 * 4, container.getHeight() / 4,
                "NAME", Color.yellow);
        
        typeFontPlayer.drawString(container.getWidth() / 8, container.getHeight() / 3,
                "PLAYER 1", Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 2, container.getHeight() / 3,
                Integer.toString(players[0].getScore()), Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3, container.getHeight() / 3,
                Integer.toString(players[0].getMoney()), Color.white);
        
        graphics.setColor(Color.white);             
        textFieldPlayer1.render(container, graphics);
        textFieldPlayer1.setConsumeEvents(true);
        
        if (true) {
            typeFontPlayer.drawString(container.getWidth() / 8, container.getHeight() / 2,
                    "PLAYER 2", Color.white);
            typeFontPlayer.drawString(container.getWidth() / 5 * 2, container.getHeight() / 2,
                    Integer.toString(players[0].getScore()), Color.white);
            typeFontPlayer.drawString(container.getWidth() / 5 * 3, container.getHeight() / 2,
                    Integer.toString(players[0].getMoney()), Color.white);
            
            textFieldPlayer2.render(container, graphics);
            textFieldPlayer2.setConsumeEvents(true);
        }        
        
        typeFontPlayer.drawString(
                container.getWidth() - margin - typeFontPlayer.getWidth(gameContinueText),
                container.getHeight() - margin - typeFontPlayer.getHeight(),
                "CONTINUE", Color.yellow);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOverTextField1.isMouseOver()) {
                System.out.println("player1");
                textFieldPlayer1.setFocus(true);
            } else if (mouseOverTextField2.isMouseOver()) {
                System.out.println("player2");
                textFieldPlayer2.setFocus(true);
            } else if (mouseOverContinueButton.isMouseOver()) {
                game.enterState(0);
            }
        }

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 6;
    }

}
