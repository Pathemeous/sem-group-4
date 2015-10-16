package nl.tudelft.semgroup4;

import java.awt.Font;

import nl.tudelft.model.Game;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by justin on 10/09/15.
 */
public class Dashboard implements Renderable {

    private static float margin = 10;
    private static float border = 2;
    private static float timeBarHeight = 30;

    private final Image dashboardPlayerContainerLeft;
    private final Image dashboardPlayerContainerRight;
    private final Image dashboardLivesContainer;
    private final Image dashboardlivesFull;
    private final Image dashboardlivesEmpty;
    private final Image levelContainer;

    private Rectangle timeBarBackground;
    private Rectangle timeBar;

    private final TrueTypeFont ttf;

    private int levelId = -1;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private int livesLeft = 0;
    private int livesRight = 0;
    private int moneyLeft = 0;
    private int moneyRight = 0;

    private final int left;
    private final int right;
    private final int bottom;
    
    private Game game;
    private DashboardController controller;

    /* package */Dashboard(ResourcesWrapper resources, Game game, int left,
                     int right, int bottom) {
        this.game = game;
        controller = new DashboardController(this);
        
        
        ttf = resources.createFont(new Font("Verdana", Font.BOLD, 30), true);
        this.left = left;
        this.right = right;
        this.bottom = bottom;

        dashboardPlayerContainerLeft = resources
                .getDashboardPlayerContainerLeft();
        dashboardPlayerContainerRight = resources
                .getDashboardPlayerContainerRight();
        dashboardLivesContainer = resources.getDashboardLivesContainer();
        dashboardlivesFull = resources.getDashboardlivesFull();
        dashboardlivesEmpty = resources.getDashboardlivesEmpty();
        levelContainer = resources.getLevelContainer();

        timeBarBackground = new Rectangle(left + margin, 0, right - left - 2
                * margin, timeBarHeight);
        timeBar = new Rectangle(timeBarBackground.getX() + border, 0,
                timeBarBackground.getWidth() - 2 * border,
                timeBarBackground.getHeight() - 2 * border);
    }

    /**
     * This renders the Dashboard in his current state.
     *
     * <p>
     * Checkstyle for LineLength and VariableDeclarationUsageDistance are turned
     * off for the scope of the method. This helps for readability because the
     * lines can become very long. The VariableDeclarationUsageDistance is
     * turned off for the variable 'offset', for readability this variable is
     * declared at the start. Structurally this is logical so the warning is
     * ignored. CHECKSTYLE:Off:lineLength
     * CHECKSTYLE:Off:variableDeclarationUsageDistance
     * </p>
     *
     * @param container
     *            GameContainer - The Slick2D GameContainer that the game runs
     *            in.
     * @param graphics
     *            Graphics - The Slick2D Graphics object used for rendering.
     * @throws SlickException
     *             When something is wrong with the OpenGL context.
     */
    @Override
    public void render(GameContainer container, Graphics graphics)
            throws SlickException {
        float offset = bottom;
        drawLeftSide(graphics);
        drawRightSide(graphics);

        offset -= (margin + dashboardPlayerContainerLeft.getHeight());

        drawLifesLeft(graphics, offset);
        drawLifesRight(graphics, offset);

        offset -= (margin + dashboardLivesContainer.getHeight());

        drawTimeBar(graphics, offset);
        drawLevelIndicator(graphics);
    }

    /**
     * Updates the dashboard every tick.
     * 
     * @param delta
     *            int - Time since last call.
     * @throws SlickException
     *             - If the Game engine crashes
     */
    public void update(int delta) throws SlickException {
        controller.setTimeBar(timeBar, right, left, margin);
        controller.setPlayerInfo();
        controller.setLevel();
    }

