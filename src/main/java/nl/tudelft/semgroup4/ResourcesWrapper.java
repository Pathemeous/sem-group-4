package nl.tudelft.semgroup4;

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
public final class ResourcesWrapper {
    
    public final Image getWallImage() {
        return Resources.wallImage;
    }

    public final Image getVwallImage() {
        return Resources.vwallImage;
    }

    public final Image getWeaponImageRegular() {
        return Resources.weaponImageRegular;
    }

    public final Image getWeaponImageSticky() {
        return Resources.weaponImageSticky;
    }

    public final Image getWeaponImageFlower() {
        return Resources.weaponImageFlower;
    }

    public final Image getTitleScreenBackground() {
        return Resources.titleScreenBackground;
    }

    public final Image getBackgroundImage() {
        return Resources.backgroundImage;
    }

    public final Image getDashboardPlayerContainerLeft() {
        return Resources.dashboardPlayerContainerLeft;
    }

    public final Image getDashboardPlayerContainerRight() {
        return Resources.dashboardPlayerContainerRight;
    }

    public final Image getDashboardLivesContainer() {
        return Resources.dashboardLivesContainer;
    }

    public final Image getDashboardlivesFull() {
        return Resources.dashboardlivesFull;
    }

    public final Image getDashboardlivesEmpty() {
        return Resources.dashboardlivesEmpty;
    }

    public final Image getLevelContainer() {
        return Resources.levelContainer;
    }

    public final Image getPlayerImageStill() {
        return Resources.playerImageStill;
    }

    public final Image getOn() {
        return Resources.on;
    }

    public final Image getOff() {
        return Resources.off;
    }

    public final Image getSoundText() {
        return Resources.soundText;
    }

    public final Image getOptionsText() {
        return Resources.optionsText;
    }

    public final Image getBackText() {
        return Resources.backText;
    }

    public final Image getQuitText() {
        return Resources.quitText;
    }

    public final Image getPauseText() {
        return Resources.pauseText;
    }

    public final ArrayList<Image> getPlayerImageLeft() {
        return Resources.playerImageLeft;
    }

    public final ArrayList<Image> getPlayerImageRight() {
        return Resources.playerImageRight;
    }

    public final Animation getPlayerWalkLeft() {
        return Resources.playerWalkLeft;
    }

    public final Animation getPlayerWalkRight() {
        return Resources.playerWalkRight;
    }

    public final Image getBubbleImage1() {
        return Resources.bubbleImage1;
    }

    public final Image getBubbleImage2() {
        return Resources.bubbleImage2;
    }

    public final Image getBubbleImage3() {
        return Resources.bubbleImage3;
    }

    public final Image getBubbleImage4() {
        return Resources.bubbleImage4;
    }

    public final Image getBubbleImage5() {
        return Resources.bubbleImage5;
    }

    public final Image getBubbleImage6() {
        return Resources.bubbleImage6;
    }

    public final Image getPickupWeaponRegular() {
        return Resources.pickupWeaponRegular;
    }

    public final Image getPickupWeaponDouble() {
        return Resources.pickupWeaponDouble;
    }

    public final Image getPickupWeaponSticky() {
        return Resources.pickupWeaponSticky;
    }

    public final Image getPickupWeaponFlowers() {
        return Resources.pickupWeaponFlowers;
    }

    public final Image getPickupPowerShield() {
        return Resources.pickupPowerShield;
    }

    public final Image getPickupPowerInvincible() {
        return Resources.pickupPowerInvincible;
    }

    public final Image getPickupPowerSpeedup() {
        return Resources.pickupPowerSpeedup;
    }

    public final Image getPickupPowerPoints() {
        return Resources.pickupPowerPoints;
    }

    public final Image getPickupUtilitySplit() {
        return Resources.pickupUtilitySplit;
    }

    public final Image getPickupUtilityFreeze() {
        return Resources.pickupUtilityFreeze;
    }

    public final Image getPickupUtilitySlow() {
        return Resources.pickupUtilitySlow;
    }

    public final Image getPickupUtilityLevelwon() {
        return Resources.pickupUtilityLevelwon;
    }

    public final Image getPickupUtilityTime() {
        return Resources.pickupUtilityTime;
    }

    public final Image getPickupUtilityLife() {
        return Resources.pickupUtilityLife;
    }

    public final Image getPowerShield() {
        return Resources.powerShield;
    }

    public final Image getPowerInvincible() {
        return Resources.powerInvincible;
    }

    public final Sound getBubblePop() {
        return Resources.bubblePop;
    }

    public final Sound getWeaponFire() {
        return Resources.weaponFire;
    }

    public final Sound getDeath() {
        return Resources.death;
    }

    public final Sound getTimeUp() {
        return Resources.timeUp;
    }

    public final Music getTitleScreenMusic() {
        return Resources.titleScreenMusic;
    }

    /**
     * Empty constructor to allow instantiation (no setup is needed).
     */
    public ResourcesWrapper() {
        
    }
}
