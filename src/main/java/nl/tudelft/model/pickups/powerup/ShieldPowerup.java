package nl.tudelft.model.pickups.powerup;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.Resources;

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
            setActive(true);
            this.player = player;
            
            if (player.hasPowerup(Powerup.SHIELD)) {
                System.out.println("Remove old shield");
                player.removePowerup(Powerup.SHIELD).toRemove();
            }
            
            if (!player.hasPowerup(Powerup.INVINCIBLE)) {
                player.setPowerup(Powerup.SHIELD, this);
            } else {
                System.out.println("Remove shield");
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
            System.out.println("remove shield after hit");
            player.removePowerup(Powerup.SHIELD).toRemove();
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);
        
        System.out.println("Shield is active: "+isActive());
        System.out.println("Removing shield counter remainder: "+(removingShieldCounter %2 == 0));
        if (isActive() && removingShieldCounter % 2 == 0) {
            graphics.setColor(Color.yellow);
            graphics.draw(player.getBounds());
            graphics.setColor(Color.green);
        }
    }
}
