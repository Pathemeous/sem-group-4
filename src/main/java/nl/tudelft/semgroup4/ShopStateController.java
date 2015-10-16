package nl.tudelft.semgroup4;

import org.newdawn.slick.state.StateBasedGame;

import nl.tudelft.model.Player;
import nl.tudelft.model.shop.ShopItem;

public class ShopStateController {

    private ShopState shopState;
    private Player selectedPlayer;
    private ShopItem selectedItem;

    public ShopStateController(ShopState shopState) {
        this.shopState = shopState;
        this.selectedPlayer = shopState.getSelectedPlayer();
        this.selectedItem = shopState.getSelectedItem();
    }

    public void applyUpgrade() {
        if (selectedItem != null
                && selectedItem.getPrice() <= selectedPlayer.getMoney()) {

            selectedItem.applyTo(selectedPlayer);
            selectedPlayer.setMoney(selectedPlayer.getMoney()
                    - selectedItem.getPrice());
        }
    }

    public void selectPlayer(int i) {
        selectedPlayer = shopState.getShop().getGame().getPlayers()[i];
    }

    public void selectItem(int i) {
        selectedItem = shopState.getShop().getInventory().get(i);
        
    }

    public void enterState(StateBasedGame game) {
        game.enterState(States.GameState);
        
    }
}
