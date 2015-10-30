package nl.tudelft.model.player;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.model.pickups.powerup.AbstractPowerup;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class AbstractPlayerDecorator implements Player {

    Player wrappedObject;

    public AbstractPlayerDecorator(Player wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    @Override
    public <T extends Modifiable> void update(T container, int delta) throws SlickException {
        wrappedObject.update(container, delta);

    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        wrappedObject.render(container, graphics);

    }

    @Override
    public void moveLeft() {
        wrappedObject.moveLeft();

    }

    @Override
    public void moveRight() {
        wrappedObject.moveRight();

    }

    @Override
    public void stopMoving() {
        wrappedObject.stopMoving();

    }

    @Override
    public void shoot() {
        wrappedObject.shoot();

    }

    @Override
    public void reset() {
        wrappedObject.reset();

    }

    @Override
    public void die() {
        wrappedObject.die();

    }

    @Override
    public boolean isAlive() {
        return wrappedObject.isAlive();
    }

    @Override
    public void removeLife() {
        wrappedObject.removeLife();

    }

    @Override
    public void clearAllPowerups() {
        wrappedObject.clearAllPowerups();

    }

    @Override
    public boolean hasPowerup(String key) {
        return wrappedObject.hasPowerup(key);
    }

    @Override
    public AbstractPowerup removePowerup(String key) {
        return wrappedObject.removePowerup(key);
    }

    @Override
    public void setPowerup(String key, AbstractPowerup value) {
        wrappedObject.setPowerup(key, value);
    }

    @Override
    public AbstractPowerup getPowerup(String key) {
        return wrappedObject.getPowerup(key);
    }

    @Override
    public boolean isInvincible() {
        return wrappedObject.isInvincible();
    }

    @Override
    public boolean hasShield() {
        return wrappedObject.hasShield();
    }

    @Override
    public boolean hasShopShield() {
        return wrappedObject.hasShopShield();
    }

    @Override
    public void setDefaultSpeed() {
        wrappedObject.setDefaultSpeed();

    }

    @Override
    public void setSpeed(int newSpeed) {
        wrappedObject.setSpeed(newSpeed);

    }

    @Override
    public int getSpeed() {
        return wrappedObject.getSpeed();
    }

    @Override
    public boolean isFirstPlayer() {
        return wrappedObject.isFirstPlayer();
    }

    @Override
    public void setWeapon(AbstractWeapon weapon) {
        wrappedObject.setWeapon(weapon);

    }

    @Override
    public AbstractWeapon getWeapon() {
        return wrappedObject.getWeapon();
    }

    @Override
    public int getLives() {
        return wrappedObject.getLives();
    }

    @Override
    public void setLives(int lives) {
        wrappedObject.setLives(lives);
    }

    @Override
    public void setScore(int score) {
        wrappedObject.setScore(score);
    }

    @Override
    public void setMoney(int money) {
        wrappedObject.setMoney(money);
    }

    @Override
    public int getMoney() {
        return wrappedObject.getMoney();
    }

    @Override
    public int getScore() {
        return wrappedObject.getScore();
    }

    @Override
    public Animation getAnimationCurrent() {
        return wrappedObject.getAnimationCurrent();
    }

    @Override
    public void setAnimationCurrent(Animation animationCurrent) {
        wrappedObject.setAnimationCurrent(animationCurrent);

    }

    @Override
    public void setShopWeapon(boolean bool) {
        wrappedObject.setShopWeapon(bool);

    }

    @Override
    public boolean isShopWeapon() {
        return wrappedObject.isShopWeapon();
    }

    @Override
    public void setShopSpeed(boolean bool) {
        wrappedObject.setShopSpeed(bool);

    }

    @Override
    public boolean isShopSpeed() {
        return wrappedObject.isShopSpeed();
    }

    @Override
    public int getRegularSpeed() {
        return wrappedObject.getRegularSpeed();
    }

    @Override
    public Image getImage() {
        return wrappedObject.getImage();
    }

    @Override
    public void setImage(Image image) {
        wrappedObject.setImage(image);

    }

    @Override
    public void setLocX(float locX) {
        wrappedObject.setLocX(locX);

    }

    @Override
    public float getLocX() {
        return wrappedObject.getLocX();
    }

    @Override
    public float getLocY() {
        return wrappedObject.getLocY();
    }

    @Override
    public void setLocY(float locY) {
        wrappedObject.setLocY(locY);

    }

    @Override
    public Shape getBounds() {
        return wrappedObject.getBounds();
    }

    @Override
    public int getWidth() {
        return wrappedObject.getWidth();
    }

    @Override
    public int getHeight() {
        return wrappedObject.getHeight();
    }

    @Override
    public Animation getAnimationLeft() {
        return wrappedObject.getAnimationLeft();
    }

    @Override
    public Animation getAnimationRight() {
        return wrappedObject.getAnimationRight();
    }

}
