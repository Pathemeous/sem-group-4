package nl.tudelft.model;


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
public class Dashboard implements Renderable, Updateable {

    private static float margin = 10;
    private static float border = 2;
    private static float timeBarHeight = 30;

    // hier blijven
    private Image dashboardPlayerContainerLeft;
    private Image dashboardPlayerContainerRight;
    private Image dashboardLivesContainer;
    private Image dashboardlivesFull;
    private Image dashboardlivesEmpty;
    private Image levelContainer;

    private final Game game;
    private final Rectangle timeBarBackground;
    private final Rectangle timeBar;

    private final TrueTypeFont ttf = new TrueTypeFont(new Font("Verdana", Font.BOLD, 30), true);

    private int levelId = -1;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private int livesLeft = 0;
    private int livesRight = 0;

    /* package */ Dashboard(Game game) {
        this.game = game;

        try {
            // naar resources
            dashboardPlayerContainerLeft = new Image("src/main/resources/img/dashboard/player_container_1.png");
            dashboardPlayerContainerRight = new Image("src/main/resources/img/dashboard/player_container_2.png");
            dashboardLivesContainer = new Image("src/main/resources/img/dashboard/lives_container.png");
            dashboardlivesFull = new Image("src/main/resources/img/dashboard/lives_full.png");
            dashboardlivesEmpty = new Image("src/main/resources/img/dashboard/lives_empty.png");
            levelContainer = new Image("src/main/resources/img/dashboard/level_container.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        timeBarBackground = new Rectangle(
                getLeft() + margin, 0,
                getRight() - getLeft() - 2 * margin, timeBarHeight);
        timeBar = new Rectangle(
                timeBarBackground.getX() + border, 0,
                timeBarBackground.getWidth() - 2 * border, timeBarBackground.getHeight() - 2 * border);
    }

    private float getLeft() {
        return 2 * margin;
    }
    private float getRight() {
        return 1200 - 2 * margin;
    }
    private float getBottom() {
        return 300;
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        final Level curLevel = game.getCurLevel();
        curLevel.getTime();

        { /* temp !*/
            graphics.setColor(new Color(0.0f, 1.0f, 0.0f, 1.0f));
            graphics.drawRect(getLeft(), 0, getRight() - getLeft(), getBottom());
        }

        float offset = getBottom();
        {
            // bottom bar
            {
                // left side
                graphics.drawImage(dashboardPlayerContainerLeft,
                        getLeft() + margin,
                        getBottom() - margin - dashboardPlayerContainerLeft.getHeight());
                String scoreString = String.valueOf(scoreLeft);
                ttf.drawString(
                        getLeft() + margin + dashboardPlayerContainerLeft.getWidth() - 2 * margin - ttf.getWidth(scoreString),
                        getBottom() - margin - dashboardPlayerContainerLeft.getHeight() / 2 - ttf.getHeight(scoreString) / 2,
                        scoreString,
                        Color.darkGray);
            }
            {
                // right side
                graphics.drawImage(dashboardPlayerContainerRight,
                        getRight() - margin - dashboardPlayerContainerRight.getWidth(),
                        getBottom() - margin - dashboardPlayerContainerRight.getHeight());
                String scoreString = String.valueOf(scoreRight);
                ttf.drawString(
                        getRight() - margin - dashboardPlayerContainerRight.getWidth() + 125 - ttf.getWidth(scoreString),
                        getBottom() - margin - dashboardPlayerContainerRight.getHeight() / 2 - ttf.getHeight(scoreString) / 2,
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
                            getLeft() + margin,
                            offset - margin - dashboardLivesContainer.getHeight());
                    int lifeWidth = (dashboardlivesFull.getWidth() * livesLeft) / 9;
                    // flames for lives
                    graphics.drawImage(dashboardlivesFull,
                            getLeft() + margin,
                            offset - margin - dashboardLivesContainer.getHeight(),
                            getLeft() + margin + lifeWidth,
                            offset - margin,
                            0,
                            0,
                            lifeWidth,
                            dashboardlivesFull.getHeight());
                    // x-ses for non-lives
                    graphics.drawImage(dashboardlivesEmpty,
                            getLeft() + margin + lifeWidth,
                            offset - margin - dashboardLivesContainer.getHeight(),
                            getLeft() + margin + dashboardLivesContainer.getWidth(),
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
                            getRight() - margin - dashboardLivesContainer.getWidth(),
                            offset - margin - dashboardLivesContainer.getHeight());
                    // flames for lives
                    graphics.drawImage(dashboardlivesFull,
                            getRight() - margin - lifeWidth,
                            offset - margin - dashboardLivesContainer.getHeight(),
                            getRight() - margin,
                            offset - margin,
                            dashboardlivesFull.getWidth() - lifeWidth,
                            0,
                            dashboardlivesFull.getWidth(),
                            dashboardlivesFull.getHeight());
                    // x-ses for non-lives
                    graphics.drawImage(dashboardlivesEmpty,
                            getRight() - margin - dashboardLivesContainer.getWidth(),
                            offset - margin - dashboardLivesContainer.getHeight(),
                            getRight() - margin - lifeWidth,
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
                    (getRight() - getLeft()) / 2 - levelContainer.getWidth() / 2,
                    getBottom() - margin - levelContainer.getHeight());

            String levelIdString = String.valueOf(levelId);
            ttf.drawString(
                    (getRight() - getLeft()) / 2 - ttf.getWidth(levelIdString) / 2,
                    getBottom() - margin - (levelContainer.getHeight() / 4) - ttf.getHeight(levelIdString) / 2,
                    levelIdString,
                    Color.red);
        }

    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        timeBar.setWidth((getRight() - getLeft() - margin * 2) * ((float) game.getCurLevel().getTime() / 120f));

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
