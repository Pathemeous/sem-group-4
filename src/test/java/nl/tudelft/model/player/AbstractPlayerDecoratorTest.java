package nl.tudelft.model.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import nl.tudelft.controller.Modifiable;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.weapon.AbstractWeapon;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AbstractPlayerDecoratorTest {

    private AbstractPlayerDecorator decorator;
    private Player mockedWrappee;

    @Before
    public void setUp() throws Exception {
        mockedWrappee = Mockito.mock(Player.class);
        decorator = new AbstractPlayerDecorator(mockedWrappee);
    }

    @Test
    public void testAbstractPlayerDecorator() {
        assertNotNull(decorator);
    }

    @Test
    public void testUpdate() throws SlickException {
        Modifiable mockedContainer = Mockito.mock(Modifiable.class);
        decorator.update(mockedContainer, 5);
        Mockito.verify(mockedWrappee).update(mockedContainer, 5);
    }

    @Test
    public void testRender() throws SlickException {
        GameContainer mockedContainer = Mockito.mock(GameContainer.class);
        Graphics mockedGraphics = Mockito.mock(Graphics.class);
        decorator.render(mockedContainer, mockedGraphics);
        Mockito.verify(mockedWrappee).render(mockedContainer, mockedGraphics);
    }

    @Test
    public void testMoveLeft() {
        decorator.moveLeft();
        Mockito.verify(mockedWrappee).moveLeft();
    }

    @Test
    public void testMoveRight() {
        decorator.moveRight();
        Mockito.verify(mockedWrappee).moveRight();
    }

    @Test
    public void testStopMoving() {
        decorator.stopMoving();
        Mockito.verify(mockedWrappee).stopMoving();
    }

    @Test
    public void testShoot() {
        decorator.shoot();
        Mockito.verify(mockedWrappee).shoot();
    }

    @Test
    public void testReset() {
        decorator.reset();
        Mockito.verify(mockedWrappee).reset();
    }

    @Test
    public void testDie() {
        decorator.die();
        Mockito.verify(mockedWrappee).die();

    }

    @Test
    public void testIsAlive() {
        decorator.isAlive();
        Mockito.verify(mockedWrappee).isAlive();
    }

    @Test
    public void testRemoveLife() {
        decorator.removeLife();
        Mockito.verify(mockedWrappee).removeLife();
    }

    @Test
    public void testClearAllPowerups() {
        decorator.clearAllPowerups();
        Mockito.verify(mockedWrappee).clearAllPowerups();
    }

    @Test
    public void testHasPowerup() {
        decorator.hasPowerup("test");
        Mockito.verify(mockedWrappee).hasPowerup("test");
    }

    @Test
    public void testRemovePowerup() {
        decorator.removeLife();
        Mockito.verify(mockedWrappee).removeLife();
    }

    @Test
    public void testSetPowerup() {
        Powerup mockedPowerup = Mockito.mock(Powerup.class);
        decorator.setPowerup("test", mockedPowerup);
        Mockito.verify(mockedWrappee).setPowerup("test", mockedPowerup);
    }

    @Test
    public void testGetPowerup() {
        decorator.getPowerup("test");
        Mockito.verify(mockedWrappee).getPowerup("test");
    }

    @Test
    public void testIsInvincible() {
        decorator.isInvincible();
        Mockito.verify(mockedWrappee).isInvincible();
    }

    @Test
    public void testHasShield() {
        decorator.hasShield();
        Mockito.verify(mockedWrappee).hasShield();
    }

    @Test
    public void testHasShopShield() {
        decorator.hasShopShield();
        Mockito.verify(mockedWrappee).hasShopShield();
    }

    @Test
    public void testSetDefaultSpeed() {
        decorator.setDefaultSpeed();
        Mockito.verify(mockedWrappee).setDefaultSpeed();
    }

    @Test
    public void testSetSpeed() {
        decorator.setSpeed(5);
        Mockito.verify(mockedWrappee).setSpeed(5);
    }

    @Test
    public void testGetSpeed() {
        decorator.getSpeed();
        Mockito.verify(mockedWrappee).getSpeed();
    }

    @Test
    public void testIsFirstPlayer() {
        decorator.isFirstPlayer();
        Mockito.verify(mockedWrappee).isFirstPlayer();

    }

    @Test
    public void testSetWeapon() {
        AbstractWeapon mockedWeapon = Mockito.mock(AbstractWeapon.class);
        decorator.setWeapon(mockedWeapon);
        Mockito.verify(mockedWrappee).setWeapon(mockedWeapon);

    }

    @Test
    public void testGetWeapon() {
        decorator.getWeapon();
        Mockito.verify(mockedWrappee).getWeapon();
    }

    @Test
    public void testGetLives() {
        decorator.getLives();
        Mockito.verify(mockedWrappee).getLives();
    }

    @Test
    public void testSetLives() {
        decorator.setLives(5);
        Mockito.verify(mockedWrappee).setLives(5);
    }

    @Test
    public void testSetScore() {
        decorator.setScore(5);
        Mockito.verify(mockedWrappee).setScore(5);
    }

    @Test
    public void testSetMoney() {
        decorator.setMoney(5);
        Mockito.verify(mockedWrappee).setMoney(5);
    }

    @Test
    public void testGetMoney() {
        decorator.getMoney();
        Mockito.verify(mockedWrappee).getMoney();
    }

    @Test
    public void testGetScore() {
        decorator.getScore();
        Mockito.verify(mockedWrappee).getScore();
    }

    @Test
    public void testGetAnimationCurrent() {
        decorator.getAnimationCurrent();
        Mockito.verify(mockedWrappee).getAnimationCurrent();
    }

    @Test
    public void testSetAnimationCurrent() {
        Animation mockedAnimation = Mockito.mock(Animation.class);
        decorator.setAnimationCurrent(mockedAnimation);
        Mockito.verify(mockedWrappee).setAnimationCurrent(mockedAnimation);
    }

    @Test
    public void testSetShopWeapon() {
        decorator.setShopWeapon(true);
        Mockito.verify(mockedWrappee).setShopWeapon(true);
    }

    @Test
    public void testIsShopWeapon() {
        decorator.isShopWeapon();
        Mockito.verify(mockedWrappee).isShopWeapon();
    }

    @Test
    public void testSetShopSpeed() {
        decorator.setShopSpeed(true);
        Mockito.verify(mockedWrappee).setShopSpeed(true);
    }

    @Test
    public void testIsShopSpeed() {
        decorator.isShopSpeed();
        Mockito.verify(mockedWrappee).isShopSpeed();
    }

    @Test
    public void testGetRegularSpeed() {
        decorator.getRegularSpeed();
        Mockito.verify(mockedWrappee).getRegularSpeed();
    }

    @Test
    public void testGetImage() {
        decorator.getImage();
        Mockito.verify(mockedWrappee).getImage();
    }

    @Test
    public void testSetImage() {
        Image mockedImage = Mockito.mock(Image.class);
        decorator.setImage(mockedImage);
        Mockito.verify(mockedWrappee).setImage(mockedImage);
    }

    @Test
    public void testSetLocX() {
        decorator.setLocX(5);
        Mockito.verify(mockedWrappee).setLocX(5);
    }

    @Test
    public void testGetLocX() {
        decorator.getLocX();
        Mockito.verify(mockedWrappee).getLocX();
    }

    @Test
    public void testSetLocY() {
        decorator.setLocY(5);
        Mockito.verify(mockedWrappee).setLocY(5);
    }

    @Test
    public void testGetLocY() {
        decorator.getLocY();
        Mockito.verify(mockedWrappee).getLocY();
    }

    @Test
    public void testGetBounds() {
        decorator.getBounds();
        Mockito.verify(mockedWrappee).getBounds();
    }

    @Test
    public void testGetWidth() {
        decorator.getWidth();
        Mockito.verify(mockedWrappee).getWidth();
    }

    @Test
    public void testGetHeight() {
        decorator.getHeight();
        Mockito.verify(mockedWrappee).getHeight();
    }

    @Test
    public void testGetAnimationLeft() {
        decorator.getAnimationLeft();
        Mockito.verify(mockedWrappee).getAnimationLeft();
    }

    @Test
    public void testGetAnimationRight() {
        decorator.getAnimationRight();
        Mockito.verify(mockedWrappee).getAnimationRight();
    }

}
