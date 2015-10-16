package nl.tudelft.semgroup4;

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
     * Checks if the selected player has enough money and an item is selected,
     * buys the item and aplies it to the selected player.
     */
    public void applyUpgrade() {
        if (shopState.getSelectedItem() != null
                && shopState.getSelectedItem().getPrice() <= shopState
                        .getSelectedPlayer().getMoney()) {

            shopState.getSelectedItem().applyTo(shopState.getSelectedPlayer());
            shopState.getSelectedPlayer().setMoney(
                    shopState.getSelectedPlayer().getMoney()
                            - shopState.getSelectedItem().getPrice());
        }
    }

    /**
     * Selects the player according to the which player was clicked on.
     * 
     * @param i
     *            which player to select
     */
    public void selectPlayer(int selectedPlayer) {
        shopState
                .setSelectedPlayer(shopState.getShop().getGame().getPlayers()[selectedPlayer]);
    }

    /**
     * Selects the item according to the which item was clicked on.
     * 
     * @param i
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
}
