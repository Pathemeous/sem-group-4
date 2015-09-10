package nl.tudelft.semgroup4;


import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

import java.awt.*;


/**
 * Created by justin on 10/09/15.
 */
public class Dashboard implements Renderable {

    private static float margin = 10;
    private static float border = 2;
    private static float timeBarHeight = 30;

    private Image dashboardPlayerContainerLeft = Resources.dashboardPlayerContainerLeft.copy();
    private Image dashboardPlayerContainerRight = Resources.dashboardPlayerContainerRight.copy();
    private Image dashboardLivesContainer = Resources.dashboardLivesContainer.copy();
    private Image dashboardlivesFull = Resources.dashboardlivesFull.copy();
    private Image dashboardlivesEmpty = Resources.dashboardlivesEmpty.copy();
    private Image levelContainer = Resources.levelContainer.copy();

    private final Game game;
    private final Rectangle timeBarBackground;
    private final Rectangle timeBar;

    private final TrueTypeFont ttf = new TrueTypeFont(new Font("Verdana", Font.BOLD, 30), true);

    private int levelId = -1;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private int livesLeft = 0;
    private int livesRight = 0;

    private final int left;
    private final int right;
    private final int bottom;

    /* package */ Dashboard(Game game, int left, int right, int bottom) {
        this.game = game;

        this.left = left;
        this.right = right;
        this.bottom = bottom;

        timeBarBackground = new Rectangle(
                left + margin, 0,
                right - left - 2 * margin, timeBarHeight);
        timeBar = new Rectangle(
                timeBarBackground.getX() + border, 0,
                timeBarBackground.getWidth() - 2 * border, timeBarBackground.getHeight() - 2 * border);
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        final Level curLevel = game.getCurLevel();
        curLevel.getTime();

        float offset = bottom;
        {
            // bottom bar
            {
                // left side
                graphics.drawImage(dashboardPlayerContainerLeft,
                        left + margin,
                        bottom - margin - dashboardPlayerContainerLeft.getHeight());
                String scoreString = String.valueOf(scoreLeft);
                ttf.drawString(
                        left + margin + dashboardPlayerContainerLeft.getWidth() - 2 * margin - ttf.getWidth(scoreString),
                        bottom - margin - dashboardPlayerContainerLeft.getHeight() / 2 - ttf.getHeight(scoreString) / 2,
                        scoreString,
                        Color.darkGray);
            }
            {
                // right side
                graphics.drawImage(dashboardPlayerContainerRight,
                        right - margin - dashboardPlayerContainerRight.getWidth(),
                        bottom - margin - dashboardPlayerContainerRight.getHeight());
                String scoreString = String.valueOf(scoreRight);
                ttf.drawString(
                        right - margin - dashboardPlayerContainerRight.getWidth() + 125 - ttf.getWidth(scoreString),
                        bottom - margin - dashboardPlayerContainerRight.getHeight() / 2 - ttf.getHeight(scoreString) / 2,
                        scoreString,
                        Color.darkGray);
            }
        }
        offset -= (margin + dashboardPlayerContainerLeft.getHeight());
        {
            // lives
            {
                {
                    // left
                    graphics.drawImage(dashboardLivesContainer,
                            left + margin,
                            offset - margin - dashboardLivesContainer.getHeight());
                    int lifeWidth = (dashboardlivesFull.getWidth() * livesLeft) / 9;
                    // flames for lives
                    graphics.drawImage(dashboardlivesFull,
                            left + margin,
                            offset - margin - dashboardLivesContainer.getHeight(),
                            left + margin + lifeWidth,
                            offset - margin,
                            0,
                            0,
                            lifeWidth,
                            dashboardlivesFull.getHeight());
                    // x-ses for non-lives
                    graphics.drawImage(dashboardlivesEmpty,
                            left + margin + lifeWidth,
                            offset - margin - dashboardLivesContainer.getHeight(),
                            left + margin + dashboardLivesContainer.getWidth(),
                            offset - margin,
                            0,
                            0,
                            dashboardlivesEmpty.getWidth() - lifeWidth,
                            dashboardlivesEmpty.getHeight());
                }
                {
                    // right
                    int lifeWidth = (dashboardlivesFull.getWidth() * livesRight) / 9;
                    graphics.drawImage(dashboardLivesContainer,
                            right - margin - dashboardLivesContainer.getWidth(),
                            offset - margin - dashboardLivesContainer.getHeight());
                    // flames for lives
                    graphics.drawImage(dashboardlivesFull,
                            right - margin - lifeWidth,
                            offset - margin - dashboardLivesContainer.getHeight(),
                            right - margin,
                            offset - margin,
                            dashboardlivesFull.getWidth() - lifeWidth,
                            0,
                            dashboardlivesFull.getWidth(),
                            dashboardlivesFull.getHeight());
                    // x-ses for non-lives
                    graphics.drawImage(dashboardlivesEmpty,
                            right - margin - dashboardLivesContainer.getWidth(),
                            offset - margin - dashboardLivesContainer.getHeight(),
                            right - margin - lifeWidth,
                            offset - margin,
                            0,
                            0,
                            dashboardlivesFull.getWidth() - lifeWidth,
                            dashboardlivesFull.getHeight());

                }

            }
        }
        offset -= (margin + dashboardLivesContainer.getHeight());
        {
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
        }
        {
            // middle (level)
            graphics.drawImage(levelContainer,
                    (right - left) / 2 - levelContainer.getWidth() / 2,
                    bottom - margin - levelContainer.getHeight());

            String levelIdString = String.valueOf(levelId);
            ttf.drawString(
                    (right - left) / 2 - ttf.getWidth(levelIdString) / 2,
                    bottom - margin - (levelContainer.getHeight() / 4) - ttf.getHeight(levelIdString) / 2,
                    levelIdString,
                    Color.red);
        }

    }

    public void update(int delta) throws SlickException {
        timeBar.setWidth((right - left - margin * 2) * ((float) game.getCurLevel().getTime() / game.getCurLevel().getMaxTime()));

        List<Player> players = game.getPlayers();
        if (players.size() > 1) {
            scoreLeft  = players.get(0).getScore();
            scoreRight = players.get(1).getScore();

            livesLeft  = players.get(0).getLives();
            scoreRight = players.get(1).getLives();
        } else if (players.size() == 1) {
            scoreLeft  = players.get(0).getScore();
            livesLeft  = players.get(0).getLives();
        } else {
            // ??
        }

        Level level = game.getCurLevel();
        if (level != null) {
            levelId = level.getID();
        }

    }
}
