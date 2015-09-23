package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ShieldPowerup extends Powerup {
    
    private Player player;
    private int removingShieldCounter = 0;
    private boolean isHit = false;
    
    public ShieldPowerup(float locX, float locY) {
        super(Resources.pickupPowerShield, locX, locY);
    }
    
    public boolean isHit() {
        return isHit;
    }
    
    public void setHit(boolean hit) {
        isHit = hit;
    }
    
    @Override
    public void activate(Player player) {
        if (!isActive()) {
            super.activate(player);
            this.player = player;
            
            if (player.hasPowerup(Powerup.SHIELD)) {
                player.removePowerup(Powerup.SHIELD).toRemove();
            }
            
            if (!player.hasPowerup(Powerup.INVINCIBLE)) {
                player.setPowerup(Powerup.SHIELD, this);
            } else {
                toRemove();
            }
        }
    }
    
    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);
        
        if (isHit) {
            removingShieldCounter++;
        }
        
        if (removingShieldCounter == 120) {
            player.removePowerup(Powerup.SHIELD).toRemove();
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);
        
        if (isActive() && removingShieldCounter % 2 == 0) {
            graphics.setColor(Color.yellow);
            graphics.draw(player.getBounds());
            graphics.setColor(Color.green);
        }
    }
}
