package nl.tudelft.controller;

import nl.tudelft.model.player.Player;
import nl.tudelft.view.ShopState;
import nl.tudelft.view.States;

import org.newdawn.slick.state.StateBasedGame;

public class ShopStateController {

    private ShopState shopState;

    /**
     * Creates the controller for the shopState.
     * 
     * @param shopState
     *            shopstate to create a controller for
     */
    public ShopStateController(ShopState shopState) {
        this.shopState = shopState;
    }

    /**
     * Checks if the selected player has enough money and an item is selected, buys the item and
     * aplies it to the selected player.
     */
    public void applyUpgrade() {
        if (shopState.getSelectedItem() != null
                && shopState.getSelectedItem().getPrice() <= shopState.getShop().getGame()
                        .getPlayers()[shopState.getSelectedPlayer()].getMoney()) {

            // Gets the return value of the applyTo, which is the decorated player.
            Player modifiedPlayer =
                    shopState.getSelectedItem().applyTo(
                            shopState.getShop().getGame().getPlayers()[shopState
                                    .getSelectedPlayer()]);
            // Deducts money from the player.
            shopState.getShop().getGame().getPlayers()[shopState.getSelectedPlayer()]
                    .setMoney(shopState.getShop().getGame().getPlayers()[shopState
                            .getSelectedPlayer()].getMoney()
                            - shopState.getSelectedItem().getPrice());

            // Gets the original player
            Player oldPlayer =
                    shopState.getShop().getGame().getPlayers()[shopState.getSelectedPlayer()];
            // replaces the old instance of the player with the new decorated version.
            shopState.getShop().getGame().decoratePlayer(oldPlayer, modifiedPlayer);
        }
    }

    /**
     * Selects the player according to the which player was clicked on.
     * 
     * @param selectedPlayer
     *            which player to select
     */
    public void selectPlayer(int selectedPlayer) {
        shopState.setSelectedPlayer(selectedPlayer);
    }

    /**
     * Selects the item according to the which item was clicked on.
     * 
     * @param selectedItem
     *            which item to select
     */
    public void selectItem(int selectedItem) {
        shopState.setSelectedItem(shopState.getShop().getInventory().get(selectedItem));

    }

    /**
     * Enters an other state.
     * 
     * @param game
     *            game in which to enter a different state.
     */
    public void enterState(StateBasedGame game) {
        game.enterState(States.GameState);

    }

    /**
     * Returns the shopState.
     * 
     * @return the shopState
     */
    public ShopState getShopState() {
        return shopState;
    }
}
