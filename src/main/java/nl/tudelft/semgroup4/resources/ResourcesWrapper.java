package nl.tudelft.semgroup4.resources;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

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
    
    public static boolean musicOn = true;
    private static HashMap<String, Image> images;
    
    public ResourcesWrapper() {
        images = Resources.images;
    }
    

    /**
     * Loops the fire sound if the music is on.
     */
    public void playFireSound() {
        if (musicOn) {
            getWeaponFire().loop();
        }
    }

    /**
     * stops the sound of shooting.
     */
    public void stopFireSound() {
        getWeaponFire().stop();
    }

    /**
     * plays the sound of a bubble splitting.
     */
    public void playBubbleSplit() {
        if (musicOn) {
            getBubblePop().play();
        }

    }

    /**
     * plays the sound of time running out.
     */
    public void playTimeUp() {
        if (musicOn) {
            getTimeUp().play();
        }

    }

    /**
     * plays the sound of the titlescreen.
     */
    public void playTitleScreen() {
        if (musicOn) {
            getTitleScreenMusic().play();
        }

    }

    /**
     * stops the sound of the titlescreen.
     */
    public void stopTitleScreen() {
        getTitleScreenMusic().stop();

    }

    /**
     * plays the sound of the player dying.
     */
    public void playDeath() {
        if (musicOn) {
            getDeath().play();
        }

    }

    public Image getWallImage() {
        return images.get("src\\main\\resources\\img\\wall2_h.png");
    }

    public Image getVwallImage() {
        return images.get("src\\main\\resources\\img\\wall2_v.png");
    }
    
    public Image getSmallHWallImage() {
        return images.get("src\\main\\resources\\img\\small_wall_h.png");
    }
    
    public Image getSmallVWallImage() {
        return images.get("src\\main\\resources\\img\\small_wall_v.png");
    }

    public Image getWeaponImageRegular() {
        return images.get("src\\main\\resources\\img\\weapon_arrow.png");
    }

    public Image getWeaponImageSticky() {
        return images.get("src\\main\\resources\\img\\weapon_arrow.png");
    }

    public Image getWeaponImageFlower() {
        return images.get("src\\main\\resources\\img\\weapon_flowers.png");
    }

    public Image getTitleScreenBackground() {
        return images.get("src\\main\\resources\\img\\titlescreen2.png");
    }

    public Image getBackgroundImage() {
        return images.get("src\\main\\resources\\img\\level1.jpg");
    }

    public Image getDashboardPlayerContainerLeft() {
        return images.get("src\\main\\resources\\img\\dashboard\\player_container_1.png");
    }

    public Image getDashboardPlayerContainerRight() {
        return images.get("src\\main\\resources\\img\\dashboard\\player_container_2.png");
    }

    public Image getDashboardLivesContainer() {
        return images.get("src\\main\\resources\\img\\dashboard\\lives_container.png");
    }

    public Image getDashboardlivesFull() {
        return images.get("src\\main\\resources\\img\\dashboard\\lives_full.png");
    }

    public Image getDashboardlivesEmpty() {
        return images.get("src\\main\\resources\\img\\dashboard\\lives_empty.png");
    }

    public Image getLevelContainer() {
        return images.get("src\\main\\resources\\img\\dashboard\\level_container.png");
    }

    public Image getPlayerImageStill() {
        return images.get("src\\main\\resources\\img\\player_still.png");
    }

    public Image getOn() {
        return images.get("src\\main\\resources\\img\\on.png");
    }

    public Image getOff() {
        return images.get("src\\main\\resources\\img\\off.png");
    }

    public Image getSoundText() {
        return images.get("src\\main\\resources\\img\\sound.png");
    }

    public Image getOptionsText() {
        return images.get("src\\main\\resources\\img\\options.png");
    }

    public Image getBackText() {
        return images.get("src\\main\\resources\\img\\back.png");
    }

    public Image getQuitText() {
        return images.get("src\\main\\resources\\img\\quitText.png");
    }

    public Image getPauseText() {
        return images.get("src\\main\\resources\\img\\pausedText.png");
    }

    public Image getNewKeyText() {
        return images.get("src\\main\\resources\\img\\newKeyText.png");
    }
    
    public Image getShopBackGround() {
        return images.get("src\\main\\resources\\img\\shopBackground.png");
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
        return images.get("src\\main\\resources\\img\\yball1.png");
    }

    public Image getBubbleImage2() {
        return images.get("src\\main\\resources\\img\\yball2.png");
    }

    public Image getBubbleImage3() {
        return images.get("src\\main\\resources\\img\\yball3.png");
    }

    public Image getBubbleImage4() {
        return images.get("src\\main\\resources\\img\\rball4.png");
    }

    public Image getBubbleImage5() {
        return images.get("src\\main\\resources\\img\\rball5.png");
    }

    public Image getBubbleImage6() {
        return images.get("src\\main\\resources\\img\\rball6.png");
    }

    public Image getPickupWeaponRegular() {
        return images.get("src\\main\\resources\\img\\pickup_regular_weapon.png");
    }

    public Image getPickupWeaponDouble() {
        return images.get("src\\main\\resources\\img\\pickup_weapon_double.png");
    }

    public Image getPickupWeaponSticky() {
        return images.get("src\\main\\resources\\img\\pickup_sticky.png");
    }

    public Image getPickupWeaponFlowers() {
        return images.get("src\\main\\resources\\img\\pickup_flowers.png");
    }

    public Image getPickupPowerShield() {
        return images.get("src\\main\\resources\\img\\pickup_shield.png");
    }

    public Image getPickupPowerInvincible() {
        return images.get("src\\main\\resources\\img\\pickup_invincible.png");
    }

    public Image getPickupPowerMoney() {
        return images.get("src\\main\\resources\\img\\pickup_money.png");
    }

    public Image getPickupPowerSpeedup() {
        return images.get("src\\main\\resources\\img\\pickup_speed.png");
    }

    public Image getPickupPowerPoints() {
        return images.get("src\\main\\resources\\img\\pickup_points.png");
    }

    public Image getPickupUtilitySplit() {
        return images.get("src\\main\\resources\\img\\pickup_split.png");
    }

    public Image getPickupUtilityFreeze() {
        return images.get("src\\main\\resources\\img\\pickup_freeze.png");
    }

    public Image getPickupUtilitySlow() {
        return images.get("src\\main\\resources\\img\\pickup_slow_down.png");
    }

    public Image getPickupUtilityLevelwon() {
        return images.get("src\\main\\resources\\img\\pickup_level_won.png");
    }

    public Image getPickupUtilityTime() {
        return images.get("src\\main\\resources\\img\\pickup_time.png");
    }

    public Image getPickupUtilityLife() {
        return images.get("src\\main\\resources\\img\\pickup_life.png");
    }

    public Image getPowerShield() {
        return images.get("src\\main\\resources\\img\\powerup_shield.png");
    }

    public Image getPowerInvincible() {
        return images.get("src\\main\\resources\\img\\powerup_invincible.png");
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

    public Image getContinueText() {
        return images.get("src\\main\\resources\\img\\continue.png");
        
    }

    public Image getLoggerText() {
        return images.get("src\\main\\resources\\img\\logger.png");
    }

    public Image getKeyText() {
        return images.get("src\\main\\resources\\img\\keyBindings.png");
    }

    public Image getShopText() {
        return images.get("src\\main\\resources\\img\\shop.png");
    }
    
    public Image getSpecialWeapon() {
        return images.get("src\\main\\resources\\img\\pickup_weapon_special.png");
    }
    
    public Image getBuy() {
        return images.get("src\\main\\resources\\img\\buy.png");
    }

    public Image getPlayer2On() {
        return images.get("src\\main\\resources\\img\\player2TextOn.png");
    }

    public Image getPlayer1Off() {
        return images.get("src\\main\\resources\\img\\player1TextOff.png");
    }
    
    public Image getPlayer1On() {
        return images.get("src\\main\\resources\\img\\player1TextOn.png");
    }

    public Image getPlayer2Off() {
        return images.get("src\\main\\resources\\img\\player2TextOff.png");
    }
    
    public TrueTypeFont getCountdownFont() {
        return Resources.countdownFont;
    }
}
