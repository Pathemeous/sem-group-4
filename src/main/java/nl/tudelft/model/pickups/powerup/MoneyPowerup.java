package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Resources;

public class MoneyPowerup extends Powerup {

    //TODO: change resource
    public MoneyPowerup(float locX, float locY) {
        super(Resources.pickupPowerPoints, locX, locY);
    }

    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            player.setMoney(player.getMoney() + 50);
            toRemove();
        }
    }
}
