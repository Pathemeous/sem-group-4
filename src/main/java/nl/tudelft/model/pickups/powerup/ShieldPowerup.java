package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ShieldPowerup extends AbstractPowerup {
    
    private Player player;
    private int timeoutCounter = 0;
    private boolean hit = false;
    
    public ShieldPowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerShield(), locX, locY);
    }
    
    public boolean isHit() {
        return hit;
    }
    
    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            this.player = player;

            if (player.hasPowerup(AbstractPowerup.SHOPSHIELD)) {
                setToRemove();
                return;
            }

            if (player.hasPowerup(AbstractPowerup.SHIELD)) {
                player.removePowerup(AbstractPowerup.SHIELD).setToRemove();
            }
            
            if (player.hasPowerup(AbstractPowerup.INVINCIBLE)) {
                setToRemove();
            } else {
                player.setPowerup(AbstractPowerup.SHIELD, this);
            }
        }
    }
    
    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);
        
        if (hit) {
            timeoutCounter++;
        }
        
        if (timeoutCounter == 120) {
            player.removePowerup(AbstractPowerup.SHIELD).setToRemove();
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);
        
        if (isActive() && timeoutCounter % 2 == 0) {
            graphics.setColor(Color.yellow);
            graphics.draw(player.getBounds());
            graphics.setColor(Color.green);
        }
    }
    
    protected int getRemovalCounter() {
        return timeoutCounter;
    }
    
    protected void setRemovalCounter(int counter) {
        timeoutCounter = counter;
    }
}