    /**
     * Draws the current level number in the bottom of the screen.
     * 
     * @param graphics
     *            graphics used to draw the elements
     */
    private void drawLevelIndicator(Graphics graphics) {
        graphics.drawImage(levelContainer, left + (right - left) / 2
                - levelContainer.getWidth() / 2, bottom - margin
                - levelContainer.getHeight());

        final String levelIdString = String.valueOf(levelId);
        ttf.drawString(left + (right - left) / 2 - ttf.getWidth(levelIdString)
                / 2, bottom - margin - (levelContainer.getHeight() / 4.0f)
                - ttf.getHeight(levelIdString) / 2.0f, levelIdString, Color.red);
    }

    /**
     * draws the timerbar with the current amount of time left in the level.
     * 
     * @param graphics
     *            graphics used to draw the timerbar.
     * @param offset
     *            offset used to determine the position of the timerbar.
     */
    private void drawTimeBar(Graphics graphics, float offset) {
        timeBarBackground.setY(offset - margin - timeBarBackground.getHeight());
        timeBar.setY(timeBarBackground.getY() + border);

        graphics.setColor(Color.gray);
        graphics.fill(timeBarBackground);
        graphics.setColor(Color.lightGray);
        graphics.draw(timeBarBackground);
        graphics.setColor(Color.red);
        graphics.fill(timeBar);
    }

    /**
     * draws the current lifes of the first player on the left side of the
     * screen.
     * 
     * @param graphics
     *            graphics used to draw the elements
     * @param offset
     *            offset used to determine the position of the elements
     */
    private void drawLifesRight(Graphics graphics, float offset) {
        final int lifeWidthRight = (dashboardlivesFull.getWidth() * livesRight) / 9;
        graphics.drawImage(dashboardLivesContainer, right - margin
                - dashboardLivesContainer.getWidth(), offset - margin
                - dashboardLivesContainer.getHeight());
        // flames for lives
        graphics.drawImage(dashboardlivesFull, right - margin - lifeWidthRight,
                offset - margin - dashboardLivesContainer.getHeight(), right
                        - margin, offset - margin,
                dashboardlivesFull.getWidth() - lifeWidthRight, 0,
                dashboardlivesFull.getWidth(), dashboardlivesFull.getHeight());
        // x-ses for non-lives
        graphics.drawImage(dashboardlivesEmpty, right - margin
                - dashboardLivesContainer.getWidth(), offset - margin
                - dashboardLivesContainer.getHeight(), right - margin
                - lifeWidthRight, offset - margin, 0, 0,
                dashboardlivesFull.getWidth() - lifeWidthRight,
                dashboardlivesFull.getHeight());
    }

    /**
     * draws the current lifes of the second player on the right side of the
     * screen.
     * 
     * @param graphics
     *            graphics used to draw the elements
     * @param offset
     *            offset used to determine the position of the elements
     */
    private void drawLifesLeft(Graphics graphics, float offset) {
        graphics.drawImage(dashboardLivesContainer, left + margin, offset
                - margin - dashboardLivesContainer.getHeight());
        final int lifeWidthLeft = (dashboardlivesFull.getWidth() * livesLeft) / 9;
        // flames for lives
        graphics.drawImage(dashboardlivesFull, left + margin, offset - margin
                - dashboardLivesContainer.getHeight(), left + margin
                + lifeWidthLeft, offset - margin, 0, 0, lifeWidthLeft,
                dashboardlivesFull.getHeight());
        // x-ses for non-lives
        graphics.drawImage(dashboardlivesEmpty, left + margin + lifeWidthLeft,
                offset - margin - dashboardLivesContainer.getHeight(), left
                        + margin + dashboardLivesContainer.getWidth(), offset
                        - margin, 0, 0, dashboardlivesEmpty.getWidth()
                        - lifeWidthLeft, dashboardlivesEmpty.getHeight());
    }

