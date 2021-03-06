package nl.tudelft.controller;

import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.Level;
import nl.tudelft.model.player.Player;
import nl.tudelft.view.Dashboard;

import org.newdawn.slick.geom.Rectangle;

public class DashboardController {
    private AbstractGame game;
    private Dashboard dashboard;

    /**
     * Constructor of the Dashboard controller.
     * 
     * @param game
     *            game of which the data is displayed
     * @param dashboard
     *            dashboard on which the controller makes changes
     */
    public DashboardController(Dashboard dashboard) {
        this.dashboard = dashboard;
        this.game = dashboard.getGame();
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

    /**
     * Returns the game.
     * @return the game
     */
    public AbstractGame getGame() {
        return game;
    }

    /**
     * Returns the dashboard.
     * @return the dashboard
     */
    public Dashboard getDashboard() {
        return dashboard;
    }

    /**
     * Sets the game.
     * @param game the game to set
     */
    public void setGame(AbstractGame game) {
        this.game = game;
    }
    
    
}
