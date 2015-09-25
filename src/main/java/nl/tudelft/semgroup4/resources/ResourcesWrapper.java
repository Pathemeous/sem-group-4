package nl.tudelft.semgroup4.resources;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

/**
 * Creates a layer between the static {@link Resources} class and the classes that use it. By
 * mocking this class and injecting it into the class under test with the required stubs, the Slick
 * resources can be faked without creating LWJGL dependency crashes in a headless environment such
 * as Travis.
 * 
 * @author Pathemeous
 *
 */
public class ResourcesWrapper {

    public Image getWallImage() {
        return Resources.wallImage;
    }

    public Image getVwallImage() {
        return Resources.vwallImage;
    }

    public Image getWeaponImageRegular() {
        return Resources.weaponImageRegular;
    }

    public Image getWeaponImageSticky() {
        return Resources.weaponImageSticky;
    }

    public Image getWeaponImageFlower() {
        return Resources.weaponImageFlower;
    }

    public Image getTitleScreenBackground() {
        return Resources.titleScreenBackground;
    }

    public Image getBackgroundImage() {
        return Resources.backgroundImage;
    }

    public Image getDashboardPlayerContainerLeft() {
        return Resources.dashboardPlayerContainerLeft;
    }

    public Image getDashboardPlayerContainerRight() {
        return Resources.dashboardPlayerContainerRight;
    }

    public Image getDashboardLivesContainer() {
        return Resources.dashboardLivesContainer;
    }

    public Image getDashboardlivesFull() {
        return Resources.dashboardlivesFull;
    }

    public Image getDashboardlivesEmpty() {
        return Resources.dashboardlivesEmpty;
    }

    public Image getLevelContainer() {
        return Resources.levelContainer;
    }

    public Image getPlayerImageStill() {
        return Resources.playerImageStill;
    }

    public Image getOn() {
        return Resources.on;
    }

    public Image getOff() {
        return Resources.off;
    }

    public Image getSoundText() {
        return Resources.soundText;
    }

    public Image getOptionsText() {
        return Resources.optionsText;
    }

    public Image getBackText() {
        return Resources.backText;
    }

    public Image getQuitText() {
        return Resources.quitText;
    }

    public Image getPauseText() {
        return Resources.pauseText;
    }

    public ArrayList<Image> getPlayerImageLeft() {
        return Resources.playerImageLeft;
    }

    public ArrayList<Image> getPlayerImageRight() {
        return Resources.playerImageRight;
    }

    public Animation getPlayerWalkLeft() {
        return Resources.playerWalkLeft;
    }

    public Animation getPlayerWalkRight() {
        return Resources.playerWalkRight;
    }

    public Image getBubbleImage1() {
        return Resources.bubbleImage1;
    }

    public Image getBubbleImage2() {
        return Resources.bubbleImage2;
    }

    public Image getBubbleImage3() {
        return Resources.bubbleImage3;
    }

    public Image getBubbleImage4() {
        return Resources.bubbleImage4;
    }

    public Image getBubbleImage5() {
        return Resources.bubbleImage5;
    }

    public Image getBubbleImage6() {
        return Resources.bubbleImage6;
    }

    public Image getPickupWeaponRegular() {
        return Resources.pickupWeaponRegular;
    }

    public Image getPickupWeaponDouble() {
        return Resources.pickupWeaponDouble;
    }

    public Image getPickupWeaponSticky() {
        return Resources.pickupWeaponSticky;
    }

    public Image getPickupWeaponFlowers() {
        return Resources.pickupWeaponFlowers;
    }

    public Image getPickupPowerShield() {
        return Resources.pickupPowerShield;
    }

    public Image getPickupPowerInvincible() {
        return Resources.pickupPowerInvincible;
    }

    public Image getPickupPowerSpeedup() {
        return Resources.pickupPowerSpeedup;
    }

    public Image getPickupPowerPoints() {
        return Resources.pickupPowerPoints;
    }

    public Image getPickupUtilitySplit() {
        return Resources.pickupUtilitySplit;
    }

    public Image getPickupUtilityFreeze() {
        return Resources.pickupUtilityFreeze;
    }

    public Image getPickupUtilitySlow() {
        return Resources.pickupUtilitySlow;
    }

    public Image getPickupUtilityLevelwon() {
        return Resources.pickupUtilityLevelwon;
    }

    public Image getPickupUtilityTime() {
        return Resources.pickupUtilityTime;
    }

    public Image getPickupUtilityLife() {
        return Resources.pickupUtilityLife;
    }

    public Image getPowerShield() {
        return Resources.powerShield;
    }

    public Image getPowerInvincible() {
        return Resources.powerInvincible;
    }

    public Sound getBubblePop() {
        return Resources.bubblePop;
    }

    public Sound getWeaponFire() {
        return Resources.weaponFire;
    }

    public Sound getDeath() {
        return Resources.death;
    }

    public Sound getTimeUp() {
        return Resources.timeUp;
    }

    public Music getTitleScreenMusic() {
        return Resources.titleScreenMusic;
    }
}
