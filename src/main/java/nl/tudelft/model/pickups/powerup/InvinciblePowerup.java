package nl.tudelft.model.pickups.powerup;

import nl.tudelft.model.Player;
import nl.tudelft.semgroup4.Modifiable;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class InvinciblePowerup extends Powerup {

    private Player player;
    private int invincibilityCounter = 0;

    public InvinciblePowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerInvincible(), locX, locY);
    }

    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            this.player = player;

            if (player.hasPowerup(Powerup.INVINCIBLE)) {
                player.removePowerup(Powerup.INVINCIBLE).toRemove();
            }
            if (player.hasPowerup(Powerup.SHIELD)) {
                player.removePowerup(Powerup.SHIELD).toRemove();
            }

            player.setPowerup(Powerup.INVINCIBLE, this);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);

        if (isActive()) {
            invincibilityCounter++;
        }

        if (invincibilityCounter == 600) {
            player.removePowerup(Powerup.INVINCIBLE).toRemove();
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);
        final ResourcesWrapper res = new ResourcesWrapper();

        if (isActive() && ((invincibilityCounter > 540 && invincibilityCounter % 2 == 0) 
                || invincibilityCounter < 540)) {
            graphics.drawImage(res.getPowerInvincible(), player.getLocX(), player.getLocY());
        }
    }
    
    protected int getCounter() {
        return invincibilityCounter;
    }
    
    protected void setCounter(int counter) {
        invincibilityCounter = counter;
    }
}
