package nl.tudelft.semgroup4.resources;

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
    private static HashMap<String, Sound> sounds;
    private static HashMap<String, Animation> animations;
    private static HashMap<String, Music> music;
    
    /**
     * Creates a new resourceswrapper, which contains all the resources
     * that can be used.
     */
    public ResourcesWrapper() {
        images = Resources.images;
        sounds = Resources.sounds;
        animations = Resources.animations;
        music = Resources.music;
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
        return images.get("img\\wall2_h.png");
    }

    public Image getVwallImage() {
        return images.get("img\\wall2_v.png");
    }
    
    public Image getSmallHWallImage() {
        return images.get("img\\small_wall_h.png");
    }
    
    public Image getSmallVWallImage() {
        return images.get("img\\small_wall_v.png");
    }

    public Image getWeaponImageRegular() {
        return images.get("img\\weapon_arrow.png");
    }

    public Image getWeaponImageSticky() {
        return images.get("img\\weapon_arrow.png");
    }

    public Image getWeaponImageFlower() {
        return images.get("img\\weapon_flowers.png");
    }

    public Image getTitleScreenBackground() {
        return images.get("img\\titlescreen2.png");
    }

    public Image getBackgroundImage() {
        return images.get("img\\level1.jpg");
    }

    public Image getDashboardPlayerContainerLeft() {
        return images.get("img\\dashboard\\player_container_1.png");
    }

    public Image getDashboardPlayerContainerRight() {
        return images.get("img\\dashboard\\player_container_2.png");
    }

    public Image getDashboardLivesContainer() {
        return images.get("img\\dashboard\\lives_container.png");
    }

    public Image getDashboardlivesFull() {
        return images.get("img\\dashboard\\lives_full.png");
    }

    public Image getDashboardlivesEmpty() {
        return images.get("img\\dashboard\\lives_empty.png");
    }

    public Image getLevelContainer() {
        return images.get("img\\dashboard\\level_container.png");
    }

    public Image getPlayerImageStill() {
        return images.get("img\\player_still.png");
    }

    public Image getOn() {
        return images.get("img\\on.png");
    }

    public Image getOff() {
        return images.get("img\\off.png");
    }

    public Image getSoundText() {
        return images.get("img\\sound.png");
    }

    public Image getOptionsText() {
        return images.get("img\\options.png");
    }

    public Image getBackText() {
        return images.get("img\\back.png");
    }

    public Image getQuitText() {
        return images.get("img\\quitText.png");
    }

    public Image getPauseText() {
        return images.get("img\\pausedText.png");
    }

    public Image getNewKeyText() {
        return images.get("img\\newKeyText.png");
    }
    
    public Image getShopBackGround() {
        return images.get("img\\shopBackground.png");
    }

    public Animation getPlayerWalkLeft() {
        return animations.get("pl");
    }

    public Animation getPlayerWalkRight() {
        return animations.get("pr");
    }

    public Image getBubbleImage1() {
        return images.get("img\\yball1.png");
    }

    public Image getBubbleImage2() {
        return images.get("img\\yball2.png");
    }

    public Image getBubbleImage3() {
        return images.get("img\\yball3.png");
    }

    public Image getBubbleImage4() {
        return images.get("img\\rball4.png");
    }

    public Image getBubbleImage5() {
        return images.get("img\\rball5.png");
    }

    public Image getBubbleImage6() {
        return images.get("img\\rball6.png");
    }

    public Image getPickupWeaponRegular() {
        return images.get("img\\pickup_regular_weapon.png");
    }

    public Image getPickupWeaponDouble() {
        return images.get("img\\pickup_weapon_double.png");
    }

    public Image getPickupWeaponSticky() {
        return images.get("img\\pickup_sticky.png");
    }

    public Image getPickupWeaponFlowers() {
        return images.get("img\\pickup_flowers.png");
    }

    public Image getPickupPowerShield() {
        return images.get("img\\pickup_shield.png");
    }

    public Image getPickupPowerInvincible() {
        return images.get("img\\pickup_invincible.png");
    }

    public Image getPickupPowerMoney() {
        return images.get("img\\pickup_money.png");
    }

    public Image getPickupPowerSpeedup() {
        return images.get("img\\pickup_speed.png");
    }

    public Image getPickupPowerPoints() {
        return images.get("img\\pickup_points.png");
    }

    public Image getPickupUtilitySplit() {
        return images.get("img\\pickup_split.png");
    }

    public Image getPickupUtilityFreeze() {
        return images.get("img\\pickup_freeze.png");
    }

    public Image getPickupUtilitySlow() {
        return images.get("img\\pickup_slow_down.png");
    }

    public Image getPickupUtilityLevelwon() {
        return images.get("img\\pickup_level_won.png");
    }

    public Image getPickupUtilityTime() {
        return images.get("img\\pickup_time.png");
    }

    public Image getPickupUtilityLife() {
        return images.get("img\\pickup_life.png");
    }

    public Image getPowerShield() {
        return images.get("img\\powerup_shield.png");
    }

    public Image getPowerInvincible() {
        return images.get("img\\powerup_invincible.png");
    }

    public Sound getBubblePop() {
        return sounds.get("sound\\pop.ogg");
    }

    public Sound getWeaponFire() {
        return sounds.get("sound\\weaponFire.ogg");
    }

    public Sound getDeath() {
        return sounds.get("sound\\death.ogg");
    }

    public Sound getTimeUp() {
        return sounds.get("sound\\timeUp.ogg");
    }

    public Music getTitleScreenMusic() {
        return music.get("src\\main\\resources\\music\\titleScreen.ogg");
    }

    public Image getContinueText() {
        return images.get("img\\continue.png");
    }

    public Image getLoggerText() {
        return images.get("img\\logger.png");
    }

    public Image getKeyText() {
        return images.get("img\\keyBindings.png");
    }

    public Image getShopText() {
        return images.get("img\\shop.png");
    }
    
    public Image getSpecialWeapon() {
        return images.get("img\\pickup_weapon_special.png");
    }
    
    public Image getBuy() {
        return images.get("img\\buy.png");
    }

    public Image getPlayer2On() {
        return images.get("img\\player2TextOn.png");
    }

    public Image getPlayer1Off() {
        return images.get("img\\player1TextOff.png");
    }
    
    public Image getPlayer1On() {
        return images.get("img\\player1TextOn.png");
    }

    public Image getPlayer2Off() {
        return images.get("img\\player2TextOff.png");
    }
    
    public TrueTypeFont getCountdownFont() {
        return Fonts.countdownFont;
    }
}
