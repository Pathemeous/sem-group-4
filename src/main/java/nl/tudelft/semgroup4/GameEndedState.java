package nl.tudelft.semgroup4;

import java.awt.Font;
import java.io.IOException;
import java.util.List;

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

    // margin for the continue button.
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

    /**
     * Initializes text settings, buttons and mouseOverAreas.
     * 
     * @param container
     *            game container in which operations are initialized
     * @param game
     *            current game in which operations are initialized
     * @exception SlickException
     *                exception thrown when something on slick2D's goes wrong
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        input = container.getInput();

        initializeTextUtilities();
        initializeTextFields(container);
        initializeTextFieldMouseOvers(container);
        initializeContinueButton(container);

    }

    /**
     * Setup for initializing extra data after the state has been made at the
     * beginning of the app.
     * 
     * @param players
     *            Players currently in the game
     * @param won
     *            True if the game has been won
     */
    public void setup(Player[] players, boolean won) {
        this.won = won;
        this.players = players;
    }

    /**
     * Renders all elements on the screen.
     * 
     * @param container
     *            container in which elements need to be rendered
     * @param game
     *            game in which elements need to be rendered
     * @param graphics
     *            graphics used to render the elements
     */
    @Override
    public void render(GameContainer container, StateBasedGame game,
            Graphics graphics) throws SlickException {

        drawTitle(container);
        drawColumnNames(container);
        drawFirstPlayerInfo(container);

        graphics.setColor(Color.white);
        textFieldPlayer1.render(container, graphics);

        drawRectangle(container, graphics);

        if (players.length == 2) {
            drawSecondPlayerInfo(container, graphics);
        }

        drawContinueButton(container);

    }

    /**
     * Updates all elements on the screen.
     * 
     * @param container
     *            container in which elements need to be updated
     * @param game
     *            game in which elements need to be updated
     * @param delta
     *            time between each frame
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

        // checks all mouse clicks in order to be able to select the text boxes
        // and continue button.
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOverTextField1.isMouseOver()) {
                textFieldPlayer1.setFocus(true);
            } else if (mouseOverTextField2.isMouseOver()) {
                textFieldPlayer2.setFocus(true);
            } else if (mouseOverContinueButton.isMouseOver()) {
                saveScore();
                game.enterState(States.StartScreenState);
            }
        }

    }

    /**
     * Returns the id of this state.
     */
    @Override
    public int getID() {
        return States.GameEndedState;
    }

    /**
     * saves the score of the player(s) which have finished a game, the score is
     * save with the corresponding filled out name.
     */
    public void saveScore() {
        try {
            List<HighscoreEntry> highscores = HighscoresHelper.load();

            highscores.add(new HighscoreEntry(textFieldPlayer1.getText(),
                    players[0].getScore()));

            if (players.length == 2) {
                highscores.add(new HighscoreEntry(textFieldPlayer2.getText(),
                        players[1].getScore()));
            }

            HighscoresHelper.save(highscores);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes fonts and strings for texts on screen.
     */
    public void initializeTextUtilities() {
        fontTitle = new Font("Calibri", Font.BOLD, 60);
        typeFontTitle = new TrueTypeFont(fontTitle, true);

        fontPlayer = new Font("Calibri", Font.BOLD, 40);
        typeFontPlayer = new TrueTypeFont(fontPlayer, true);

        gameOverText = "GAME OVER";
        gameWonText = "GAME COMPLETED";
        gameContinueText = "CONTINUE";
    }

    /**
     * Initializes the continue button.
     * 
     * @param container
     *            container in which the button is initialized
     */
    public void initializeContinueButton(GameContainer container) {
        mouseOverContinueButton = new MouseOverArea(container, null,
                container.getWidth() - margin
                        - typeFontPlayer.getWidth(gameContinueText),
                container.getHeight() - margin - typeFontPlayer.getHeight(),
                typeFontPlayer.getWidth(gameContinueText),
                typeFontPlayer.getHeight());

    }

    /**
     * Initialized the MouseOver field for the textFields.
     * 
     * @param container
     *            container in which the mouseOver field is initialized
     */
    public void initializeTextFieldMouseOvers(GameContainer container) {
        mouseOverTextField1 = new MouseOverArea(container, null,
                container.getWidth() / 5 * 4, container.getHeight() / 3,
                textFieldPlayer1.getWidth(), textFieldPlayer1.getHeight());

        mouseOverTextField2 = new MouseOverArea(container, null,
                container.getWidth() / 5 * 4, container.getHeight() / 2,
                textFieldPlayer2.getWidth(), textFieldPlayer2.getHeight());

    }

    /**
     * Initializes the textFields.
     * 
     * @param container
     *            container in which the textFields are initialized
     */
    public void initializeTextFields(GameContainer container) {
        textFieldPlayer1 = new TextField(container, typeFontPlayer,
                container.getWidth() / 5 * 4, container.getHeight() / 3, 200,
                50);
        textFieldPlayer1.setConsumeEvents(true);
        textFieldPlayer1.setMaxLength(10);

        textFieldPlayer2 = new TextField(container, typeFontPlayer,
                container.getWidth() / 5 * 4, container.getHeight() / 2, 200,
                50);
        textFieldPlayer2.setConsumeEvents(true);
        textFieldPlayer2.setMaxLength(10);
    }

    /**
     * Draws the first players information onto the screen.
     * 
     * @param container
     *            container in which to draw onto.
     */
    public void drawFirstPlayerInfo(GameContainer container) {
        // draws all information of the fist player
        typeFontPlayer.drawString(container.getWidth() / 8,
                container.getHeight() / 3, "PLAYER 1", Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 2,
                container.getHeight() / 3,
                Integer.toString(players[0].getScore()), Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3,
                container.getHeight() / 3,
                Integer.toString(players[0].getMoney()), Color.white);

    }

    /**
     * draws the seconds player information onto the screen.
     * 
     * @param container
     *            container in which to draw the information
     * @param graphics
     *            graphics to use to draw the elements
     */
    public void drawSecondPlayerInfo(GameContainer container, Graphics graphics) {
        typeFontPlayer.drawString(container.getWidth() / 8,
                container.getHeight() / 2, "PLAYER 2", Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 2,
                container.getHeight() / 2,
                Integer.toString(players[1].getScore()), Color.white);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3,
                container.getHeight() / 2,
                Integer.toString(players[1].getMoney()), Color.white);

        // Draws the second players textfield box.
        textFieldPlayer2.render(container, graphics);

        // Draws a white rectangle around the textfield because
        // slick can't handle it on it's own.
        graphics.drawRect(container.getWidth() / 5 * 4,
                container.getHeight() / 2, textFieldPlayer1.getWidth() - 1,
                textFieldPlayer1.getHeight() - 1);
    }

    /**
     * Draws the title according to of the game has been won or lost.
     * 
     * @param container
     *            container in which to draw the title
     */
    public void drawTitle(GameContainer container) {
        if (won) {
            typeFontTitle.drawString(
                    container.getWidth() / 2
                            - typeFontTitle.getWidth(gameOverText) / 2,
                    container.getHeight() / 8, gameWonText, Color.yellow);
        } else {
            typeFontTitle.drawString(
                    container.getWidth() / 2
                            - typeFontTitle.getWidth("game over") / 2,
                    container.getHeight() / 8, gameOverText, Color.yellow);
        }

    }

    /**
     * Draws the column names of the player information.
     * 
     * @param container
     *            container to draw onto
     */
    public void drawColumnNames(GameContainer container) {
        // draws the column names
        typeFontPlayer.drawString(container.getWidth() / 5 * 2,
                container.getHeight() / 4, "SCORE", Color.yellow);
        typeFontPlayer.drawString(container.getWidth() / 5 * 3,
                container.getHeight() / 4, "MONEY", Color.yellow);
        typeFontPlayer.drawString(container.getWidth() / 5 * 4,
                container.getHeight() / 4, "NAME", Color.yellow);

    }

    /**
     * Draws the continueButton on the screen.
     * 
     * @param container
     *            container to draw onto.
     */
    public void drawContinueButton(GameContainer container) {
        typeFontPlayer.drawString(container.getWidth() - margin
                - typeFontPlayer.getWidth(gameContinueText),
                container.getHeight() - margin - typeFontPlayer.getHeight(),
                "CONTINUE", Color.yellow);
    }

    /**
     * Draws a rectangle around the textfield, because slick2D is incapable.
     * 
     * @param container
     *            container to draw onto
     * @param graphics
     *            graphics to use to draw the rectangle
     */
    public void drawRectangle(GameContainer container, Graphics graphics) {
        graphics.drawRect(container.getWidth() / 5 * 4,
                container.getHeight() / 3, textFieldPlayer1.getWidth() - 1,
                textFieldPlayer1.getHeight() - 1);

    }

    /**
     * Sets the fontype of the title.
     * @param typeFontTitle
     *            the typeFontTitle to set
     */
    public void setTypeFontTitle(TrueTypeFont typeFontTitle) {
        this.typeFontTitle = typeFontTitle;
    }

    /**
     * Sets the font type of the players.
     * @param typeFontPlayer
     *            the typeFontPlayer to set
     */
    public void setTypeFontPlayer(TrueTypeFont typeFontPlayer) {
        this.typeFontPlayer = typeFontPlayer;
    }

    /**
     * Sets the players.
     * @param players
     *            the players to set
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Sets the mouseOverArea of the textfield of player1.
     * @param textFieldPlayer1
     *            the textFieldPlayer1 to set
     */
    public void setTextFieldPlayer1(TextField textFieldPlayer1) {
        this.textFieldPlayer1 = textFieldPlayer1;
    }

    /**
     * Sets the mouseOverArea of the textfield of player2.
     * @param textFieldPlayer2
     *            the textFieldPlayer2 to set
     */
    public void setTextFieldPlayer2(TextField textFieldPlayer2) {
        this.textFieldPlayer2 = textFieldPlayer2;
    }

    /**
     * Sets the mouseOverArea of the continue button.
     * @param mouseOverContinueButton
     *            the mouseOverContinueButton to set
     */
    public void setMouseOverContinueButton(MouseOverArea mouseOverContinueButton) {
        this.mouseOverContinueButton = mouseOverContinueButton;
    }

}
