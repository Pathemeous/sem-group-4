package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class InvinciblePowerup extends AbstractPowerup {

    private Player player;
    private int timeoutCounter = 0;

    public InvinciblePowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerInvincible(), locX, locY);
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

            if (player.hasPowerup(AbstractPowerup.INVINCIBLE)) {
                player.removePowerup(AbstractPowerup.INVINCIBLE).setToRemove();
            }
            if (player.hasPowerup(AbstractPowerup.SHIELD)) {
                player.removePowerup(AbstractPowerup.SHIELD).setToRemove();
            }

            player.setPowerup(AbstractPowerup.INVINCIBLE, this);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);

        if (isActive()) {
            timeoutCounter++;
        }

        if (timeoutCounter == 600) {
            player.removePowerup(AbstractPowerup.INVINCIBLE).setToRemove();
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);
        final ResourcesWrapper res = new ResourcesWrapper();

        if (isActive() && ((timeoutCounter > 540 && timeoutCounter % 2 == 0) 
                || timeoutCounter < 540)) {
            graphics.drawImage(res.getPowerInvincible(), player.getLocX(), player.getLocY());
        }
    }
    
    protected int getCounter() {
        return timeoutCounter;
    }
    
    protected void setCounter(int counter) {
        timeoutCounter = counter;
    }
}
