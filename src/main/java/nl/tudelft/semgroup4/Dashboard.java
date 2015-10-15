package nl.tudelft.semgroup4;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
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

    private final Game game;
    private final Rectangle timeBarBackground;
    private final Rectangle timeBar;

    private final TrueTypeFont ttf = new TrueTypeFont(new Font("Verdana", Font.BOLD, 30), true);

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

    /* package */Dashboard(ResourcesWrapper resources, Game game, int left, int right, int bottom) {
        this.game = game;

        this.left = left;
        this.right = right;
        this.bottom = bottom;
        
        dashboardPlayerContainerLeft = resources.getDashboardPlayerContainerLeft().copy();
        dashboardPlayerContainerRight = resources.getDashboardPlayerContainerRight().copy();
        dashboardLivesContainer = resources.getDashboardLivesContainer().copy();
        dashboardlivesFull = resources.getDashboardlivesFull().copy();
        dashboardlivesEmpty = resources.getDashboardlivesEmpty().copy();
        levelContainer = resources.getLevelContainer().copy();
        
        timeBarBackground = new Rectangle(
                left + margin,
                0,
                right - left - 2 * margin,
                timeBarHeight);
        timeBar = new Rectangle(
                timeBarBackground.getX() + border,
                0,
                timeBarBackground.getWidth() - 2 * border,
                timeBarBackground.getHeight() - 2 * border);
    }

    /**
     * This renders the Dashboard in his current state.
     *
     * <p>Checkstyle for LineLength and VariableDeclarationUsageDistance are
     * turned off for the scope of the method. This helps for readability because the lines
     * can become very long. The VariableDeclarationUsageDistance is turned off for
     * the variable 'offset', for readability this variable is declared at the start.
     * Structurally this is logical so the warning is ignored.</p>
     *
     * @param container
     *            GameContainer - The Slick2D GameContainer that the game runs in.
     * @param graphics
     *            Graphics - The Slick2D Graphics object used for rendering.
     * @throws SlickException When something is wrong with the OpenGL context.
     */
    @Override
    //CHECKSTYLE:OFF:LineLength
    //CHECKSTYLE:OFF:VariableDeclarationUsageDistance
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        float offset = bottom;
        // bottom bar
        // left side
        graphics.drawImage(dashboardPlayerContainerLeft,
                left + margin,
                bottom - margin - dashboardPlayerContainerLeft.getHeight());
        final String scoreStringLeft = String.valueOf(scoreLeft);
        ttf.drawString(
                left + margin + dashboardPlayerContainerLeft.getWidth() - 2 * margin - ttf.getWidth(scoreStringLeft),
                bottom - margin - dashboardPlayerContainerLeft.getHeight() / 2.0f - ttf.getHeight(scoreStringLeft) / 2.0f, scoreStringLeft, Color.darkGray);
        final String moneyStringLeft = String.format("$ %d", moneyLeft);
        ttf.drawString(
                left   + margin + dashboardPlayerContainerLeft.getWidth() - 2 * margin - ttf.getWidth(moneyStringLeft) - 135.0f,
                bottom - margin - dashboardPlayerContainerLeft.getHeight() / 2.0f - ttf.getHeight(moneyStringLeft) / 2.0f, moneyStringLeft, Color.darkGray);

        // right side
        graphics.drawImage(dashboardPlayerContainerRight,
                right - margin - dashboardPlayerContainerRight.getWidth(),
                bottom - margin - dashboardPlayerContainerRight.getHeight());
        final String scoreStringRight = String.valueOf(scoreRight);
        ttf.drawString(
                right - margin - dashboardPlayerContainerRight.getWidth() + 125 - ttf.getWidth(scoreStringRight),
                bottom - margin - dashboardPlayerContainerRight.getHeight() / 2.0f - ttf.getHeight(scoreStringRight) / 2.0f, scoreStringRight, Color.darkGray);
        final String moneyStringRight = String.format("$ %d", moneyRight);
        ttf.drawString(
                right  - margin - dashboardPlayerContainerRight.getWidth() + 125 + 135  - ttf.getWidth(moneyStringRight),
                bottom - margin - dashboardPlayerContainerRight.getHeight() / 2.0f - ttf.getHeight(moneyStringRight) / 2.0f, moneyStringRight, Color.darkGray);

        offset -= (margin + dashboardPlayerContainerLeft.getHeight());

        // lives
        // left
        graphics.drawImage(dashboardLivesContainer,
                left   + margin,
                offset - margin - dashboardLivesContainer.getHeight());
        final int lifeWidthLeft = (dashboardlivesFull.getWidth() * livesLeft) / 9;
        // flames for lives
        graphics.drawImage(dashboardlivesFull,
                left   + margin,
                offset - margin - dashboardLivesContainer.getHeight(),
                left   + margin + lifeWidthLeft,
                offset - margin,
                0,
                0,
                lifeWidthLeft, dashboardlivesFull.getHeight());
        // x-ses for non-lives
        graphics.drawImage(dashboardlivesEmpty,
                left   + margin + lifeWidthLeft,
                offset - margin - dashboardLivesContainer.getHeight(),
                left   + margin + dashboardLivesContainer.getWidth(),
                offset - margin,
                0,
                0,
                dashboardlivesEmpty.getWidth() - lifeWidthLeft,
                dashboardlivesEmpty.getHeight());

        // right
        final int lifeWidthRight = (dashboardlivesFull.getWidth() * livesRight) / 9;
        graphics.drawImage(dashboardLivesContainer,
                right  - margin - dashboardLivesContainer.getWidth(),
                offset - margin - dashboardLivesContainer.getHeight());
        // flames for lives
        graphics.drawImage(dashboardlivesFull,
                right  - margin - lifeWidthRight,
                offset - margin - dashboardLivesContainer.getHeight(),
                right  - margin,
                offset - margin,
                dashboardlivesFull.getWidth() - lifeWidthRight,
                0,
                dashboardlivesFull.getWidth(),
                dashboardlivesFull.getHeight());
        // x-ses for non-lives
        graphics.drawImage(dashboardlivesEmpty,
                right  - margin - dashboardLivesContainer.getWidth(),
                offset - margin - dashboardLivesContainer.getHeight(),
                right  - margin - lifeWidthRight,
                offset - margin,
                0,
                0,
                dashboardlivesFull.getWidth() - lifeWidthRight,
                dashboardlivesFull.getHeight());

        offset -= (margin + dashboardLivesContainer.getHeight());

        // timebar
        timeBarBackground.setY(offset - margin - timeBarBackground.getHeight());
        timeBar.setY(timeBarBackground.getY() + border);

        // timeBar
        graphics.setColor(Color.gray);
        graphics.fill(timeBarBackground);
        graphics.setColor(Color.lightGray);
        graphics.draw(timeBarBackground);
        graphics.setColor(Color.red);
        graphics.fill(timeBar);

        // middle (level)
        graphics.drawImage(levelContainer,
                left + (right - left) / 2 - levelContainer.getWidth() / 2,
                bottom - margin - levelContainer.getHeight());

        final String levelIdString = String.valueOf(levelId);
        ttf.drawString(
                left + (right - left) / 2 - ttf.getWidth(levelIdString) / 2,
                bottom - margin - (levelContainer.getHeight() / 4.0f) - ttf.getHeight(levelIdString) / 2.0f,
                levelIdString, Color.red);

    }
    //CHECKSTYLE:ON:VariableDeclarationUsageDistance
    //CHECKSTYLE:ON:LineLength

    /**
     * Updates the dashboard every tick.
     * 
     * @param delta
     *            int - Time since last call.
     * @throws SlickException
     *             - If the Game engine crashes
     */
    public void update(int delta) throws SlickException {
        timeBar.setWidth((right - left - margin * 2)
                * ((float) game.getCurLevel().getTime() / game.getCurLevel().getMaxTime()));

        Player[] players = game.getPlayers();

        for (int i = 0; i < players.length; i++) {
            final Player player = players[i];
            if (player != null) {
                if (player.isFirstPlayer()) {
                    scoreLeft = player.getScore();
                    livesLeft = player.getLives();
                    moneyLeft = player.getMoney();
                } else {
                    scoreRight = player.getScore();
                    livesRight = player.getLives();
                    moneyRight = player.getMoney();
                }
            }
        }

        Level level = game.getCurLevel();
        if (level != null) {
            levelId = level.getId();
        }

    }
}
