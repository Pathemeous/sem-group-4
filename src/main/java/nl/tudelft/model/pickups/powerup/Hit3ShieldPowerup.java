package nl.tudelft.model.pickups.powerup;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Hit3ShieldPowerup extends Powerup {

    private Player player;
    private int removingShieldCounter = 0;
    private int isHit = 0;

    public Hit3ShieldPowerup(ResourcesWrapper resources, float locX, float locY) {
        super(resources.getPickupPowerShield(), locX, locY);
    }

    public boolean isHit() {
        return isHit >= 3;
    }

    public void incrementHit() {
        isHit++;
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

            player.setPowerup(Powerup.SHOPSHIELD, this);
        }
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        super.update(container, delta);

        if (isHit()) {
            removingShieldCounter++;
        }

        if (removingShieldCounter == 120) {
            player.removePowerup(Powerup.SHOPSHIELD).toRemove();
        }
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        super.render(container, graphics);

        if (isActive() && removingShieldCounter % 2 == 0) {
            graphics.setColor(Color.green);
            graphics.draw(player.getBounds());
            graphics.setColor(Color.blue);
        }
    }

    protected int getRemovalCounter() {
        return removingShieldCounter;
    }

    protected void setRemovalCounter(int counter) {
        removingShieldCounter = counter;
    }
}
