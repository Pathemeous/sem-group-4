package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreenState extends BasicGameState {
    private MouseOverArea mouseOverOnePlayer;
    private MouseOverArea mouseOverTwoPlayer;
    private MouseOverArea mouseOverOptions;
    private MouseOverArea mouseOverQuit;
    private MouseOverArea mouseOverHighScores;
    private Input input;
    private boolean alreadyAdded = false;
    
    private Font font;
    private TrueTypeFont typeFont;  
    private Image highScoreButton;
    private String highscores;
    private Shape shape;
    

    @Override
    public void init(GameContainer container, StateBasedGame mainApp) throws SlickException {
        font = new Font("Verdana", Font.BOLD, 36);
        typeFont = new TrueTypeFont(font, true);  
        highscores  = "HIGHSCORES"; 
        highScoreButton = new Image(typeFont.getWidth(highscores), typeFont.getHeight());
        shape = new Rectangle(650, 650, typeFont.getWidth(highscores), typeFont.getHeight());
        shape = shape.transform(Transform.createRotateTransform(6, 650, 650));        
        
        input = container.getInput();
        final ResourcesWrapper resources = new ResourcesWrapper();
        // initializes all the areas where the buttons are to see if the mouse is on one of those
        // areas
        mouseOverOnePlayer =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 391,
                        364, 88);
        mouseOverTwoPlayer =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 476,
                        364, 88);
        mouseOverOptions =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 573,
                        364, 88);
        mouseOverQuit =
                new MouseOverArea(container, resources.getTitleScreenBackground(), 211, 672,
                        364, 88);
        mouseOverHighScores =
                new MouseOverArea(container, highScoreButton, shape);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics)
            throws SlickException {
        final ResourcesWrapper resources = new ResourcesWrapper();
        graphics.drawImage(resources.getTitleScreenBackground(), 0, 0, container.getWidth(),
                container.getHeight(), 0, 0, resources.getTitleScreenBackground().getWidth(),
                resources.getTitleScreenBackground().getHeight());
        
        //draws the highscore button
        graphics.rotate(650, 650, 340);       
        typeFont.drawString(650, 650, highscores, Color.red);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int ticks)
            throws SlickException {
        final ResourcesWrapper resources = new ResourcesWrapper();
        if (!resources.getTitleScreenMusic().playing()) {
            resources.playTitleScreen();
        }
        
        //
        // checks if the left mouse button is pressed and where it was pressed to determine
        // what action to perform

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (mouseOverHighScores.isMouseOver()) {
                game.getState(4).init(container, game);
                game.enterState(4);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User enters options menu");
            } else if (mouseOverOnePlayer.isMouseOver()) {
                game.addState(new GameState(game.getTitle(), true));
                game.getState(1).init(container, game);
                resources.stopTitleScreen();
                game.enterState(1);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                        "User starts a single player game");
            } else if (mouseOverTwoPlayer.isMouseOver()) {
                game.addState(new GameState(game.getTitle(), false));
                game.getState(2).init(container, game);
                resources.stopTitleScreen();
                game.enterState(2);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu",
                        "User starts a multi-player game");
            } else if (mouseOverOptions.isMouseOver()) {
                game.enterState(3);
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User enters options menu");
            } else if (mouseOverQuit.isMouseOver()) {
                Game.LOGGER.log(LogSeverity.DEBUG, "StartMenu", "User quits the application");
                resources.stopTitleScreen();
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
        return 0;
    }

}
