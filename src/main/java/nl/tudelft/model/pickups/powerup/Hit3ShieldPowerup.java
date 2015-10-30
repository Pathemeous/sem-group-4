package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Hit3ShieldPowerup extends AbstractPowerup {

    private Player player;
    private int timeoutCounter = 0;
    private int hit = 0;

    public Hit3ShieldPowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerShield(), locX, locY);
    }

    public boolean isHit() {
        return hit >= 3;
    }

    public void incrementHit() {
        hit++;
    }

    @Override
    public void activate(Player player) {
        if (!isActive()) {
            setActive(true);
            this.player = player;

            if (player.hasPowerup(AbstractPowerup.INVINCIBLE)) {
                player.removePowerup(AbstractPowerup.INVINCIBLE).setToRemove();
            }
            if (player.hasPowerup(AbstractPowerup.SHIELD)) {
                player.removePowerup(AbstractPowerup.SHIELD).setToRemove();
            }

            player.setPowerup(AbstractPowerup.SHOPSHIELD, this);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);

        if (isHit()) {
            timeoutCounter++;
        }

        if (timeoutCounter == 120) {
            player.removePowerup(AbstractPowerup.SHOPSHIELD).setToRemove();
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);

        if (isActive() && timeoutCounter % 2 == 0) {
            graphics.setColor(Color.green);
            graphics.draw(player.getBounds());
            graphics.setColor(Color.blue);
        }
    }

    protected int getRemovalCounter() {
        return timeoutCounter;
    }

    protected void setRemovalCounter(int counter) {
        timeoutCounter = counter;
    }
}
