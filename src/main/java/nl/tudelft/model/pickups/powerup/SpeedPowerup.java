package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Player;

import org.newdawn.slick.SlickException;

public class SpeedPowerup extends Powerup {
    
    private Player player;
    private int speedCount = 0;
    
    public SpeedPowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerSpeedup(), locX, locY);
    }
    
    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            this.player = player;
            
            if (player.hasPowerup(Powerup.SPEED)) {
                player.removePowerup(Powerup.SPEED).toRemove();
            }
            
            player.setPowerup(Powerup.SPEED, this);
            player.applySpeedup();
        }
    }
    
    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);
        
        if (isActive()) {
            speedCount++;
        }
        
        if (speedCount == 600) {
            player.removePowerup(Powerup.SPEED).toRemove();
            if (!player.isShopSpeed()) {
                player.setDefaultSpeed();
            }
        }
    }
    
    protected int getSpeedCount() {
        return speedCount;
    }
    
    protected void setSpeedCount(int speed) {
        speedCount = speed;
    }
}
