package nl.tudelft.semgroup4;

import nl.tudelft.model.Game;
import nl.tudelft.model.Level;
import nl.tudelft.model.Player;

import org.newdawn.slick.geom.Rectangle;

public class DashboardController {
    private Game game;
    private Dashboard dashboard;

    /**
     * Constructor of the Dashboard controller.
     * 
     * @param game
     *            game of which the data is displayed
     * @param dashboard
     *            dashboard on which the controller makes changes
     */
    public DashboardController(Game game, Dashboard dashboard) {
        this.game = game;
        this.dashboard = dashboard;
    }

    /**
     * Sets the timerBar according to the current time left in the level.
     * 
     * @param timeBar
     *            timerbar to set
     * @param right
     *            distance from the right
     * @param left
     *            distance from the left
     * @param margin
     *            margin for positioning the bar
     */
    public void setTimeBar(Rectangle timeBar, int right, int left, float margin) {
        timeBar.setWidth((right - left - margin * 2)
                * ((float) game.getCurLevel().getTime() / game.getCurLevel()
                        .getMaxTime()));
    }

    /**
     * sets all information on the screen of the player according to the the
     * current information provided by the game. This includes score, lives and
     * money for both players.
     */
    public void setPlayerInfo() {
        Player[] players = game.getPlayers();

        for (int i = 0; i < players.length; i++) {
            final Player player = players[i];
            if (player != null) {
                if (player.isFirstPlayer()) {
                    dashboard.setScoreLeft(player.getScore());
                    dashboard.setLivesLeft(player.getLives());
                    dashboard.setMoneyLeft(player.getMoney());
                } else {
                    dashboard.setScoreRight(player.getScore());
                    dashboard.setLivesRight(player.getLives());
                    dashboard.setMoneyRight(player.getMoney());
                }
            }
        }

    }

    /**
     * sets the current level to be displayed on the dashboard.
     */
    public void setLevel() {
        Level level = game.getCurLevel();
        if (level != null) {
            dashboard.setLevelId(level.getId());
        }
    }
}
