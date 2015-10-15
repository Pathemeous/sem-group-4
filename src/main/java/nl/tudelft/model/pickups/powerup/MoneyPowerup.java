package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.resources.ResourceWrapper;

public class MoneyPowerup extends Powerup {

    public MoneyPowerup(ResourceWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerMoney(), locX, locY);
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
