package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class MoneyPowerup extends Powerup {

    public MoneyPowerup(ResourcesWrapper resources, float locX, float locY) {
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