    /**
     * draws the containers and elements of the right side of the screen, these
     * containers container the second players info.
     * 
     * @param graphics
     *            graphics used to draw the elements
     */
    private void drawRightSide(Graphics graphics) {
        graphics.drawImage(dashboardPlayerContainerRight, right - margin
                - dashboardPlayerContainerRight.getWidth(), bottom - margin
                - dashboardPlayerContainerRight.getHeight());
        final String scoreStringRight = String.valueOf(scoreRight);
        ttf.drawString(
                right - margin - dashboardPlayerContainerRight.getWidth() + 125
                        - ttf.getWidth(scoreStringRight), bottom - margin
                        - dashboardPlayerContainerRight.getHeight() / 2.0f
                        - ttf.getHeight(scoreStringRight) / 2.0f,
                scoreStringRight, Color.darkGray);
        final String moneyStringRight = String.format("$ %d", moneyRight);
        ttf.drawString(
                right - margin - dashboardPlayerContainerRight.getWidth() + 125
                        + 135 - ttf.getWidth(moneyStringRight), bottom - margin
                        - dashboardPlayerContainerRight.getHeight() / 2.0f
                        - ttf.getHeight(moneyStringRight) / 2.0f,
                moneyStringRight, Color.darkGray);
    }

    /**
     * draws the containers and elements of the left side of the screen, these
     * containers container the first players info.
     * 
     * @param graphics
     *            graphics used to draw the elements
     */
    private void drawLeftSide(Graphics graphics) {
        graphics.drawImage(dashboardPlayerContainerLeft, left + margin, bottom
                - margin - dashboardPlayerContainerLeft.getHeight());
        final String scoreStringLeft = String.valueOf(scoreLeft);
        ttf.drawString(left + margin + dashboardPlayerContainerLeft.getWidth()
                - 2 * margin - ttf.getWidth(scoreStringLeft),
                bottom - margin - dashboardPlayerContainerLeft.getHeight()
                        / 2.0f - ttf.getHeight(scoreStringLeft) / 2.0f,
                scoreStringLeft, Color.darkGray);
        final String moneyStringLeft = String.format("$ %d", moneyLeft);
        ttf.drawString(left + margin + dashboardPlayerContainerLeft.getWidth()
                - 2 * margin - ttf.getWidth(moneyStringLeft) - 135.0f, bottom
                - margin - dashboardPlayerContainerLeft.getHeight() / 2.0f
                - ttf.getHeight(moneyStringLeft) / 2.0f, moneyStringLeft,
                Color.darkGray);

    }

    /**
     * sets the score of the first player on the screen.
     * 
     * @param scoreLeft
     *            the scoreLeft to set
     */
    public void setScoreLeft(int scoreLeft) {
        this.scoreLeft = scoreLeft;
    }

    /**
     * sets the score of the second player on the screen.
     * 
     * @param scoreRight
     *            the scoreRight to set
     */
    public void setScoreRight(int scoreRight) {
        this.scoreRight = scoreRight;
    }

    /**
     * sets the lives of the first player on the screen.
     * 
     * @param livesLeft
     *            the livesLeft to set
     */
    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    /**
     * sets the lives of the first player on the screen.
     * 
     * @param livesRight
     *            the livesRight to set
     */
    public void setLivesRight(int livesRight) {
        this.livesRight = livesRight;
    }

    /**
     * sets the money of the first player on the screen.
     * 
     * @param moneyLeft
     *            the moneyLeft to set
     */
    public void setMoneyLeft(int moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    /**
     * sets the money of the first player on the screen.
     * 
     * @param moneyRight
     *            the moneyRight to set
     */
    public void setMoneyRight(int moneyRight) {
        this.moneyRight = moneyRight;
    }

    /**
     * sets the Id of the level.
     * 
     * @param levelId
     *            the levelId to set
     */
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    /**
     * Sets the background of the timer bar.
     * @param timeBarBackground the timeBarBackground to set
     */
    protected void setTimeBarBackground(Rectangle timeBarBackground) {
        this.timeBarBackground = timeBarBackground;
    }

    /**
     * Sets the timer bar.
     * @param timeBar the timeBar to set
     */
    protected void setTimeBar(Rectangle timeBar) {
        this.timeBar = timeBar;
    }

    /**
     * Sets the controller.
     * @param controller the controller to set
     */
    public void setController(DashboardController controller) {
        this.controller = controller;
    }

    /**
     * Returns the game.
     * @return the game
     */
    public Game getGame() {
        return game;
    }
    
    

}
