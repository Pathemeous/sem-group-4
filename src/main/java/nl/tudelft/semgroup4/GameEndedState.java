package nl.tudelft.semgroup4;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.tudelft.model.Player;

import nl.tudelft.semgroup4.util.HighscoreEntry;
import nl.tudelft.semgroup4.util.HighscoresHelper;
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
    
    //margin for the continue button.
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
        
        //initializes all the mouseover areas.
        textFieldPlayer1 = new TextField(container,
                typeFontPlayer,
                container.getWidth() / 5 * 4,
                container.getHeight() / 3,
                200,
                50);
        textFieldPlayer1.setConsumeEvents(true);
        textFieldPlayer1.setMaxLength(10);
        
        textFieldPlayer2 = new TextField(container,
                typeFontPlayer,
                container.getWidth() / 5 * 4,
                container.getHeight() / 2,
                200,
                50);
        textFieldPlayer2.setConsumeEvents(true);
        textFieldPlayer2.setMaxLength(10);
        
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
    
    /**
     * Setup for initializing extra data after the state has been made at the beginning
     * of the app.
     * @param players Players currently in the game
     * @param won True if the game has been won
     */
    public void setup(Player[] players, boolean won) {
        this.won = won;
        this.players = players;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {
        //draws a different title depending on if the game has been won or lost.
        if (won) {
            typeFontTitle.drawString(
                    container.getWidth() / 2 - typeFontTitle.getWidth(gameOverText) / 2,
                    container.getHeight() / 8, gameWonText, Color.yellow);
        } else {
            typeFontTitle.drawString(
                    container.getWidth() / 2 - typeFontTitle.getWidth(gameOverText) / 2,
                    container.getHeight() / 8, gameOverText, Color.yellow);
        }
        //draws the column names
        typeFontPlayer.drawString(container.getWidth() / 5 * 2, container.getHeight() / 4,
                "SCORE", Color.yellow);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3, container.getHeight() / 4,
                "MONEY", Color.yellow);
        typeFontPlayer.drawString(container.getWidth() / 5 * 4, container.getHeight() / 4,
                "NAME", Color.yellow);
        //draws all information of the fist player
        typeFontPlayer.drawString(container.getWidth() / 8, container.getHeight() / 3,
                "PLAYER 1", Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 2, container.getHeight() / 3,
                Integer.toString(players[0].getScore()), Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3, container.getHeight() / 3,
                Integer.toString(players[0].getMoney()), Color.white);
        
        //draws the first players textfield box.
        graphics.setColor(Color.white);             
        textFieldPlayer1.render(container, graphics);
        
        //Draws a white rectangle around the textfield because
        //slick can't handle it on it's own.
        graphics.drawRect(
                container.getWidth() / 5 * 4,
                container.getHeight() / 3,
                textFieldPlayer1.getWidth() - 1,
                textFieldPlayer1.getHeight() - 1);
        
        //Displays all information of the second player if there is a second player.
        if (players.length == 2) {
            typeFontPlayer.drawString(container.getWidth() / 8, container.getHeight() / 2,
                    "PLAYER 2", Color.white);
            typeFontPlayer.drawString(container.getWidth() / 5 * 2, container.getHeight() / 2,
                    Integer.toString(players[1].getScore()), Color.white);
            typeFontPlayer.drawString(container.getWidth() / 5 * 3, container.getHeight() / 2,
                    Integer.toString(players[1].getMoney()), Color.white);
            
            //Draws the second players textfield box.
            textFieldPlayer2.render(container, graphics);
            
            //Draws a white rectangle around the textfield because
            //slick can't handle it on it's own.
            graphics.drawRect(
                    container.getWidth() / 5 * 4,
                    container.getHeight() / 2,
                    textFieldPlayer1.getWidth() - 1,
                    textFieldPlayer1.getHeight() - 1);
        }        
        
        //draws the continue button on the screen.
        typeFontPlayer.drawString(
                container.getWidth() - margin - typeFontPlayer.getWidth(gameContinueText),
                container.getHeight() - margin - typeFontPlayer.getHeight(),
                "CONTINUE", Color.yellow);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        
        //checks all mouse clicks in order to be able to select the text boxes and 
        //continue button.
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOverTextField1.isMouseOver()) {
                textFieldPlayer1.setFocus(true);
            } else if (mouseOverTextField2.isMouseOver()) {
                textFieldPlayer2.setFocus(true);
            } else if (mouseOverContinueButton.isMouseOver()) {
                
                try {
                    List<HighscoreEntry> highscores = HighscoresHelper.load();

                    highscores.add(new HighscoreEntry(
                            textFieldPlayer1.getText(),
                            players[0].getScore()));

                    if (players.length == 2) {
                        highscores.add(new HighscoreEntry(
                                textFieldPlayer2.getText(),
                                players[1].getScore()));
                    }

                    HighscoresHelper.save(highscores);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                game.enterState(States.StartScreenState);
            }
        }

    }

    @Override
    public int getID() {
        return States.GameEndedState;
    }

}
