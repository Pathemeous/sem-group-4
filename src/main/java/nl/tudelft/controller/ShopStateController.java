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
                && shopState.getSelectedItem().getPrice() <= shopState.getSelectedPlayer()
                        .getMoney()) {

            Player modifiedplayer =
                    shopState.getSelectedItem().applyTo(shopState.getSelectedPlayer());
            shopState.getSelectedPlayer().setMoney(
                    shopState.getSelectedPlayer().getMoney()
                            - shopState.getSelectedItem().getPrice());

            if (shopState.getSelectedPlayer().isFirstPlayer()) {
                shopState.getShop().getGame().getPlayers()[0] = modifiedplayer;
            } else {
                shopState.getShop().getGame().getPlayers()[1] = modifiedplayer;
            }
        }
    }

    /**
     * Selects the player according to the which player was clicked on.
     * 
     * @param selectedPlayer
     *            which player to select
     */
    public void selectPlayer(int selectedPlayer) {
        shopState
                .setSelectedPlayer(shopState.getShop().getGame().getPlayers()[selectedPlayer]);
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
