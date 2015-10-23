package nl.tudelft.controller.resources;

import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
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
    private HashMap<String, Image> images;
    private HashMap<String, Sound> sounds;
    private HashMap<String, Animation> animations;
    private HashMap<String, Music> music;
    private final String fileSeparator;
    
    /**
     * Creates a new resourceswrapper, which contains all the resources
     * that can be used.
     */
    public ResourcesWrapper() {
        images = Resources.getImages();
        sounds = Resources.getSounds();
        animations = Resources.getAnimations();
        music = Resources.getMusic();
        fileSeparator = System.getProperty("file.separator");
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
        return images.get("img" + fileSeparator + "wall2_h.png");
    }

    public Image getVwallImage() {
        return images.get("img" + fileSeparator + "wall2_v.png");
    }
    
    public Image getSmallHWallImage() {
        return images.get("img" + fileSeparator + "small_wall_h.png");
    }
    
    public Image getSmallVWallImage() {
        return images.get("img" + fileSeparator + "small_wall_v.png");
    }

    public Image getWeaponImageRegular() {
        return images.get("img" + fileSeparator + "weapon_arrow.png");
    }

    public Image getWeaponImageSticky() {
        return images.get("img" + fileSeparator + "weapon_arrow.png");
    }

    public Image getWeaponImageFlower() {
        return images.get("img" + fileSeparator + "weapon_flowers.png");
    }

    public Image getTitleScreenBackground() {
        return images.get("img" + fileSeparator + "titlescreen2.png");
    }

    public Image getBackgroundImage() {
        return images.get("img" + fileSeparator + "level1.jpg");
    }

    public Image getDashboardPlayerContainerLeft() {
        return images.get("img" + fileSeparator + "dashboard" + fileSeparator 
                + "player_container_1.png");
    }

    public Image getDashboardPlayerContainerRight() {
        return images.get("img" + fileSeparator + "dashboard" + fileSeparator 
                + "player_container_2.png");
    }

    public Image getDashboardLivesContainer() {
        return images.get("img" + fileSeparator + "dashboard" + fileSeparator 
                + "lives_container.png");
    }

    public Image getDashboardlivesFull() {
        return images.get("img" + fileSeparator + "dashboard" + fileSeparator + "lives_full.png");
    }

    public Image getDashboardlivesEmpty() {
        return images.get("img" + fileSeparator + "dashboard" + fileSeparator + "lives_empty.png");
    }

    public Image getLevelContainer() {
        return images.get("img" + fileSeparator + "dashboard" + fileSeparator 
                + "level_container.png");
    }

    public Image getPlayerImageStill() {
        return images.get("img" + fileSeparator + "player_still.png");
    }

    public Image getOn() {
        return images.get("img" + fileSeparator + "on.png");
    }

    public Image getOff() {
        return images.get("img" + fileSeparator + "off.png");
    }

    public Image getSoundText() {
        return images.get("img" + fileSeparator + "sound.png");
    }

    public Image getOptionsText() {
        return images.get("img" + fileSeparator + "options.png");
    }

    public Image getBackText() {
        return images.get("img" + fileSeparator + "back.png");
    }

    public Image getQuitText() {
        return images.get("img" + fileSeparator + "quitText.png");
    }

    public Image getPauseText() {
        return images.get("img" + fileSeparator + "pausedText.png");
    }

    public Image getNewKeyText() {
        return images.get("img" + fileSeparator + "newKeyText.png");
    }
    
    public Image getShopBackGround() {
        return images.get("img" + fileSeparator + "shopBackground.png");
    }

    public Animation getPlayerWalkLeft() {
        return animations.get("animation" + fileSeparator + "player" + fileSeparator + "pl");
    }

    public Animation getPlayerWalkRight() {
        return animations.get("animation" + fileSeparator + "player" + fileSeparator + "pr");
    }

    public Image getBubbleImage1() {
        return images.get("img" + fileSeparator + "yball1.png");
    }

    public Image getBubbleImage2() {
        return images.get("img" + fileSeparator + "yball2.png");
    }

    public Image getBubbleImage3() {
        return images.get("img" + fileSeparator + "yball3.png");
    }

    public Image getBubbleImage4() {
        return images.get("img" + fileSeparator + "rball4.png");
    }

    public Image getBubbleImage5() {
        return images.get("img" + fileSeparator + "rball5.png");
    }

    public Image getBubbleImage6() {
        return images.get("img" + fileSeparator + "rball6.png");
    }

    public Image getBubbleImageBach() {
        return images.get("img" + fileSeparator + "bachball.png");
    }

    public Image getPickupWeaponRegular() {
        return images.get("img" + fileSeparator + "pickup_regular_weapon.png");
    }

    public Image getPickupWeaponDouble() {
        return images.get("img" + fileSeparator + "pickup_weapon_double.png");
    }

    public Image getPickupWeaponSticky() {
        return images.get("img" + fileSeparator + "pickup_sticky.png");
    }

    public Image getPickupWeaponFlowers() {
        return images.get("img" + fileSeparator + "pickup_flowers.png");
    }

    public Image getPickupPowerShield() {
        return images.get("img" + fileSeparator + "pickup_shield.png");
    }

    public Image getPickupPowerInvincible() {
        return images.get("img" + fileSeparator + "pickup_invincible.png");
    }

    public Image getPickupPowerMoney() {
        return images.get("img" + fileSeparator + "pickup_money.png");
    }

    public Image getPickupPowerSpeedup() {
        return images.get("img" + fileSeparator + "pickup_speed.png");
    }

    public Image getPickupPowerPoints() {
        return images.get("img" + fileSeparator + "pickup_points.png");
    }

    public Image getPickupUtilitySplit() {
        return images.get("img" + fileSeparator + "pickup_split.png");
    }

    public Image getPickupUtilityFreeze() {
        return images.get("img" + fileSeparator + "pickup_freeze.png");
    }

    public Image getPickupUtilitySlow() {
        return images.get("img" + fileSeparator + "pickup_slow_down.png");
    }

    public Image getPickupUtilityLevelwon() {
        return images.get("img" + fileSeparator + "pickup_level_won.png");
    }

    public Image getPickupUtilityTime() {
        return images.get("img" + fileSeparator + "pickup_time.png");
    }

    public Image getPickupUtilityLife() {
        return images.get("img" + fileSeparator + "pickup_life.png");
    }

    public Image getPowerShield() {
        return images.get("img" + fileSeparator + "powerup_shield.png");
    }

    public Image getPowerInvincible() {
        return images.get("img" + fileSeparator + "powerup_invincible.png");
    }

    public Sound getBubblePop() {
        return sounds.get("sound" + fileSeparator + "pop.ogg");
    }

    public Sound getWeaponFire() {
        return sounds.get("sound" + fileSeparator + "weaponFire.ogg");
    }

    public Sound getDeath() {
        return sounds.get("sound" + fileSeparator + "death.ogg");
    }

    public Sound getTimeUp() {
        return sounds.get("sound" + fileSeparator + "timeUp.ogg");
    }

    public Music getTitleScreenMusic() {
        return music.get("music" + fileSeparator + "titleScreen.ogg");
    }

    public Image getContinueText() {
        return images.get("img" + fileSeparator + "continue.png");
    }

    public Image getLoggerText() {
        return images.get("img" + fileSeparator + "logger.png");
    }

    public Image getKeyText() {
        return images.get("img" + fileSeparator + "keyBindings.png");
    }

    public Image getShopText() {
        return images.get("img" + fileSeparator + "shop.png");
    }
    
    public Image getSpecialWeapon() {
        return images.get("img" + fileSeparator + "pickup_weapon_special.png");
    }
    
    public Image getBuy() {
        return images.get("img" + fileSeparator + "buy.png");
    }

    public Image getPlayer2On() {
        return images.get("img" + fileSeparator + "player2TextOn.png");
    }

    public Image getPlayer1Off() {
        return images.get("img" + fileSeparator + "player1TextOff.png");
    }
    
    public Image getPlayer1On() {
        return images.get("img" + fileSeparator + "player1TextOn.png");
    }

    public Image getPlayer2Off() {
        return images.get("img" + fileSeparator + "player2TextOff.png");
    }
    
    public Image createImage(int width, int height) throws SlickException {
        return new Image(width, height);
    }

    public TrueTypeFont createFont(Font font, boolean bool) {
        return new TrueTypeFont(font, bool);
    }
    
    public TrueTypeFont getCountdownFont() {
        return Fonts.getTypeFont();
    }
    
    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean state) {
        musicOn = state;
    }

    /**
     * Replace all maps with a mocked map. Used for testing.
     * Unchecked warnings are suppressed as these aren't relevant.
     * @param mapSound
     *              the mocked map for sounds
     * @param mapOther
     *              the mocked map for all other maps
     */
    @SuppressWarnings("unchecked")
    public void setMaps(HashMap mapSound, HashMap mapOther) {
        this.images = mapOther;
        this.animations = mapOther;
        this.sounds = mapSound;
        this.music = mapSound;
    }
}

